import java.util.LinkedList;

public class HashTable {
	private int m;
	private LinkedList<ObjectWithCoordinates>[] H_table;



	//Hashtable constructor
	public HashTable(int table_dim){
		this.m = table_dim;
		this.H_table = new LinkedList[m];
	}


	/**Will return the hash key based on the given function (x+y)%m **/
	public int Hash_func(int X, int Y){
		if (m==0){
			throw new IllegalArgumentException("division by zero");
		}
		return ((X + Y)% m);
	}


	public void insert(ObjectWithCoordinates object) {
		int h_key = Hash_func(object.getX(), object.getY());
		if( object!=null){
		if (H_table[h_key] == null) { H_table[h_key] = new LinkedList<ObjectWithCoordinates>();}  //insert value to linked list in the right key
		H_table[h_key].add(object);}
	}

	public ObjectWithCoordinates search(int x, int y){
		int h_key = Hash_func(x,y);
		if (H_table[h_key] == null) {return null;}
		for(ObjectWithCoordinates H_key : getHashKeys(h_key)){
			if ((H_key.getX() == x) && (H_key.getY() == y)){return H_key;}
		}
		return null;
	}

	public LinkedList<ObjectWithCoordinates> getHashKeys(int h_key){
		return H_table[h_key];
	}

}

