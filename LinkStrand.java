
public class LinkStrand implements IDnaStrand {
	 private class Node { //creates a new node called with the info given with string and no next node
		   	String info;
		   	Node next;
		   	public Node(String s) {
		      	info = s;
		      	next = null;
		   	}
		   }
		   private Node myFirst,myLast;
		   private long mySize; //node count
		   private int myAppends; //how many times append is called
		   private int myIndex; // last index of charAt
		   private int myLocalIndex; // last local index (within a node) called in charAt
		   private Node myCurrent; //last node examined in charAt

	
	public LinkStrand(){ //first constructor w/out parameters
		this("");
	}

	public LinkStrand(String string) { //second parameter with the string to create linkstrand on
		initialize(string);
	}

	@Override
	public long size() { //the size of linked list, # of nodes
		return mySize;
	}

	@Override
	public void initialize(String source) { //sets default values for a new LinkStrand
		Node intitial = new Node (source);
		myIndex =0;
		myLocalIndex=0;
		myCurrent = null;
		myFirst = intitial;
		myLast = intitial;
		myAppends = 0;
		mySize = source.length();	
	}

	@Override
	public IDnaStrand getInstance(String source) { //makes new instance of the class linkstrand
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) { //appends a node on the end of the list
		Node dnanode = new Node(dna); //new node to be added. Info is given string
		myAppends+=1; //updates append count
		mySize+= dna.length(); //updates size
		myLast.next = dnanode; //gives dnanode a home within the linkedlist
		myLast = dnanode; //updates pointer
		return this;
	}

	@Override
	public IDnaStrand reverse() { 
		Node Starter = myFirst;
		Node last = null;
		while (Starter != null){
			StringBuilder ss = new StringBuilder (Starter.info); //new string buoilder to copy node's info
			ss =  ss.reverse(); //uses stringbuilder reverse function
			String newer =ss.toString(); 
			Node filler = new Node(newer); //new node with reversed string
			if (last ==null) {
				last=filler; //starts reversed list
			}
			else {
				filler.next = last; //moves first node down
				last = filler; //updates last with new node
			}
			Starter = Starter.next; //moves pointer down
		}
		LinkStrand ans = new LinkStrand(last.info); //initializes linkstrand
		last = last.next; //moves last for next loop
		while(last!= null) { //buildsLinkStrand
			ans.append(last.info);
			last=last.next;
		}
		return ans; 
	}

	@Override
	public int getAppendCount() { //simply accsesses myAppend counter
		return myAppends;
	}
	
	@Override
	public char charAt(int index) throws IndexOutOfBoundsException {
		Node pointer; //intializes node to be created later
		if(myCurrent ==null) { //starts as first node if no previous calls
			pointer = myFirst;
		}
		else {
			pointer = myCurrent;
		}
		while (myIndex != index) { 
			myLocalIndex++;
			myIndex++; //loops through possible indexes in node and in general
			if (myLocalIndex >= pointer.info.length()) { //if the Local index is larger than that node, move to the next node and restart the local index	
				myLocalIndex = 0;
				pointer = pointer.next;
			}
		}
		myIndex =index; //update variables after the meathod stops
		myCurrent = pointer;
		return pointer.info.charAt(myLocalIndex);

	}
	
}
	
	
				