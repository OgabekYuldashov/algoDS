package com.ogabek.algorithms.arrays;

import java.util.*;

/**
 * 1229. Meeting Scheduler
 */
public class MeetingScheduler {
    /**
     * time: O(nlogn + mlogm)
     * space: O(1)
     */
    public static List<Integer> minAvailableDurationTwoPointerWithSorting(int[][] slots1, int[][] slots2, int duration) {
        int i = 0, j = 0;
        Arrays.sort(slots1, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(slots2, (a, b) -> Integer.compare(a[0], b[0]));

        while(i < slots1.length && j < slots2.length) {
            int[] slot1 = slots1[i];
            int[] slot2 = slots2[j];

            int maxStart = Math.max(slot1[0], slot2[0]);
            int minEnd = Math.min(slot1[1], slot2[1]);
            int newEnd = maxStart + duration;

            if(newEnd <= minEnd) {
                return new ArrayList<>(Arrays.asList(maxStart, newEnd));
            } else if (newEnd <= slot1[1]) {
                j++;
            } else {
                i++;
            }
        }

        return new ArrayList<>();
    }

    /**
     * time: O((m+m) * log(m+n))
     * space: O(m+n)
     */
    public static List<Integer> minAvailableDurationWithHeap(int[][] slots1, int[][] slots2, int duration) {
        Queue<int[]> timeslots = new PriorityQueue<>((slot1, slot2) -> Integer.compare(slot1[0], slot2[0]));

        for(int[] slot : slots1) {
            if(slot[0] + duration <= slot[1]) timeslots.offer(slot);
        }

        for(int[] slot : slots2) {
            if(slot[0] + duration <= slot[1]) timeslots.offer(slot);
        }

        while(timeslots.size() > 1) {
            int[] slot1 = timeslots.poll();
            int[] slot2 = timeslots.peek();

            if(slot2[0] + duration <= slot1[1]) {
                return new ArrayList<>(Arrays.asList(slot2[0], slot2[0] + duration));
            }
        }

        return new ArrayList<>();
    }

    public static void main(String[] args) {
        int[][] slotsA = new int[][] {{10, 50}, {60, 120}, {140, 210}};
        int[][] slotsB = new int[][] {{0, 15}, {60, 70}};

        System.out.println(minAvailableDurationWithHeap(slotsA, slotsB, 8));
    }
}
