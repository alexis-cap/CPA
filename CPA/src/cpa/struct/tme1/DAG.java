package cpa.struct.tme1;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class DAG {

	protected List4DAG[] graph;
	
	public DAG(AdjArray adjArr) {
		LinkedList<Integer> [] src = adjArr.getGraph();
		graph = new List4DAG[src.length];
		
		for(int i=0; i < src.length; i++) {
			if (src[i] != null) {
				graph[i] = new List4DAG();
				for(int k : src[i]) {
					if (i < k) {
						graph[i].insert(k);
					}
				}
			}
		}
	}
	
	public DAG(File file) {
		//initialisation du tableau
		graph = new List4DAG[idMaxofEdgeListF(file) + 1];
		
		Scanner lecteur = null;
		try {
			lecteur = new Scanner(file);
			int id1, id2,low, high;
			while(lecteur.hasNext()) {
				//recuperation des id de l'arrete
				id1 = lecteur.nextInt();
				id2 = lecteur.nextInt();
				if (id1 < id2) {
					low = id1;
					high = id2;
				} else {
					low = id2;
					high = id1;
				}
				//initialisation des Liste de voisins a la premiere arrete visiter
				if(graph[low] == null) {
					graph[low] = new List4DAG();
				}
				graph[low].insert(high);
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
	
	public List4DAG getListOfNeighbour(int i) {
		return graph[i];
	}
	
	public List4DAG[] getGraph() {
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
		return cpt;
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
