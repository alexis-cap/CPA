package cpa.tme.tme1.exo;

import java.io.File;

import cpa.struct.tme1.DAG;
import cpa.tme.tme1.Tools;

public class Exo4 {

public static void main(String[] args) {
		
		File file = new File(args[0]);
		DAG dag = new DAG(file);
		System.out.println("ouverture dag");
		
		//Set<Triangle> triangles;
		long startTime, endTime;
		
		startTime = System.nanoTime();
		int res = Tools.listTriangle(dag);
		endTime = System.nanoTime();
		
		System.out.println("Pour le graphe : <" + args[0] + "\nOn trouve : " + res 
				+ " triangles en " + (endTime - startTime) * Math.pow(10, -9) + " seconde.");
	}
}
