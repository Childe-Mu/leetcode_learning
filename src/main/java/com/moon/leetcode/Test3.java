package com.moon.leetcode;

public class Test3 {
    public static void main(String[] args) {
//        System.out.println("12344544544".indexOf("44"));
        System.out.println(new Test3().removeOccurrences("axxxxyyyyb", "xy"));
    }

    public boolean canBeIncreasing(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            int p = -1;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (nums[j] <= p) {
                    flag = false;
                }
                p = nums[j];
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    public String removeOccurrences(String s, String part) {
        int n = part.length();
        while (true) {
            StringBuilder sb = new StringBuilder(s);
            int i = s.indexOf(part);
            if (i != -1) {
                sb.delete(i, i + n);
                s = sb.toString();
            } else {
                break;
            }
        }
        return s;
    }

//    class MovieRentingSystem {
//        Map<Integer, List<Entry>> shopMap = new HashMap<>();
//        Map<Integer, List<Entry>> movieMap = new HashMap<>();
//        List<Entry> all = new ArrayList<>();
//        public MovieRentingSystem(int n, int[][] entries) {
//            for (int i = 0; i < entries.length; i++) {
//                int[] entry = entries[i];
//                Entry e = new Entry(entry[0], entry[1], entry[2], i);
//                List<Entry> shopList = shopMap.computeIfAbsent(e.shop, o -> new ArrayList<>());
//                shopList.add(e);
//                List<Entry> movieList = movieMap.computeIfAbsent(e.movie, o -> new ArrayList<>());
//                movieList.add(e);
//                all.add(e);
//            }
//        }
//
//        public List<Integer> search(int movie) {
//
//        }
//
//        public void rent(int shop, int movie) {
//
//        }
//
//        public void drop(int shop, int movie) {
//
//        }
//
//        public List<List<Integer>> report() {
//
//        }
//
//        private class Entry {
//            int shop;
//            int movie;
//            int price;
//            int index;
//
//            public Entry(int shop, int movie, int price, int index) {
//                this.shop = shop;
//                this.movie = movie;
//                this.price = price;
//                this.index = index;
//            }
//        }
//    }

    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        int[][] f = new int[n + 1][n + 1];


        return 0L;
    }
}
