package cpa.struct;

public class ElementList {
	public final int id;
	private ElementList next;
	
	
	public ElementList(int id, ElementList next) {
		this.id = id;
		this.next = next;
	}
	
	public ElementList(int id) {
		this.id = id;
		this.next = null;
	}

	public ElementList getNext() {
		return next;
	}

	public void setNext(ElementList next) {
		this.next = next;
	}

	public boolean hasNext() {
		return next != null;
	}

	@Override
	public String toString() {
		return "ElementList [id=" + id + ", hasNext()=" + hasNext() + "]";
	}
	
	
	
	
	
	

}
