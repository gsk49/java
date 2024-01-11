class Main {
  public static void main(String[] args) {
		//Tests the classes using the tester class
		test bstTest = new test();

  }
}


class BinarySearchTree {

	class Node {
		//Each node has a value and points to a left and right child 
		private Node lChild = null;
		private Node rChild = null;
		private int val;

		//When creating a Node, set the value to x
		Node(int x){
			val = x;
		}
	
		//basic getter and setter functions for the vars for the Node

		public int getVal(){
			return val;
		}
		public void setVal(int i){
			val = i;
		}
		public Node getLChild(){
			return lChild;
		}
		public Node getRChild(){
			return rChild;
		}
		public void setLChild(Node lc){
			lChild = lc;
		}
		public void setRChild(Node rc){
			rChild = rc;
		}

	}


	//The BinarySearchTree consists of a node
	private Node bst;

	//sum starts at zero, kc (k counter) is initialized
	private int sum=0;
	private int kc;

	//Insert function which inserts the new key into a node in the correct child of the original node
	public Node insert(Node root, int key){
		if (root == null){
			root = new Node(key);
			return root;
		}
		else if (root.getVal()>key){
			root.setLChild(insert(root.getLChild(), key));
		}
		else if (root.getVal()<=key){
			root.setRChild(insert(root.getRChild(), key));
		}
		bst=root;
		return bst;
	}

	//Prints out each value in the Binary Search Tree as an inorder
	public void inorder(Node root){
		if (root == null){
			return;
		}
		inorder(root.getLChild());
		System.out.println(root.getVal());
		inorder(root.getRChild());
	}

	//sum returns the sum of each node in the binary search tree
	public int sum(Node root){
	
		if (root == null){
			return 0;
		}
		sum+=root.getVal();

		sum(root.getLChild());
		sum(root.getRChild());

		return sum;
	}

	//search returns the node of the key that is being looked for
	public Node search(Node root, int key){
		if (root == null){
			return null;
		}
		else if (root.getVal()==key){
			return root;
		}
		else if (root.getVal()>key){
			return search(root.getLChild(), key);
		}
		else{
			return search(root.getRChild(), key);
		}
	}

	//kthSmallest returns the kthSmallest value in the root by using a counter and a recursive function
	public Node kthSmallest(Node root, int k){
		kc = 0;
		return kthSmallestRecursive(root, k);
	}

	public Node kthSmallestRecursive(Node root, int k){
		if (root == null){
			return null;
		}
		Node left = kthSmallestRecursive(root.getLChild(), k);
	
		if (left!=null){
			return left;
		}

		kc++;

		if (kc==k){
			return root;
		}

		return kthSmallestRecursive(root.getRChild(), k);
	}

	//delete method deletes the node with the value that is equal to key
	public Node delete(Node root, int key){
		if (root == null){
			return null;
		}
		if (root.getVal() > key){
			root.setLChild(delete(root.getLChild(), key));
		}
		else if (root.getVal() < key){
			root.setRChild(delete(root.getRChild(), key));
		}
		else{
			if (root.getLChild() == null){
				return root.getRChild();
			}
			else if (root.getRChild() == null){
				return root.getLChild();
			}
		

			Node toDelete = root.getRChild();
			int toDeleteKey = toDelete.getVal();

			while (toDelete.getLChild() != null){
				toDeleteKey = toDelete.getLChild().getVal();
				toDelete = toDelete.getLChild();
			}

			root.setVal(toDeleteKey);
			root.setRChild(delete(root.getRChild(), root.getVal()));
		}
		return root;
	}


	//basic getter and setter methods
	public int getVal(){
		return bst.getVal();
	}
	public Node lChild(){
		return bst.getLChild();
	}
	public Node rChild(){
		return bst.getRChild();
	}

}


//Tester method tests all of the methods that were created and returns the expected value
class test{
	test(){

		BinarySearchTree tree = new BinarySearchTree();
		BinarySearchTree.Node root = tree.new Node(3);
		tree.insert(root,2);
		tree.insert(root,1);
		tree.insert(root,4);
		tree.inorder(root);
		System.out.println(tree.sum(root));
		System.out.println(tree.kthSmallest(root, 2));
		System.out.println(tree.search(root, 4));
		System.out.println(tree.delete(root, 4));

	}
}