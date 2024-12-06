package student;

public class SSNBST implements java.io.Serializable{
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
        return searchR(currNode.getSSNLeft(), key);
      } else {
        return searchR(currNode.getSSNRight(), key);
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
        if (currNode.getSSNRight() == null){
          currNode.setSSNRight(newNode);
        } else {
          insertR(currNode.getSSNRight(), newNode);
        }
      } else {
        if (currNode.getSSNLeft() == null){
          currNode.setSSNLeft(newNode);
        } else {
          insertR(currNode.getSSNLeft(), newNode);
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
      if (delNode.getSSN() < pNode.getSSN() && pNode.getSSNLeft() != null){
        if (pNode.getSSNLeft().getSSN() == delNode.getSSN()){
          pNode.setSSNLeft(deleteRoot(pNode.getSSNLeft()));
          pNode.getSSNLeft();
        } else {
          deleteR(pNode.getSSNLeft(), delNode);
        }
      } else if (delNode.getSSN() > pNode.getSSN() && pNode.getSSNRight() != null){
        if (pNode.getSSNRight().getSSN() == delNode.getSSN()){
          pNode.setSSNRight(deleteRoot(pNode.getSSNRight()));
          pNode.getSSNRight();
        } else {
          deleteR(pNode.getSSNRight(), delNode);
        }
      }
      return pNode;
    }
    
    //delete remove node recursion
    private Student deleteRoot(Student root){
      if (root.getSSNRight() == null && root.getSSNLeft() == null){
        return null;
      } else if (root.getSSNRight() == null && root.getSSNLeft() != null){
        return root.getSSNLeft();
      } else if (root.getSSNRight() != null && root.getSSNLeft() == null){
        return root.getSSNRight();
      } else {
        Student successor = root.getSSNRight();
        Student successorParent = root;
         
        while (successor.getSSNLeft() != null){
          successorParent = successor;
          successor = successor.getSSNLeft();
        }
       
        if (successorParent == root){
          successor.setSSNLeft(root.getSSNLeft());
          successor.setSSNRight(null);
        } else {
          successorParent.setSSNLeft(deleteRoot(successor));
          successor.setSSNRight(root.getSSNRight());
          successor.setSSNLeft(root.getSSNLeft());
        }
        
        root.setSSNLeft(null);
        root.setSSNRight(null);
       
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
        traverseR(currNode.getSSNLeft());
        System.out.print(currNode.getSSN() + " ");
        traverseR(currNode.getSSNRight());
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
        if (tree.getSSNLeft() != null){
          System.out.print("Left: " + tree.getSSNLeft().getSSN() + " ");
        } else {
          System.out.print("Left: null ");
        }
        if (tree.getSSNRight() != null){
          System.out.println("Right: " + tree.getSSNRight().getSSN() + " ");
        } else {
          System.out.println("Right: null ");
        }
        printTree2(tree.getSSNLeft());
        printTree2(tree.getSSNRight());
      }
    }
}
