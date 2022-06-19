public class Tester {
    private static boolean testPassed = true;
    private static int testNum = 0;

    /**
     * This entry function will test all classes created in this assignment.
     * @param args command line arguments
     */
    public static void main(String[] args) {


        // Each function here should test a different class.
        testAVLNode();

        testAVL();

        testHash();




        /* TODO - continue writing a function for each class */


        // Notifying the user that the code have passed all tests.
        if (testPassed) {
            System.out.println("All " + testNum + " tests passed!");
        }
    }

    /**
     * This utility function will count the number of times it was invoked.
     * In addition, if a test fails the function will print the error message.
     * @param exp The actual test condition
     * @param msg An error message, will be printed to the screen in case the test fails.
     */
    private static void test(boolean exp, String msg) {
        testNum++;

        if (!exp) {
            testPassed = false;
            System.out.println("Test " + testNum + " failed: "  + msg);
        }
    }
    private static void testAVLNode() {
        AVLNode<Integer> Anode = new AVLNode<Integer>(1,12);
        test(Anode.getHeight() == 0, "should be 0");
        test(Anode.getFather() == null, "should be null");
        Integer dat = 1;
        Anode.setData(dat);
        test(Anode.getData() ==1, "should be 1");

    }

    private static void testAVL() {
        AVL<Integer> newAVL = new AVL<Integer>();
        test(newAVL.getRoot()== null, " null");
        newAVL.insert(10, 12);
        test(newAVL.getRoot().getKey() == 10, "10");
        newAVL.insert(6,22);
        newAVL.insert(12,50);
        test(newAVL.getRoot().getLeftChild().getKey()==6, "6");
        test(newAVL.getRoot().getRightChild().getKey()==12, "12");
        test(newAVL.getRoot().getHeight() == 1, "1");
        test(newAVL.getRoot().getLeftChild().getHeight() == 0, "0");
        newAVL.insert(5,60);
        test(newAVL.getRoot().getLeftChild().getHeight() == 1, "1");
//        newAVL.insert(4,66);
//        newAVL.insert(22, 500);
//        newAVL.insert(13,200);
//        newAVL.insert(5,60);
//        newAVL.insert(40,300);
//        newAVL.insert(27,555);
//        Object[] tt = newAVL.range_Query(newAVL.getRoot(),10, 28);
//        newAVL.insert(14,600);
//        System.out.println(newAVL.getRoot().getRightChild().getRightChild().getKey());
//        test(newAVL.getRoot().getLeftChild().getKey() == 5, "5");
//        test(newAVL.getRoot().getLeftChild().getRightChild().getKey() == 6, "6");
//        System.out.println((newAVL.toString()));
//        System.out.println((newAVL.searchRange(newAVL.getRoot(),10,12)));
//        System.out.println(newAVL.search(10));
//        System.out.println(newAVL.range_query(10,12, newAVL.getRoot()));
//        Object aa =  newAVL.range_query(10, 12, newAVL.getRoot());
//        System.out.println(aa);
//        System.out.println(newAVL.ranger(newAVL.getRoot(), 5,10));
//        Object[] ttt = newAVL.range_q(newAVL.getRoot(), 5,10);
//        System.out.println(ttt[0]);
//        System.out.println(ttt[1]);
//        System.out.println(ttt[2]);
//        System.out.println(newAVL.ranger(newAVL.getRoot(), 5, 10));
//        newAVL.range_val(newAVL.getRoot(), 5, 10);
//        System.out.println(newAVL.getList());
//        System.out.println(newAVL.range_q(newAVL.getRoot(), 6,13)[0]);
//        System.out.println(newAVL.range_q(newAVL.getRoot(), 6,13)[1]);
//        System.out.println(newAVL.range_q(newAVL.getRoot(), 6,13)[2]);
//        System.out.println(newAVL.range_q(newAVL.getRoot(), 6,13)[3]);
//        System.out.println(tt[0]);
//        System.out.println(tt[1]);
//        System.out.println(tt[2]);
//        RecPoint a = new RecPoint(12, 10 ,1);
//        RecPoint b = new RecPoint(22, 6 ,2);
//        RecPoint c = new RecPoint(50, 12 ,3);
//        RecPoint d = new RecPoint(60, 5 ,4);
//        RecPoint e = new RecPoint(555, 27 ,5);
//        AVL<RecPoint> AVLT = new AVL<RecPoint>();
//        AVLT.insert(a.getX(), (RecPoint) a.getData());
//        AVLT.insert(b.getX(), (RecPoint) b.getData());
//        AVLT.insert(c.getX(), (RecPoint) c.getData());
//        AVLT.insert(d.getX(), (RecPoint) d.getData());
//        AVLT.insert(e.getX(), (RecPoint) e.getData());
//        RecPoint[] aaa = AVLT.range_Query(AVLT.getRoot(), 10, 28);
//        System.out.println(aaa[0]);
//        System.out.println(aaa[1]);
//        System.out.println(aaa[2]);






    }

    private static void testHash() {
        AVL<Integer> newAVL = new AVL<Integer>();
        HashTable hash1 = new HashTable(10);
        RecPoint newPoint = new RecPoint("da", 1, 2);
//        System.out.println(newPoint.toString());
//        test(newPoint.toString()== "da X=1 Y=2", "g");
        hash1.insert(newPoint);
//        System.out.println(hash1.search(1,2));

    }
}
