package menu;

import java.lang.reflect.Field;
import java.util.Scanner;

public class AddStudentMenu extends Menu {
    private Student student;
    protected Query[] items;
    private String[] input;

    public AddStudentMenu() {
	super();
	this.items = new Query[10];
	this.input = new String[10];
	this.itemCount = 0;
	this.student = new Student();
	addItem("Name: ", this.student, "name");
	addItem("Email: ", this.student, "email");
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

	while (true) {
	    clearScreen();
	    displayMenu();
	    centerText("Is this information correct? (y/n): ");
	    String confirmation = scanner.nextLine().trim().toLowerCase();

	    if (confirmation.equals("y")) {
		quit();
		return;
	    } else if (confirmation.equals("n")) {
		for (int i=0; i<this.input.length; i++) {
		    input[i] = null;
		}
		handleInput();
		return;
	    } else {
		centerText("Invalid input. Please type 'y' or 'n'.\n");
	    }
	}
    }

}

