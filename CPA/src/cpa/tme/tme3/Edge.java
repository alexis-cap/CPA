package cpa.tme.tme3;

public class Edge {
	private int id1, id2;

	public int getId1() {
		return id1;
	}


	public int getId2() {
		return id2;
	}

	public Edge(int id1, int id2) {
		super();
		this.id1 = id1;
		this.id2 = id2;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (id1 != other.id1)
			return false;
		if (id2 != other.id2)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Edge (" + id1 + ", " + id2 + ")";
	}
	
	
}
