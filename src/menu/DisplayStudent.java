package menu;

import student.Student;
import student.SSNBST;
import student.IDBST;
import idea.IdeaQueue;
import idea.IdeaHeap;

public class DisplayStudent extends Menu {
    private Student student;
    private SSNBST SSNbst;
    private IDBST IDbst;
    private IdeaHeap ideaHeap;

    public DisplayStudent(Student student, SSNBST ssnbst, IDBST idbst, IdeaHeap ideaHeap) {
	super();
	this.student = student;
	this.IDbst = idbst;
	this.SSNbst = ssnbst;
	this.ideaHeap = ideaHeap;
	setTopText("Viewing Student: " + student.getName());

	// Add menu options
	addItem("Edit Student", this::editStudent);
	addItem("Delete Student", this::deleteStudent);
	addItem("Browse This Student's Ideas", this::browseIdeas);
	addItem("Quit", this::quit);
    }

    @Override
    protected void displayMenu() {
	clearScreen();
	printBorder();

	// Display Student Details
	centerText("Student Details");
	System.out.println();
	centerText("Name: " + student.getName() + "\n");
	centerText("Email: " + student.getEmail() + "\n");
	centerText("SSN: " + student.getSSN() + "\n");
	centerText("Student ID: " + student.getID() + "\n");
	System.out.println("\n");
	centerText(" --- ");
	System.out.println("\n");

	printItems();
	printBorder();
	centerText("Enter your choice: ");
    }

    private void browseIdeas() {
	IdeaQueue ideas = this.student.getIdeas();
	studentIdeasMenu ideaBrowse = new studentIdeasMenu(ideas, this.ideaHeap);
	ideaBrowse.start();
	quit();
    }
	

    private void editStudent() {
	clearScreen();
	centerText("==================\n");
	centerText("Editing Student\n");

	// Update name
	centerText("Enter a new name (leave blank to skip): ");
	String newName = scanner.nextLine();
	if (!newName.isBlank()) {
	    student.setName(newName);
	}

	// Update Email
	centerText("Enter a new email (leave blank to skip): ");
	String newEmail = scanner.nextLine();
	if (!newEmail.isBlank()) {
	    student.setEmail(newEmail);
	}

	// Update SSN
	centerText("Enter a new SSN (leave blank to skip): ");
	String newSSN = scanner.nextLine();
	if (!newSSN.isBlank()) {
	    try {
		int newSSN_int = Integer.parseInt(newSSN);
		student.setSSN(newSSN_int);
	    } catch (NumberFormatException e) {
		centerText("Invalid SSN. Skipping update.");
		System.out.println();
	    }
	}

	centerText("Enter a new ID (leave blank to skip): ");
	String newID = scanner.nextLine();
	if (!newID.isBlank()) {
	    try {
		int newID_int = Integer.parseInt(newID);
		student.setID(newID_int);
	    } catch (NumberFormatException e) {
		centerText("Invalid ID. Skipping Update.");
		System.out.println();
	    }
	}
    }

    private void deleteStudent() {
	clearScreen();
	System.out.println("\n");
	printBorder();
	centerText("Are you sure you want to delete this idea? (yes/no): ");
	String confirm = scanner.nextLine();
	if (confirm.equalsIgnoreCase("yes")) {
	    SSNbst.delete(this.student);
	    IDbst.delete(this.student);
	    System.out.println("\n");
	    printBorder();
	    centerText("Student deleted. Press enter to exit.");
	    scanner.nextLine();
	    quit();
	} else {
	    System.out.println("\n");
	    printBorder();
	    System.out.println("\n");
	    centerText("Deletion cancelled. Press enter to return to menu.");
	    scanner.nextLine();
	}
    }
		
	    
}