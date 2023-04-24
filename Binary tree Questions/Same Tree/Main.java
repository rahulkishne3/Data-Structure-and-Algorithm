import java.io.*;
import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

class Main {

  static Node buildTree(String str) {
    if (str.length() == 0 || str.charAt(0) == 'N') {
      return null;
    }
    String ip[] = str.split(" ");
    Node root = new Node(Integer.parseInt(ip[0]));
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    int i = 1;
    while (queue.size() > 0 && i < ip.length) {
      Node currNode = queue.peek();
      queue.remove();
      String currVal = ip[i];
      if (!currVal.equals("N")) {
        currNode.left = new Node(Integer.parseInt(currVal));
        queue.add(currNode.left);
      }
      i++;
      if (i >= ip.length) break;
      currVal = ip[i];
      if (!currVal.equals("N")) {
        currNode.right = new Node(Integer.parseInt(currVal));
        queue.add(currNode.right);
      }
      i++;
    }

    return root;
  }

  void inOrder(Node node) {
    if (node == null) {
      return;
    }
    inOrder(node.left);
    System.out.print(node.data + " ");
    inOrder(node.right);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s1 = br.readLine();
    Node root1 = buildTree(s1);
    String s2 = br.readLine();
    Node root2 = buildTree(s2);
    Solution g = new Solution();
    g.areTreesIdentical(root1, root2);
  }
}

class Node {

  int data;
  Node left;
  Node right;

  Node(int data) {
    this.data = data;
    left = null;
    right = null;
  }
}

class Solution {

  public static boolean areTreesIdentical_(Node root1, Node root2) {
    if (root1 == null && root2 == null) {
      return true;
    }
    if (root1 != null && root2 != null) {
      return (
        areTreesIdentical_(root1.left, root2.left) &&
        areTreesIdentical_(root1.right, root2.right)
      );
    }
    return false;
  }

  public static void areTreesIdentical(Node root1, Node root2) {
    //Your code here
    if (areTreesIdentical_(root1, root2) == true) {
      System.out.println("YES");
    } else {
      System.out.println("NO");
    }
  }
}
