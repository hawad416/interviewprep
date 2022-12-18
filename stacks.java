import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class stacks {

    // valid  paranthesis: https://leetcode.com/problems/valid-parentheses/description/
    public static boolean isValid(String s){
        if(s == null) throw new IllegalArgumentException("string in invalid");

        Map<Character, Character> paranMap = new HashMap<>();
        Stack<Character> paranStack = new Stack<>();

        paranMap.put('(', ')');
        paranMap.put('{', '}');
        paranMap.put('[', ']');


        for(int i = 0; i < s.length(); i++){
            if(paranMap.containsKey(s.charAt(i))){
                paranStack.push(s.charAt(i));
            } else{                 
                if(paranStack.isEmpty()) return false; 

                char mostRecentParan = paranStack.pop();
                if(paranStack.get(mostRecentParan) != s.charAt(i)) return false;
            }
        }

        if(!paranStack.isEmpty()) return false;

        return true; 
    }
}
