package com.stringmatcher.algorithm;

public class DriverRegex1 {
	public static void main(String[] args)	{
		Regex r = new Regex("IvaM J merupakan mahasiswa inFormatika iTb angkatan 2016", "informatika itb ivan");
		//ivan -> I nya jadi uppercase dan typo satu huruf dari n jadi M
		//itb -> T nya jadi uppercase
		//informatika -> typo satu huruf dari o jadi tidak ada
		for (int i=0; i<r.getPatternSize(); i++)	{
			System.out.println(r.getIdxStartAt(i) + " " + r.getIdxFinishAt(i) + " " +  r.getText().substring(r.getIdxStartAt(i),r.getIdxFinishAt(i)));
		}
	}
}
