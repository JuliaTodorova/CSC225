package solution;
import java.io.*;
import java.util.*;


public class Solution {

    public static void main(String[] args) {
        FastReader userInput = new FastReader();
        String lengthOfList = userInput.nextLine();
        int arrayLength = Integer.parseInt(lengthOfList);
        String candidates = userInput.nextLine();

        winner(arrayLength, candidates);

    }
    
    static void winner(int sizeOfList, String candidates){
        
        HashMap<String, Integer> electionMap = new HashMap(sizeOfList*2); 
        
        String candidatesArray[] = candidates.split("\\s+");    
    
        for(int i =0;i<sizeOfList;i++){
            String key = candidatesArray[i];
            if(electionMap.containsKey(key)){
                electionMap.put(key, electionMap.get(key) + 1);
            }
            else{
                electionMap.put(key, 1);
            }
        }
        
        int findLargestValue = (Collections.max(electionMap.values()));
        
        ArrayList<String> topCandidates = new ArrayList();
        
        for (Map.Entry<String, Integer> entry : electionMap.entrySet()) {
            if(entry.getValue() == findLargestValue){
                topCandidates.add(entry.getKey());
            }
        }    

        String shortestName = topCandidates.get(0);

            for(int i = 1; i < topCandidates.size();i++){
                if(topCandidates.get(i).compareTo(shortestName) < 0){
                    shortestName = topCandidates.get(i);
                    
                }
            }
            System.out.println(shortestName + " " + findLargestValue );
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