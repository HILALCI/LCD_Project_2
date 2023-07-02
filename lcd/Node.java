package lcd;

public class Node {
	String  block, term;
	int state;
	Node head, next;
			
	public Node() {
		block = null;
		term = null;
		state = 0; 
		next = null;
	}
	
	public Node(String bl, String tm, int st, Node n) {
		block = bl;
		term = tm;
		state = st;
		next = n;
	}
	
}