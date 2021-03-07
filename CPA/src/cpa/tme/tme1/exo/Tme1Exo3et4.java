package cpa.tme.tme1.exo;

import java.io.File;

import cpa.struct.tme1.AdjArray;
import cpa.struct.tme1.DAG;
import cpa.struct.tme1.DAG2;
import cpa.tme.tme1.Tools;

public class Tme1Exo3et4 {

	public static void main(String[] args) {
		
		File file = new File(args[0]);
		
		AdjArray graph = new AdjArray(file);
		DAG2 dag = new DAG2(file);
		
		System.out.println("ouverture ok");
		
		System.out.println("le fichier : <" + args[0] + "> a un diametre d'au moins " + Tools.lowerBound(graph));
		
		System.out.println("On compte " + Tools.listTriangle(dag).size() + " triangles dans le graphe");
	}

}
