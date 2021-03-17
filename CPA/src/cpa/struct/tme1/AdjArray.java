package cpa.struct.tme1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdjArray {

	private List<Integer>[] graph;
	
	@SuppressWarnings("unchecked")
	public AdjArray(File file) {
		//initialisation du tableau 
		graph = new List[idMaxofEdgeListF(file) + 1];
		//Scanner lecteur = null;
		try (
			BufferedReader br = new BufferedReader(new FileReader(file))){
			
			String line;
			int id1, id2;
			String[] lineCoupe;
			while((line = br.readLine())!=null) {
				if(!line.contains("#")) {
					//System.out.println(i);
					lineCoupe = line.split("\t");
					id1 = Integer.parseInt(lineCoupe[0]);
					id2 = Integer.parseInt(lineCoupe[1]);
					
					//initialisation des Liste de voisins a la premiere arrete visiter
					if(graph[id1] == null) {
						graph[id1] = new ArrayList<Integer>();
					}
					if(graph[id2] == null) {
						graph[id2] = new ArrayList<Integer>();
					}
					graph[id1].add(id2);
					graph[id2].add(id1);
				}
			}
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
	
	public List<Integer> getListOfNeighbour(int i) {
		return graph[i];
	}
	
	public List<Integer>[] getGraph() {
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
