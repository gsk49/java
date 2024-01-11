import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

class Main {
	public static void main(String[] args) {

		//Formatting
		System.out.println();
		System.out.println("-customQStack-");

		//Creates a new object of class "customQStack" named exQueue
		customQStack exQueue = new customQStack();

		//Adds integers into exQueue
		exQueue.push(44);
		exQueue.push(37);
		exQueue.push(56);
		exQueue.push(13);
		exQueue.push(9);

		//Print out exQueue
		exQueue.print();

		//Removes the last variable of exQueue (like a stack)
		int pVal = exQueue.pop();

		//Formatting
		System.out.println("Remove the last int from this Queue by using .pop()");

		//Prints out exQueue
		exQueue.print();

		//Formatting
		System.out.print("Result of .pop(): ");
		System.out.println(pVal);
		for (int i = 0; i<2; i++){
			System.out.println();
		}
		System.out.println("-customSQueue-");

		//New customQStack called exStack
		customSQueue exStack = new customSQueue();

		//Add New values to exStack
		exStack.add(44);
		exStack.add(37);
		exStack.add(56);
		exStack.add(13);
		exStack.add(9);

		//Print out exStack
		exStack.print();

		//Formatting
		System.out.println("Remove the first variable in the Stack using .poll()");

		//Remove the first variable in the Stack similarly to a Queue
		int poVal = exStack.poll();

		//Print out exStack
		exStack.print();

		//Formatting
		System.out.print("Result of .poll(): ");
		System.out.println(poVal);

  }
}


class customQStack {

	//New Queue that takes in Integers and uses a LinkedList
	private Queue<Integer> qStack = new LinkedList<Integer>();

	//Method that returns true if qStack is NOT empty
	public boolean empty(){
		return qStack.size() != 0;

	}


	//Pop method that removes the last variable from qStack and returns the variable removed or -1 if the stack is empty
	public int pop(){

		//Tests if qStack is not empty
		if (empty()) {

			//Moves the first int of the queue to the end until the last int is in front
			for (int i = 0; i<qStack.size()-1; i++){
				qStack.add(qStack.remove());
			}

			//Removes the int in front and returns it
			return qStack.remove();
		}

		//returns -1 if qStack was empty 
		return -1;

	}


	//Method push adds a new integer to the end of qStack
	public void push(int i){
		qStack.add(i);

	}


	//Prints out qStack
	public void print(){
		System.out.println(qStack);

	}


}


class customSQueue {

	//Create 2 new stacks named s1 and s2
	Stack<Integer> s1 = new Stack<Integer>();
	Stack<Integer> s2 = new Stack<Integer>();

	//New method add() that adds and int to the first open place in s1
	public void add(int i){
		s1.push(i);

	}


	//New method poll() that removes the first int in s1 similarily to how a queue would behave with .poll()
	public int poll(){

		//initial size of s1
		int s1s = s1.size();

		//iterates s1s-1 times and adds the last int in s1 to s2 and then removes it from s1
		for (int i =0; i<s1s-1; i++){
			s2.push(s1.pop());

		}

		//poll Value (to be returned later) = first int in s1
		int poVal = s1.pop();

		//size of s2 after the first int in s1 is removed
		int s2s = s2.size();

		//iterates s2s times and adds every value in s2 into s1 and then removes it from s2
		for (int i =0; i<s2s; i++){
			s1.push(s2.pop());

		}

		//returns poVal which is the item that was removed from s1
		return poVal;

	}

	//New method print that prints out s1
	public void print(){
		System.out.println(s1);
		
	}


}