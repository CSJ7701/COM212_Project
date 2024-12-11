package idea;

public class IdeaQueue implements java.io.Serializable{
    private Idea[] ideaQ = new Idea[10];
    private int front = 0;
    private int n = 0;
  
    //isEmpty method
    public boolean isEmpty(){
        return n == 0;
    }
  
    //front method
    public Idea front(){
        return ideaQ[front];
    }
  
    //dequeue method
    public Idea dequeue(){
        int temp = front;
        front = (front + 1) % 10;
        n = n - 1;
        return ideaQ[temp];
    }
  
    //enqueue method
    public void enqueue(Idea x) {
	if (n == 10) {
	    dequeue();
	}
        int tail = (front + n) % 10;
        ideaQ[tail] = x;
        n = n + 1;
    }

    public void delete(int id) { // Runs in O(n) time...
	// Find the index of the idea with the matching ID
	int indexToDelete = -1;
	for (int i = 0; i < n; i++) {
	    int actualIndex = (front + i) % 10;
	    if (ideaQ[actualIndex].getID() == id) {
		indexToDelete = actualIndex;
		break;
	    }
	}

	// If not found, return
	if (indexToDelete == -1) {
	    System.out.println("ID not found in the queue.");
	    return;
	}

	// Shift elements to remove the idea
	for (int i = indexToDelete; i != (front + n - 1) % 10; i = (i + 1) % 10) {
	    ideaQ[i] = ideaQ[(i + 1) % 10];
	}

	// Decrement n and update the tail
	n--;
	ideaQ[(front + n) % 10] = null; // Clear the last element
    }


    // returns # of ideas in IdeaQueue
    public int getSize() {
        return n;
    }

    // returns Idea at specified index in the IdeaQueue
    public Idea get(int index) {
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return ideaQ[index]; 
    }

}

