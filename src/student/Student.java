package student;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import idea.Idea;
import idea.IdeaQueue;

public class Student implements java.io.Serializable{
    private String name;
    private String email;
    private int SSN;
    private int ID;
    private Student SSNLeft;
    private Student SSNRight;
    private Student IDLeft;
    private Student IDRight;
    
    private IdeaQueue ideas;  // Q w/ last 10 ideas
    private double avgRating; // for Student's last 10 ideas

    public Student() {
	this.ideas = new IdeaQueue();
    }

    public Student getSSNLeft(){
        return SSNLeft;
    }

    public void setSSNLeft(Student left){
        this.SSNLeft = left;
    }

    public Student getIDLeft(){
        return IDLeft;
    }

    public void setIDLeft(Student left){
        this.IDLeft = left;
    }

    public Student getSSNRight(){
        return SSNRight;
    }

    public void setSSNRight(Student right){
        this.SSNRight = right;
    }

    public Student getIDRight(){
        return IDRight;
    }

    public void setIDRight(Student right){
        this.IDRight = right;
    }

    public String getName() {
	    return this.name;
    }

    public void setName(String s) {
	    this.name = s;
    }

    public String getEmail() {
	    return this.email;
    }

    public void setEmail(String s) {
        Pattern pattern = Pattern.compile("\b[abcdefghijklmnopqrstuvwxyx]+|\b[0-9]+@\b[abcdefghijklmnopqrstuvwxyz]+|\b[0-9]+..\b[edu]");
        Matcher matcher = pattern.matcher(s);
        boolean matchFound = matcher.find();
        if (matchFound) { // vet for valid email
	        this.email = s;
	    } else {
	        throw new IllegalArgumentException("Please enter a valid email.");
	    }
    }

    public int getSSN() {
	    return this.SSN % 10000;
    }

    public void setSSN(int i) {
	    this.SSN = i;
    }

    public int getID() {
	    return this.ID;
    }

    public void setID(int i) {
	    this.ID = i;
    }

    public IdeaQueue getIdeas() {
        return ideas;
    }

    public void addIdea(Idea x) {
        ideas.enqueue(x);
        recalculateAvgRating();
    }

    public double getAvgRating() {
        return this.avgRating;
    }

    // Helper method to recalc the avg (use when idea adding/amount is updated)
    public void recalculateAvgRating() {
        double total = 0.0;
        int count = 0;

        // sum each idea rating in ideaQ
        for (int i = 0; i < ideas.getSize(); i++) { 
            Idea idea = ideas.get(i); 
            if (idea != null) { // Check for non-null values
                total += idea.getRating();
                count++;
            }
        }
        // Ternary operator to calc avg rating & avoid division by 0
        this.avgRating = (ideas.isEmpty()) ? 0.0 : total / ideas.getSize();
    }

}
