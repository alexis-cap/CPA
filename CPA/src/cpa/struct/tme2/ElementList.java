package cpa.struct.tme2;


public class ElementList extends cpa.struct.tme1.ElementList {

	private int weight;

	public ElementList(int id, ElementList next) {
		super(id, next);
		this.weight = 0;
	}

	public ElementList(int id) {
		super(id);
		this.weight = 0;
	}
	
	@Override
	public void setNext(cpa.struct.tme1.ElementList next) {
		this.next = (ElementList) next;
	}


	@Override
	public ElementList getNext() {
		// TODO Auto-generated method stub
		return (ElementList) next;
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
