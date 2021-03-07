package cpa.tme.tme1.exo;

import java.io.File;
import java.util.Scanner;

import cpa.struct.tme1.AdjArray;
import cpa.struct.tme1.AdjMatrix;
import cpa.struct.tme1.EdgeList;


public class Tme1Exo1et2 {

	public static void main(String[] args) {
		
		File file = new File(args[0]);
		
		
		
		AdjArray adj = new AdjArray(file);
		
		System.out.println("le fichier : <" + args[0] + "> a " + adj.nbEdges() + " liens et " + adj.nbNodes() + " noeuds.");

		try {
			//EdgeList edgeList = new EdgeList(file);
			//AdjMatrix m = new AdjMatrix(file);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
