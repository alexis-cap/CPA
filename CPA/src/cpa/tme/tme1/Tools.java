package cpa.tme.tme1;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import cpa.struct.AdjArray;
import cpa.struct.DAG;
import cpa.struct.EdgeList;
import cpa.struct.ElementList;
import cpa.struct.List4DAG;
import cpa.struct.Triangle;

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
	
	public static Set<Triangle> listTriangle(List4DAG[] dag) {
		Set<Triangle> triangles = new HashSet<Triangle>();
		//parcours tous les noeuds du graph
		for(int i=0; i < dag.length; i++) {
			if(dag[i] != null) {
				ElementList n = dag[i].getFirst();
				//parcurs les voisins directs
				while(n.hasNext()) {
					//recherche des noeuds communs avec ces voisins directs
					Set<Integer> commun = compareListDag(n, dag[n.id]);
					for (int k : commun) {
						//ajout des triangles trouver
						triangles.add(new Triangle(i, n.id, k));
					}
				}
			}
			
		}
		return triangles;
	}
	
	public static Set<Integer> compareListDag(ElementList n, List4DAG l) {
		Set<Integer> res = new HashSet<Integer>();
		ElementList n1 = n, n2 = l.getFirst();
		
		while(n1.hasNext() && n2.hasNext()) {
			if(n1.id == n2.id) {
				res.add(n1.id);
				n1 = n1.getNext();
				n2 = n2.getNext();
			} else if (n1.id < n2.id) {
				n1 = n1.getNext();
			} else {
				n2 = n2.getNext();
			}
		}
		
		return res;
	}
	
}
