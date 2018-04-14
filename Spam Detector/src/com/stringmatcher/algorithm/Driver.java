package com.stringmatcher.algorithm;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BMAlgorithm bmAlgo = new BMAlgorithm("Hey what's up", "t");
		System.out.println(bmAlgo.matchAt());
		
		KMPAlgorithm kmpAlgo = new KMPAlgorithm("qwertyuipodsahjfdshfd", "ahjf");
		System.out.println(kmpAlgo.matchAt());
	}
}

