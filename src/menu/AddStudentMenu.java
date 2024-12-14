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
	String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	String numberRegex = "^\\d{4}$"; // Regex to match a 4 digit number - for SSN and ID
	// prompts in yellow:
	addItem("\033[33mName: \033[0m", this.student, "name");
	addItem("\033[33mEmail: \033[0m", this.student, "email", emailRegex, "Not a valid email.");
	addItem("\033[33mSSN: \033[0m", this.student, "SSN", numberRegex, "Not a valid SSN. Must be 4 digits.");
	addItem("\033[33mStudent ID: \033[0m", this.student, "ID", numberRegex, "Not a valid Student ID. Must be 4 digits.");
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

    public void addItem(String prompt, Object target, String attribute, String validationRegex, String errorMessage) {
	if (this.itemCount < 10) {
	    this.items[this.itemCount] = new Query(prompt, target, attribute, validationRegex, errorMessage);
	    this.itemCount++;
	} else {
	    centerText("Cannot add more queries. Max limit reached.\n");
	}
    }

    public Student getStudent() {
	return this.student;
    }

    @Override
    protected void printItems() {  // Print the menu options
	for (int i=0; i<this.itemCount; i++) {
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
    protected void displayMenu() { // Wrapper for all display methods
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
			this.student = new Student();
			quit();
			return;
		    }
		
		    if (query.execute(userInput)) {
			input[i] = userInput;
			break;
		    } else {
			centerText("Invalid input. Please try again.\n");
			centerText(query.getError() + "\n");
		    }
		}
	    }
	    
	    clearScreen();
	    displayMenu();

	    centerText("Is this information correct? (y/n): ");
	    String confirmation = scanner.nextLine().trim().toLowerCase();


	    if (confirmation.equals("y")) {
		quit();
		return;
	    } else if (confirmation.equals("n")) {
		centerText("Re-enter information now: \n");
	    } else {
		centerText("Invalid input. Please type 'y' or 'n'.\n");
	    }
	}
    }

}

