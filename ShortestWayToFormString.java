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

// Approach : Two Pointer Approach with extra space of map storing the character and value as list of indices with occurence of that character
// As we iterate through target and when characters dont match instead of just moving character on source until you get the match
// go directly to the character Index where it matches
// Time : O(m + klogm)
// Space : O(m) # Map with array list
class Solution {
    public int shortestWay(String source, String target) {
        int s = 0;
        int t = 0;
        // All the source characters are put in the map
        HashMap<Character, List<Integer>> map = new HashMap<>(); // character along with all its occurences
        for (int i = 0; i < source.length(); i++) {
            char ch = source.charAt(i);
            map.putIfAbsent(ch, new ArrayList<>());
            map.get(ch).add(i);

        }

        int count = 0;
        while (t < target.length()) {
            char sChar = source.charAt(s);
            char tChar = target.charAt(t);
            if (!map.containsKey(tChar)) {
                return -1;
            }
            if (tChar == sChar) {
                s++;
                t++;
                if (t == target.length()) {
                    return count + 1;
                }
            } else {
                // both characters not matching - instead of iterating character by character in
                // source
                // get the list of all occurences of the target character in the map
                List<Integer> list = map.get(tChar);
                // Perform binary search on that list with target as Source Index
                int bIdx = binarySearch(list, s);
                if (bIdx == list.size()) {
                    // completed one iteration on the source
                    count++;
                    s = list.get(0); // place source pointer back to 0 or list.get(0)

                } else {
                    s = list.get(bIdx);
                    // now both characters will be matched
                    s++;
                    t++;
                    if (t == target.length()) {
                        return count + 1;
                    }
                }
            }

            if (s == source.length()) {
                count = count + 1;
                s = 0;
            }
        }
        return -1;
    }

    private int binarySearch(List<Integer> list , int target){
        int low =0;
        int high = list.size()-1;
        while(low<=high){
            int mid = low + (high - low)/2;
            if(list.get(mid) == target){
                return mid;
            } else if (list.get(mid) > target){
                high = mid-1;
            } else{
                low = mid+1;
            }
        }

        return low;
        
    }
}