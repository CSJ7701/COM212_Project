package idea;

public class IdeaHeap{
    private Idea[] heap = new Idea[120];
    private int n = 0;
    
    //isEmptySet function
    public boolean isEmptySet(){
      return n == 0;
    }
    
    //findMin function
    public int findMax(){
      return heap[0].getID();
    }
    
    //insert function
    public void insert(Idea newIdea){
      heap[n] = newIdea;
      n = n + 1; 
      
      if (n != 1){
        //reorder
        int cIndex = n - 1;
        int pIndex;
      
        pIndex = (cIndex - 1) / 2;
      
        while (heap[pIndex].getID() < heap[cIndex].getID()){
          swap(pIndex, cIndex);
        
          cIndex = pIndex;
          pIndex = (cIndex - 1) / 2;
        }
      }
    }
    
    //deleteMin function
    public void deleteMax(){
      n = n - 1;
      swap(0, n);
      
      trickle(0, 1, 2);
    }
    
    //trickle recursion to reorder
    private void trickle(int parent, int child1, int child2){
      //test if parent two children, one child, or no children
      if ((child1 <= (n - 1)) && (child2 <= (n - 1))){
        //test if both children are greater than parent
        if ((heap[parent].getID() < heap[child1].getID()) && (heap[parent].getID() < heap[child2].getID())){
          //swap bigger child
          if (heap[child1].getID() > heap[child2].getID()){
              swap(parent, child1);
              trickle(child1, ((2 * child1) + 1), ((2 * child1) + 2));
          } else if (heap[child2].getID() > heap[child1].getID()){
              swap(parent, child2);
              trickle(child2, ((2 * child2) + 1), ((2 * child2) + 2));
          }
        //test which child is bigger and which is smaller than parent
        } else if ((heap[parent].getID() < heap[child1].getID()) && (heap[parent].getID() > heap[child2].getID())){
            swap(parent, child1);
            trickle(child1, ((2 * child1) + 1), ((2 * child1) + 2));
        } else if ((heap[parent].getID() < heap[child2].getID()) && (heap[parent].getID() > heap[child1].getID())){
            swap(parent, child2);
            trickle(child2, ((2 * child2) + 1), ((2 * child2) + 2));
        }
      } else if ((child1 <= (n - 1)) && (child2 > (n - 1))){
        //swap child1 becuase it's an only child
        if (heap[parent].getID() < heap[child1].getID()){
          swap(parent, child1);
          trickle(child1, ((2 * child1) + 1), ((2 * child1) + 2));
        }
      }
    }
    
    //swap private function
    private void swap(int x, int y){
      Idea temp = heap[x];
      heap[x] = heap[y];
      heap[y] = temp;
    }
    
    //print function
    public void print(){
      System.out.println("");
      for (int i = 0; i < n; i++){
        System.out.print(heap[i].getID() + " ");
      }
      System.out.println("\n");
    }
  }

