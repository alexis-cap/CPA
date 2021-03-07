package cpa.struct.tme2;

import java.util.Comparator;

import cpa.struct.tme1.Edge;

public class ComparatorEdge implements Comparator<Edge>{

	@Override
	public int compare(Edge o1, Edge o2) {
		if(o1.id1 == o2.id1) {
			return 0;
		}
		if(o1.id1 > o1.id1) {
			return 1;
		}
		return -1;
	}
	
}
