package cpa.tme.tme3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;



public class EdgeList {
	private int nbNode;
	private int idMax;
	
	private Set<Edge> graph;
	
	

	public EdgeList() {
		graph = new HashSet<Edge>();
	}

	public EdgeList(File f){
		graph = readFile(f);
	}
	
	private Set<Edge> readFile(File f){
		Scanner lecteur = null;
		try {
			lecteur = new Scanner(f);
			Set<Edge> loe = new LinkedHashSet<Edge>();
			Set<Integer> nodes = new LinkedHashSet<Integer>();
			idMax = -1;
			while(lecteur.hasNext()) {
				int id1 = lecteur.nextInt();
				int id2 = lecteur.nextInt();
				
				if (id1 > idMax) {
					idMax = id1;
				}
				if (id2 > idMax) {
					idMax = id2;
				}
				
				nodes.add(id1);
				nodes.add(id2);
				loe.add(new Edge(id1, id2));
			}
			nbNode = nodes.size();
			lecteur.close();
			return loe;
		} catch(IOException e) {
			System.err.println("ICI !!!!" + e.getMessage());
			return null;
		}
	}

	public int getNbNode() {
		return nbNode;
	}

	public int getNbEdge() {
		return graph.size();
	}

	public int getIdMax() {
		return idMax;
	}
	
	public Set<Edge> getGraph() {
		return graph;
	}
	
	@Override
	public String toString() {
		String str = "EdgeList = nodes:" + nbNode +"; edges:" + getNbEdge() + "; idMax:"+ idMax + "\ngrapgh:\n";
		for(Edge e : graph) {
			str = str + "\t" + e +"\n";
		}
		return str;
	}
	
	public void addEge(Edge e) {
		graph.add(e);
	}
	
	public List<Integer> voisins(int i){
		List<Integer> voisins = new ArrayList<Integer>();
		for (Edge e : graph) {
			if(e.getId1()==i) voisins.add(e.getId2());
		}
		return voisins;
	}

	public int size() {
		return graph.size();
	}
	
	public int internalLinkGroup(List<Integer> s) {
		return (int) graph.parallelStream().filter( e -> s.contains(e.getId1()) && s.contains(e.getId2())).count();
	}
	
	public int degree(int n) {
		return (int) graph.parallelStream().filter(e -> e.getId1()==n || e.getId2()==n).count();
	}
	
	public int degreeGroup(List<Integer> s) {
		return s.parallelStream().mapToInt(e -> degree(e)).sum();
	}
	
}
