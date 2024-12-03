package student;

public class SSNBST {
    private Student root = null;
    
    //isEmptyTree function
    public boolean isEmptyTree(){
      return root == null;
    }
   
    //search function
    public Student search(int key){
      return searchR(root, key);
    }
   
    //search recursion
    private Student searchR(Student currNode, int key){
      if (currNode == null){
        return null;
      } else if (key == currNode.getSSN()){
        return currNode;
      } else if (key < currNode.getSSN()){
        return searchR(currNode.getLeft(), key);
      } else {
        return searchR(currNode.getRight(), key);
      }
    }
    
    //insert function
    public void insert(Student node){
      if (root == null) {
        root = node;
      } else {
        insertR(root, node);
      }
    }
    
    //insert recursion
    private void insertR(Student currNode, Student newNode){
      if (currNode.getSSN() < newNode.getSSN()){
        if (currNode.getRight() == null){
          currNode.setRight(newNode);
        } else {
          insertR(currNode.getRight(), newNode);
        }
      } else {
        if (currNode.getLeft() == null){
          currNode.setLeft(newNode);
        } else {
          insertR(currNode.getLeft(), newNode);
        }
      }
    }
    
    //delete function
    public void delete(Student node){
      if (root != null){
        if (root == node){
          root = deleteRoot(root);
        } else {
          root = deleteR(root, node);
        }
      }
    }
    
    //delete find node recursion
    private Student deleteR(Student pNode, Student delNode){
      if (delNode.getSSN() < pNode.getSSN() && pNode.getLeft() != null){
        if (pNode.getLeft().getSSN() == delNode.getSSN()){
          pNode.setLeft(deleteRoot(pNode.getLeft()));
          pNode.getLeft();
        } else {
          deleteR(pNode.getLeft(), delNode);
        }
      } else if (delNode.getSSN() > pNode.getSSN() && pNode.getRight() != null){
        if (pNode.getRight().getSSN() == delNode.getSSN()){
          pNode.setRight(deleteRoot(pNode.getRight()));
          pNode.getRight();
        } else {
          deleteR(pNode.getRight(), delNode);
        }
      }
      return pNode;
    }
    
    //delete remove node recursion
    private Student deleteRoot(Student root){
      if (root.getRight() == null && root.getLeft() == null){
        return null;
      } else if (root.getRight() == null && root.getLeft() != null){
        return root.getLeft();
      } else if (root.getRight() != null && root.getLeft() == null){
        return root.getRight();
      } else {
        Student successor = root.getRight();
        Student successorParent = root;
         
        while (successor.getLeft() != null){
          successorParent = successor;
          successor = successor.getLeft();
        }
       
        if (successorParent == root){
          successor.setLeft(root.getLeft());
          successor.setRight(null);
        } else {
          successorParent.setLeft(deleteRoot(successor));
          successor.setRight(root.getRight());
          successor.setLeft(root.getLeft());
        }
        
        root.setLeft(null);
        root.setRight(null);
       
        return successor;
      }
    }
    
    //traverse function
    public void traverse(){
      traverseR(root);
      System.out.println("");
    }
    
    //traverse recursion
    private void traverseR(Student currNode){
      if (currNode != null){
        traverseR(currNode.getLeft());
        System.out.print(currNode.getSSN() + " ");
        traverseR(currNode.getRight());
      }
    }
  
    //printTree function
    public void printTree() {
      printTree2(root);
      System.out.println();
    }
    
    //printTree recursion
    private void printTree2(Student tree) {
      if (tree != null) {
        System.out.print(tree.getSSN() + " ");
        if (tree.getLeft() != null){
          System.out.print("Left: " + tree.getLeft().getSSN() + " ");
        } else {
          System.out.print("Left: null ");
        }
        if (tree.getRight() != null){
          System.out.println("Right: " + tree.getRight().getSSN() + " ");
        } else {
          System.out.println("Right: null ");
        }
        printTree2(tree.getLeft());
        printTree2(tree.getRight());
      }
    }
}
