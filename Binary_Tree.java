package com.webpage.real_estate.utils;

import com.webpage.real_estate.model.Property;

public class Binary_Tree {
    public Node root;

    public Binary_Tree(){
        this.root = null;
    }

    public void insert(Property p){
        Node new_node = new Node(p);
        if (root == null){
            root = new_node;
        }else{
            Node current = root;
            Node parent = current;
            while (current != null){
                parent = current;
                if ((current.property).getPrice() > p.getPrice()){
                    current = current.left;
                }else{
                    current = current.right;
                }
            }
            if ((parent.property).getPrice() > p.getPrice()){
                parent.left = new_node;
            }else{
                parent.right = new_node;
            }
        }
    }

    public boolean search(Property p){
        if (root == null){
            Node current = root;
            boolean found = false;
            while (current != null){
                if (p.getPrice() == ((current.property).getPrice())){
                    found = true;
                }else if (p.getPrice() > current.property.getPrice()){
                    current = current.left;
                }else {
                    current = current.right;
                }
            }
            return found;
        }
        return false;
    }

    public Node delete(Node root, double price){
        if (root == null){
            return null;
        }
        if ((root.property).getPrice() > price){
            root.left = delete(root.left, price);
        }else if ((root.property).getPrice() < price){
            root.right = delete(root.right, price);
        }else {
            if (root.left == null){
                return root.right;
            }

            if (root.right == null){
                return root.left;
            }

            Node successor = getSuccessor(root);
            root.property = successor.property;
            root.right = delete(root.right, (successor.property).getPrice());
        }
        return root;
    }

    static Node getSuccessor(Node curr) {
        curr = curr.right;
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    /* Not used in the program, for reference created the transverse of the tree
    public void Trans_inOrder(Node localNode){
        if (localNode != null){
            Trans_inOrder(localNode.left);
            localNode.displayNode();
            Trans_inOrder(localNode.right);
        }
    }

    public void Trans_preOrder(Node localNode){
        if (localNode != null){
            localNode.displayNode();
            Trans_preOrder(localNode.left);
            Trans_preOrder(localNode.right);
        }
    }

    public void Trans_postOrder(Node localNode){
        if (localNode != null){
            Trans_postOrder(localNode.left);
            Trans_postOrder(localNode.right);
            localNode.displayNode();
        }
    }
    public void inOrder(){
        Trans_inOrder(root);
    }

    public void preOrder(){
        Trans_preOrder(root);
    }

    public void postOrder(){
        Trans_postOrder(root);
    }
    */


}

class Node{
    Property property;
    Node left;
    Node right;

    public Node(Property property) {
        this.property = property;
        this.left = null;
        this.right = null;
    }

    void displayNode(){
        System.out.println(property.getTitle());
        System.out.println(property.getPrice());
    }
}