import java.util.LinkedList;

public class AVL<T> {

	private AVLNode<T> Root;
	private final LinkedList<T> range_list;

	//AVL Tree constructor
	public AVL() {
		this.Root = null;
		this.range_list = new LinkedList<>();
	}

	public LinkedList<T> getList(){return this.range_list;}

	/**helper to get right son height**/
	private int right_height(AVLNode<T> node) {
		if (node.getRightChild() == null) return -1;
		else {
			return node.getRightChild().getHeight();
		}
	}
	/**helper to get left son height**/
	private int left_height(AVLNode<T> node) {
		if (node.getLeftChild() == null) return -1;
		else {
			return node.getLeftChild().getHeight();
		}
	}
	/**helper for the rotations functions**/
	private void height_update(AVLNode<T> Node) {
		Node.setHeight(Math.max(left_height(Node), right_height(Node)) + 1);
	}

	/**update the height of all nodes**/
	private void Height_update(AVLNode<T> Node) {
		AVLNode<T> node = Node;
		while (node != null) {
			int Right = right_height(node);
			int Left = left_height(node);
			node.setHeight(Math.max(Left, Right) + 1);
			node = node.getFather();
		}
	}
	/**left rotation algorithm from AVL trees class**/
	private void LeftChildRotate(AVLNode<T> Node) {
		AVLNode<T> k2 = Node;
		AVLNode<T> k1 = k2.getRightChild();
		AVLNode<T> k1_left = k1.getLeftChild();
		if(k2.getFather() != null) {  //update the father child if it is not the root
			if (k2.getFather().getKey() > k2.getKey()) {
				k2.getFather().SetLeftChild(k1);
			} else {
				k2.getFather().SetRightChild(k1);
			}}
			k1.SetLeftChild(k2);
			k2.SetRightChild(k1_left);
			k1.SetFather(k2.getFather());
			if (k1_left != null) {
				k1_left.SetFather(k2);
			}
			k2.SetFather(k1);
			if (k2 == this.Root) {  //update the Avl root if needed
				this.Root = k1;
			}
			height_update(k2);
			height_update(k1);

	}

	/**right rotation algorithm from AVL trees class**/
	private void RightChildRotate(AVLNode<T> Node) {
		AVLNode<T> k2 = Node;
		AVLNode<T> k1 = k2.getLeftChild();
		AVLNode<T> k1_right = k1.getRightChild();
		if (k2.getFather() != null) {     //update the father child if it is not the root
			if (k2.getFather().getKey() > k2.getKey()) {
				k2.getFather().SetLeftChild(k1);
			} else {
				k2.getFather().SetRightChild(k1);
			}
		}
			k1.SetRightChild(k2);
			k2.SetFather(k1);
			k1.SetFather(k2.getFather());
			if (k1_right != null) {
				k1_right.SetFather(k2);
			}
			k2.SetLeftChild(k1_right);
			if (k2 == this.Root) {   //update the Avl root if needed
				this.Root = k1;
			}
			height_update(k2);
			height_update(k1);

	}


	public void insert(int key, T data) {
		AVLNode<T> NewNode = new AVLNode<>(key, data);
		if (this.Root == null) {
			this.Root = NewNode;return;}
		insertNode(NewNode, this.Root);
		Height_update(NewNode);
		treeBalance(NewNode);

	}

	/**recursive function to insert the new node**/
	public void insertNode(AVLNode<T> Node, AVLNode<T> insert_To) {
		int key = insert_To.getKey();
		if (Node.getKey() < key) {
			if (insert_To.getLeftChild() == null) {
				Node.SetFather(insert_To);
				insert_To.SetLeftChild(Node);
			} else {
				insertNode(Node, insert_To.getLeftChild());
			}
		} else if (Node.getKey() > key) {
			if (insert_To.getRightChild() == null) {
				insert_To.SetRightChild(Node);
				Node.SetFather(insert_To);
			} else {
				insertNode(Node, insert_To.getRightChild());
			}
		}
	}

	/**balance the tree with the right case**/
	private void treeBalance(AVLNode<T> Node) {
		int Left;
		int Right;
		if (Node != null) {
			Left = left_height(Node);
			Right = right_height(Node);
			//Right rotation - case 1
			if (Left - Right > 1) {
				if (left_height(Node.getLeftChild()) > right_height(Node.getLeftChild())) {
					RightChildRotate(Node);
				}
				// Left rotation on left child than right rotation on the node itself - case 3
				else {
					LeftChildRotate(Node.getLeftChild());
					RightChildRotate(Node);
				}
			}
			// Left rotation - case 3
			else if (Right - Left > 1) {
				if (right_height(Node.getRightChild()) > left_height(Node.getRightChild())) {
					LeftChildRotate(Node);
				}
				// Right rotation on right child than left rotation on the node itself - case 4
				else {
					RightChildRotate(Node.getRightChild());
					LeftChildRotate(Node);
				}
			} else treeBalance(Node.getFather());

		}
	}

	/**recursive function for the search function shown in class**/
	public T Rec_search(AVLNode<T> Node, int key) {
		int node_key = Node.getKey();
		if (Node == null) {
			return null;
		}
		if (node_key == key) {
			return Node.getData();
		} //the key found
		if (key < node_key) {
			return Rec_search(Node.getLeftChild(), key);
		}
		if (key > node_key) {
			return Rec_search(Node.getRightChild(), key);
		} else {
			return null;
		}
	}

	public T search(int key) {
		if(key>=0)
		{return Rec_search(this.Root, key);}
		return null;
	}


	public AVLNode<T> getRoot() {
		if (this.Root == null) {
			return null;
		}
		return Root;
	}


	/**the function help to find how many elements are in the range(a,b)**/
	public int range_size(AVLNode<T> X, int a, int b) {
		if( X == null) {return 0;}   //if the root or the split node between a,b is null there is no elements in range
		if (X.getKey()>= a && X.getKey() <= b)
			return (1 + this.range_size(X.getLeftChild(), a, b) + this.range_size(X.getRightChild(), a, b));  //if the middle node exist, call the function recursively with left anf right child's
		else if (X.getKey() < a)
			return this.range_size(X.getRightChild(), a, b);
		else
			return this.range_size(X.getLeftChild(), a, b);
	}

	/** function to store the value in range in a LinkedList**/
	public void range_val(AVLNode<T> x, int a, int b) {
		if (x == null) {  //will call the function on the tree root
			return;
		}
		if (a < x.getKey()) {      //call the function recursively on left subtree
			range_val(x.getLeftChild(), a, b);
		}
		if (a <= x.getKey() && b >= x.getKey()) {
			this.range_list.addLast(x.getData());  //if the key is between a,b wi will store it in the linked list
		}
		if (b > x.getKey()) {   //call the function recursively on right subtree
			range_val(x.getRightChild(), a, b);
		}
	}

	/**this function put all the values from the LinkedList in array of points**/
	public RecPoint[] range_Query(AVLNode<T> x, int a, int b){
		int i = 0;
		int size = range_size(x, a, b);
		this.range_val(x, a, b);
		RecPoint[] res_points =  new RecPoint[size];
		while (!this.range_list.isEmpty()){
			res_points[i] = (RecPoint) this.range_list.pop();
			i++;}
		return res_points;}


}
