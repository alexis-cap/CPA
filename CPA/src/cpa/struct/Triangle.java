package cpa.struct;

public class Triangle {

	public final int id1, id2, id3;

	public Triangle(int id1, int id2, int id3) {
		super();
		this.id1 = id1;
		this.id2 = id2;
		this.id3 = id3;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Triangle other = (Triangle) obj;
		// si ce triangle a (au moins) un id different avec l'autre, alors il ne sont pas egaux
		if ((id1 == other.id1 || id1 == other.id2 || id1 == other.id3) == false)
			return false;
		if ((id2 == other.id1 || id2 == other.id2 || id2 == other.id3) == false)
			return false;
		if ((id3 == other.id1 || id3 == other.id2 || id3 == other.id3) == false)
			return false;
		return true;
	}
	
	
}
