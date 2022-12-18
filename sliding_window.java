import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class sliding_window {
    
    public static void main(String[] args){

        System.out.println(maxProfit1(new int[]{7,1,5,3,6,4}));
    }
    

    // best time to buy and sell stock: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

    // o(n^2) time complexity
    public static int maxProfit(int[] prices){

        int max = 0; 

        for(int i = 0; i < prices.length; i++){

            for(int j = i + 1; j < prices.length; j++){

                int profit = prices[j] - prices[i];

                System.out.println("curr profit " + profit);

                max = Math.max(max, profit);
            }
        }

        return max; 
    }

    // use a two pointer sliding window approach
    // the first time you see there is a potential for profit is the where you want to keep your left pointer
    // you calculate the profit against your right pointer 
    // left pointer can always be ahead of the right pointer since when we buy (left), we have to sell in the future(right)
    //o(n)
    public static int maxProfit1(int[] prices){

        if(prices == null || prices.length == 1) throw new IllegalArgumentException("no profit to be made :(");

       int buyPointer = 0;
       int sellPointer = buyPointer + 1;
       int max = 0;
       // want the left pointer at the global minimum
       // want the right pointer at the global maximum

       while(sellPointer < prices.length){

            if(prices[sellPointer] < prices[buyPointer]){
                buyPointer = sellPointer; // be careful this is where you had a bug, you dont just do buyPointer++, you need to update the byPointer to that new local min you are seeing
                sellPointer++;
            } else{
                int currProfit = prices[sellPointer] - prices[buyPointer];
                max = Math.max(max, currProfit);
                sellPointer++;
            }
       }

        return max; 
    }


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
