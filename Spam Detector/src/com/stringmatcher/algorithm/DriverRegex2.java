package com.stringmatcher.algorithm;

public class DriverRegex2 {

   private static String[] pattern;
   private static String[] text;
   private static int nPattern;
   private static int nText;

   public static void main( String args[] ) {
	  pattern = new String[100];
	  text = new String[100];
	  pattern[0] = "jim";
	  pattern[1] = "joe";
	  pattern[2] = "informatika itb ivan";
	  nPattern = 3;
	  text[0] = "humbapumpa Joe ahhahahah";
	  text[1] = "Ivan J merupakan mahasiswa Informatika Itb 2016";
	  text[2] = "IvaM J merupakan mahasiswa iTb jurusan infrmatika angkatan 2016";
	  nText = 3;
	  
	  Regex r = new Regex();
	  
	  for (int j=0; j<nText; j++) {
		  for (int i=0; i<nPattern; i++) {
			  r.proccess(text[j], pattern[i]);			  
		      System.out.println("Current text is: "+ text[j]);
		      System.out.println("Current pattern is: "+ pattern[i]);
		      System.out.print("Current regex is: "); r.printRegex();
		      System.out.println("Is Spam text: " + r.matches());
		      if (r.matches()==true)	break;
		  }
	  }
   }
}