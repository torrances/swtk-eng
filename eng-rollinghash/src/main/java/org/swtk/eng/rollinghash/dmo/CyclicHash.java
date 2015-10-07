package org.swtk.eng.rollinghash.dmo;

/* prov:wasInfluencedBy <https://github.com/lemire/rollinghashjava/blob/master/src/rollinghash/CyclicHash.java> */
public class CyclicHash {

	static CharacterHash	hasher		= CharacterHash.getInstance();

	public final static int	wordsize	= 32;

	private static int fastleftshift1(int x) {
		return (x << 1) | (x >>> (wordsize - 1));
	}

	// this is purely for testing purposes
	public static int nonRollingHash(CharSequence s) {
		int value = 0;
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			int z = hasher.hashvalues[c];
			value = fastleftshift1(value) ^ z;
		}
		return value;
	}

	public int	hashvalue;

	int			myr;

	int			n;

	// myn is the length in characters of the blocks you want to hash
	public CyclicHash(int myn) {
		n = myn;
		if (n > wordsize) {
			throw new IllegalArgumentException();
		}

	}

	// add new character (useful to initiate the hasher)
	// to get a strongly universal hash value, you have to ignore the last or first (n-1) bits.
	public int eat(char c) {
		hashvalue = fastleftshift1(hashvalue);
		hashvalue ^= hasher.hashvalues[c];
		return hashvalue;
	}

	private int fastleftshiftn(int x) {
		return (x << n) | (x >>> (wordsize - n));
	}

	// remove old character and add new one
	// to get a strongly universal hash value, you have to ignore the last or first (n-1) bits.
	public int update(char outchar, char inchar) {
		int z = fastleftshiftn(hasher.hashvalues[outchar]);
		hashvalue = fastleftshift1(hashvalue) ^ z ^ hasher.hashvalues[inchar];
		return hashvalue;
	}
}
