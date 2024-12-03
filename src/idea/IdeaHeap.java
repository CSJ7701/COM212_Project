package idea;

public class IdeaHeap{
    private Idea[] heap = new Idea[120];
    private int n = 0;
    
    //isEmptySet function
    public boolean isEmptySet(){
      return n == 0;
    }

    public int length() {
	return n;
    }
    
    //findMin function
    public int findMax(){
      return heap[0].getID();
    }

    public Idea getMax() {
	return heap[0];
    }

        // Find function
    public Idea find(int id) {
        for (int i = 0; i < n; i++) {
            if (heap[i].getID() == id) {
                return heap[i];
            }
        }
        return null; // Not found
    }
    
    // Delete function
    public void delete(int id) {
        int index = -1;
        
        // Find the index of the idea to delete
        for (int i = 0; i < n; i++) {
            if (heap[i].getID() == id) {
                index = i;
                break;
            }
        }
        
        if (index == -1) {
            throw new IllegalArgumentException("Idea with ID " + id + " not found.");
        }
        
        // Replace with the last element and reduce size
        n--;
        swap(index, n);
        
        // Restore heap property
        int parent = (index - 1) / 2;
        if (index > 0 && heap[index].getID() > heap[parent].getID()) {
            // Trickle up
            while (index > 0 && heap[index].getID() > heap[parent].getID()) {
                swap(index, parent);
                index = parent;
                parent = (index - 1) / 2;
            }
        } else {
            // Trickle down
            trickle(index, ((2*index)+1), ((2*index)+2));
        }
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

