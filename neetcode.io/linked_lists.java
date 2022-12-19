import java.util.HashMap;
import java.util.Map;

public class linked_lists {
    
    public static void main(String[] args) {
        ListNode n = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

        reverseList(n);
    }



    /* reverse a linked list : https://leetcode.com/problems/reverse-linked-list/description/ */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // reverse linked list iterative 

    public static ListNode reverseList(ListNode head){

        if(head == null) return head;

        ListNode prev = null; // begins at null since first value should now be the last value which should point to null
        ListNode curr = head; 
        ListNode next = null;  // we set this at null instead of immediatley setting it to currrent.next because we can't guarantee that there exists a next value 


        while(curr != null){    
            next = curr.next;
            curr.next = prev; 

            prev = curr; 
            curr = next; 
        }


        System.out.println(head.val);

        head = prev; // we set head = to prev, because we will know that curr is now null, meaning the prev value is the last known concrete value 

        return head;

    }

    // reverse linked list recursive 

    public static ListNode reverseList1(ListNode head){

        // 1 -> 2 -> 3 -> 4
        //base case : 
        if(head == null || head.next == null) return head; 

        //split the list into two chunks
        reverseList1(head.next);  // 2 -> 3 -> 4
        head.next.next = head; 
        head.next = null;

        return head;
    }





    // merge two sorted linked list : https://leetcode.com/problems/merge-two-sorted-lists/description/

    // extra memory 
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        
        ListNode list3 = new ListNode(1);
        ListNode curr = list3; 

        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                curr.next = new ListNode(list1.val);
                curr = curr.next; 
                list1 = list1.next; 
            } else{
                curr.next = new ListNode(list2.val);
                curr = curr.next; 
                list2 = list2.next; 
            }
        }

        while(list1 != null){
            curr.next = new ListNode(list1.val);
            curr = curr.next;
            list1 = list1.next;
        }

         while(list2 != null){
            curr.next = new ListNode(list2.val);
            curr = curr.next;
            list2 = list2.next;
        }

        return list3.next;
    }


    // linked list cycle : https://leetcode.com/problems/linked-list-cycle/description/
    

    // o(n) space where is the number of nodes in out linked list structure

    public boolean hasCycle(ListNode head) {
        Map<ListNode, Integer> nodeMap = new HashMap<>();

        ListNode curr = head;

        while(curr != null){
            if(nodeMap.containsKey(curr)) return true; 

            nodeMap.put(curr, 1);

            curr = curr.next;
        }

        return false;  //no cycle. 
    }

    // o(1) space and O(n) time solution using Floyds Tortoise & Hare Technique
    public boolean hasCycle1(ListNode head){

        if(head == null) return false; 

        ListNode slowPtr = head; 
        ListNode fastPtr = head;

        while(fastPtr.next != null){
            slowPtr = slowPtr.next; 
            fastPtr = fastPtr.next.next;

            if(slowPtr.equals(fastPtr)) return true;
        }

        return false;

    }
}
