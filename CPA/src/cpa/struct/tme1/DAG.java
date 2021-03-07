package cpa.struct.tme1;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class DAG {

	protected LinkedList<Integer> [] graph;
	
	public DAG(AdjArray adjArr) {
		LinkedList<Integer> [] src = adjArr.getGraph();
		graph = new LinkedList[src.length];
		
		for(int i=0; i < src.length; i++) {
			if (src[i] != null) {
				graph[i] = new LinkedList<Integer>();
				for(int k : src[i]) {
					if (i < k) {
						graph[i].add(k);
					}
				}
			}
		}
	}
	
	public DAG(File file) {
		//initialisation du tableau
		graph = new LinkedList[idMaxofEdgeListF(file) + 1];
		
		Scanner lecteur = null;
		try {
			lecteur = new Scanner(file);
			int id1, id2,low, high;
			
			while(!lecteur.hasNextInt()) {
				lecteur.nextLine();
			}
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
				//comparaison des id
				if (id1 < id2) {
					low = id1;
					high = id2;
				} else {
					low = id2;
					high = id1;
				}
				
				graph[low].add(high);
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
			
			while(!lecteur.hasNextInt()) {
				lecteur.nextLine();
			}
			
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
