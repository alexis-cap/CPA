package cpa.struct;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class AdjMatrix {

	private boolean [][] graph;
	
	public AdjMatrix(File file) {
		int idMax = idMaxofEdgeListF(file);
		
		graph = new boolean[idMax+1][idMax+1];
		
		Scanner lecteur = null;
		try {
			lecteur = new Scanner(file);
			int id1, id2;
			while(lecteur.hasNext()) {
				//recuperation des id de l'arrete
				id1 = lecteur.nextInt();
				id2 = lecteur.nextInt();
				//initialisation des Liste de voisins a la premiere arrete visiter
				
				graph[id1][id2] = true;
				graph[id2][id1] = true;
			}
			lecteur.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private int idMaxofEdgeListF(File f) {
		int idMax = -1;
		Scanner lecteur = null;
		try {
			lecteur = new Scanner(f);
			int tmp;
			while(lecteur.hasNext()) {
				//recuperation des id de l'arrete
				tmp = lecteur.nextInt();
				//M.A.J idMAX
				if(idMax < tmp) { idMax = tmp; }
			}
			lecteur.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return idMax;
	}
	
	public int getIdMax() {
		return graph.length - 1;
	}
	
	public int nbNodes() {
		int cpt = 0;
		for (int i=0; i < graph.length; i++) {
			for (int j=0; j < graph.length; j++) {
				if (graph[i][j]) {
					cpt++;
					break;
				}
			}
		}
		return cpt;
	}
	
	public int nbEdges() {
		int cpt = 0;
		for (int i=0; i < graph.length; i++) {
			for (int j=0; j < graph.length; j++) {
				if (graph[i][j]) {
					cpt++;
				}
			}
		}
		return cpt/2;
	}
	
}
