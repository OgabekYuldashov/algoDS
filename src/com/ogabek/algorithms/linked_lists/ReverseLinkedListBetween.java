package com.ogabek.algorithms.linked_lists;

/**
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 */
public class ReverseLinkedListBetween {

    public static void main(String[] args) {
        ListNode test1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        reverseBetween(test1, 2, 4).print(); // Expected: 1->4->3->2->5

        System.out.println();
        ListNode test2 = new ListNode(3, new ListNode(5, null));
        reverseBetween(test2, 1,2).print(); // Expected: 5->3
    }


    /**
     * time: O(n)
     * space: O(1)
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null) return null;

        ListNode current = head;
        ListNode start = head;
        int counter = 1;
        while (current != null && counter < left) {
            start = current;
            current = current.next;
            counter++;
        }

        ListNode sublistHead = current;
        ListNode reverseSublist = null;
        ListNode next = null;
        while (current != null && counter <= right) {
            next = current.next;
            current.next = reverseSublist;
            reverseSublist = current;
            current = next;
            counter++;
        }

        start.next = reverseSublist;
        sublistHead.next = next;

        if(left == 1) return reverseSublist;
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public void print() {
            System.out.print(val);
            if(next != null) {
                System.out.print("->");
                next.print();
            }
        }
    }
}
