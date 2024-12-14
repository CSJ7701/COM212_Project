package menu;

import idea.Idea;
import idea.IdeaQueue;
import idea.IdeaHeap;
import menu.DisplayIdea;

public class studentIdeasMenu extends Menu {
    private IdeaQueue ideas;
    private IdeaHeap ideaHeap;
    private int currentPage;
    private int itemsPerPage;

    public studentIdeasMenu(IdeaQueue ideas, IdeaHeap heap) {
	super();
	this.ideas = ideas;
	this.ideaHeap = heap;
	this.itemsPerPage = 5;
	this.currentPage = 0;

	addItem("Next Page", this::nextPage);
	addItem("Previous Page", this::previousPage);
	addItem("Select Idea", this::selectIdea);
	addItem("Quit", this::quit);
    }

    @Override
    protected void printTop() {

	clearScreen();
	printBorder();

	
	if (this.ideas == null || this.ideas.isEmpty()) {
	    // Print the message in red
	    centerText("\033[31mThere are no known ideas associated with this student.\033[0m");
	    System.out.println();
	    return;
	} else {

	    // Display ideas for the current page
	    int start = currentPage * itemsPerPage;
	    int end = Math.min(start + itemsPerPage, ideas.getSize());


	    for (int i = start; i < end; i++) {
		Idea idea = ideas.get(i);
		centerText((i + 1) + ". [ID: " + idea.getID() + "] " + idea.getDescription() + "\n");
	    }

	    // Add a page number indicator
	    System.out.println("[Page " + (currentPage + 1) + " of " + getTotalPages() + "]\n");
	    printBorder();
	}
    }

    private void previousPage() {
	if (currentPage > 0) {
	    currentPage--;
	} else {
	    // Could show an error message, but its not necessary
	}
    }

    private void nextPage() {
	if (currentPage < getTotalPages() - 1) {
	    currentPage++;
	} else {
	    // Again, error largely unneccessary
	}
    }

    private void selectIdea() { // Incorporates pages, lets you choose an idea from list
	if (this.ideas == null) {
	    setTopText("\n\033[31mThere are no ideas to select.\033[0m\n");
	    return;
	}
	centerText("Enter the idea number to select: ");
	String input = scanner.nextLine();
	try {
	    int ideaIndex = Integer.parseInt(input) - 1;
	    int globalIndex = (currentPage * itemsPerPage) + ideaIndex;
	    if (globalIndex >= 0 && globalIndex < ideas.getSize()) {
		Idea selected = ideas.get(globalIndex);
		DisplayIdea display = new DisplayIdea(selected, this.ideaHeap);
		display.start();
	    } else {
		centerText("Invalid idea number. Press enter to try again.");
		scanner.nextLine();
	    }
	} catch (NumberFormatException e) {
	    centerText("Invalid input. Please enter a valid number.\n");
	    scanner.nextLine();
	}
    }

    private int getTotalPages() {
	if (this.ideas == null) {
	    return 0;
	} else {
	    return (int) Math.ceil((double) ideas.getSize() / itemsPerPage);
	}
    }

}
