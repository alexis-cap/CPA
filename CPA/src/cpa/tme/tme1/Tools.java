package cpa.tme.tme1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import cpa.struct.tme1.AdjArray;
import cpa.struct.tme1.DAG;
import cpa.struct.tme1.ElementList;
import cpa.struct.tme1.List4DAG;

public class Tools {

	private Tools(){}
	
	public static BFSres bfs(AdjArray g, int s) {
		//dernier noeud visite
		int last = -1;
		int next = -1;
		int cpt = 0;
		//creation de la queue fifo et du tableau marquant les noeud visites
		LinkedList<Integer> fifo = new LinkedList<Integer>();
		boolean[] visited = new boolean[g.size()];
		fifo.add(s);
		visited[s] = true;
		
		while(!fifo.isEmpty()) {
			last = fifo.pop();
			//System.out.println(last);
			//si nouvelle generation incrementation du compteur de distance
			if(last == next) {
				cpt++;
				next = -1;
			}
			
			for(int v : g.getListOfNeighbour(last)) {
				if(!visited[v]) {
					//besoin d'un nouveau repere pour la gen suivante
					if(next == -1) {
						next = v;
					}
					fifo.add(v);
					visited[v] = true;
				}
			}
		}
		
		return new BFSres(last, cpt);
	}
	
	public static int lowerBound(AdjArray g) {
		
		int n = -1;
		while(n  < 0) {
			n = (int) (Math.random() * g.size());
			if (g.getListOfNeighbour(n) == null) {
				n = -1;
			}
		}
		
		int max = -1;
		BFSres tmp; 
		for (int i=0; i < 20; i++) {
			tmp = bfs(g, n);
			n = tmp.id;
			if (max < tmp.distance) {
				max = tmp.distance;
			}
		}
		return max;
	}
	
	
	public static int listTriangle(DAG dag) {
//		Set<Triangle> triangles = new HashSet<Triangle>();
		int res = 0;
		List4DAG[] graph = dag.getGraph();
		//parcours tous les noeuds du graph
		for(int i=0; i < graph.length; i++) {
			if(graph[i] != null) {
				ElementList n = graph[i].getFirst();
				//parcours des voisins
				while(n != null) {
					//cherche le noeud commun
					Set<Integer> commun = compareList(n, graph[n.id]);
//					for(int k : commun) {
//						//triangles.add(new Triangle(i, n.id, k));
//						res++;
//					}
					res = res + commun.size();
					n = n.getNext();
				}
			}
				
		}
		//return triangles;
		return res;
	}
	
	public static Set<Integer> compareList(ElementList n, List4DAG l) {
		Set<Integer> res = new HashSet<Integer>();
		
		if(l != null && n != null) { 
			
			ElementList n1 = n, n2 = l.getFirst();
			
			while(n1 != null && n2 != null) {
				if(n1.id == n2.id) {
					res.add(n1.id);
					n1 = n1.getNext();
					n2 = n2.getNext();
				}
				else if (n1.id < n2.id) {
					n1 = n1.getNext();
				} else {
					n2 = n2.getNext();
				}
			}
		}
		return res;
	}
	
	
	
	public static Set<Integer> compareList(LinkedList<Integer> l1, LinkedList<Integer> l2) {
		Set<Integer> res = new HashSet<Integer>();
		
		if(l1 != null && l2 != null && !l1.isEmpty() && !l2.isEmpty()) { 
			Iterator<Integer> it1 = l1.iterator();
			Iterator<Integer> it2 = l2.iterator();
			int n1 = it1.next(), n2 = it2.next();
			
			while(it1.hasNext() && it2.hasNext()) {
				if(n1 == n2) {
					res.add(n1);
					n1 = it1.next();
					n2 = it2.next();
				}
				else if (n1 < n2) {
					n1 = it1.next();
				} else {
					n2 = it2.next();
				}
			}
			
			if(n1 == n2) {
				res.add(n1);
			}
		}
		return res;
	}
	
}
