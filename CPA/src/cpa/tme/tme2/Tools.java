package cpa.tme.tme2;

import java.util.LinkedList;

import cpa.struct.tme2.*;

public class Tools {

	private Tools() {}
	
	public static double [] prodMatVec(EdgeList m, double [] a) {
		//initialisation de b
		double[] b = createRandomVec(m.getIdMax() + 1);
		//Calcule produit Matriciel
		for(Edge e : m.getGraph()) {
			b[e.id1] += e.getWeight() + a[e.id2];
		}
		return b;
	}
	
	public static double [] prodMatVec(AdjArrayW m, double [] a) {
		//initialisation de b
		double[] b = createRandomVec(m.size());
		//Calcule produit Matriciel
		LinkedList<Integer>[] graph = m.getGraph();
		for(int i=0; i < graph.length; i++) {
			if(graph[i]!=null) {
				for(int n : graph[i]) {
					b[i] = m.getWeightOfNodes(n) + a[n];
				}
			}
		}
		return b;
	}

	private static double[] createRandomVec(int sz) {
		double [] b = new double[sz];
		for(int i=0; i < b.length; i++) {
			b[i] = 0;
		}
		return b;
	}
	
	private static void normalize(double [] a) {
		double norme = 0;
		for(double e : a) {
			norme = Math.pow(e, 2);
		}
		norme = Math.sqrt(norme);
		
		for(int i=0; i < a.length; i++) {
			a[i] = a[i]/norme;
		}
	}
	
	public static double [] powerIteration(AdjArrayW m, int t) {
		//initialisation de a
		double [] a = new double[m.size()];
		for(int i = 0 ; i < a.length; i++) {
			a[i] = Math.random();
		}
		
		for (int i=0; i < t; i++) {
			a = prodMatVec(m, a);
			normalize(a);
		}
		return a;
	}
	
	public static void pageRank(AdjArrayW graph, double alpha, int iter) {
		// Initialisation du marcheur aleatoire
		int walker = -1;
		while(walker == -1) {
			walker = (int) (Math.random() * graph.size());
			if(graph.getListOfNeighbour(walker) == null) {
				walker = -1;
			}
		}
		for(int i=0; i < iter; i++) {
			
		}
		
	}
	
	
	
}
