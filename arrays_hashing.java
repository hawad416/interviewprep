/* interview prep arrays and hasing neetcode + structy + ctci */

public class arrays_hashing {
    
    public static void main(String[] args) {
    
    }


    // contains duplicate: https://leetcode.com/problems/contains-duplicate/

    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int num : nums){
            if(map.containsKey(num)) return true; 

            map.put(num, 1);
        }

        return false; 
    }


    // valid anagram: https://leetcode.com/problems/valid-anagram/
    public boolean isAnagram(String s, String t) {
        
        int[] compMap = new int[26];

        for(int i = 0; i < s.length(); i ++) compMap[s.charAt(i) - 'a']++;

        for(int i = 0; i < t.length(); i++) compMap[t.charAt(i) - 'a']--;

        for(int occurence : compMap){
            if(occurence != 0 ) return false; 
        }


        return true; 
    
    }
}
