import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/* interview prep arrays and hasing neetcode + structy + ctci */

public class arrays_hashing {
    
    public static void main(String[] args) {
    

        String[] list = new String[6];
        list[0] = "eat";
        list[1] = "tea";
        list[2] = "tan";
        list[3] = "ate";
        list[4] = "nat";
        list[5] = "bat";

        System.out.println(groupAnagrams(list));
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
        
        int[] compMap = new int[26]; // o(26) can argue for o(1) constant space since we are just considering lowercase english alphabet letters

        for(int i = 0; i < s.length(); i ++) compMap[s.charAt(i) - 'a']++; // O(n) where n is the # of strings in s

        for(int i = 0; i < t.length(); i++) compMap[t.charAt(i) - 'a']--; // O(m) where m is the # of strings in t

        for(int occurence : compMap){ //o(n) where n is the # of elements in the compMap, but can argue for o(1) since we know exacty 26 letters should be there ? &maybe)
            if(occurence != 0 ) return false; 
        }


        return true; 
    
    }


    // group anagrams: https://leetcode.com/problems/group-anagrams/description/

    public static List<List<String>> groupAnagrams(String[] strs){

        // map each value to its corresponding sorted string in a hashmap. 
        // in this way, we can simply add the corresponding list value for each key into our resultant list
        Map<String, List<String>> anagramMap = new HashMap<>();  // O(N) where N is the number of strings in our array
        List<List<String>> result = new ArrayList<List<String>>(); // O(m * n) where m is the # of anagram groups and n is the # of strings

        // iterate through each string in the array, computing its sorted value. 

        for(int i = 0; i < strs.length; i++){        // O(n * m log m) where n is the total number of words, and m is the length of each word
            String sortedStr = sortHelper(strs[i]);

            if(anagramMap.containsKey(sortedStr)){ //if the sorted value is in the string, then add the current string to the corresponding list value of the sorted string
                List<String> anagramValues = anagramMap.get(sortedStr);
                anagramValues.add(strs[i]);

                anagramMap.put(sortedStr, anagramValues);

                continue;
            } 
            anagramMap.put(sortedStr, new ArrayList<>(List.of(strs[i]))); // if sorted value is not in the string, add the sorted value with the current string that we are on 
        }

        anagramMap.entrySet().forEach(entry -> {  // iterate through each entry in our hashmap, and add each anagram list to our result
            List<String> temp = entry.getValue();
            result.add(temp);
        });
        
        return result;
    }

    // sorts a string!
    public static String sortHelper(String str){ // o(nlogn) sorting complexity 
        char[] tempArr = str.toCharArray(); //o(n) to copy all characters of the string into an array
        Arrays.sort(tempArr);

        String tempStr = new String(tempArr);

        return tempStr;
    }

}
