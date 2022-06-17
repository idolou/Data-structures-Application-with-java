
public class AVLNode<T> {

	private final int key;
	private T data;
	private int height;
	private AVLNode<T> Left;
	private AVLNode<T> Right;
	private AVLNode<T> Parent;

	//AVLNode Constructor
	public  AVLNode(int key, T data){
		this.Parent = null;
		this.Left = null;
		this.Right = null;
		this.height = 0;
		this.data = data;
		this.key = key;
	}
	public int getHeight() {return this.height;}

	public void  setHeight(int height) {this.height = height;}


	public AVLNode<T> getLeftChild(){
		if (this.Left == null){
			return null;
		}
		return this.Left;
	}
	public void SetLeftChild(AVLNode<T> node) {this.Left = node;}



	public AVLNode<T> getRightChild(){
		if (this.Right == null){
			return null;
		}
		return this.Right;
	}
	public void SetRightChild(AVLNode<T> node) {this.Right = node;}
	
	public AVLNode<T> getFather(){
		if (this.Parent == null){
			return null;
		}
		return this.Parent;
	}
	public void SetFather(AVLNode<T> node) {this.Parent = node;}


	public int getKey(){
		return this.key;
		//return 0;
	}
	
	public T getData(){
		if(this.data!=null){
		return this.data;}
		return null;
	}

	public void setData(T data){
		this.data = data;
	}

	@Override
	public String toString() {
		
		String s = "";
		
		if (getLeftChild() != null){
			s+="(";
			s+=getLeftChild().toString();
			s+=")";
		}
		s+=getKey();
		
		if (getRightChild() != null){
			s+="(";
			s+=getRightChild().toString();
			s+=")";
		}
		
		return s;
	}

}

