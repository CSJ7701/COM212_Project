package student;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import idea.Idea;
import idea.IdeaQueue;

public class Student {
    private String name;
    private String email;
    private int SSN;
    private int ID;
    private IdeaQueue ideas;  // Q w/ last 10 ideas
    private double avgRating; // for Student's last 10 ideas

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
        if (matchFound) { // vet if valid email
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

    public Idea[] getIdeas() {
        if (ideas == null || ideas.length == 0) {
            return new Idea[0]; //return empty array if no ideas
        }

        if (ideas.length <= 10) {
            return ideas;
        }

        // Else just return last 10 ideas
        return Arrays.copyOfRange(ideas, ideas.length - 10, ideas.length); 
    }

    public void addIdea(String ideaText, double rating) {
        if (rating < 0 || rating > 100) {
            throw new IllegalArgumentException("Rating must be between 0 and 100.");
        }

        // Add the new idea and remove the oldest if the size exceeds 10
        if (ideas.length == 10) {
            //remove oldest idea from `ideas`
        }

        // in `ideas` arr, add a new Idea(ideaID, submittersSSN, ideaDescription, ideaRating)

        recalculateAvgRating();
    }

    public double getAvgRating() {
        return this.avgRating;
    }

    // Helper method to recalc the avg (use when idea adding/amount is updated)
    public void recalculateAvgRating() {
        double total = 0.0;
        for (Idea idea : ideas) {
            total += idea.getRating();
        }

        // Ternary operator to assign avg rating
        this.avgRating = (ideas.length == 0) ? 0.0 : total / ideas.length;
    }

}