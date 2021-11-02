package top.adxd.tikonlinejudge.auth;
import java.util.*;

/*
 * public class ListNode {
 *   int val;
 *   ListNode next = null;
 * }
 */

public class Solution {
    public static void main(String[] args) {
        ListNode listNode = createListNode(1, 2);
        Solution solution = new Solution();
        ListNode listNode1 = solution.reverseKGroup(listNode, 2);
        println(listNode1);
    }
    public static void println(ListNode listNode){
        while (listNode!=null){
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }
    public static ListNode createListNode(int... numbers){
        ListNode head = new ListNode();
        head.val = numbers[0];
        ListNode current = head;
        for (int i = 1;i<numbers.length ;i++){
            ListNode listNode = new ListNode();
            listNode.val = numbers[i];
            current.next = listNode;
            current = current.next;
        }
        return head;
    }
    /**
     *
     * @param head ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public ListNode reverseKGroup (ListNode head, int k) {
        if(head == null || k <= 1){
            return head;
        }
        ListNode headNode = head , tempNode = null,result = null,lastHead = null;
        while(headNode != null){
            if(hasK(headNode,k)){
                int temp = 1;
                ListNode current = headNode.next , last = headNode;
                while(temp < k){
                    tempNode = current.next;
                    current.next = last;
                    temp ++;
                    last = current;
                    current = tempNode;
                }
                if (lastHead != null){
                    lastHead.next = last;
                }
                lastHead = headNode;
                headNode = current;
                if(result == null){
                    result = last;
                }
            }else{
                if(lastHead != null){
                    lastHead.next = headNode;
                }
                if (result == null){
                    return head;
                }
                break;
            }
        }
        return result;
    }

    private boolean hasK(ListNode node,int k){
        if(node == null){
            return false;
        }
        int temp = 0;
        while(node != null && temp < k){
            node = node.next;
            temp ++;
        }
        return temp == k;
    }
}
class ListNode{
    int val;
    ListNode next = null;
}