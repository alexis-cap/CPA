package cpa.tme.tme2;

import java.io.File;

import cpa.struct.tme2.AdjArrayW;

public class MainTme2 {
	
	public static void main(String[] args) {
		
		File file = new File(args[0]);
		
		AdjArrayW adj = new AdjArrayW(file);
		
		int nbIter = Tools.powerIteration4PR(adj, 0.15, 0.001);
		System.out.println("Il faut " + nbIter + "pour obtenir un graph stabiliser");
		
		

	}

}
