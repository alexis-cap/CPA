package cpa.struct.tme2;

public class Edge extends cpa.struct.tme1.Edge {
	private int weight;
	
	public Edge(cpa.struct.tme1.Edge e) 
	{
		super(e.id1, e.id2);
		weight = 0;
	}

	public Edge(int id1, int id2, int weight) {
		super(id1, id2);
		this.weight = weight;
	}

	public Edge(int id1, int id2) {
		super(id1, id2);
		weight = 0;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return super.toString() + " weight=" + weight;
	}
	
	
	
}
