package org.example;

public class RedBlackTree {
    private Node root;

    public boolean add(int value){
        if (root != null) {
            boolean result = addNode(root, value);
            root = rebalance(root);
            root.color = Color.BLACK;
            return result;
        } else {
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }

    }
    private boolean addNode(Node node, int value){
        if(node.value == value){
            return false;
        } else{
            if (node.value > value){
                if (node.lefChild != null){
                    boolean result = addNode(node.lefChild, value);
                    node.lefChild = rebalance(node.lefChild);
                    return result;
                } else {
                    node.lefChild = new Node();
                    node.lefChild.color = Color.RED;
                    node.lefChild.value = value;
                    return true;
                }
            } else {
                if (node.rightChild != null) {
                    boolean result = addNode(node.rightChild, value);
                    node.rightChild = rebalance (node.rightChild);
                    return result;
                } else {
                    node.rightChild = new Node();
                    node.rightChild.color = Color.RED;
                    node.rightChild.value = value;
                    return true;
                }
            }
        }
    }

    private Node rebalance(Node node){
       Node result = node;
       boolean needRebalance;
       do {
           needRebalance = false;
           if (result.rightChild != null && result.rightChild.color == Color.RED &&
                   (result.lefChild == null || result.lefChild.color == Color.BLACK)) {
               needRebalance = true;
               result = rightSwap(result);
           }
           if (result.lefChild != null && result.lefChild.color == Color.RED &&
                    result.lefChild.lefChild != null && result.lefChild.lefChild.color == Color.RED){
               needRebalance = true;
               result = leftSwap(result);
           }
           if (result.lefChild != null && result.lefChild.color == Color.RED &&
                    result.rightChild != null && result.rightChild.color == Color.RED){
               needRebalance = true;
               colorSwap(result);
           }
       }
       while (needRebalance);
       return result;
    }
    private Node rightSwap(Node node){
        Node rightChild = node.rightChild;
        Node betweenChild = rightChild.lefChild;
        rightChild.lefChild = node;
        node.rightChild = betweenChild;
        rightChild.color = node.color;
        node.color = Color.RED;
        return rightChild;
    }
    private Node leftSwap(Node node){
        Node leftChild = node.lefChild;
        Node betweenChild = leftChild.rightChild;
        leftChild.rightChild = node.lefChild;
        node.lefChild = betweenChild;
        leftChild.color = node.color;
        node.color = Color.RED;
        return leftChild;
    }
    private void colorSwap(Node node){
        node.rightChild.color = Color.BLACK;
        node.lefChild.color = Color.BLACK;
        node.color = Color.RED;
    }
    private class Node{
        private int value;
        private Color color;
        private Node lefChild;
        private Node rightChild;

        @Override
        public String toString() {
            return "Node {" +
                    "value" + value +
                    ", color=" + color +
                    "}";
        }
    }
    private enum Color{
        RED, BLACK
    }
}
