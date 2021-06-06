package com.ogabek.algorithms.graphs;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 1376. Time Needed to Inform All Employees
 *
 * A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company is the one with headID.
 *
 * Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee,
 * manager[headID] = -1. Also, it is guaranteed that the subordination relationships have a tree structure.
 *
 * The head of the company wants to inform all the company employees of an urgent piece of news. He will inform his direct
 * subordinates, and they will inform their subordinates, and so on until all employees know about the urgent news.
 *
 * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e., After informTime[i] minutes,
 * all his direct subordinates can start spreading the news).
 *
 * Return the number of minutes needed to inform all the employees about the urgent news.
 */
public class TimeNeededToInformAllEmployees {

    /**
     * time: O(3n) => O(n)
     * space: O(2n) => O(n)
     */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        // 1. create adjacencyList of heirarchy
        LinkedList<Integer>[] adjList = new LinkedList[n];
        for(int empId = 0; empId < n; empId++) {
            if(adjList[empId] == null) adjList[empId] = new LinkedList<Integer>();

            int managerId = manager[empId];
            if(managerId != -1) {
                if(adjList[managerId] == null) adjList[managerId] = new LinkedList<Integer>();
                adjList[managerId].add(empId);
            }
        }

        // 2. calculate the total time
        return totalTime(adjList, headID, informTime);
    }

    private int totalTime(LinkedList<Integer>[] adjList, int headID, int[] informTime) {
        LinkedList<Integer> children = adjList[headID];
        if(children.size() == 0) return informTime[headID];

        int maxTime = 0;

        for (Integer nextEmpId : children) {
            int time = totalTime(adjList, nextEmpId, informTime);
            if (time > maxTime) maxTime = time;
        }

        return maxTime + informTime[headID];
    }
}
