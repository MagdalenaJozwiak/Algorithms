
public class LinkedLists {

	private Node root;

	public void initialiseFirst(){
		root = new Node(-9);
		Node n2 = new Node(5);
		Node n3 = new Node(0);
		Node n4 = new Node(0);
		Node n5 = new Node(5);
		Node n6 = new Node(-9);
		this.root.setNext(n2);
		n2.setNext(n3);
		n3.setNext(n4);
		n4.setNext(n5);
		n5.setNext(n6);
	}
	
	public void initialiseSecond(){
		root = new Node(15);
		Node n2 = new Node(0);
		Node n3 = new Node(5);
		Node n4 = new Node(-9);
		this.root.setNext(n2);
		n2.setNext(n3);
		n3.setNext(n4);
	}
	
	public void initialiseThird(){
		root = new Node(21);
		Node n2 = new Node(-90);
		Node n3 = new Node(5);
		this.root.setNext(n2);
		n2.setNext(n3);
	}

	public void reverse() {
		Node current = this.root;
		Node prev = null;
		while (current != null) {
			Node buf = current.getNext();
			current.setNext(prev);
			prev = current;
			current = buf;
		}
		this.root = prev;
	}

	public void reverseBetween(int start, int stop) {
		int index = 0;
		Node current = this.root;
		Node prev = null;
		while(index != start && current != null){
			prev = current;
			current = current.getNext();
			index ++;
		}
		System.out.println("Starting with: " + current.getValue());
		Node newCur = null;
		Node prevTraverse = null;
		Node last = null;
		Node first = current;
		while(index <= stop && current != null){
			if(index == stop){
				last = current.getNext();
			}
			newCur = current.getNext();
			current.setNext(prevTraverse);
			prevTraverse = current;
			if (index != stop) {
				current = newCur;
			}
			index ++;
		}
		first.setNext(last);
		if (prev != null) {
			prev.setNext(prevTraverse);
		}else{
			this.root = prevTraverse;
		}
		System.out.println("Stopping on: " + current.getValue());
	}
	
	private int getLength(){
		int index = 0;
		Node current = this.root;
		while(current != null){
			index ++;
			current = current.getNext();
		}
		return index;
	}
	
	public Node getNode(int n) {
		int index = 0;
		Node current = this.root;
		while(current != null && index < n){
			current = current.getNext();
			index ++;
		}
		return current;
	}
	
	public boolean isPalindrome(){
		int half = getLength()/2;
		reverseBetween(0, half - 1);
		print();
		Node halfNode = getNode(half);
		Node current = this.root;
		while(current != null && halfNode != null){
			if(current.getValue() != halfNode.getValue()){
				return false;
			}
			current = current.getNext();
			halfNode = halfNode.getNext();
		}
		return true;
	}
	
	public Node overlap(LinkedLists anotherList){
		if (anotherList == null) {
			System.err.println("Another list empty.");
			return null;
		}
		int currentSize = getLength();
		int anotherListSize = anotherList.getLength();
		int diff = Math.abs(currentSize - anotherListSize);
		Node shorterListNode = anotherList.root;
		Node longerListNode = this.root;
		if(currentSize < anotherListSize){
			shorterListNode = this.root;
			longerListNode = anotherList.root;
		}
		
		int index = 0;
		while(index < diff){
			longerListNode = longerListNode.getNext();
			index ++;
		}
		while(longerListNode != null && shorterListNode != null && !longerListNode.equals(shorterListNode)){
			longerListNode = longerListNode.getNext();
			shorterListNode = shorterListNode.getNext();
		}
		
		return longerListNode != null && longerListNode.equals(shorterListNode) ? longerListNode : null;
	}

	public void print() {
		Node next = this.root;
		while (next != null) {
			System.out.print(next.getValue() + ", ");
			next = next.getNext();
		}
		System.out.println("\n");
	}

	public static void main(String[] args) {
		LinkedLists list = new LinkedLists();
		list.print();
		System.out.println(list.isPalindrome());
	}

}
