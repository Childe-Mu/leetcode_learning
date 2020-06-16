package com.moon.leetcode;


public class No13_romanToInt {
	
	public static int romanToInt(String s) {
		char arr[] = s.toCharArray();
		int ret = 0;
    	for (int i = 0; i < s.length(); i++) {
    		
    		switch (arr[i]) {
			case 'I':
				if (i + 1 < s.length() && arr[i + 1] == 'V') {
					ret += 4;
					i++;
				} else if (i + 1 < s.length() && arr[i + 1] == 'X') {
					ret += 9;
					i++;
				} else {
					ret += 1;
				}
				break;
			case 'V':
				ret += 5;
				break;
			case 'X':
				if (i + 1 < s.length() && arr[i + 1] == 'L') {
					ret += 40;
					i++;
				} else if (i + 1 < s.length() && arr[i + 1] == 'C') {
					ret += 90;
					i++;
				} else {
					ret += 10;
				}
				break;
			case 'L':
				ret += 50;
				break;
			case 'C':
				if (i + 1 < s.length() && arr[i + 1] == 'D') {
					ret += 400;
					i++;
				} else if (i + 1 < s.length() && arr[i + 1] == 'M') {
					ret += 900;
					i++;
				} else {
					ret += 100;
				}
				break;
			case 'D':
				ret += 500;
				break;
			case 'M':
				ret += 1000;
				break;
			default:
				break;
			}
    		
    	}
		return ret;
    }
	
	public static void main(String[] args) {
		System.out.println(romanToInt("DCXXI"));
	}
}

