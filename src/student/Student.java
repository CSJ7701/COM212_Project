package student;

import idea.Idea;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {
    private String name;
    private String email;
    private int SSN;
    private int ID;

    public void getName() {
	return this.name;
    }

    public void setName(String s) {
	this.name = s;
    }

    public void getEmail() {
	return this.email;
    }

    public void setEmail(String s) {
	Pattern pattern = Pattern.compile("\w+@\w+\.\w+");
	Matcher matcher = pattern.matcher(s);
	boolean matchFound = matcher.find();
	if (matchFound) {
	    this.email = s;
	} else {
	    throw new ValueException("Please enter a valid email.");
	}
    }

    public void getSSN() {
	return this.SSN % 10000;
    }

    public void setSSN(int i) {
	this.SSN = i;
    }

    public void getID() {
	return this.ID;
    }

    public void setID(int i) {
	this.ID = i;
    }

}
