package menu;

import java.util.Scanner;

public class Menu {
    protected boolean isRunning;
    protected Scanner scanner;
    protected int width;
    protected int height;
    protected int topHeight;
    protected Button[] items;
    protected int itemCount;
    protected StringBuilder topText;
    // Use a StringBuilder since String is immutable in java - took me ages to figure out.

    public Menu() {
	this.isRunning = true;
	this.scanner = new Scanner(System.in);
	this.width = 80;
	this.height = 50;
	this.topHeight = 10;
	this.topText = new StringBuilder();
	this.items = new Button[11]; // Fixed size - assuming 10 will work. (11-1, for exit button)
	this.itemCount = 0;
    }

    public void start() {
	while (this.isRunning) {
	    displayMenu();
	    handleInput();
	}
    }

    public void setTopText(String text) {
	topText.setLength(0); // Clear current text
	topText.append(text); // Append new text
    }

    public void addItem(String label, Runnable action) {
	if (this.itemCount < 10) {
	    this.items[this.itemCount] = new Button(label, action);
	    this.itemCount++;
	} else {
	    centerText("Cannot add more buttons. Max limit reached.\n");
	}
    }

    public void quit() {
	this.isRunning = false;
    }

    protected void displayMenu() {
	clearScreen();
	printBorder();

	printTop();
	printItems();

	printBorder();
	centerText("Enter your choice: ");
    }

    protected void handleInput() {
	String input = scanner.nextLine();
	try {
	    int choice = Integer.parseInt(input) - 1;
	    if (choice >= 0 && choice < this.itemCount+1 && this.items[choice] != null ) {
		this.items[choice].press();
	    } else {
		centerText("Invalid choice! Press enter to try again.\n");
		scanner.nextLine();
	    }
	} catch (NumberFormatException e) {
	    centerText("Invalid Input. Press enter to try again.\n");
	    scanner.nextLine();
	}
    }
    
    protected void printTop() {
	// Print the top section, up to height of topHeigh
	String[] lines = topText.toString().split("\n"); // Break the topText into individual lines.
	for (int i=0; i< this.topHeight && i<lines.length; i++) {
	    centerText(lines[i]);
	    System.out.println();
	}

	// If there's space left, fill with blank lines
	for (int i = lines.length; i<this.topHeight; i++) {
	    System.out.println();
	}
    }

    protected void printItems() {
	for (int i=0; i<=this.itemCount; i++) {
	    if (items[i] != null) {
		centerText((i+1) + ". " + items[i].getLabel() + "\n");
	    }
	}
    }

    protected void clearScreen() {
	// ANSI escape code to clear the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    protected void printBorder() {
	System.out.print("=".repeat(this.width));
    }

    protected void centerText(String text) {
	int padding = (this.width - text.length()) / 2;
	System.out.print(" ".repeat(Math.max(padding,0)) + text);
    }

    protected void exit() {
	centerText("Exiting the menu. Goodbye.");
	this.isRunning = false;
    }

}

    
