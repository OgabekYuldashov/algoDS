package com.ogabek.algorithms.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 207. Course Schedule
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you
 * must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 */
public class CourseSchedule {
    /**
     * time: O(p + n^2)
     * space: O(n^2)
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. create an adjList and indegree array
        LinkedList<Integer>[] adjList = new LinkedList[numCourses]; // space: O(n^2)
        int[] indegrees = new int[numCourses]; // space: O(n)

        // 1.1. fill with an empty list
        for (int c = 0; c < numCourses; c++) {   // O(n)
            adjList[c] = new LinkedList<Integer>();
        }

        // 1.2. add relationships and calculate indegrees
        for (int[] pre : prerequisites) {    // O(p)
            int parent = pre[1];
            int child = pre[0];
            adjList[parent].add(child);

            indegrees[child]++;
        }

        Queue<Integer> noIndegree = new LinkedList<>();     // space: O(n)
        for (int i = 0; i < numCourses; i++) {   // O(n)
            if (indegrees[i] == 0) noIndegree.add(i);
        }

        int count = 0;
        while (!noIndegree.isEmpty()) {  // O(n)
            Integer current = noIndegree.poll();
            count++;

            LinkedList<Integer> children = adjList[current];
            for (Integer child : children) {     // O(n)
                indegrees[child]--;
                if (indegrees[child] == 0) noIndegree.add(child);
            }

            if (count == numCourses && noIndegree.size() > 0) return false;
        }

        return count == numCourses;

    }
}
