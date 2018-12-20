package solution;
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        computeMovies(sortArray());
    }
    
    public static int[] getArrayList(){
        FastReader userInput = new FastReader();
        int numberOfItems = userInput.nextInt();
        int []ArrayList = new int [numberOfItems];
        
        for(int i=0;i<numberOfItems;i++){
            ArrayList[i] = userInput.nextInt();
        }
        return ArrayList;
    }
    
    public static int[] sortArray(){
        int []sortedArray = getArrayList();
        Arrays.sort(sortedArray);
        return sortedArray;
    }
    
    public static int[] computeMovies(int[] sortedArray){

        int minDistanceSoFar = Math.abs(sortedArray[0] - sortedArray[1]);               
        int[] elementOneAndTwo = new int[2];
         
        for(int i=0; i < sortedArray.length - 1;i++){
            int currentIndex = i;
            int nextIndex = i+1;
            int tempDistance = Math.abs(sortedArray[currentIndex] - sortedArray[nextIndex]);

            if(tempDistance <= minDistanceSoFar ){
                minDistanceSoFar = tempDistance;
                elementOneAndTwo[0] = sortedArray[currentIndex];
                elementOneAndTwo[1] = sortedArray[nextIndex];  
            }
        }
        
        for(int m=0;m<2;m++){
            System.out.print(elementOneAndTwo[m] + " ");
        } 
        
        return elementOneAndTwo;
    }
    
    /*This class is taken from geeksforgeeks.org*/
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
