package idea;

public class Idea {
    private int ideaID;
    private int submittersSSN;
    private String ideaDescription;
    private int ideaRating;
    private Idea next;

    //Constructor
    public Idea(int ideaID, int submittersSSN, String ideaDescription, int ideaRating){
        this.ideaID = ideaID;
        this.submittersSSN = submittersSSN;
        this.ideaDescription = ideaDescription;
        this.ideaRating = ideaRating;
        next = null;
    }

    public Idea() {
    }


    //Getters
    public int getID(){
        return ideaID;
    }

    public int getSubmitterSSN(){
        return submittersSSN;
    }

    public String getDescription(){
        return ideaDescription;
    }

    public int getRating(){
        return ideaRating;
    }

    public Idea getNext(){
        return next;
    }

    //Setters
    public void setID(int newID){
        ideaID = newID;
    }

    public void setSubmitterSSN(int newSSN){
        submittersSSN = newSSN;
    }

    public void setDescription(String newDescription){
        ideaDescription = newDescription;
    }

    public void setRating(int newRating){
        ideaRating = newRating;
    }

    public void setNext(Idea idea){
        next = idea;
    }
}
