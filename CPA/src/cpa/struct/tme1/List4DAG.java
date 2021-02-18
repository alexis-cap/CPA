package cpa.struct.tme1;

public class List4DAG {
	private ElementList first;
	private int sz;
	
	public List4DAG() {
		first = null;
		sz = 0;
	}
	
	public void insert(int id) {
		sz++;
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

	public ElementList getFirst() {
		return first;
	}

	public int size() {
		return sz;
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
