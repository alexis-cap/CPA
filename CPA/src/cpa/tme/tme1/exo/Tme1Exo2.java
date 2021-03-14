package cpa.tme.tme1.exo;

import java.io.File;

import cpa.struct.tme1.AdjArray;
import cpa.struct.tme1.AdjMatrix;
import cpa.struct.tme1.EdgeList;


public class Tme1Exo2 {

	public static void main(String[] args) {
		
		File file = new File(args[0]);
		
		
		
		AdjArray adj = new AdjArray(file);
		EdgeList edgeList = new EdgeList(file);
		System.out.println("AdjArray : " + adj.nbEdges() + " liens et " + adj.nbNodes() + " noeuds.");
		System.out.println("EdgeList : " + edgeList.nbEdges() + " liens et " + edgeList.nbNodes() + " noeuds.");

		try {
			AdjMatrix m = new AdjMatrix(file);
		}catch (Exception e) {
			System.err.println("Matrice trop volumineuse :\n" + e.getMessage());
		}
		
	}

}
