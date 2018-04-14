package com.stringmatcher.algorithm;

public class BMAlgorithm {
	private String text;
	private String pattern;
	private int n,m;
	private StringBuffer uniqueStr;
	private int[] LOfunc; //Last Occurence function

	public BMAlgorithm(String text, String pattern) {
		this.text = text;
		this.pattern = pattern;
		n = text.length();
		m = pattern.length();

		uniqueStr = new StringBuffer();
		for (int i = 0; i < m; i++) {
			char current = pattern.charAt(i);
			if (uniqueStr.toString().indexOf(current) < 0) {
				uniqueStr.append(current);
			}
		}

		int nUnique = uniqueStr.length();
		LOfunc = new int[nUnique];
		for (int i = 0; i < nUnique; i++) {
			boolean isFound = false;
			int j = m-1;
			while (!isFound && j >= 0) {
				if (pattern.charAt(j) == uniqueStr.charAt(i)) {
					isFound = true;
					LOfunc[i] = j;
				} else {
					j--;
				}
			}
		}
	}

	public int matchAt() {
		String s = uniqueStr.toString();
		if (n < m) {
			return -1;
		} else {
			int i = m-1; //Untuk text
			int j = m-1; //Untuk pattern

			while (i < n) {
				if (pattern.charAt(j) == text.charAt(i)) {
					if (j == 0) {
						return i;
					} else {
						i--;
						j--;
					}
				} else {
					if (s.indexOf(text.charAt(i)) >= 0) {
						int LO = LOfunc[s.indexOf(text.charAt(i))];
						i += (j - LO);
					} else {
						i += m;
					}
					j = m-1;
				}
			}

			return -1;
		}
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