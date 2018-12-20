package solution;
import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        getMedian();
    }    
    
    public static int[] getArrayList(){
        FastReader userInput = new FastReader();
        int sizeOfArray = userInput.nextInt();
        int []ArrayList = new int [sizeOfArray];
        
        for(int i=0;i<sizeOfArray;i++){
            ArrayList[i] = userInput.nextInt();
        }
        return ArrayList;
    }
    
    public static void getMedian(){
       int [] userInput = getArrayList();
       int lengthOfArray = userInput.length;
       int [] medianArray = new int[lengthOfArray];
       
        TreeSet<Integer> smallerThanMedian = new TreeSet<Integer>();
        TreeSet<Integer> largerThanMedian = new TreeSet<Integer>();
        
        for (int i = 0; i < lengthOfArray; i++) {
            int currentElement = userInput[i];

            Integer median = null;
            if (i == 0) {
                smallerThanMedian.add(currentElement);
            } else {
                median = smallerThanMedian.last();
                //System.out.println("Current median: " + median);
                if (currentElement > median) {
                     //System.out.println("Adding " + currentElement + " to largerThanMedian");
                    largerThanMedian.add(currentElement);
                } else {
                    //System.out.println("Adding " + currentElement + " to smallerThanMedian");
                    smallerThanMedian.add(currentElement);
                }
            }

            // Rebalance trees
            if (smallerThanMedian.size() > largerThanMedian.size() + 1) {
                int oldMedian = smallerThanMedian.pollLast();
                largerThanMedian.add(oldMedian);
            }
            else if(largerThanMedian.size() > smallerThanMedian.size()){
                int minElement = largerThanMedian.pollFirst();
                smallerThanMedian.add(minElement);
            }
            
            //System.out.println("This tree is the smaller ");
            //printTreeSet(smallerThanMedian);
            //System.out.println("This tree is the larger ");
            //printTreeSet(largerThanMedian);
            
            int newMedian = smallerThanMedian.last();
            medianArray[i] = newMedian;
        }
        
        //PrintMe(medianArray);
        System.out.println(calculateResult(medianArray));
   }
    
    public static void PrintMe(int [] array){
        for(int i = 0; i<array.length;i++){
            System.out.print(array[i] + " ");
        }
    }
    
    public static long calculateResult(int [] array) {
        long result = 1;
        for (int i = 0; i < array.length; i++) {
            result = (result * array[i]) % ((long)Math.pow(10, 9) + 7);
            
        }
        return result;
    }
    
    public static void printTreeSet(TreeSet tree){
        Iterator<Integer> iterator = tree.iterator();
	System.out.print("[");
 
	// Displaying the Tree set data
	while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
	}
        
        System.out.print("]");
	System.out.println();
    }
   
    static class FastReader {

        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    } 
}
