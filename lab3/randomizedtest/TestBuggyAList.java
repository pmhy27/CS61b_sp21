package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public  void testThreeAddThreeRemove(){
        AListNoResizing<Integer> aa = new AListNoResizing<>();
        BuggyAList<Integer> bb = new BuggyAList<>();

        for(int i = 4; i<7; i++){
            aa.addLast(i);
            bb.addLast(i);
        }
        for(int i = 0; i<3; i++){
            assertEquals(aa.size(),bb.size());
            assertEquals(aa.removeLast(),bb.removeLast());
        }
    }
    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                assertEquals(L.size(),B.size());
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // getLast
                if (L.size() > 0) {
                    Integer L1 = L.getLast();
                    Integer B1 = B.getLast();
                    assertEquals(L1,B1);
                    System.out.println("getLast_L: " + L1+"getLast_B: "+B1);
                }
            }else if (operationNumber == 2) {
                // removeLast
                if(L.size() > 0){
                    Integer L1 = L.removeLast();
                    Integer B1 = B.removeLast();
                    assertEquals(L1,B1);
                    System.out.println("removeLast_L1: " + L1+"; removeLast_L2: " + B1);
                }
            }else if (operationNumber == 3) {
                // size
                int size_L = L.size();
                int size_B = B.size();
                assertEquals(size_L,size_B);
                System.out.println("size_L: " + size_L+"; size_B: " + size_B);
            }
        }
    }


    public static void main(String[] args) {


    }
}
