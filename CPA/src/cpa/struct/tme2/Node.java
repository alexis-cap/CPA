package cpa.struct.tme2;

public class Node {
	public final int id;
	private int weight;
	
	public Node(int id) {
		this.id = id;
		this.weight = 0;
	}
	
	public Node(int id, int weight) {
		this.id = id;
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", weight=" + weight + "]";
	}
	
}
