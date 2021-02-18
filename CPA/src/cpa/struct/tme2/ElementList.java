package cpa.struct.tme2;

import cpa.struct.tme1.ElementList;

public class ElementW extends ElementList {

	private int weight;

	public ElementW(int id, int weight, ElementList next) {
		super(id, next);
		this.weight = weight;
	}
	
	public ElementW(int id, int weight) {
		super(id);
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "ElementW [id=" + id + ", weight=" + weight + ", next=" + this.hasNext() + "]";
	}
	
	
	
}
