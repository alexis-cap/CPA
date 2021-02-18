package cpa.struct.tme2;


public class List4DAG extends cpa.struct.tme1.List4DAG {

	protected ElementList first;
	
	public List4DAG() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(int id) {
		this.sz++;
		if (first == null) {
			first = new ElementList(id);
			return;
		}
		
		if (id < first.id) {
			ElementList tmp = new ElementList(id, first);
			first = tmp;
		} 
		ElementList n = first;
		while(n.hasNext()) {
			if (id < n.getNext().id) {
				ElementList tmp = new ElementList(id, n.getNext());
				n.setNext(tmp);
				return;
			}
			n = n.getNext();
		}
		n.setNext(new ElementList(id));
	}

	@Override
	public ElementList getFirst() {
		return first;
	}
	
	@Override
	public String toString() {
	
		String str = "List4DAG [sz=" + sz + "list={";
		ElementList n = first;
		while (n.hasNext()) {
			str += n.id;
			n = n.getNext();
			if(n.hasNext()) {
				str += ", ";
			}
		}
		str += "}";
		return str;
	}
}
