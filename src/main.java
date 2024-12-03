import menu.Menu;
import menu.AddIdeaMenu;
import menu.AddStudentMenu;
import menu.searchIdeasMenu;

import idea.Idea;
import idea.IdeaHeap;
import idea.IdeaQueue;
import idea.IdeaSCHash;

import java.util.Scanner;
import java.io.*; 

public class main {
    private Menu menu;
    private IdeaHeap ideaHeap;
    private IdeaQueue ideaQueue;
	private IdeaSCHash ideaHash;
    private boolean initialized;

	public static void main(String[] args) {
        main mainInstance = new main();  // Create an instance of the Main class
        mainInstance.runMenu();          // Call the method to run the menu
    }

    // Method to initialize and start the menu
    private void runMenu() {
	if (this.initialized != true) {
	    this.ideaHeap = new IdeaHeap();
	    this.initialized = true;
	}
	this.menu = new Menu(); // Initialize the menu object
	String text = "Welcome.\n";
	this.menu.setTopText(text); // Set the top section text

	// Add buttons and associate them with actions
	this.menu.addItem("Add Idea", this::addIdeaOpen);
	this.menu.addItem("Add Student", this::addStudentOpen);
	this.menu.addItem("Search Students", this::searchStudentsOpen);
	this.menu.addItem("Search Ideas", this::searchIdeasOpen);
	this.menu.addItem("Get Best Idea", this::bestIdeaOpen);
	this.menu.addItem("Exit", menu::quit);
        // Start the menu loop
        this.menu.start();
    }

    // Submenu Definitions

    // Finished
    private void addIdeaOpen() {
        menu.quit();  // Quit the menu
	AddIdeaMenu addIdea = new AddIdeaMenu();
	String text = "Create a new idea.\nPlease enter details:";
	addIdea.setTopText(text);
	addIdea.start();
	Idea newIdea = addIdea.getIdea();
	newIdea.setID(this.ideaHeap.length()+1);
	this.ideaHeap.insert(newIdea);
	this.ideaQueue.enqueue(newIdea);
	this.ideaHash.insert(newIdea);
	runMenu();
    }

    // Finished
    private void addStudentOpen() {
	menu.quit();
	AddStudentMenu addStudent = new AddStudentMenu();
	String text = "Create a new student.\nPlease enter details:";
	addStudent.setTopText(text);
	addStudent.start();
	runMenu();
    }

    // TODO
    private void searchStudentsOpen() {
	menu.quit();
	Menu searchStudents = new Menu();
	String text = "Search for students...\nCan search by SSN or Student #...";
	searchStudents.setTopText(text);
	searchStudents.addItem("Exit", () -> submenuClose(searchStudents));
	searchStudents.start();
    }

    // TODO
    private void searchIdeasOpen() {
	menu.quit();
	Menu searchIdeas = new searchIdeasMenu(this.ideaHeap);
	searchIdeas.start();
	runMenu();
    }

    // TODO
    private void bestIdeaOpen() {
	menu.quit();
	Menu bestIdea = new Menu();
	String text = "Get the best idea\nCan open idea, or delete.";
	bestIdea.setTopText(text);
	bestIdea.addItem("Exit", () -> submenuClose(bestIdea));
	bestIdea.start();
    }


    // Utility Functions
    
    private void submenuClose(Menu submenu) {
	submenu.quit();
	runMenu();
    }
}
