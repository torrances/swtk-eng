package org.swtk.eng.asm.utils;

public final class ArrayUtils {

	public static double[][] dsDouble(int size, int value) {
		return dsDouble(size, size, value);
	}

	public static double[][] dsDouble(int rows, int columns, int value) {
		double[][] a = new double[rows][columns];

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				a[i][j] = value;

		return a;
	}

	public static int[][] dsInt(int rows, int columns, int value) {
		int[][] a = new int[rows][columns];

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				a[i][j] = value;

		return a;
	}

	public static double[] ssDouble(int size, int value) {
		double[] a = new double[size];

		for (int i = 0; i < size; i++)
			a[i] = value;

		return a;
	}

	public static int[] ssInt(int size, int value) {
		int[] a = new int[size];

		for (int i = 0; i < size; i++)
			a[i] = value;

		return a;
	}

	public static String toString(double[] row) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < row.length; i++) {
			sb.append(row[i]);
			sb.append((i + 1 < row.length) ? "\t" : "");
		}

		return sb.toString();
	}

	public static String toString(int[] row) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < row.length; i++) {
			sb.append(row[i]);
			sb.append((i + 1 < row.length) ? "\t" : "");
		}

		return sb.toString();
	}

	public static String toString(int[][] m, int padding) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[i].length; j++)
				for (int k = 0; k < m.length; k++) {
					sb.append(m[k][j]);
					sb.append((k + 1 < m.length) ? "\t" : "\n");
				}

		return sb.toString();
	}

	public static String toString2(double[][] m) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < m.length; i++) {
			sb.append(toString(m[i]));
			if (i + 1 < m.length) sb.append("\n");
		}

		return sb.toString();
	}

	public static String toString2(int[][] m) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < m.length; i++) {
			sb.append(toString(m[i]));
			if (i + 1 < m.length) sb.append("\n");
		}

		return sb.toString();
	}
}
