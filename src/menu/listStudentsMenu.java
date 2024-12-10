package menu;

import student.Student;
import student.IDBST;
import student.SSNBST;

import java.util.ArrayList;
import java.util.List;

import idea.IdeaHeap;
import menu.DisplayStudent;

public class listStudentsMenu extends Menu {
    private IDBST studentsID;
    private List<Student> studentsOrdered;
    private SSNBST studentsSSN;
    private IdeaHeap ideas;
    private int currentPage;
    private int itemsPerPage;

    public listStudentsMenu(IDBST studentsID, SSNBST studentsSSN, IdeaHeap ideas) {
	super();
	this.studentsID = studentsID;
	this.studentsSSN = studentsSSN;
	this.ideas = ideas;
	this.itemsPerPage = 5;
	this.currentPage = 0;

	// Create ordered list of students
	this.studentsOrdered = toOrderedList();

	addItem("Next Page", this::nextPage);
	addItem("Previous Page", this::previousPage);
	addItem("Select Student", this::selectStudent);
	addItem("Quit", this::quit);
    }

    @Override
    protected void printTop() {

	clearScreen();
	printBorder();

	if (this.studentsOrdered == null || this.studentsOrdered.isEmpty()) {
	    // Print in red
	    centerText("\033[31mThere are no known students.\033[0m");
	    System.out.println();
	    return;
	} else {

	    // Display current page
	    int start = currentPage * itemsPerPage;
	    int end = Math.min(start + itemsPerPage, studentsOrdered.size());

	    for (int i = start; i < end; i++) {
		// Get the next student from the ordered list
		Student student = studentsOrdered.get(i);
		
		centerText((i+1)+". [ID: " + student.getID() + "] " + student.getName() + "\n");
	    }

	    // Add a page number
	    System.out.println("[Page " + (currentPage+1) + " of " + getTotalPages() + "]\n");
	    printBorder();
	}
    }

    private void previousPage() {
	if (currentPage > 0) {
	    currentPage--;
	} else {
	    // Could show error here... but not really necessary
	}
    }

    private void nextPage() {
	if (currentPage < getTotalPages() - 1) {
	    currentPage++;
	} else {
	    // Again, error not necessary
	}
    }

    private void selectStudent() {
	if (this.studentsOrdered == null) {
	    setTopText("\n\033[31mThere are no students to select.\033[0m\n");
	    return;
	}
	centerText("Enter the idea number to select: ");
	String input = scanner.nextLine();
	try {
	    int studentIndex = Integer.parseInt(input)-1;
	    int globalIndex = (currentPage * itemsPerPage) + studentIndex;
	    if (globalIndex >= 0 && globalIndex < studentsOrdered.size()) {
		Student selected = studentsOrdered.get(globalIndex);
		DisplayStudent display = new DisplayStudent(selected, studentsSSN, studentsID, ideas);
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
	if (this.studentsOrdered == null) {
	    return 0;
	} else {
	    return (int) Math.ceil((double) studentsOrdered.size() / itemsPerPage);
	}
    }

    public List<Student> toOrderedList() {
	List<Student> orderedList = new ArrayList<>();
	toOrderedListR(this.studentsID.getRoot(), orderedList);
	return orderedList;
    }

    private void toOrderedListR(Student currNode, List<Student> list) {
	if (currNode != null) {
	    toOrderedListR(currNode.getIDLeft(), list);
	    list.add(currNode);
	    toOrderedListR(currNode.getIDRight(), list);
	}
    }
}

