package cpa.tme.tme3;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Exo4 {
	
	public static void main(String[] args) {
		
		AdjencyArray el = new AdjencyArray(new File(args[0]));
		
		System.out.println(el.size());
		
		long timeDebut = System.currentTimeMillis();
		
		//Label Propagation
		Map<Integer, Pair<String, List<Integer>>> m = new HashMap<>();
		for(int i=0; i<el.nbNodes(); i++) {
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
		
		
		//Community detection with the label
		Map<String, List<Integer>> cm = new HashMap<String, List<Integer>>();
		for(Entry<Integer, Pair<String, List<Integer>>> e:m.entrySet()) {
			List<Integer> test = cm.get(e.getValue().getLabel());
			if(test==null) cm.put(e.getValue().getLabel(), new ArrayList<Integer>());
			else test.add(e.getKey());
		}
		
		long timeFin = System.currentTimeMillis();
		
		System.out.println("Temps du label propagation : " + (timeFin - timeDebut) * Math.pow(10, -9));
		System.out.println("Nombre de communaute " + cm.size());
		for(String s : cm.keySet()) {
			System.out.println("Val : " + s + " nb : " + cm.get(s).size());
		}
		
		
		
	}
	
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
	
	
}
