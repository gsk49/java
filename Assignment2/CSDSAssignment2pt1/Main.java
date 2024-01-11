import java.util.LinkedList;

public class Main {
  public static void main(String[] args) {

		//Create a new LinkedList named linkList that takes in Integers
    	LinkedList<Integer> linkList = new LinkedList<Integer>();

		//Add integers into linkList
    	linkList.add(44);
    	linkList.add(37);
    	linkList.add(56);
    	linkList.add(13);
		linkList.add(9);

		//Print out the original LinkedList 'linkList'
    	System.out.println(linkList);

		//Print out the reversed LinkedList 'linkList'
		reverse(linkList);


  }


	//Method named 'reverse' that prints the items in the LinkedList in reverse-order by treating the LinkedList as a Queue
	public static void reverse(LinkedList<Integer> list){

			//Iterated throught the LinkedList 'list' and prints the items in reverse-order
			for (int i = 0; i<list.size(); i++){
				
				//Prints out the last item in the linked list
				System.out.println(list.getLast());

				//adds a new header variable that is equivalent to the last item in the LinkedList
				list.addFirst(list.getLast());

				//Removes the final value in the LinkedList
				list.removeLast();

			}

			
	}

	
}

