import java.util.regex.Pattern;

public class two_pointers {

    public static void main(String[] args){

        System.out.println(removeNonAlpha("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }


    //valid palindrome: https://leetcode.com/problems/valid-palindrome/

    public static boolean isPalindrome(String s) {

        if(s == null) throw new IllegalArgumentException("invalid string");

        String cleanStr = removeNonAlpha(s);


        int start = 0; 
        int end = cleanStr.length() -1;

        while(end > start){
            if(cleanStr.charAt(end) != cleanStr.charAt(start)) return false; 
            end--;
            start++;
        }

        return true;
    }

    // first need to remove all non-alphanumeric characters
    
    public static String removeNonAlpha(String s){

        /* 

        // using ascii range lol way too much work
        s = s.toLowerCase();

        for(int i = 0; i < s.length(); i++){
            int asciiVal = s.charAt(i);

            if(! ( (asciiVal >= 48 && asciiVal <= 57) || (asciiVal >= 97 && asciiVal <= 122)) ){
                s = s.replace(s.charAt(i), ' ');
            }
        }

        s = s.replaceAll("\\s",  "");

        return s;
        */

        //using regex 
        String clearStr = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();

        return clearStr;
    }

}