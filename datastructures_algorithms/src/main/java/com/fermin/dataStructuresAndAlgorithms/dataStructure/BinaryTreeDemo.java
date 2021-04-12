package cn.fermin.dataStructuresAndAlgorithms.dataStructure;

/**
 * 二叉树
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {

    }
}

class BinaryTree {
    public Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root);
        if (this.root.getLeft() != null) {
//            this.root.getLeft().
        }

    }

    public void midOrder() {

    }

    public void postOrder() {

    }

    public void levelOrder() {

    }

}

/**
 * 节点
 */
class Node {
    private Integer id;

    private String name;

    private Node left;

    private Node right;

    public Node(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    @Override
    public String toString() {
        return String.format("");
    }
}
