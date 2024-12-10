package menu;

import idea.Idea;
import idea.IdeaHeap;

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
        addItem("Sell Idea", this::deleteIdea);
        addItem("Quit", this::quit);
    }

    @Override
    protected void displayMenu() {
        clearScreen();
        printBorder();

        // Display the idea details
        centerText("Idea Details:");
        System.out.println();
        centerText("ID: " + idea.getID());
        System.out.println();
	centerText("Submitter SSN: " + idea.getSubmitterSSN());
        System.out.println();
	centerText("Description: \n");
	centerText(idea.getDescription());
        System.out.println();
	centerText("Rating: " + idea.getRating());
	System.out.println("\n");
	centerText("---");
	System.out.println("\n");
        
        printItems();
        printBorder();
        centerText("Enter your choice: ");
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
	centerText("===================\n\n");
        centerText("Are you sure you want to sell this idea? (yes/no): ");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("yes")) {
	    ideaHeap.delete(idea.getID()); // Call the delete method on the heap
	    System.out.println("\n");
	    centerText("===================\n\n");
            centerText("Idea sold. Press enter to exit.");
            scanner.nextLine();
            quit(); // Exit the menu
        } else {
	    System.out.println("\n");
	    centerText("===================\n\n");
            centerText("Sell canceled. Press enter to return to menu.");
            scanner.nextLine();
        }
    }
}
