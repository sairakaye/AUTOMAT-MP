package main;

import java.util.*;

public class BFS {
    public Node search(Node initialNode) {
        if (initialNode.isAccepting()) {
            return initialNode;
        }
        Queue<Node> siblings = new LinkedList<Node>();	// FIFO queue
        Set<Node> explored = new HashSet<Node>();
        siblings.add(initialNode);
        while (true) {
            if (siblings.isEmpty()) {
                return null;	// failure
            }
            Node node = siblings.poll(); //get node in head of queue
            explored.add(node); //add the node to the explored list
            List<Node> successors = node.generateChildren();
            for (Node child : successors) {
                if (!explored.contains(child) || !siblings.contains(child)) {
                    if (child.isAccepting()) {
                        return child;
                    }
                    siblings.add(child);
                }
            }
        }
    }


}