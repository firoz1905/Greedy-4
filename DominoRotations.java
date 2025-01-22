// APproach : BruteForce - calculate Max Frequency element
// Time : O(6n+n) = O(7n)
// Space : O(1)
class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int maxFreq = 0;
        for(int i=1;i<7;i++){
            int count =0;
            for(int j=0;j<tops.length;j++){
                if(tops[j]==i) count++;
                if(bottoms[j] == i) count++;
            }
            if(count >= tops.length){
                maxFreq = i; // we found the candidate to be made on the top or bottom
                break;
            }
        }

        int topR = 0;
        int botR=0;
        for(int i=0;i<tops.length;i++){
            if(tops[i] != maxFreq && bottoms[i]!=maxFreq){
                return -1;
            } else if(tops[i] != maxFreq){
                topR++;
            } else if(bottoms[i]!=maxFreq){
                botR++;
            }
        }
        return Math.min(topR,botR);
    }
}


// Approach : Try with either tops elemenet to be replaced or bottoms element to be replaced
// Time : O(2n)
// Space : O(1)
class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int result = check(tops,bottoms,tops[0]);
        if(result!=-1) return result;
        result = check(tops,bottoms,bottoms[0]);
        return result; 
    }

    public int check(int[] tops,int[] bottoms, int target){
        int topR = 0;
        int botR=0;
        for(int i=0;i<tops.length;i++){
            if(tops[i] != target && bottoms[i]!=target){
                return -1;
            } else if(tops[i] != target){
                topR++;
            } else if(bottoms[i]!=target){
                botR++;
            }
        }

        return Math.min(topR , botR);
    }
}