package bst;

import java.util.ArrayList;
import java.util.Scanner;

class Node{
    int data;
    Node left;
    Node right;

    public String toString(){
        return Integer.toString(data);
    }
    public boolean estFeuille(){
        if(this.right == null && this.left == null){
            return true;
        }
        return false;
    }
}

class BTS{
    private Node createNewNode(int k) {
        Node a = new Node();
        a.data = k;
        a.left = a.right = null;
        return a;
    }

    public void AfficheRacine(Node node){
        System.out.print(node.data + ", ");
    }

    public void infixe(Node node){
        if(node != null){
            infixe(node.left);
            AfficheRacine(node);
            infixe(node.right);
        }
    }

    public void prefixe(Node node){
        if (node != null){
            AfficheRacine(node);
            prefixe(node.left);
            prefixe(node.right);
        }
    }
    public Node insert(Node node, int val){
        if (node == null){
            return createNewNode(val);
        }
        if (val < node.data){
            node.left = insert(node.left, val);
        } else if (val > node.data){
            node.right = insert(node.right, val);
        }
        return node;
    }


}

public class BSTApp {

    public static String affiche(Node node){

        if (node.left != null && node.right != null) {
            if (node.left.estFeuille() && node.right.estFeuille()) {
                return "(" + node.toString() + ",(" + node.left.toString() + "," + node.right.toString() + ")";
            } else {
                return "(" + node.toString() + ",(" + affiche(node.left) + "," + affiche(node.right) + "))";
            }
        }else if(node.left == null & node.right != null){
            return "(" + node.toString() +"," +"(-," + affiche(node.right)+"))";
        }else if(node.left != null & node.right == null){
            return "(" + node.toString() +",(" + affiche(node.left)+",-))";
        }else{
            return node.toString() ;
        }


    }

    public static Node remplir(){
        Scanner scan = new Scanner(System.in);
        Node node = null;
        BTS B = new BTS();
        System.out.print("Donner le nombre des elements de l'arbre: ");
        int n = scan.nextInt();
        for(int i = 0; i<n ; i++){
            System.out.print("Donner l'element Num "+ (i+1)+" : ");
            node = B.insert(node, scan.nextInt());;
        }
        return node;
    }

    public static Node insertion(Node node){
        Scanner scan = new Scanner(System.in);
        BTS B = new BTS();
        System.out.print("Donner l'element a inserer: ");
        node = B.insert(node, scan.nextInt());;
        return node;
    }

    public static Node supprimer(Node node, int val){
        if (node == null){
            System.out.println("l'element n'existe pas!");
        }else{
            if (node.data > val){
                node.left = supprimer(node.left, val);
            }else{
                if (node.data < val) {
                    node.right =supprimer(node.right, val);
                }else{
                    if (node.estFeuille()){
                        node = null;
                    }else{
                        if (node.left != null && node.right == null){
                            node = node.left;
                        }else{
                            if (node.left == null && node.right != null){
                                node = node.right;
                            }else{
                                node.data = pPSAD(node.right);
                                node.right =supprimer(node.right, node.data);
                            }
                        }
                    }
                }
            }
        }
        return node;
    }// supprimer

    public static int pPSAD(Node node){
        if (node.left == null){
            return node.data;
        }else{
            return pPSAD(node.left);
        }
    }




    public static void main(String[] args) {
        BTS a = new BTS();
        Node root = null;
        System.out.println("Remplissage de l'arbre: ");
        root  = remplir();


//        root = a.insert(root, 10);
//        root = a.insert(root, 4);
//        root = a.insert(root, 7);
//        root = a.insert(root, 1);
//        root = a.insert(root, 14);
//        root = a.insert(root, 13);
//        System.out.print("infixe: ");
//        a.infixe(root);
//        System.out.println();
//        System.out.print("préfixe: ");
//        a.prefixe(root);
        boolean test = true;
        while(test){
            System.out.println("Entrer un numéro:");
            System.out.println("1. Insérer un élement");
            System.out.println("2. Afficher l'arbre");
            System.out.println("3. Supprimer un élement");
            System.out.println("0. Exit");
            Scanner scan = new Scanner(System.in);
            int i = scan.nextInt();
            switch (i){
                case 0:
                    test = false;
                    break;
                case 1:
                    root = insertion(root);
                    break;
                case 2:
                    System.out.println(affiche(root));
                    break;
                case 3:
                    System.out.print("Donner l'élement à supprimer: ");
                    root = supprimer(root, scan.nextInt());
                    break;

            }
        }

    }
}
