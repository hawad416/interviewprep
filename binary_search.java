public class binary_search {
    





    //binary search: https://leetcode.com/problems/binary-search/description/

    public static int search(int[] nums, int target){

        if(nums == null) throw new IllegalArgumentException();

        int start = 0; 
        int end = nums.length; // you want the entire length to properly calculate the middle value 

        return search(nums, target, start, end);
    }

    public static int search(int[] nums, int target, int start, int end){
 
        if(start == end) return -1; //there is no more interval left to search 

        int mid = (start + end) / 2;

        if(nums[mid] == target) return mid;

        if(target < mid){
            return search(nums, target, start, mid);
        }else{
            return search(nums, target, mid + 1, end);
        }
    }
}
