package solution;
import java.io.*;
import java.util.*;

public class Solution {
    
    public static void main(String[] args) {
       int [][] result = getArrayList();
       int [] userInputOne = result[0];
       int [] userInputTwo = result[1];
       
        if (areEqual(userInputOne, userInputTwo)){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }
    }
    
    public static int[][] getArrayList(){
        
        FastReader userInput = new FastReader();
        int numberOfItems = userInput.nextInt();
        int []ArrayList1 = new int [numberOfItems];
        int [] ArrayList2 = new int [numberOfItems];
        
        for(int i=0;i<numberOfItems;i++){
            ArrayList1[i] = userInput.nextInt();
        }
        for(int j=0;j<numberOfItems;j++){
            ArrayList2[j] = userInput.nextInt();
        }
        
        int getBothArrays [][]  = new int [2][];
        getBothArrays[0] = ArrayList1;
        getBothArrays[1] = ArrayList2;
        
        return getBothArrays;
    }
    
    public static int [][] divideArray(int [] array){
        int [] leftArray = Arrays.copyOfRange(array, 0, array.length/2);
        
        int [] rightArray = Arrays.copyOfRange(array, array.length/2, array.length);
        int result [][] = new int [2][];
        
        result[0] = leftArray;
        result[1] = rightArray;
   
        return result;
    }
   
    
    public static boolean areEqual(int [] a1, int [] a2){

        if(a1.length == 1 && a2.length == 1){
            return a1[0] == a2[0];   
        }
        
        if(a1.length % 2 != 0){
            return Arrays.equals(a1, a2);
        }
        
        int [][] a1Result = divideArray(a1);
        int [][] a2Result = divideArray(a2);
        int [] a1Left= a1Result[0];
        int [] a1Right = a1Result[1];
        int [] a2Left = a2Result[0];
        int [] a2Right = a2Result[1];

        boolean firstCondition = areEqual(a1Left, a2Left) && areEqual(a1Right, a2Right);
        boolean secondCondition = areEqual(a1Left, a2Left) && areEqual(a1Left, a2Right);
        boolean thirdCondition = areEqual(a1Right, a2Left) && areEqual(a1Right, a2Right);
        
        return(firstCondition || secondCondition || thirdCondition);
    }
    
    public static String printArray(int [] array){
        String arrayString = " ";
        for(int i =0; i<array.length;i++){
            arrayString += array[i] + " ";
        }
        return arrayString;
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
