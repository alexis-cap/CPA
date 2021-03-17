package cpa.tme.tme4;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cpa.struct.tme1.AdjArray;

public class Main {
	public static void main(String[] args) {
		System.out.println("Debut");
		AdjArray ad = new AdjArray(new File(args[0]));
		System.out.println("Fin lecture");
		List<Integer> l = new ArrayList<Integer>();
		//id Noeud ->  the number of neighbors of v that are not already in L
		Map<Integer,Integer> nbNeighbors = new HashMap<>();
		for(int i=0; i<ad.size(); i++) {
			List<Integer> lN = ad.getListOfNeighbour(i);
			if(lN !=null)
				nbNeighbors.put(i, (int)lN.stream().filter(e -> !l.contains(e)).count());
		}
		
		List<List<Integer>> d = new ArrayList<>(max(nbNeighbors)+1);
		for(int i=0; i<max(nbNeighbors)+1; i++) {
			d.add(new ArrayList<>());
		}
		for(Entry<Integer, Integer> e : nbNeighbors.entrySet()) {
			d.get(e.getValue()).add(e.getKey());
		}
		
		int k=0;
		
		//Repeat n times
		for(int n = 0; n<ad.size(); n++) {
			int i;
			for(i=0; i<d.size(); i++) {
				if(d.get(i)!=null && !d.get(i).isEmpty()) {
					if(k<i) k = i;
					break;
				}
			}
			int v = d.get(i).get(0);
			d.get(i).remove(0);
			
			l.add(0, v);
			for(Integer w : ad.getListOfNeighbour(v)) {
				if(!l.contains(w)) {
					int wNb = nbNeighbors.get(w);
					nbNeighbors.put(w, wNb-1);
					d.get(wNb).remove(w);
					d.get(wNb-1).add(w);
				}
			}
		}
		System.out.println("k - dimension " + k);
		
		
		
	}

	private static int max(Map<Integer, Integer> nbNeighbors) {
		int res = 0;
		for(Entry<Integer, Integer> e : nbNeighbors.entrySet()) {
			if(e.getValue()>res)res = e.getValue();
		}
		return res;
	}
}
