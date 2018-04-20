package com.stringmatcher.algorithm;

public class KMPAlgorithm {
	private String text;
	private String pattern;
	private int[] b; //border function
	private int n;
	private int m;

	public KMPAlgorithm(String text, String pattern) {
		this.pattern = pattern;
		this.text = text;
		n = text.length();
		m = pattern.length();
		createBorderFunction();
	}

	private void createBorderFunction() {
		b = new int[m-1];
		b[0] = 0;

		//Buat b[x] di rentang 1 <= x <= n-1

		int j = 0;
		int i = 1;

		while (i < m-1) {
			if (Character.toLowerCase(pattern.charAt(j)) == Character.toLowerCase(pattern.charAt(i))) {
				b[i] = j + 1;
				i++;
				j++;
			}
			else if (j > 0)
				j = b[j-1];
			else {
				b[i] = 0;
				i++;
			}
		}
	}

	public int matchAt() {
		int j = 0;
		int i = 0;

		while (j < n) {
			if (Character.toLowerCase(pattern.charAt(i)) == Character.toLowerCase(text.charAt(j))) {
				if (i == m-1)
					return j - m + 1;
				i++;
				j++;
			} else if (i > 0) {
				i = b[i-1];
			} else {
				j++;
			}
		}
		return -1;
	}

	public int getTextLength() {
		return n;
	}

	public int getPatternLength() {
		return m;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}