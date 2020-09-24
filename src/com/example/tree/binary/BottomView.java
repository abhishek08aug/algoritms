package com.example.tree.binary;

import java.util.Map;
import java.util.TreeMap;

public class BottomView {
    public static void main(String[] args) {
        Node n1 = new Node();
        n1.data = 20;

        Node n2 = new Node();
        n2.data = 8;

        Node n3 = new Node();
        n3.data = 22;

        Node n4 = new Node();
        n4.data = 5;

        Node n5 = new Node();
        n5.data = 3;

        Node n6 = new Node();
        n6.data = 4;

        Node n7 = new Node();
        n7.data = 25;

        Node n8 = new Node();
        n8.data = 10;

        Node n9 = new Node();
        n9.data = 14;

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;

        n5.left = n8;
        n6.right = n9;

        Tree t = new Tree();
        t.root = n1;

        t.printBottomView();
    }

    static class Node {
        int data;
        Node left;
        Node right;
    }

    static class Tree {
        Node root;

        void printBottomView() {
            if(root == null) {
                return;
            }

            Map<Integer, Node> distanceVsNode = new TreeMap<>();
            traverse(root, 0, distanceVsNode);

            for(Integer dist : distanceVsNode.keySet()) {
                System.out.println(distanceVsNode.get(dist).data);
            }
        }

        void traverse(Node root, int dist, Map<Integer, Node> distanceVsNode) {
            if(root == null) {
                return;
            }

            insertAtHead(root, dist, distanceVsNode);
            traverse(root.left, dist-1, distanceVsNode);
            traverse(root.right, dist+1, distanceVsNode);
        }

        void insertAtHead(Node root, int dist, Map<Integer, Node> distanceVsNode) {
            distanceVsNode.remove(dist);
            distanceVsNode.put(dist, root);
        }
    }
}
