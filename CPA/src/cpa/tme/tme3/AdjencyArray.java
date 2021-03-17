package cpa.tme.tme3;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AdjencyArray {
	
	private List<Integer> [] array;
	
	public AdjencyArray(Set<Edge> listOfEdge, int idMax) {
		initArray(listOfEdge, idMax);
	}
	public AdjencyArray(File f) {
		initArray(f);
	}
	
	private void initArray(File f){
		Scanner lecteur = null;
		try {
			lecteur = new Scanner(f);
			Set<Edge> loe = new LinkedHashSet<Edge>();
			Set<Integer> nodes = new LinkedHashSet<Integer>();
			int idMax = -1;
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
			lecteur.close();
			initArray(loe, idMax);
		} catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	private void initArray(Set<Edge> listOfEdge, int idMax) {
		array = new LinkedList [idMax+1];
		
		//parcours de tous les edges du graph
		for (Edge e : listOfEdge) {
			//si le noeud n'a pas encore été visité, création d'une nouvelle liste
			if (array[e.getId1()] == null) {
				array[e.getId1()] = new LinkedList<Integer>();
			}
			//ajout de l'id du noeud de l'autre coté de l'arrête
			array[e.getId1()].add(e.getId2());
			//réciprocité pour le l'autre noeud de l'arrête
			if (array[e.getId2()] == null) {
				array[e.getId2()] = new LinkedList<Integer>();
			}
			array[e.getId2()].add(e.getId1());
		}
	}
	
	public List<Integer>[] getArray() {
		return array;
	}
	public List<Integer> getEdgeOfNode(int nodeId){
		return array[nodeId];
	}
	
	public int size() {
		return array.length;
	}
	
	public int nbNodes() {
		int cpt = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null) {
				cpt++;
			}
		}
		return cpt;
	}
	
	public int nbEdges() {
		int cpt = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null) {
				cpt += array[i].size();
			}
		}
		return cpt/2;
	}
	
	public boolean existEdge(int id1, int id2) {
		if(array[id1] == null || array[id2] == null) {
			return false;
		}
		for(int i : array[id1]) {
			if (i == id2) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String str = "AdjencyArray = nodes:" + nbNodes() +"; edges:" + nbEdges() + "; size:"+ array.length + "\ngrapgh:\n";
		for(int i=0; i < array.length; i++) {
			str += "\tNode " + i + ":";
			if(array[i] != null) {
				for(int n : array[i]) {
					str += " "+n+",";
				}
				str += "\n";
			} else {
				str += "None\n";
			}
		}
		return str;
	}
	
	public List<Integer> voisins(int nodeId){
		return array[nodeId];
	}
}
