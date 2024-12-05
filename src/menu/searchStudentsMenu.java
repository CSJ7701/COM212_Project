package menu;

import student.Student;
import student.IDBST;
import student.SSNBST;
import idea.IdeaHeap;
import menu.DisplayStudent;

public class searchStudentsMenu extends Menu {
    private IDBST IDbst;
    private SSNBST SSNbst;
    private IdeaHeap ideaHeap;
    private Student student;

    public searchStudentsMenu(IDBST idbst, SSNBST ssnbst, IdeaHeap ideaHeap) {
	this.SSNbst = ssnbst;
	this.IDbst = idbst;
	this.ideaHeap = ideaHeap;
	setTopText("Search for a student.\nCan search using SSN or Student ID.");
	addItem("Search with SSN", this::searchSSN);
	addItem("Search with ID", this::searchID);
    }

    
    private void searchSSN() {
	centerText("Please enter the last four SSN digits: ");
	String input = scanner.nextLine();
	try {
	    int ssn = Integer.parseInt(input);
	    Student student = this.SSNbst.search(ssn);
	    if (student == null) {
		centerText("No student found with this SSN. Try again? (y/n): ");
		String retry = scanner.nextLine();
		if (retry.equalsIgnoreCase("y")) {
		    searchSSN();
		} else {
		    quit();
		}
	    } else {
		this.student = student;
		DisplayStudent display = new DisplayStudent(this.student, this.SSNbst, this.IDbst, this.ideaHeap);
		display.start();
		quit();
	    }
	} catch (NumberFormatException e) {
	    centerText("Invalid input. Please try again.\n");
	    scanner.nextLine();
	}
    }

    private void searchID() {
	centerText("Please enter an ID number: ");
	String input = scanner.nextLine();
	try {
	    int id = Integer.parseInt(input);
	    Student student = this.IDbst.search(id);
	    if (student == null) {
		centerText("No student found. Try again? (y/n): ");
		String retry = scanner.nextLine();
		if (retry.equalsIgnoreCase("y")) {
		    searchID();
		} else {
		    quit();
		}
	    } else {
		this.student = student;
		DisplayStudent display = new DisplayStudent(this.student, this.SSNbst, this.IDbst, this.ideaHeap);
		display.start();
		quit();
	    }
	} catch (NumberFormatException e) {
	    centerText("Invalid input. Please try again.\n");
	    scanner.nextLine();
	}
    }

}
