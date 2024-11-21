import menu.Menu;

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
        this.menu.addButton("Add Idea", this::addIdeaOpen);
        this.menu.addButton("Add Student", this::addStudentOpen);
	this.menu.addButton("Search Students", this::searchStudentsOpen);
	this.menu.addButton("Search Ideas", this::searchIdeasOpen);
	this.menu.addButton("Get Best Idea", this::bestIdeaOpen);
	this.menu.addButton("Exit", menu::quit);

        // Start the menu loop
        this.menu.start();
    }

    // Submenu Definitions
    private void addIdeaOpen() {
        menu.quit();  // Quit the menu
	Menu addIdea = new Menu();
	String text = "Add an idea here.\nHow? IDK yet.\n We'll figure that out later.";
	addIdea.setTopText(text);
	addIdea.addButton("Exit", () -> submenuClose(addIdea));
	addIdea.start();
    }

    private void addStudentOpen() {
	menu.quit();
	Menu addStudent = new Menu();
	String text = "Add a student here.\nIDK how we're gonna do this either.";
	addStudent.setTopText(text);
	addStudent.addButton("Exit", () -> submenuClose(addStudent));
	addStudent.start();
    }

    private void searchStudentsOpen() {
	menu.quit();
	Menu searchStudents = new Menu();
	String text = "Search for students...\nCan search by SSN or Student #...";
	searchStudents.setTopText(text);
	searchStudents.addButton("Exit", () -> submenuClose(searchStudents));
	searchStudents.start();
    }

    private void searchIdeasOpen() {
	menu.quit();
	Menu searchIdeas = new Menu();
	String text = "Search for ideas by idea #...";
	searchIdeas.setTopText(text);
	searchIdeas.addButton("Exit", () -> submenuClose(searchIdeas));
	searchIdeas.start();
    }

    private void bestIdeaOpen() {
	menu.quit();
	Menu bestIdea = new Menu();
	String text = "Get the best idea\nCan open idea, or delete.";
	bestIdea.setTopText(text);
	bestIdea.addButton("Exit", () -> submenuClose(bestIdea));
	bestIdea.start();
    }


    // Utility Functions
    
    private void submenuClose(Menu submenu) {
	submenu.quit();
	runMenu();
    }
}
