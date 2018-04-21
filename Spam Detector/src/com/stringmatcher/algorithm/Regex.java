package com.stringmatcher.algorithm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
	
	public Regex() {}
	public void proccess(String text, String pattern)	{
		this.text = text;
		this.pattern = pattern.split(" ");
		this.patternSize = this.pattern.length;
		this.patternUL = new String[patternSize];
		this.generateRegexUpperLowerCase();
		this.generateRegexAll();
		if (this.matches()==true)	{
			this.highlight();
		}
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
	public void highlight()	{
		this.idxstart = new int[patternSize];
		this.idxfinish = new int[patternSize];
		for (int i=0; i<patternSize; i++)	{
			patternCompile = Pattern.compile("\\b" + patternUL[i] + "\\b");
			matcher = patternCompile.matcher(text);
			matcher.find();
			idxstart[i] = matcher.start();
			idxfinish[i] = matcher.end();
		}
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
	private void generateRegexAll()	{
		regex = ".*(";
		int[] order = new int[patternUL.length];
		for (int i=0; i<patternUL.length; i++)	{
			order[i]=i;
		}
		int npermute = 1;
		for (int i=1; i<=patternUL.length; i++)	{
			npermute *= i;
		}
		for (int j=0; j<npermute; j++)	{
			regex += "(";
			for (int i=0; i<patternUL.length; i++)	{
				regex += patternUL[order[i]];
				if (i!=patternUL.length-1) regex += ".*";
			}
			regex += ")";
			if (j!=npermute-1)	{
				regex += "|";
				nextPermutation(order);
			}
		}
		regex += ").*";	
	}
	public boolean matches()	{
		patternCompile = Pattern.compile(regex);
		matcher = patternCompile.matcher(text);
		return matcher.matches();
	}
	public void nextPermutation(int[] nums) {
	    if(nums == null || nums.length<2)
	        return;
	 
	    int p=0;            
	    for(int i=nums.length-2; i>=0; i--){
	        if(nums[i]<nums[i+1]){
	            p=i;
	            break;
	        }    
	    }
	 
	    int q = 0;
	    for(int i=nums.length-1; i>p; i--){
	        if(nums[i]> nums[p]){
	            q=i;
	            break;
	        }    
	    }
	 
	    if(p==0 && q==0){
	        reverse(nums, 0, nums.length-1);
	        return;
	    }
	 
	    int temp=nums[p];
	    nums[p]=nums[q];
	    nums[q]=temp;
	 
	    if(p<nums.length-1){
	        reverse(nums, p+1, nums.length-1);
	    }
	}
	 
	public void reverse(int[] nums, int left, int right){
	    while(left<right){
	        int temp = nums[left];
	        nums[left]=nums[right];
	        nums[right]=temp;
	        left++;
	        right--;
	    }
	}

}
