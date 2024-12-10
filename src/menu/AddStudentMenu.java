package menu;

import student.Student;
import student.SSNBST;
import student.IDBST;

public class AddStudentMenu extends Menu {
    private Student student;
    private SSNBST SSNbst;
    private IDBST IDbst;
    protected Query[] items;
    private String[] input;

    public AddStudentMenu(SSNBST ssnbst, IDBST idbst) {
	super();
	this.items = new Query[10];
	this.input = new String[10];
	this.itemCount = 0;
	this.student = new Student();
	this.SSNbst = ssnbst;
	this.IDbst = idbst;
	// prompts in yellow:
	addItem("\033[33mName: \033[0m", this.student, "name");
	addItem("\033[33mEmail: \033[0m", this.student, "email");
	addItem("\033[33mSSN: \033[0m", this.student, "SSN");
	addItem("\033[33mStudent ID: \033[0m", this.student, "ID");
    }

    // Most methods will remain the same.
    // There will be no buttons in this menu though; only "Queries"
    // So I need to override the 'addItem', 'printItems', handleInput

    public void addItem(String prompt, Object target, String attribute) {
	if (this.itemCount < 10) {
	    this.items[this.itemCount] = new Query(prompt, target, attribute);
	    this.itemCount++;
	} else {
	    centerText("Cannot add more queries. Max limit reached.\n");
	}
    }

    public Student getStudent() {
	return this.student;
    }

    @Override
    protected void printItems() {
	for (int i=0; i<=this.itemCount; i++) {
	    if (items[i] != null) {
		String info = "";
		if (input[i] != null) {
		    info = " " + input[i] + "\n";
		} else {
		    info = "Not Set...\n";
		}
		centerText((i+1) + ". " + items[i].getPrompt() + info);
	    }
	}
    }

    @Override
    protected void displayMenu() {
	clearScreen();
	printBorder();

	printTop();
	printItems();

	printBorder();
    }
	

    @Override
    protected void handleInput() {
	while (true) {
	    centerText("Type 'EXIT' to quit student creation...\n\n");
	    for (int i=0; i<this.itemCount; i++) {
		Query query = items[i];

		if (query == null) {
		    return;
		}

		while (true) {
		    centerText("Enter " + query.getPrompt());
		    String userInput = scanner.nextLine().trim();

		    if (userInput.equalsIgnoreCase("EXIT")) {
			quit();
			return;
		    }
		
		    if (query.execute(userInput)) {
			input[i] = userInput;
			break;
		    } else {
			centerText("Invalid input. Please try again.\n");
		    }
		}
	    }
	    
	    //	    while (true) {
	    clearScreen();
	    displayMenu();

	    // Check for errors
	    boolean error = false;
	    String errorString = new String();
	    if (this.IDbst.search(this.student.getID()) != null) {
		error = true;
		errorString = "ID. Must be unique";
	    } else if (this.SSNbst.search(this.student.getSSN()) != null) {
		error = true;
		errorString = "SSN. Must be unique";
	    } else if (this.student.getID() > 9999 || this.student.getID() < 1000) {
		error = true;
		errorString = "ID. Must be 4 digits";
	    } else if (this.student.getSSN() > 9999 || this.student.getSSN() < 1000) {
		error = true;
		errorString = "SSN. Must be 4 digits.";
	    }
		


	    // Show error message
	    if (error) {
		clearScreen();
		this.input = new String[10];
		this.student = new Student();
		centerText("\033[33mInvalid " + errorString + ".\n\033[0m");
		centerText("Please try again.\n");
		scanner.nextLine();
		continue;
	    }

	    centerText("Is this information correct? (y/n): ");
	    String confirmation = scanner.nextLine().trim().toLowerCase();


	    if (confirmation.equals("y")) {
		quit();
		return;
	    } else if (confirmation.equals("n")) {
		this.input = new String[10];
		this.student = new Student();
	    } else {
		centerText("Invalid input. Please type 'y' or 'n'.\n");
	    }
	}
    }

}

