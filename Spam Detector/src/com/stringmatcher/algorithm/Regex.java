package com.stringmatcher.algorithm;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Arrays;

public class Regex {
	private Pattern patternCompile;
	private Matcher matcher;
	private String regex;
	private String text;
	private String[] pattern;
	private String[] patternUL;
	private int[] idxstart;
	private int[] idxfinish;
	private int patternSize;
	private boolean found;
	
	public Regex(String text, String pattern) {
		this.text = text;
		this.pattern = pattern.split(" ");
		this.patternSize = this.pattern.length;
		this.patternUL = new String[patternSize];
		this.generateRegexUpperLowerCase();
		this.found = true;
		this.highlight();
	}
	public boolean getFound()	{
		return found;
	}
	public int getIdxStartAt(int i)	{
		return idxstart[i];
	}
	public int getIdxFinishAt(int i) {
		return idxfinish[i];
	}
	public int getPatternSize()	{
		return patternSize;
	}
	public String getText()	{
		return text;
	}
	public String getRegex()	{
		return regex;
	}
	public void highlight()	{
		this.idxstart = new int[patternSize];
		this.idxfinish = new int[patternSize];
		for (int i=0; i<patternSize; i++)	{
			patternCompile = Pattern.compile(patternUL[i]);
			matcher = patternCompile.matcher(text);
			if(matcher.find())	{
				idxstart[i] = matcher.start();
				idxfinish[i] = matcher.end();
			}	else	{
				found = false;
			}
		}
		Arrays.sort(idxstart);
		Arrays.sort(idxfinish);
	}
	public void printRegex()	{
		System.out.println(regex);
	}
	public void printPattern()	{
		for (int i=0; i<pattern.length; i++)	{
			System.out.println(pattern[i]);
		}
	}
	public void printPatternUL()	{
		for (int i=0; i<patternUL.length; i++)	{
			System.out.println(patternUL[i]);
		}		
	}
	private void generateRegexUpperLowerCase()	{
		for (int i=0; i<patternUL.length; i++)	{
			patternUL[i] = "(";
			for (int l=0; l<pattern[i].length(); l++) {
				if (l!=0) patternUL[i] += "|";
				for (int k=0; k<pattern[i].length(); k++) {
					if (l==k)	{
						patternUL[i] += "[a-zA-Z]?";
					} else	{
						patternUL[i] += "[" + pattern[i].substring(k,k+1).toUpperCase() + pattern[i].substring(k,k+1).toLowerCase() + "]"; 
					}
				}
			}
			patternUL[i] += ")";
		}
	}
}
