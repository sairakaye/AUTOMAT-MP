package main;

import java.util.*;

public class BFS {
    public Node search(Node initialNode, int i) {
        if (initialNode.isAccepting()) {
            return initialNode;
        }
        Queue<Node> siblings = new LinkedList<Node>();
        ArrayList<Node> explored = new ArrayList<Node>();
        if (i == 1) {
            siblings.add(initialNode);
            while (true) {
                if (siblings.isEmpty()) {
                    return null;    // failure
                }
                Node node = siblings.poll(); //get node in head of the queue
                explored.add(node); //add the node to the explored list
                List<Node> successors = node.generateChildren(); //generate children of head of queue
                for (Node child : successors) { //for each child in the generated list of children,
                    if (!explored.contains(child) || !siblings.contains(child)) { //if current child has not yet been explored, or if the nodes to be discovered does not contain the current child
                        if (child.isAccepting()) { // if current child is goal state
                            return child; // return the node
                        }
                        siblings.add(child); // add it to the nodes to be discovered.
                    }
                }
            }
        } else if (i == 2) {
            siblings.add(initialNode);
            while (true) {
                if (siblings.isEmpty()) {
                    return null;    // failure
                }
                Node node = siblings.poll(); //get node in head of queue
                explored.add(node); //add the node to the explored list
                List<Node> successors = node.generateChildrenType2();
                for (Node child : successors) {
                    if (!explored.contains(child) || !siblings.contains(child)) {
                        if (child.isAccepting()) {
                            return child;
                        }
                        siblings.add(child);
                    }
                }
            }
        } else if (i == 3) {
            siblings.add(initialNode);
            while (true) {
                if (siblings.isEmpty()) {
                    return null;    // failure
                }
                Node node = siblings.poll(); //get node in head of queue
                explored.add(node); //add the node to the explored list
                List<Node> successors = node.generateChildrenType3();
                for (Node child : successors) {
                    if (!explored.contains(child) || !siblings.contains(child)) {
                        if (child.isAccepting()) {
                            return child;
                        }
                        siblings.add(child);
                    }
                }
            }
        } else {
            siblings.add(initialNode);
            while (true) {
                if (siblings.isEmpty()) {
                    return null;    // failure
                }
                Node node = siblings.poll(); //get node in head of queue
                explored.add(node); //add the node to the explored list
                List<Node> successors = node.generateChildrenType4();
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
}
