package cpa.struct.tme1;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class EdgeList {
	
	private Set<Edge> graph;
	private int idMax;
	
	public EdgeList(File file) {
		idMax = -1;
		Scanner lecteur = null;
		try {
			lecteur = new Scanner(file);
			graph = new HashSet<Edge>();
			int id1, id2;
			while(lecteur.hasNext()) {
				//recuperation des id de l'arrete
				id1 = lecteur.nextInt();
				id2 = lecteur.nextInt();
				//M.A.J idMAX
				if(idMax < id1) { idMax = id1; }
				if(idMax < id2) { idMax = id2; }
				//ajout d'une nouvelle arrete
				graph.add(new Edge(id1, id2));
			}
			lecteur.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public Set<Edge> getGraph() {
		return graph;
	}

	public int getIdMax() {
		return idMax;
	}
	
	public int nbEdges() {
		return graph.size();
	}
	
	public int nbNodes() {
		Set<Integer> nodes = new HashSet<Integer>();
		for(Edge e : graph) {
			nodes.add(e.id1);
			nodes.add(e.id2);
		}
		return nodes.size();
	}

	@Override
	public String toString() {
		String str = "EdgeList : idMax=" + idMax + ", nbEdges()=" + nbEdges() + ", nbNodes()=" + nbNodes() + "\ngraph = {\n" ;
		for(Edge e : graph) {
			str += "\t" + e + "\n";
		}
		return str + "}";
	}
	
}
