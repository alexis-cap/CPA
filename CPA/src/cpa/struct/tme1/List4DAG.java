package cpa.struct.tme1;

public class List4DAG {
	protected ElementList first;
	protected int sz;
	
	public List4DAG() {
		first = null;
		sz = 0;
	}
	
	public void insert(int id) {
		sz++;
		if (this.first == null) {
			this.first = new ElementList(id);
			return;
		}
		
		if (id < first.id) {
			ElementList tmp = new ElementList(id, first);
			this.first = tmp;
			return;
		}
		
		ElementList n = this.first;
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

	public ElementList getFirst() {
		return first;
	}

	public int size() {
		return sz;
	}

	@Override
	public String toString() {
	
		String str = "List4DAG [sz = " + sz + " list={";
		ElementList n = first;
		while (n!= null) {
			str += n.id;
			if(n.hasNext()) {
				str += ", ";
			}
			n = n.getNext();
		}
		str += "}";
		return str;
	}
	
	
	
	
}
