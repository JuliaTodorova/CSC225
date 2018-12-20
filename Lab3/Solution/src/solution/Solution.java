package solution;
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception{
        FastReader in = new FastReader();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        FastPriorityQueue pq = new FastPriorityQueue();    
        
        int [] firstInput = new int[2];
        
        for(int i=0; i<firstInput.length;i++){
            firstInput[i] = in.nextInt();
        }
        
        int numberOfApplicants = firstInput[0];
        int [] applicantsArray = new int[numberOfApplicants];

        for(int j=0;j<applicantsArray.length;j++){
            applicantsArray[j] = in.nextInt();
        }
 
        int consecutiveNumbers = firstInput[1];
        int numberOfIterations = numberOfApplicants - consecutiveNumbers + 1;
        int [] results = new int[numberOfIterations];

        for(int m=0;m<consecutiveNumbers;m++){
            pq.add(applicantsArray[m]);
        }
        
        for(int j=0; j<numberOfIterations; j++){
            
            int highestGpa = pq.peek();
            
            pq.remove();
            
            int secondHighestGpa = pq.peek();
            
            results[j] = highestGpa + secondHighestGpa;
            pq.add(highestGpa);
            
            int removeElement = applicantsArray[j];

            pq.remove(removeElement);
            
            int addElementIndex = j + consecutiveNumbers;
            
            if(addElementIndex <  applicantsArray.length){
                pq.add(applicantsArray[addElementIndex]);
            }
        }
        
        for(int i=0; i < results.length;i++){
            out.write(results[i] + " ");
        }
        out.flush();
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
    
    static class FastPriorityQueue {
        private final TreeMap<Integer, Integer> tree
        = new TreeMap<>(Collections.reverseOrder());
        
        public void add(Integer x){//adds an element in O(log n)
            if(tree.containsKey(x)){
                tree.put(x, tree.get(x) + 1);
            }
            else{
                tree.put(x, 1);
            }
        }
        
        public void remove(Integer x){//removes any given element in O(log n)
            if(tree.containsKey(x)){
                Integer count = tree.get(x);
                if(count == 1){
                    tree.remove(x);
                }
                else{
                    tree.put(x, count - 1);
                }
            }
        }
        
        public Integer remove(){//removes and returns the maximum element
            Integer max = tree.firstKey();
            this.remove(max);
            return max;
        }
        
        public Integer peek(){//returns without removing the maximum element
            return tree.firstKey();
        }
        
        public boolean isEmpty(){
            return tree.isEmpty();
        }
       
    }
}    
