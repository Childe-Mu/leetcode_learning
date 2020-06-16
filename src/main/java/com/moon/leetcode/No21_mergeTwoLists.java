package com.moon.leetcode;

public class No21_mergeTwoLists {
	private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode lastNode1 = l1, lastNode2 = l2;
		ListNode tmp = new ListNode(0);
		ListNode ret = tmp;

		if (l1 == null && l2 != null) {
			System.out.println(1);
			return l2;
		} else if (l2 == null && l1 != null) {
			System.out.println(2);
			return l1;
		} else if (l1 == null) {
			System.out.println(3);
			return null;
		}
		while (lastNode1.next != null) {
			lastNode1 = lastNode1.next;
		}
		while (lastNode2.next != null) {
			lastNode2 = lastNode2.next;
		}

		if (l1.val > lastNode2.val) {
			System.out.println("=====" + lastNode2.val);
			ret = l2;
			lastNode2.next = l1;
			return ret;
		}
		if (l2.val > lastNode1.val) {
			System.out.println(lastNode1.val);
			ret = l1;
			lastNode1.next = l2;
			return ret;
		}
		int i = 0;
		
		while (l1 != null) {
			if (l2 != null) {
				if (l1.val <= l2.val) {
					tmp.next = new ListNode(l1.val);
					l1 = l1.next;
				} else {
					tmp.next = new ListNode(l2.val);
					l2 = l2.next;
				}
				tmp = tmp.next;
			} else {
				tmp.next = l1;
				break;
			}
		}
		if (l2 != null) {
			tmp.next = l2;
		}
		return ret.next;
	}

	public static void main(String[] args) {
		ListNode a = new ListNode(5);
		ListNode b = new ListNode(1);
		b.next = new ListNode(2);
		b.next.next = new ListNode(4);
		ListNode c = mergeTwoLists(a, b);

		while (c != null) {
			System.out.println(c.val);
			c = c.next;
		}
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}


}
