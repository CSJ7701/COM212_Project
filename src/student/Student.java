package student;

import idea.Idea;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {
    private String name;
    private String email;
    private int SSN;
    private int ID;

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
	if (matchFound) {
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

}
