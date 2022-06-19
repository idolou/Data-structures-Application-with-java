import java.util.LinkedList;
public class Solution  implements MyInterface{

	private AVL<RecPoint> X_tree;
	private AVL<RecPoint> Y_tree;

	//constructor
	public Solution() {
		this.X_tree = new AVL<RecPoint>();
		this.Y_tree = new AVL<RecPoint>();
	}

	@Override
	public void insertDataFromDBFile(String objectName, int objectX, int objectY) {
		RecPoint newCoordinate = new RecPoint(objectName, objectX, objectY);
		this.X_tree.insert(objectX, newCoordinate);
		this.Y_tree.insert(objectY, newCoordinate);
	}
	
	@Override
	public String[] firstSolution(int leftTopX, int leftTopY, int rightBottomX,	int rightBottomY) {
		RecPoint [] x_val = X_tree.range_Query(X_tree.getRoot(), leftTopX, rightBottomX); //single dimension query for x values
		RecPoint [] y_val = Y_tree.range_Query(Y_tree.getRoot(), leftTopY, rightBottomY);//single dimension query for y values
		int x_len = x_val.length;
		int y_len = y_val.length;
		String[] res = new String[Math.max(x_len, y_len)];
		HashTable new_Hash = new HashTable(x_val.length);
		for( RecPoint ele : x_val) {
			if (ele!=null) new_Hash.insert(ele);  //put all the x values inside a Hashtable
		}

		for (int ind = 0; ind< y_len; ind++){  //we will check which values are in both x and y ranges
			if (y_val[ind] != null){
				RecPoint to_Add = y_val[ind];
				if (new_Hash.search(to_Add.getX(), to_Add.getY()) != null){
					res[ind] = to_Add.toString();  //store the correct data on the result with to string method

				}
			}
		}
		res = remove_nulls(res);  //remove all the nulls from the array
		return res;
	}


	@Override
	public String[] secondSolution(int leftTopX, int leftTopY, int rightBottomX, int rightBottomY) {
		//in this solution we will use binary search function to find the crossing values
		RecPoint[] x_val = X_tree.range_Query(X_tree.getRoot(), leftTopX, rightBottomX); //single dimension query for x values
		RecPoint[] y_val = Y_tree.range_Query(Y_tree.getRoot(), leftTopY, rightBottomY);//single dimension query for y values
		RecPoint[] min;
		RecPoint[] max;
		String[] finalArr;

		int type = 0;
		if (x_val.length > y_val.length) {
			type = 1; //the max tree sorted by Y values
			max = y_val;
			min = x_val;
		} else {
			//type = 0;   //the max tree sorted by X values
			max = x_val;
			min = y_val;
		}

		int index = 0;
		finalArr = new String[min.length];

		for (RecPoint Point : min) {

			int result = 0;

			if (type == 0) {
				result = binarySearch(Point.getX(), max, type);

			} else {            //if (type == 1) {
				result = binarySearch(Point.getY(), max, type);

			}
			if (result == 1) {  //we found the item
				finalArr[index] = Point.toString();
				index++;}
		}
		finalArr = remove_nulls(finalArr);
		return finalArr;
	}

	/**function to remove unnecessary nulls from an array**/
	public String[] remove_nulls(String[] arr){
		LinkedList<String> toList = new LinkedList<String>();
		for (String ele : arr) {
			if (ele != null){ toList.addFirst(ele);}
		}
		String[] new_Arr = new String[toList.size()];
		int index = 0;
		while (!toList.isEmpty()){
			new_Arr[index] = toList.pop();
			index++;
		}
		return new_Arr;
	}

	/**binarySearch helper function**/
	int binarySearch(int val, RecPoint[] arr, int type) {
		int left = 0;
		int right = arr.length - 1;

		while (right >= left) {
			int m = (left + right) / 2;
			//check if we need to search by x or by y
			// then, we will check  if val is equal to the middle value, if it does, we will return 1
			//if it doesn't then we go ahead right or left according to it value
			if (type == 0) {
				if (arr[m].getX() == val) {
					return 1;
				} else if (arr[m].getX() < val) {
					left = m + 1;
				} else {
					right = m - 1;
				}
			} else {
				if (arr[m].getY() == val) {
					return 1;
				} else if (arr[m].getY() < val) {
					left = m + 1;
				} else {
					right = m - 1;
				}
			}
		}
		// if no element found in the array then return 0
		return 0;
	}

}
