package cpa.tme.tme3;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Main {

	public static void main(String[] args) {

		
		//File f = new File("res.txt");
		//creation graph
		EdgeList el = new EdgeList();
		
		//Generation du graphe
		double p =0.3, q = 0.0001;
		double r;
		try(FileOutputStream fOs= new FileOutputStream(new File("test2.txt"))){
			
			for(int i=0; i < 400; i++) {
				for(int j=0; j < 400; j++) {
					if(i != j) {
						r = Math.random();
						
						if(i/100==j/100) {
							//same cluster
							if(r < p) {
								el.addEge(new Edge(i, j));
								String line =i+"\t" + j + "\n";
								fOs.write(line.getBytes());
							}
						} else {
							// different cluster
							if(r < q) {
								el.addEge(new Edge(i, j));
								String line =i+"\t" + j + "\n";
								fOs.write(line.getBytes());
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(el.size());
		
		//Label Propagation
		Map<Integer, Pair<String, List<Integer>>> m = new HashMap<>();
		for(int i=0; i<400; i++) {
			List<Integer> voisins = el.voisins(i);
			m.put(i, new Pair<String, List<Integer>>(i+"", voisins));
		}
		List<Integer> temp = new ArrayList<>(m.keySet());
		while(!isValid(m)) {
			Collections.shuffle(temp);
		
			for(Integer i: temp) {
				Pair<String, List<Integer>> p1 = m.get(i);
				
				List<String> labelsVoisins = new ArrayList<String>();
				for( Integer v :  p1.getNode()) {
					String s = m.get(v).getLabel();
					labelsVoisins.add(s);
				}
				
				String s = max(labelsVoisins);
				p1.setLabel(s);
			}
		}
		
//			drawLabel(m);
		
		//Community detection with the label
		Map<String, List<Integer>> cm = new HashMap<String, List<Integer>>();
		for(Entry<Integer, Pair<String, List<Integer>>> e:m.entrySet()) {
			List<Integer> test = cm.get(e.getValue().getLabel());
			if(test==null) cm.put(e.getValue().getLabel(), new ArrayList<Integer>());
			else test.add(e.getKey());
		}
		System.out.println("Nombre de communaute " + cm.size());
		for(String s : cm.keySet()) {
			System.out.println("Val : " + s + " nb : " + cm.get(s).size());
		}
		
		//Louvain
		

	}

//	private static void drawLabel(Map<Integer, Pair<String, List<Integer>>> m) {
//		for(Entry<Integer, Pair<String, List<Integer>>> e : m.entrySet()) {
//			if(e.getValue().getLabel().equals("")) System.out.println("ZZZZZZZZZ ++ " + e.getKey());
//			System.out.println(e.getValue().getLabel());
//		}
//		
//	}

	private static boolean isValid(Map<Integer, Pair<String, List<Integer>>> m) {
		for (Entry<Integer, Pair<String, List<Integer>>> e : m.entrySet()) {
			List<String> labelsVoisins = new ArrayList<String>();
			for( Integer v :  e.getValue().getNode()) {
				String s = m.get(v).getLabel();
				labelsVoisins.add(s);
			}
			
			String s = max(labelsVoisins);
			if(!s.equals(e.getValue().getLabel())) return false;
		}
		return true;
		
	}

	private static String max(List<String> labelsVoisins) {
		String res = "";
		int i=0;
		Map<String, Integer> m = new HashMap<>();
		for (String string : labelsVoisins) {
			Integer temp = m.get(string);
			if(temp==null)m.put(string, 0);
			else {m.put(string, temp+1);}
		}
		for(Entry<String, Integer> e : m.entrySet()) {
			if(i<=e.getValue()) {
				i=e.getValue();
				res = e.getKey();
			}
		}
		//System.out.println(res);
		return res;
	}
	
	private double modularity(EdgeList el, List<Integer> s) {
		int m = el.size();
		int ms = el.internalLinkGroup(s);
		double droite = 0;
		double degreeGroup = el.degreeGroup(s);
		for(Integer i:s) {
			droite += (ms - Math.pow(degreeGroup, 2) / ( 4 * m));
		}
		return (1/m) * droite ;
	}
	
	

}
