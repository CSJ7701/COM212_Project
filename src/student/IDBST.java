package student;

public class IDBST implements java.io.Serializable{
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
      } else if (key == currNode.getID()){
        return currNode;
      } else if (key < currNode.getID()){
        return searchR(currNode.getIDLeft(), key);
      } else {
        return searchR(currNode.getIDRight(), key);
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
      if (currNode.getID() < newNode.getID()){
        if (currNode.getIDRight() == null){
          currNode.setIDRight(newNode);
        } else {
          insertR(currNode.getIDRight(), newNode);
        }
      } else {
        if (currNode.getIDLeft() == null){
          currNode.setIDLeft(newNode);
        } else {
          insertR(currNode.getIDLeft(), newNode);
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
      if (delNode.getID() < pNode.getID() && pNode.getIDLeft() != null){
        if (pNode.getIDLeft().getID() == delNode.getID()){
          pNode.setIDLeft(deleteRoot(pNode.getIDLeft()));
          pNode.getIDLeft();
        } else {
          deleteR(pNode.getIDLeft(), delNode);
        }
      } else if (delNode.getID() > pNode.getID() && pNode.getIDRight() != null){
        if (pNode.getIDRight().getID() == delNode.getID()){
          pNode.setIDRight(deleteRoot(pNode.getIDRight()));
          pNode.getIDRight();
        } else {
          deleteR(pNode.getIDRight(), delNode);
        }
      }
      return pNode;
    }
    
    //delete remove node recursion
    private Student deleteRoot(Student root){
      if (root.getIDRight() == null && root.getIDLeft() == null){
        return null;
      } else if (root.getIDRight() == null && root.getIDLeft() != null){
        return root.getIDLeft();
      } else if (root.getIDRight() != null && root.getIDLeft() == null){
        return root.getIDRight();
      } else {
        Student successor = root.getIDRight();
        Student successorParent = root;
         
        while (successor.getIDLeft() != null){
          successorParent = successor;
          successor = successor.getIDLeft();
        }
       
        if (successorParent == root){
          successor.setIDLeft(root.getIDLeft());
          successor.setIDRight(null);
        } else {
          successorParent.setIDLeft(deleteRoot(successor));
          successor.setIDRight(root.getIDRight());
          successor.setIDLeft(root.getIDLeft());
        }
        
        root.setIDLeft(null);
        root.setIDRight(null);
       
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
        traverseR(currNode.getIDLeft());
        System.out.print(currNode.getID() + " ");
        traverseR(currNode.getIDRight());
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
        System.out.print(tree.getID() + " ");
        if (tree.getIDLeft() != null){
          System.out.print("Left: " + tree.getIDLeft().getID() + " ");
        } else {
          System.out.print("Left: null ");
        }
        if (tree.getIDRight() != null){
          System.out.println("Right: " + tree.getIDRight().getID() + " ");
        } else {
          System.out.println("Right: null ");
        }
        printTree2(tree.getIDLeft());
        printTree2(tree.getIDRight());
      }
    }
}
