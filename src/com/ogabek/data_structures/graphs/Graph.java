package com.ogabek.data_structures.graphs;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;

public class Graph {

    public static void main(String[] args) {
        Graph myGraph = new Graph();
        myGraph.addVertex(0);
        myGraph.addVertex(1);
        myGraph.addVertex(2);
        myGraph.addVertex(3);
        myGraph.addVertex(4);
        myGraph.addVertex(5);
        myGraph.addVertex(6);
        myGraph.addEdge(3, 1);
        myGraph.addEdge(3, 4);
        myGraph.addEdge(4, 2);
        myGraph.addEdge(4, 5);
        myGraph.addEdge(1, 2);
        myGraph.addEdge(1, 0);
        myGraph.addEdge(0, 2);
        myGraph.addEdge(6, 5);

        myGraph.showConnections();
    }

    private Integer numberOfNodes;
    private Hashtable<Integer, ArrayList<Integer>> adjacencyList;

    public Graph() {
        numberOfNodes = 0;
        adjacencyList = new Hashtable<>();
    }

    public void addVertex(Integer node) {
        adjacencyList.put(node, new ArrayList<>());
        numberOfNodes++;
    }

    public void addEdge(Integer node1, Integer node2) {
        if(adjacencyList.containsKey(node1) && adjacencyList.containsKey(node2)) {
            ArrayList<Integer> node1Connections = adjacencyList.get(node1);
            node1Connections.add(node2);

            ArrayList<Integer> node2Connections = adjacencyList.get(node2);
            node2Connections.add(node1);
        } else {
            throw new RuntimeException("Not existing nodes provided!");
        }
    }

    public void showConnections() {
        for(Map.Entry<Integer, ArrayList<Integer>> entry: adjacencyList.entrySet()) {
            ArrayList<Integer> connections = entry.getValue();
            String connectionsString = connections.stream()
                    .map(Object::toString).collect(Collectors.joining(" "));
            System.out.println(entry.getKey() + "--> " + connectionsString);
        }
    }
}
