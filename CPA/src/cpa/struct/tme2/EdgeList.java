package cpa.struct.tme2;

import java.io.File;
import java.util.Collections;


public class EdgeList extends cpa.struct.tme1.EdgeList {

	private double [] weight;


	public EdgeList(File file) {
		super(file);
		weight = new double[this.idMax + 1];
		Collections.sort(this.graph, new ComparatorEdge());
	}


	public double[] getWeight() {
		return weight;
	}


	public void setWeight(double[] weight) {
		this.weight = weight;
	}
	
	public double getWeightOf(int id) {
		return weight[id];
	}


	public void setWeightOf(int id, double weight) {
		this.weight[id] = weight;
	}
	
}

