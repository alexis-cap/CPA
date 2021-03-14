package cpa.tme.tme2;

import java.util.LinkedList;
import java.util.List;

import cpa.struct.tme1.Edge;
import cpa.struct.tme2.*;

public class Tools {

	private Tools() {}
	
	
	public static int powerIteration4PR(AdjArrayW graph, double alpha, double epsilon) {
		
		int iteration = 0; // nombre d'iteration
		int n = graph.nbNodes();
		//initialisation de p
		double [] p = new double[graph.size()];
		for(int i=0; i < p.length; i++) {
			if(graph.getListOfNeighbour(i) != null) {
				p[i] = 1/n;
			}
		}
		boolean converged = false;
		while(!converged) {
			p = prodTransitionMatVec(graph, p);
			// P = (1 − alpha) * P + alpha * I
			for(int j=0; j < p.length; j++) {
				if(graph.getListOfNeighbour(j) != null) {
					p[j] = (1-alpha) * p[j] + alpha * 1.0/n;
				}
			}
			normalize2(p, graph);
			converged = hasConverged(graph, p, epsilon);
			iteration++;
			graph.setWeight(p);
		}
		return iteration;
	}
	private static boolean hasConverged(AdjArrayW graph, double[] p, double epsilon) {
		double[] g = graph.getWeight();
		for (int i = 0; i < graph.size(); i++) {
			if(graph.getListOfNeighbour(i) != null) {
				if(epsilon - g[i] > p[i] || epsilon + g[i] < p[i]) {
					return false;
				}
			}
		}
		return true;
	}

	// prduit de la matrice de transition d'un graph et d'un vecteur
	private static double[] prodTransitionMatVec(AdjArrayW graph, double[] a) {
		//initialisation de b
		double[] b = new double[graph.size()];
		//Calcule produit Matriciel
		LinkedList<Integer>[] g = graph.getGraph();
		for(int i=0; i < g.length; i++) {
			if(g[i]!=null) {
				double t = 1.0 / g[i].size();
				for(int j : g[i]) {
					b[i] += t  * a[j];
				}
			}
		}
		return b;
	}
	
	
	
	
	
	// Essaie 
	
	public static double [] prodMatVec(EdgeList m, double [] a) {
		//initialisation de b
		double[] b = createRandomVec(m.getIdMax() + 1);
		//Calcule produit Matriciel
		for(Edge e : m.getGraph()) {
			b[e.id1] += m.getWeightOf(e.id2) + a[e.id2];
		}
		return b;
	}
	
	public static double [] prodMatVec(AdjArrayW m, double [] a) {
		//initialisation de b
		double[] b = new double[m.size()];
		//Calcule produit Matriciel
		LinkedList<Integer>[] graph = m.getGraph();
		for(int i=0; i < graph.length; i++) {
			if(graph[i]!=null) {
				for(int j : graph[i]) {
					b[i] += m.getWeightOfNodes(i) / graph[i].size()  * a[j];
				}
			}
		}
		return b;
	}

	private static double[] createRandomVec(int sz) {
		double [] b = new double[sz];
		for(int i=0; i < b.length; i++) {
			b[i] = Math.random();
		}
		return b;
	}
	
	private static void normalize(double [] a) {
		double norme = 0;
		for(double e : a) {
			norme += Math.pow(e, 2);
		}
		norme = Math.sqrt(norme);
		
		for(int i=0; i < a.length; i++) {
			a[i] = a[i]/norme;
		}
	}
	
	public static double [] powerIteration(AdjArrayW m, double alpha, int t) {
		double [] a = createRandomVec(m.size());
		for (int i=0; i < t; i++) {
			a = prodMatVec(m, a);
			normalize(a);
		}
		return a;
	}
	
	public static double [] powerIteration(EdgeList m, int t) {
		double [] a = createRandomVec(m.getIdMax() + 1);
		for (int i=0; i < t; i++) {
			a = prodMatVec(m, a);
			normalize(a);
		}
		return a;
	}
	
	public static void pageRankIter(AdjArrayW graph, double alpha, int iter) {
		
		LinkedList<Integer>[] g = graph.getGraph();
		double [] tmpWeight;
		
		for(int i=0; i < iter; i++) {
			tmpWeight = new double [g.length];
			for(int j=0; j < g.length; i++) {
				if(g[j] != null) {
					for(int id : g[j]) {
						tmpWeight[id] += graph.getWeightOfNodes(j) / g[j].size();
					}
				}
			}
			int n = graph.nbNodes();
			for(int j=0; j < g.length; j++) {
				if(g[j] != null) {
					tmpWeight[j] = (1-alpha) * tmpWeight[j] + alpha / n ;
				}
			}
			
			graph.setWeight(tmpWeight);
		}
	}
	
	public static int pageRankEps(AdjArrayW graph, double alpha, double epsilon) {
		
		LinkedList<Integer>[] g = graph.getGraph();
		double [] tmpWeight;
		boolean encore = true;
		int cpt = 0;
		int n = graph.nbNodes();
		
		while(encore) {
			encore = false;
			cpt++;
			tmpWeight = new double [g.length];
			for(int j=0; j < g.length; j++) {
				if(g[j] != null) {
					for(int id : g[j]) {
						tmpWeight[id] += graph.getWeightOfNodes(j) / g[j].size();
					}
				}
			}
			for(int j=0; j < g.length; j++) {
				if(g[j] != null) {
					tmpWeight[j] = (1-alpha) * tmpWeight[j] + alpha / n ;
					if(tmpWeight[j] - epsilon <= graph.getWeightOfNodes(j) && graph.getWeightOfNodes(j) <= tmpWeight[j] + epsilon ) {
						encore = true;
					}
				}
			}
			graph.setWeight(tmpWeight);
		}
		return cpt;
	}
	
	private static  void normalize2(double [] p,AdjArrayW graph) {
		double norme = 0;
		for(double e : p) {
			norme += Math.pow(e, 2);
		}
		norme = Math.sqrt(norme);
		int n = graph.nbNodes();
		for(int i = 0; i < p .length; i++) {
			if(graph.getListOfNeighbour(i) != null) {
				p[i] += (1-norme) / n;
			}
		}
		
	}
}
