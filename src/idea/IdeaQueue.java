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
    public void enqueue(Idea x){
        int tail = (front + n) % 10;
        ideaQ[tail] = x;
        n = n + 1;
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

