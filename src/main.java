import menu.Menu;
import menu.AddIdeaMenu;
import menu.AddStudentMenu;
import menu.searchIdeasMenu;
import menu.searchStudentsMenu;
import menu.listStudentsMenu;
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
	// TODO load serialized data.
	// 'IF file with *name* exists, load file as *variable*, and set *this.variable* = *variable*
	// Example situation:
	//         We could end up saving serialized data to the *project_name*/data directory.
	//         Say we have a "ideaHeap.???" file where we have serialized our ideaHeap structure.
	//         This would check whether "ideaHeap.???" exists, and if it does, load its data to IdeaHeap temp = *loaded_data*.
	//         We could then set this.ideaHeap = temp;.

	// This will be dependent on where/how we end up serializing our structures.
	if (this.initialized != true) {
	    this.ideaHeap = new IdeaHeap();
	    this.ideaHash = new IdeaSCHash();
	    this.IDbst = new IDBST();
	    this.SSNbst = new SSNBST();
	    this.idea_count = this.ideaHeap.length();
	    this.initialized = true;
	}
	this.menu = new Menu(); // Initialize the menu object
	String text = "\n\nWelcome to the Student Idea Management System.\nPlease select an option...\n\n";
	this.menu.setTopText(text); // Set the top section text

	// Add buttons and associate them with actions
	this.menu.addItem("Add Idea", this::addIdeaOpen);
	this.menu.addItem("Add Student", this::addStudentOpen);
	this.menu.addItem("Search Students", this::searchStudentsOpen);
	this.menu.addItem("Search Ideas", this::searchIdeasOpen);
	this.menu.addItem("List All Students", this::listStudentsOpen);
	this.menu.addItem("Get Best Idea", this::bestIdeaOpen);
	this.menu.addItem("Exit", menu::quit);
        // Start the menu loop
        this.menu.start();
    }

    // Submenu Definitions

    private void addIdeaOpen() {
        menu.quit();  // Quit the menu
	AddIdeaMenu addIdea = new AddIdeaMenu(this.SSNbst);
	String text = "Create a new idea.\nPlease enter details:\n\nSSN should reference the student who created the idea.\nDescription must be at least 20 characters.\nRating must be a number from 0-100, where 0 is poor.\n\n\nType 'EXIT' at any time to terminate this process.\n";
	addIdea.setTopText(text);
	addIdea.start();
	Idea newIdea = addIdea.getIdea();
	if (newIdea != null) {
		this.idea_count = this.ideaHeap.length();
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
	AddStudentMenu addStudent = new AddStudentMenu(this.SSNbst, this.IDbst);
	String text = "Create a new student.\nPlease enter details:\n\nName should be the student's last name.\nEmail should be a valid email.\nSSN should be a 4 digit number, unique from other students.\nID should be a 4 digit number, unique from other students.\n\nType 'EXIT' at any time to terminate this process.\n";
	addStudent.setTopText(text);
	addStudent.start();
	Student newStudent = addStudent.getStudent();
	if (newStudent != null && newStudent.getSSN() != 0) {
	    this.IDbst.insert(newStudent);
	    this.SSNbst.insert(newStudent);
	}
	runMenu();
    }

    private void searchStudentsOpen() {
	menu.quit();
	Menu searchStudents = new searchStudentsMenu(this.IDbst, this.SSNbst, this.ideaHeap);
	searchStudents.addItem("Exit", () -> submenuClose(searchStudents));
	searchStudents.start();
	runMenu();
    }

    private void searchIdeasOpen() {
	menu.quit();
	Menu searchIdeas = new searchIdeasMenu(this.ideaHeap);
	searchIdeas.start();
	runMenu();
    }

    //TODO
    private void listStudentsOpen() {
	menu.quit();
	Menu listStudents = new listStudentsMenu(this.IDbst, this.SSNbst, this.ideaHeap);
	listStudents.start();
	runMenu();
    }

    private void bestIdeaOpen() {
	Idea best_idea = this.ideaHeap.getMax();
	if (best_idea == null) {
	    menu.setTopText("\033[31m\nThere are no valid ideas in storage.\033[0m\n");
	    menu.start();
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
