package cpa.struct.tme3;

import java.io.File;

import cpa.struct.tme1.Edge;

public class EdgeList extends cpa.struct.tme1.EdgeList {

	public EdgeList(File file) {
		super(file);
		// TODO Auto-generated constructor stub
	}
	
	public EdgeList() {
		super();
	}
	
	public void add(int i, int j) {
		this.graph.add(new Edge(i,j));
		if(this.idMax < i) {
			this.idMax = i;
		}
		if(this.idMax < j) {
			this.idMax = j;
		}
	}

}
