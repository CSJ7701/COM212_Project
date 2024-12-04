import menu.Menu;
import menu.AddIdeaMenu;
import menu.AddStudentMenu;
import menu.searchIdeasMenu;
import menu.searchStudentsMenu;
import menu.DisplayIdea;

import idea.Idea;
import idea.IdeaHeap;
import idea.IdeaQueue;
import idea.IdeaSCHash;

import student.Student;
import student.IDBST;
import student.SSNBST;

import java.util.Scanner;
import java.io.*; 

public class main {
    private Menu menu;
    private IdeaHeap ideaHeap; // Fetch the best idea
    private IdeaSCHash ideaHash; // Storing ideas for each student
    private IDBST IDbst; // Storing Students by Student ID
    private SSNBST SSNbst; // Storing Students by SSN
    private boolean initialized;
    private int idea_count;

    public static void main(String[] args) {
        main mainInstance = new main();  // Create an instance of the Main class
        mainInstance.runMenu();          // Call the method to run the menu
    }

    // Method to initialize and start the menu
    private void runMenu() {
	if (this.initialized != true) {
	    this.ideaHeap = new IdeaHeap();
	    this.ideaHash = new IdeaSCHash();
	    this.IDbst = new IDBST();
	    this.SSNbst = new SSNBST();
	    this.idea_count = this.ideaHeap.length();
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

    private void addIdeaOpen() {
        menu.quit();  // Quit the menu
	AddIdeaMenu addIdea = new AddIdeaMenu(this.SSNbst);
	String text = "Create a new idea.\nPlease enter details:";
	addIdea.setTopText(text);
	addIdea.start();
	Idea newIdea = addIdea.getIdea();
	if (newIdea != null) {
	    newIdea.setID(this.idea_count+1);
	    Student student = this.SSNbst.search(newIdea.getSubmitterSSN());
	    student.addIdea(newIdea);
	    this.ideaHeap.insert(newIdea);
	    this.ideaHash.insert(newIdea);
	}
	runMenu();
    }

    private void addStudentOpen() {
	menu.quit();
	AddStudentMenu addStudent = new AddStudentMenu();
	String text = "Create a new student.\nPlease enter details:";
	addStudent.setTopText(text);
	addStudent.start();
	Student newStudent = addStudent.getStudent();
	if (newStudent != null) {
	    this.IDbst.insert(newStudent);
	    this.SSNbst.insert(newStudent);
	}
	runMenu();
    }

    // TODO
    private void searchStudentsOpen() {
	menu.quit();
	Menu searchStudents = new searchStudentsMenu(this.IDbst, this.SSNbst, this.ideaHeap);
	searchStudents.addItem("Exit", () -> submenuClose(searchStudents));
	searchStudents.start();
	runMenu();
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
	Idea best_idea = this.ideaHeap.getMax();
	if (best_idea == null) {
	    menu.setTopText("\033[31m\nThere are no valid ideas in storage.\033[0m\n");
	} else {
	    menu.quit();
	    DisplayIdea display = new DisplayIdea(best_idea, this.ideaHeap);
	    display.start();
	    runMenu();
	}
    }


    // Utility Functions
    
    private void submenuClose(Menu submenu) {
	submenu.quit();
	runMenu();
    }
}
