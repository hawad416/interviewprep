import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.List;

/* interview prep arrays and hasing neetcode + structy + ctci */

/* First Attempt for all of these questions. Not currently too worried about performance. Will do a second attempt later */

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


        int[] nums = new int[]{1,1,1,2,2,3};
        System.out.println(Arrays.toString(topKFrequent2(nums, 2)));



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



    //n log n becuase in this version i add all n keys to the queue 
    public  int[] topKFrequent1(int[] nums, int k){

        if(nums == null ) throw new IllegalArgumentException("invalid array passed in :( ");

        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int[] result = new int[k];

        for(int num : nums){
            if(frequencyMap.containsKey(num)){
                frequencyMap.put(num, frequencyMap.get(num) + 1);
            } else {
                frequencyMap.put(num, 1);
            }
        }

        //      int[] nums = new int[]{1,1,1,2,2,3};

        System.out.println(frequencyMap);
        

        // reverse a and b in the compare since maxHeap
        PriorityQueue<Integer> kQ = new PriorityQueue<>((a,b) -> Integer.compare(frequencyMap.get(b), frequencyMap.get(a)));
        
        // need to figure out how to add k keys instead of n keys

        frequencyMap.keySet().forEach(key -> {
            kQ.add(key);
        });

        // either heap size is smaller than k, then u an just add elements 
        // heap size is = k , if element is not going to


        // f = 4
        // q. poll blah blah 

        // 3,2 k = 2
        while(k > 0){
            result[k-1] = kQ.remove();
            k--;
        }

        return result;


        // love is blind - netflix s3 
        // write down vocab 
    }

    // need to start doing boundary edge cases or making assumptions. 
    // assuming that each number appears a unique amount of times 
    // k is in the range [1, the number of unique elements in the array]


    //k log n because i only add here max k keys to the queue
    public static int[] topKFrequent2(int[] nums, int k){

        if(nums == null ) throw new IllegalArgumentException("invalid array passed in :( ");

        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int[] result = new int[k];

        for(int num : nums){
            if(frequencyMap.containsKey(num)){
                frequencyMap.put(num, frequencyMap.get(num) + 1);
            } else {
                frequencyMap.put(num, 1);
            }
        }

        // reverse a and b in the compare since maxHeap
        PriorityQueue<Integer> kQ = new PriorityQueue<>((a,b) -> Integer.compare(frequencyMap.get(a), frequencyMap.get(b)));
        
        // need to figure out how to add k keys instead of n keys
        // we add the key, and consistently poll to remove the key with lowest frequency, which is why compatator is a,b instead of b,a 
    
        //for the n log n, where we have n elements in the queue, we would reverse the comparator to be a max heap. 
        
        for(int key : frequencyMap.keySet()){
            kQ.add(key);
            
            if(kQ.size() > k) kQ.poll();      
        }
        // either heap size is smaller than k, then u an just add elements 
        // heap size is = k , if element is not going to

        while(k > 0){
            result[k-1] = kQ.remove();
            k--;
        }
        return result;
    }

}
