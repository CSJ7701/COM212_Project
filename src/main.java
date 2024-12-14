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

	if (initialized != true){
	    //Load ideaHeap
	    try{
		FileInputStream ideaHeapFile = new FileInputStream("ideaHeap.ser");
		ObjectInputStream ideaHeapIn = new ObjectInputStream(ideaHeapFile);
		ideaHeap = (IdeaHeap)ideaHeapIn.readObject();
		ideaHeapIn.close();
		ideaHeapFile.close();
	    } catch (FileNotFoundException e){
		System.out.println("Error: " + e);
	    } catch (IOException e){
		System.out.println("Error: " + e);
	    } catch (ClassNotFoundException e){
		System.out.println("Error: " + e);
	    }
	

	    // Load ideaHash
	    try{
		FileInputStream ideaHashFile = new FileInputStream("ideaHash.ser");
		ObjectInputStream ideaHashIn = new ObjectInputStream(ideaHashFile);
		ideaHash = (IdeaSCHash)ideaHashIn.readObject();
		ideaHashIn.close();
		ideaHashFile.close();
	    } catch (FileNotFoundException e){
		System.out.println("Error: " + e);
	    } catch (IOException e){
		System.out.println("Error: " + e);
	    } catch (ClassNotFoundException e){
		System.out.println("Error: " + e);
	    }


	    //Load IDbst
	    try{
		FileInputStream IDBSTFile = new FileInputStream("IDBST.ser");
		ObjectInputStream IDBSTIn = new ObjectInputStream(IDBSTFile);
		IDbst = (IDBST)IDBSTIn.readObject();
		IDBSTIn.close();
		IDBSTFile.close();
	    } catch (FileNotFoundException e){
		System.out.println("Error: " + e);
	    } catch (IOException e){
		System.out.println("Error: " + e);
	    } catch (ClassNotFoundException e){
		System.out.println("Error: " + e);
	    }

	    //Load SSNbst
	    try{
		FileInputStream SSNBSTFile = new FileInputStream("SSNBST.ser");
		ObjectInputStream SSNBSTIn = new ObjectInputStream(SSNBSTFile);
		SSNbst = (SSNBST)SSNBSTIn.readObject();
		SSNBSTIn.close();
		SSNBSTFile.close();
	    } catch (FileNotFoundException e){
		System.out.println("Error: " + e);
	    } catch (IOException e){
		System.out.println("Error: " + e);
	    } catch (ClassNotFoundException e){
		System.out.println("Error: " + e);
	    }

	    // If Serialized files are empty
	    if (ideaHeap == null){
		ideaHeap = new IdeaHeap();
	    }

	    if (ideaHash == null){
		ideaHash = new IdeaSCHash();
	    }

	    if (IDbst == null){
		IDbst = new IDBST();
	    }

	    if (SSNbst == null){
		SSNbst = new SSNBST();
	    }
	    //Finalize intializations 
	    idea_count = this.ideaHeap.length();
	    initialized = true;
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
	this.menu.addItem("Exit", this::exitAndSave);
        // Start the menu loop
        this.menu.start();
    }

    // == Submenu Definitions ==

    private void addIdeaOpen() {
        menu.quit();  // Quit the menu
	AddIdeaMenu addIdea = new AddIdeaMenu(this.SSNbst); // Create an instance of the AddIdeaMenu class
	String text = "Create a new idea.\nPlease enter details:\n\nSSN should reference the student who created the idea.\nDescription must be at least 20 characters.\nRating must be a number from 0-100, where 0 is poor.\n\n\nType 'EXIT' at any time to terminate this process.\n";
	addIdea.setTopText(text);
	addIdea.start(); // Run the menu
	Idea newIdea = addIdea.getIdea(); // Get the newly created idea from the menu.
	if (newIdea != null) {
	    this.idea_count = this.ideaHeap.getIdeaCount();
	    newIdea.setID(this.idea_count+1); // Set the idea id
	    Student student = this.SSNbst.search(newIdea.getSubmitterSSN()); // Get the student who made the idea
	    student.addIdea(newIdea); // Add the idea to the students queue
	    this.ideaHeap.insert(newIdea); // Add the idea to the heap
	    this.ideaHash.insert(newIdea); // Add the idea to the hash table
	}
	runMenu(); // Run the main menu once the AddIdeaMenu instance closes
    }

    private void addStudentOpen() {
	menu.quit(); // Quit the menu
	AddStudentMenu addStudent = new AddStudentMenu(this.SSNbst, this.IDbst); // Create new menu instance
	String text = "Create a new student.\nPlease enter details:\n\nName should be the student's last name.\nEmail should be a valid email.\nSSN should be a 4 digit number, unique from other students.\nID should be a 4 digit number, unique from other students.\n\nType 'EXIT' at any time to terminate this process.\n";
	addStudent.setTopText(text);
	addStudent.start(); // Run the addStudent menu
	Student newStudent = addStudent.getStudent();
	if (newStudent != null && newStudent.getSSN() != 0) {
	    this.IDbst.insert(newStudent); // Insert the student into the ID BST
	    this.SSNbst.insert(newStudent); // Insert the student into the SSN BST
	}
	runMenu(); // Run the main menu once the student menu closes
    }

    private void searchStudentsOpen() {
	menu.quit();
	Menu searchStudents = new searchStudentsMenu(this.IDbst, this.SSNbst, this.ideaHeap);
	searchStudents.addItem("Exit", () -> submenuClose(searchStudents));
	searchStudents.start(); // Start the search menu
	runMenu();
    }

    private void searchIdeasOpen() {
	menu.quit();
	Menu searchIdeas = new searchIdeasMenu(this.ideaHeap);
	searchIdeas.start(); // Start the search menu
	runMenu();
    }

    private void listStudentsOpen() {
	menu.quit();
	Menu listStudents = new listStudentsMenu(this.IDbst, this.SSNbst, this.ideaHeap);
	listStudents.start(); // Start the list menu
	runMenu();
    }

    private void bestIdeaOpen() {
	Idea best_idea = this.ideaHeap.getMax();
	if (best_idea == null) {
	    menu.setTopText("\033[31m\nThere are no valid ideas in storage.\033[0m\n");
	    menu.start();
	} else {
	    menu.quit();
	    // Variant of the DisplayIdea which adds an extra option - 'sell'
	    // Specified by the extra argument - true
	    DisplayIdea display = new DisplayIdea(best_idea, this.ideaHeap, true);
	    display.start();
	    runMenu();
	}
    }

    private void exitAndSave(){
        
	// Save ideaHeap
	try{
	    FileOutputStream ideaHeapFile = new FileOutputStream("ideaHeap.ser");
	    ObjectOutputStream ideaHeapOut = new ObjectOutputStream(ideaHeapFile);
	    ideaHeapOut.writeObject(ideaHeap);
	    ideaHeapOut.close();
	    ideaHeapFile.close();
	} catch (FileNotFoundException e){
	    System.out.println("Error: " + e);
	} catch (IOException e){
	    System.out.println("Error: " + e);
	}
		

	// Save ideaHash
	try{
	    FileOutputStream ideaHashFile = new FileOutputStream("ideaHash.ser");
	    ObjectOutputStream ideaHashOut = new ObjectOutputStream(ideaHashFile);
	    ideaHashOut.writeObject(ideaHash);
	    ideaHashOut.close();
	    ideaHashFile.close();
	} catch (FileNotFoundException e){
	    System.out.println("Error: " + e);
	} catch (IOException e){
	    System.out.println("Error: " + e);
	}
	

	// Save IDbst
	try{
	    FileOutputStream IDBSTFile = new FileOutputStream("IDBST.ser");
	    ObjectOutputStream IDBSTOut = new ObjectOutputStream(IDBSTFile);
	    IDBSTOut.writeObject(IDbst);
	    IDBSTOut.close();
	    IDBSTFile.close();
	} catch (FileNotFoundException e){
	    System.out.println("Error: " + e);
	} catch (IOException e){
	    System.out.println("Error: " + e);
	}

	//Save SSNbst
	try{
	    FileOutputStream SSNBSTFile = new FileOutputStream("SSNBST.ser");
	    ObjectOutputStream SSNBSTOut = new ObjectOutputStream(SSNBSTFile);
	    SSNBSTOut.writeObject(SSNbst);
	    SSNBSTOut.close();
	    SSNBSTFile.close();
	} catch (FileNotFoundException e){
	    System.out.println("Error: " + e);
	} catch (IOException e){
	    System.out.println("Error: " + e);
	}

	menu.quit();

    }

    // Utility Functions
    
    private void submenuClose(Menu submenu) {
	submenu.quit(); // Quit an arbitrary submenu.
	runMenu();
    }
}
