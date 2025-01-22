// Approach : Two Pointer Approach using HashSet to store all the charaters of source and then iterate over target string 
// if character in target string not part of source HashSet then we can never form a string.
// Time : O(m*n)
// Space : O(m) #
class Solution {
    public int shortestWay(String source, String target) {
        int s = 0;
        int t =0;
        HashSet<Character> set = new HashSet<>(); 
        for(char ch:source.toCharArray()){
            set.add(ch);
        }
        int count =0;
        while(t<target.length()){
            char sChar = source.charAt(s); 
            char tChar = target.charAt(t);
            if(!set.contains(tChar)) return -1;

            if(sChar == tChar){
                s++;
                t++;
                if(t == target.length()){
                    return count+1;
                }
            }
            else {
                // characters not matching
                s++;
            }

            if(s == source.length()){
                s =0; // reset the source pointer back to 0 and start the comparison. 
                count++;
            }
        }

        return -1;
    
    }
}