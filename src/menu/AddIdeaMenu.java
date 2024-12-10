package menu;

import idea.Idea;
import student.SSNBST;

public class AddIdeaMenu extends Menu {
    private Idea idea;
    protected Query[] items;
    private String[] input;
    private SSNBST SSNbst;

    public AddIdeaMenu(SSNBST bst) {
	super();
	this.items = new Query[10];
	this.input = new String[10];
	this.SSNbst = bst;
	this.itemCount = 0;
	this.idea = new Idea();
	String descriptionRegex = ".{20,}"; // Checks whether the description is 20 chars or longer
	addItem("\033[33mLast four digits of Student's SSN: \033[0m", this.idea, "submittersSSN");
	addItem("\033[33mDescription: \033[0m", this.idea, "ideaDescription", descriptionRegex, "Description must be at least 20 characters.");
	addItem("\033[33mRating: \033[0m", this.idea, "ideaRating");
    }

    public Idea getIdea() {
	return this.idea;
    }

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
	centerText("Type 'EXIT' to quit idea creation...\n\n");
	for (int i=0; i<this.itemCount; i++) {
	    Query query = items[i];

	    if (query==null) {
		return;
	    }

	    while (true) {
		centerText("Enter " + query.getPrompt());
		String userInput = scanner.nextLine().trim();

		if (userInput.equalsIgnoreCase("EXIT")) {
		    this.idea = null;
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

	while (true) {
	    clearScreen();
	    displayMenu();

	    if (this.SSNbst.search(this.idea.getSubmitterSSN()) == null) {
		centerText("Student with SSN " + this.idea.getSubmitterSSN() + " not found. Press enter to try again.");
		scanner.nextLine();
		for (int i = 0; i < this.input.length; i++) {
		    input[i] = null;
		}
		handleInput();
		return;
	    }

	    if (this.idea.getRating() > 100 || this.idea.getRating() < 0) {
		centerText("Rating must be between 1-100. Press enter to try again.");
		scanner.nextLine();
		handleInput();
		for (int i = 0; i < this.input.length; i++) {
		    input[i] = null;
		}
		return;
	    }
	    
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
	    
