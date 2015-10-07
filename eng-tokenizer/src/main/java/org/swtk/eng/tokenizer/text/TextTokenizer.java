package org.swtk.eng.tokenizer.text;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.TextUtils;

/**
 * 	code attribution:
 *
 * 	"the art of tokenization"
 * 	https://www.ibm.com/developerworks/community/blogs/nlp/entry/tokenization/
 *
 */
public class TextTokenizer extends TextTokenizerFrame {

	public static LogManager	logger	= new LogManager(TextTokenizer.class);

	public TextTokenizer(String input) {
		setInput(input);
	}

	public TextTokenizer normalize() {
		setInput(getInput().toLowerCase());

		return this;
	}

	public TextTokenizer tokenize() throws BusinessException {

		String input = getInput();
		StringBuilder sb = new StringBuilder();

		/* adding an extra space for the greedy lexer to detect acronyms if they occur at the end of a string */
		input = input + " ";
		char[] arr = input.toCharArray();
		for (int i = 0; i < arr.length; i++) {

			char prior = (i - 1 >= 0) ? arr[i - 1] : ' ';
			char current = arr[i];
			char next = (i + 1 < arr.length) ? arr[i + 1] : ' ';

			/* 	extract acronyms
			 	this will actually extract acronyms of any length
				once it detects this pattern a.b.c it's a greedy lexer that breaks at ' ' */
			if (Character.isLetter(current) && '.' == next) {

				/*	Pattern-1	= U.S	(3 chars)
					Pattern-2	= U.S. 	(4 chars) */
				if (i + 3 < input.length()) {

					/* Pattern-1 */
					logger.trace("Sequence (i = %s, i+1 = %s, i+2 = %s)", arr[i], arr[i + 1], arr[i + 2]);
					if (Character.isLetter(arr[i]) && '.' == arr[i + 1] && Character.isLetter(arr[i + 2])) {
						for (; i < arr.length && !TextUtils.isPunctuation(arr[i], '.') && arr[i] != ' '; i++) {
							sb.append(arr[i]);
						}

						addToken(sb);

						if (' ' != arr[i]) {
							StringBuilder sb1 = new StringBuilder();
							sb1.append(arr[i]);
							addToken(sb1);
						}
						sb = new StringBuilder();
						continue;
					}
				}
			}

			if (current == '=') {
				addToken(sb);
				addToken("=");
				sb = new StringBuilder();
				continue;
			}

			if ('w' == current && '/' == next) {
				sb.append(current);
				sb.append(next);
				addToken(sb);
				sb = new StringBuilder();
				i += 1;
				continue;
			}

			if ('&' == current) {
				if (i + 4 < arr.length) {
					if ('a' == arr[i + 1] && 'm' == arr[i + 2] && 'p' == arr[i + 3] && ';' == arr[i + 4]) {
						sb.append("&amp;");
						i += 4;
						continue;
					}
				}
			}

			/* extract URLs */
			if ('h' == current && 't' == next) {
				if (i + 7 < input.length() && "http://".equals(input.substring(i, i + 7))) {

					for (; i < arr.length && arr[i] != ' '; i++) {
						sb.append(arr[i]);
					}

					addToken(sb);
					sb = new StringBuilder();
					continue;
				}
			}

			/*	extract windows drive letter paths
				c:/ or c:\ */
			if (Character.isLetter(current) && ':' == next) {
				if (i + 2 < input.length() && (arr[i + 2] == '\\' || arr[i + 2] == '/')) {
					sb.append(current);
					sb.append(next);
					sb.append(arr[i + 2]);
					i += 2;
					continue;
				}
			}

			/* 	keep numbers together when separated by a period
				"4.0" should not be tokenized as { "4", ".", "0" } */
			if (Character.isDigit(current) && '.' == next) {
				if (i + 2 < input.length() && Character.isDigit(arr[i + 2])) {
					sb.append(current);
					sb.append(next);
					sb.append(arr[i + 2]);
					i += 2;
					continue;
				}
			}

			/*	keep alpha and numeric characters separated by hyphens together
				"b-node" should not be tokenized as { "b", "-", "node" } */
			if ((Character.isLetter(current) || Character.isDigit(current)) && '-' == next) {
				if (i + 2 < input.length() && (Character.isLetter(arr[i + 2])) || Character.isDigit(arr[i + 2])) {
					sb.append(current);
					sb.append(next);
					sb.append(arr[i + 2]);
					i += 2;
					continue;
				}
			}

			/* a greedy lexer that stops at spaces to detect mail patterns */
			if (sb.toString().equals("") && (Character.isLetter(current) || Character.isDigit(current)) && i + 5 < input.length()) {
				int iValue = i;
				for (; arr[i] != ' '; i++)
					sb.append(arr[i]);

				char lastChar = sb.charAt(sb.length() - 1);
				if (TextUtils.isSpecial(lastChar)) {
					sb.deleteCharAt(sb.length() - 1);
					i -= 2;
				}

				if (sb.toString().matches(MAIL_PATTERN)) {
					getTokens().add(sb.toString());
					sb = new StringBuilder();
					continue;
				} else {
					sb = new StringBuilder();
					i = iValue;
				}

			}

			if ((Character.isLetter(current) || Character.isDigit(current)) && '_' == next) {
				sb.append(current);
				i++;

				while (arr[i] == '_' && i < arr.length) {
					sb.append(arr[i]);
					i++;
				}

				if (i < arr.length) {
					if ((Character.isLetter(arr[i]) || Character.isDigit(arr[i]))) {
						sb.append(arr[i]);
					}
				}

				continue;
			}

			/* extract twitter channels */
			if (('#' == current || '@' == current) && ' ' != next && !TextUtils.isSpecial(next)) {
				sb.append(current);
				continue;
			}

			/* handle slash separated alphanumeric characters */
			if ((' ' != current && '/' == next) || '/' == current) {

				/* get the whole slash separated pattern */
				while (arr[i] != ' ' && i < arr.length) {
					sb.append(arr[i]);
					i++;
				}

				i--;
				char c = sb.charAt(0);

				/* potential unix file path */
				if (c == '/' || c == '.' || c == '*') {
					addToken(sb);
				} else {
					c = sb.charAt(sb.length() - 1);
					if (!Character.isDigit(c) && !Character.isLetter(c)) {
						sb.deleteCharAt(sb.length() - 1);
						i--;
					}

					boolean splitFlag = true;
					String[] splittedTokens = sb.toString().split("/");

					if (splittedTokens.length == 1) {
						sb.append("/");
						i++;
						addToken(sb.toString());
						splitFlag = false;
					}

					if (splittedTokens.length == 2) { //keep patterns like tcp/ip and os/2 as one token
						if (!isFwdSlashDelimited(sb.toString())) {
							if (splittedTokens[0].length() < 4) {
								addToken(sb.toString());
								splitFlag = false;
							}
						}
					}

					if (splitFlag) {
						for (String s : splittedTokens) { // split the token if there are more than one /
							addToken(s);
							addToken("/");
						}

						if (!splittedTokens[splittedTokens.length - 1].equals("/")) getTokens().remove(getTokens().size() - 1);
					}
				}

				sb = new StringBuilder();
				continue;
			}

			if (' ' == current) {
				addToken(sb);
				sb = new StringBuilder();
				continue;
			}

			/* 	don't tokenize on <word>'s or <words>'
				but do tokenize on '<words> */
			if ('\'' == current) {
				if (' ' == prior) {
					addToken("'");
				} else if (Character.isLetter(next) || Character.isDigit(next) || Character.isDigit(prior)) {
					sb.append(current);
				} else {
					addToken(sb.toString());
					addToken("'");
					sb = new StringBuilder();
				}

				continue;
			}

			/* handle ellipses (...) */
			if ('.' == current && '.' == next) {
				if (i + 2 < input.length() && '.' == arr[i + 2]) {
					sb.append("...");
					i += 2;
					continue;
				}
			}

			if ('~' == current && Character.isDigit(next)) {
				sb.append(current);
				continue;
			}

			/* handle commas within numbers and trailing " */
			if (Character.isDigit(prior) && (current == ',' || current == '"') && Character.isDigit(next)) {
				if (sb.toString().equals("")) sb = new StringBuilder(getTokens().remove(getTokens().size() - 1));
				sb.append(current);
				continue;
			}

			if (current == '%' && Character.isDigit(prior)) {
				sb.append(current);
				continue;
			}

			if (current == '.' && getAbbreviatedTitles().contains(sb.toString().toLowerCase())) {
				sb.append(".");
				getTokens().add(sb.toString());
				sb = new StringBuilder();
				continue;
			}

			if (TextUtils.isSpecial(current)) {
				addToken(sb);
				addToken(String.valueOf(current));
				sb = new StringBuilder();
				continue;
			}

			sb.append(current);
		}

		if (0 != sb.length()) addToken(sb);
		return this;
	}
}
