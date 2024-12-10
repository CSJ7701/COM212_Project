package menu;

import idea.Idea;
import idea.IdeaHeap;

import java.io.FileWriter;
import java.io.IOException;

public class DisplayIdea extends Menu {
    private Idea idea;
    private IdeaHeap ideaHeap;

    public DisplayIdea(Idea idea, IdeaHeap ideaHeap) {
	super();
        this.idea = idea;
        this.ideaHeap = ideaHeap;
	setTopText("Viewing Idea ID: " + idea.getID());
        
        // Add menu options
        addItem("Edit Idea", this::editIdea);
        addItem("Delete Idea", this::deleteIdea);
        addItem("Quit", this::quit);
    }

    public DisplayIdea(Idea idea, IdeaHeap ideaHeap, boolean best) {
	super();
        this.idea = idea;
        this.ideaHeap = ideaHeap;
	setTopText("Viewing Idea ID: " + idea.getID());
        
        // Add menu options
        addItem("Edit Idea", this::editIdea);
        addItem("Delete Idea", this::deleteIdea);
	addItem("Sell This Idea", this::sellIdea);
	addItem("Quit", this::quit);
    }

    @Override
    protected void displayMenu() {
        clearScreen();
        printBorder();

        // Display the idea details
        centerText("Idea Details\n");
        System.out.println();
        centerText("\033[33mID: \033[0m" + idea.getID());
        System.out.println();
	centerText("\033[33mSubmitter SSN: \033[0m" + idea.getSubmitterSSN());
        System.out.println();
	centerText("\033[33mDescription: \n\033[0m");
	centerText(idea.getDescription(), true);
        System.out.println();
	centerText("\033[33mRating: \033[0m" + idea.getRating());
	System.out.println("\n");
	printBorder();
	System.out.println("\n");
        
        printItems();
        printBorder();
        centerText("\033[32mEnter your choice: \033[0m");
    }

    private void editIdea() {
        clearScreen();
	centerText("============\n");
        centerText("Editing Idea\n");


        // Update Description
        centerText("Enter new description (leave blank to skip): ");
        String newDescription = scanner.nextLine();
        if (!newDescription.isBlank()) {
	    idea.setDescription(newDescription);
        }

        // Update Rating
        centerText("Enter new rating (leave blank to skip): ");
        String ratingInput = scanner.nextLine();
        if (!ratingInput.isBlank()) {
            try {
                int newRating = Integer.parseInt(ratingInput);
		idea.setRating(newRating);
            } catch (NumberFormatException e) {
                centerText("Invalid rating input. Skipping update.");
                System.out.println();
            }
        }

	this.ideaHeap.delete(this.idea.getID()); // Remove the current idea from the heap
	this.ideaHeap.insert(this.idea); // Reinsert the idea so that it gets sorted to the correct position

        // Return to menu
        centerText("Idea updated. Press enter to return to menu.");
        scanner.nextLine();
    }

    private void deleteIdea() {
        clearScreen();
	System.out.println("\n");
	printBorder();
        centerText("Are you sure you want to delete this idea? (yes/no): ");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("yes")) {
	    ideaHeap.delete(idea.getID()); // Call the delete method on the heap
	    System.out.println("\n");
	    printBorder();
            centerText("Idea deleted. Press enter to exit.");
            scanner.nextLine();
            quit(); // Exit the menu
        } else {
	    System.out.println("\n");
	    printBorder();
            centerText("Deletion canceled. Press enter to return to menu.");
            scanner.nextLine();
        }
    }

    private void sellIdea() {
	clearScreen();
	System.out.println("\n");
	printBorder();
	centerText("Are you sure you want to sell this idea?");
	centerText("This will delete the idea (yes/no): ");
	String confirmation = scanner.nextLine();
	if (confirmation.equalsIgnoreCase("yes")) {
	    ideaHeap.delete(idea.getID()); // Call the delete method on the heap
	    System.out.println("\n");
	    String fileName = "Exports/IdeaWriteup_" + idea.getID() + ".txt";
	    printBorder();
	    centerText("Idea has been sold.");
	    centerText("Idea writeup written to " + fileName + "Press enter to exit.");
	    exportIdea(this.idea, fileName);
	    scanner.nextLine();
	    quit(); // Exit the menu
	} else {
	    System.out.println("\n");
	    printBorder();
	    centerText("Deletion canceled. Press enter to return to menu.");
	    scanner.nextLine();
	}
    }

    private void exportIdea(Idea idea, String fileName) {
	try (FileWriter writer = new FileWriter(fileName)) {
	    writer.write("Idea Writeup\n");
	    writer.write("=================\n");
	    writer.write("Submitter's SSN: " + idea.getSubmitterSSN() + "\n");
	    writer.write("Idea ID: " + idea.getID() + "\n");
	    writer.write("Description: " + idea.getDescription() + "\n");
	    writer.write("Rating: " + idea.getRating() + "\n");
	    writer.write("=====================\n");
	    writer.write("Thank you for purchasing this idea!\n");
	} catch (IOException e) {
	    System.err.println("Error writing to file: " + e.getMessage());
	}

    }
}
