package cpa.struct.tme1;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class AdjArray {

	private LinkedList<Integer> [] graph;
	
	@SuppressWarnings("unchecked")
	public AdjArray(File file) {
		//initialisation du tableau 
		graph = new LinkedList[idMaxofEdgeListF(file) + 1];

		Scanner lecteur = null;
		try {
			lecteur = new Scanner(file);
			int id1, id2;
			while(lecteur.hasNext()) {
				//recuperation des id de l'arrete
				id1 = lecteur.nextInt();
				id2 = lecteur.nextInt();
				//initialisation des Liste de voisins a la premiere arrete visiter
				if(graph[id1] == null) {
					graph[id1] = new LinkedList<Integer>();
				}
				if(graph[id2] == null) {
					graph[id2] = new LinkedList<Integer>();
				}
				graph[id1].add(id2);
				graph[id2].add(id1);
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
	
	public LinkedList<Integer> getListOfNeighbour(int i) {
		return graph[i];
	}
	
	public LinkedList<Integer>[] getGraph() {
		return graph;
	}
	
	public int size() {
		return graph.length;
	}
	
	public int getIdMax() {
		return graph.length - 1;
	}
	
	public int nbNodes() {
		int cpt = 0;
		for (int i=0; i < graph.length; i++) {
			if (graph[i] != null) {
				cpt++;
			}
		}
		return cpt;
	}
	
	public int nbEdges() {
		int cpt = 0;
		for (int i=0; i < graph.length; i++) {
			if (graph[i] != null) {
				cpt += graph[i].size();
			}
		}
		return cpt / 2;
	}

	@Override
	public String toString() {
		String str = "AdjArray : getIdMax()=" + getIdMax() + ", nbNodes()=" + nbNodes() + ", nbEdges()=" + nbEdges() + "\ngraph = {\n";
		for (int i=0; i < graph.length; i++) {
			if (graph[i] != null) {
				str += "\t" + i +" : " + graph[i].toString() + "\n";
			}
		}
		return str + "}";
	}
	
	
}
