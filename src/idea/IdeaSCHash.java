package idea;

public class IdeaSCHash {
    private Idea[] hash = new Idea[9];
    private int n = 0;
    
    //isSetEmpty function
    public boolean isSetEmpty(){
      return n == 0;
    }
    
    //insert function
    public void insert(Idea newIdea){
      int index = newIdea.getID() % 9;
      if (hash[index] != null){
        newIdea.setNext(hash[index].getNext());
        hash[index].setNext(newIdea);
      } else {
        hash[index] = newIdea;
      }
      n = n + 1;
    }
    
    //delete function
    public void delete(Idea idea){
      int index = idea.getID() % 9;
      if (hash[index].getID() == idea.getID()){
        hash[index] = hash[index].getNext();
      } else {
        Idea temp = hash[index].getNext();
        Idea proir = hash[index];
        while (temp != null){
          if (temp.getID() == idea.getID()){
            proir.setNext(temp.getNext());
            temp.setNext(null);
            break;
          }
          proir = temp;
          temp = temp.getNext();
        }
      }
      n = n - 1;
    }
    
    //lookUp function
    public Idea lookUp(Idea node){
      int index = node.getID() % 9;
      if (hash[index].getID() == node.getID()){
        return hash[index];
      } else {
        Idea temp = hash[index].getNext();
        while (temp != null){
          if (temp.getID() == node.getID()){
            return temp;
          }
          temp = temp.getNext();
        }
      }
      return null;
    }
    
    //print function
    public void print(){
      System.out.println("\nHash Table");
      for (int i = 0; i < 9; ++i){
        if (hash[i] == null){
          System.out.print("");
        } else {
          System.out.print("\n" + hash[i].getID() + " ");
          Idea temp = hash[i].getNext();
          while (temp != null){
            System.out.print(temp.getID() + " ");
            temp = temp.getNext();
          }
        }
      }
      System.out.println("\n");
    }
  }
