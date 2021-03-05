package cpa.tme.tme3;

import cpa.struct.tme3.EdgeList;

public class Main {

	public static void main(String[] args) {
		EdgeList el = new EdgeList();
		
		double p = 0.15;
		double q = 0.05;
		double r;
		for (int i = 0; i < 400; i++) {
			for(int j = 0; j < 400; j++) {
				r = Math.random();
				if(i / 100 == j / 100) {
					if(r <= p) {
						el.add(i, j);
					}
				} else {
					if(r <= q) {
						el.add(i, j);
					}
				}
			}
		}

	}

}
