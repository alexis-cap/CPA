package cpa.tme.tme2;

import java.util.LinkedList;
import java.util.List;

import cpa.struct.tme1.Edge;
import cpa.struct.tme2.*;

public class Tools {

	private Tools() {}
	
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
	
	public static void pageRank(AdjArrayW graph, double alpha, int iter) {
		
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
			for(int j=0; j < g.length; i++) {
				if(g[j] != null) {
					tmpWeight[j] = (1-alpha) * tmpWeight[j] + alpha / n ;
				}
			}
			
			graph.setWeight(tmpWeight);
		}
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
	
	
	public static double [] powerIteration4PR(AdjArrayW graph, double alpha, int t) {
		int n = graph.nbNodes();
		double [] p = new double[graph.size()];
		for(int i=0; i < p.length; i++) {
			if(graph.getListOfNeighbour(i) != null) {
				p[i] = Math.pow(1.0/n, 2);
			}
		}
		for (int i=0; i < t; i++) {
			p = prodMatVec(graph, p);
			normalize2(p, graph);
		}
		return p;
	}
	
	
}
