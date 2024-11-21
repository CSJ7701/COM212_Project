import menu.Menu;
import menu.AddStudentMenu;

public class main {
    private Menu menu;

    public static void main(String[] args) {
        main mainInstance = new main();  // Create an instance of the Main class
        mainInstance.runMenu();          // Call the method to run the menu
    }

    // Method to initialize and start the menu
    private void runMenu() {
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

    // TODO
    private void addIdeaOpen() {
        menu.quit();  // Quit the menu
	Menu addIdea = new Menu();
	String text = "Add an idea here.\nHow? IDK yet.\n We'll figure that out later.";
	addIdea.setTopText(text);
	addIdea.addItem("Exit", () -> submenuClose(addIdea));
	addIdea.start();
    }

    // Finished
    private void addStudentOpen() {
	menu.quit();
	AddStudentMenu addStudent = new AddStudentMenu();
	String text = "THIS IS A TEST YAY.";
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
	Menu searchIdeas = new Menu();
	String text = "Search for ideas by idea #...";
	searchIdeas.setTopText(text);
	searchIdeas.addItem("Exit", () -> submenuClose(searchIdeas));
	searchIdeas.start();
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
