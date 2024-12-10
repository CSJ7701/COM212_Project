package menu;

import idea.Idea;
import idea.IdeaHeap;
import menu.DisplayIdea;

public class searchIdeasMenu extends Menu {
    private IdeaHeap ideaHeap;
    protected Query items;
    private Idea idea;

    public searchIdeasMenu(IdeaHeap ideaHeap) {
        this.ideaHeap = ideaHeap;
	this.items = new Query("ID: ", this, "searchID");
	setTopText("Search Ideas by ID\n\nYou may search for an idea by its numerical ID here.\nIds are assigned automatically when ideas are created.\n\nIf you do not know the ID\nfor the idea you want, locate the student\nwho created it instead.\n");
    }

    @Override
    protected void displayMenu() {
	clearScreen();
	printBorder();

	printTop();
	printItems();

	printBorder();
    }

    public Idea getIdea() {
	return this.idea;
    }

    @Override
    protected void handleInput() {

	centerText("Type 'EXIT' to quit idea creation...\n\n");
	while (this.isRunning) {
		centerText("Enter an Idea ID (or 'exit' to go back): ");
		String input = scanner.nextLine().trim();
		if (input.equalsIgnoreCase("EXIT")) {
			quit();
			return;
		}
		try {
			int ideaID = Integer.parseInt(input);
			Idea foundIdea = ideaHeap.find(ideaID);
			if (foundIdea != null) {
				this.idea = foundIdea;
				DisplayIdea display = new DisplayIdea(foundIdea, this.ideaHeap);
				display.start();
				quit();
			} else {
				centerText("Idea not found with ID: " + ideaID + "! Press enter to try again.\n");
				ideaHeap.print();
				scanner.nextLine();
			}
		} catch (NumberFormatException e) {
			centerText("Invalid input. Press enter to try again.\n");
			scanner.nextLine();
		}
	}
  }
    

}
