class Main {
  public static void main(String[] args) {

		//Call the wordCount Method
		wordCount("You look like a five year old in that. Josh is unhelpful because that input was too short");


  }

	public static void wordCount(String inputString){

		//makes the entire inputString lowercase so the method is not case sensitive
		inputString = inputString.toLowerCase();

		//sets the spotsFilled var to zero, used for determining whether or not ot enlarge the table
		int spotsFilled = 0;

		//creates an array of nodes that the strings will be inputted into
		Node[] hashTable = new Node[3];

		//initializes each node in the array to null
		for (int i = 0;i<hashTable.length;i++){
			hashTable[i] = null;
		}

		//Splits the input string into separate words
		String[] retStr = inputString.split("\\P{Alpha}+");

		//the split function makes the first index an empty string if the input string starts with a non-alphabetic character, so we are eliminating that if its there
		if (retStr[0].equals("")){
			String[] temp = retStr;
			retStr = new String[retStr.length-1];
			for (int i=1; i<temp.length; i++){
				retStr[i-1] = temp[i];
			}
		}

		//loops through the array of words and inputs them into the array at the index
		for (int i = 0; i<retStr.length; i++){
			//find the index using the hash function given
			int index = getIndex(retStr[i], hashTable);

			//if the word would be inputted into an empty node, add one to the var used for determining the size of the array
			if (hashTable[index] == null){
				spotsFilled += 1;
					
			}	

			//insert the word where it belongs using the insert function
			hashTable[index] = insert(hashTable[index], retStr[i]);
	
			//if the array needs to be enlargened run the enlargen function
			if ( (double) spotsFilled / hashTable.length > .7){
				spotsFilled = 0;
				hashTable = enlargen(hashTable, retStr);
				break;
			}

		
		}

		//print the hash table using the print function
		print1n(hashTable);

	}

	//enlargen function makes the array larger if needed
	public static Node[] enlargen(Node[] input, String[] wString){

		//size of the array *2-1
		input = new Node[input.length*2-1];

		int spotsFilled = 0;
		for (int i = 0; i<wString.length; i++){
			//index is calculated the same way
			int index = getIndex(wString[i], input);

			//if the node is null, insert there and add one to the var to determine length of the array
			if (input[index] == null){
				input[index] = insert(input[index], wString[i]);
				spotsFilled += 1;
			}	

			//otherwise insert where it needs to be inserted
			else{
				input[index] = insert(input[index], wString[i]);

			}	

			//determine if the array needs to be enlargened
			if ((double)spotsFilled/input.length>.7){
				spotsFilled = 0;
				input = enlargen(input, wString);
				break;
			}			
		}

		//return the node, used so this can be recursive
		return input;
				
	}

	//find the index
	public static int getIndex(String word, Node[] hashTable){
		return Math.abs(word.hashCode()) % hashTable.length;

	}
	
	//insert the word as a node where it needs to be inserted
	public static Node insert(Node inputted, String word) {

		//if the node is null, insert there
		if (inputted == null){
			inputted = new Node(word, 1);
			return inputted;
		}

		//if the node is not empty, check if it is equal to the word and add 1 to freq if necessary
		else if (inputted.getWord().equals(word)) {
			inputted.addFrq();
			return inputted;
		}

		//if neither of the previous, recurse with the next node
		else{
			inputted.setNext(insert(inputted.next(), word));
		}

		//return inputted so the method can be recursive
		return inputted;
  }

	//print the nodes in the hash table out
	public static void print1n(Node[] hashTable){

		//loops through each index in the array of Nodes
		for (int i = 0; i<hashTable.length; i++){

			Node currentNode = hashTable[i];

			//counter to show which node in the list is active
			int counter=1;

			//loops through each linked list and prints the index, node, freq and word
			while (currentNode != null){
				System.out.println("Index: "+ i + ", Node: "+counter+", Frequency: "+ currentNode.getFrq()+ ", Word: "+ currentNode.getWord());
				counter++;
				currentNode = currentNode.next();

			}
		}
		//Print out the length of the array
		System.out.println("Length of Array: "+ hashTable.length);
	}


}

class Node {
		//Each node has a value and points to a child
		private Node next = null;

		//stores data word and freq
		private String word;
		private int freq;

		//created by inputting the word and freq
		Node(String input, int frq){
			word = input;
			freq = frq;

		}

		//return the word in the current node
		public String getWord(){
			return word;		
		}

		//add one to freq
		public void addFrq(){
			freq++;

		}

		//set freq to integer frq
		public void setFrq(int frq){
			freq = frq;
		}

		//return the freq of the current node
		public int getFrq(){
			return freq;
		}

		//access the child of the current node
		public Node next(){
			return next;
		}

		//set the next node to a node input
		public void setNext(Node nNode){
			next = nNode;
		}


}