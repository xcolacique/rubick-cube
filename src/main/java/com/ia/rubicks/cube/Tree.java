package com.ia.rubicks.cube;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {

    private Node<T> root;

    public Tree(T rootData) {
        root = new Node<T>();
        root.data = rootData;
        root.children = new ArrayList<Node<T>>();  
    }

    public Node<T> getRoot() {
        return root;
    }

    public static class Node<T> {

        private T data;
        private Node<T> parent;
        private List<Node<T>> children;

        public void addChild(Node<T> parent, T d) {
            Node<T> n = new Node<>();
            n.data = d;
            n.children = new ArrayList<Node<T>>();
            n.parent = parent;
        }

        public T getData() {
            return data;
        }

        public Node<T> getParent() {
            return parent;
        }

        public List<Node<T>> getChildren() {
            return children;
        }
        
        
    }
}