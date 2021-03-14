package cpa.tme.tme1.exo;

import java.io.File;

import cpa.struct.tme1.AdjArray;
import cpa.struct.tme1.DAG;
import cpa.tme.tme1.Tools;

public class Tme1Exo3 {

	public static void main(String[] args) {
		
		File file = new File(args[0]);
		
		AdjArray graph = new AdjArray(file);
		System.out.println("ouverture adj");
		
		int diam;
		long startTime, endTime;
		
		startTime = System.nanoTime();
		diam = Tools.lowerBound(graph);
		endTime = System.nanoTime();
		
		System.out.println("Pour le graphe : <" + args[0] + " on trouve : \n une borne inferieur de " + diam 
				+ " noeuds de diametre en " + (endTime - startTime) * Math.pow(10, -9) + " seconde.");
	}

}
