package menu;

import java.util.Scanner;

public class Menu {
    private boolean isRunning;
    private Scanner scanner;
    private int width;
    private int height;
    private int topHeight;
    private Button[] buttons;
    private int buttonCount;
    private StringBuilder topText;
    // Use a StringBuilder since String is immutable in java - took me ages to figure out.

    public Menu() {
	this.isRunning = true;
	this.scanner = new Scanner(System.in);
	this.width = 80;
	this.height = 50;
	this.topHeight = 10;
	this.topText = new StringBuilder();
	this.buttons = new Button[11]; // Fixed size - assuming 10 will work. (11-1, for exit button)
	this.buttonCount = 0;
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

    public void addButton(String label, Runnable action) {
	if (buttonCount < 10) {
	    this.buttons[buttonCount] = new Button(label, action);
	    buttonCount++;
	} else {
	    centerText("Cannot add more buttons. Max limit reached.\n");
	}
    }

    public void quit() {
	this.isRunning = false;
    }

    private void displayMenu() {
	clearScreen();
	printBorder();

	printTop();
	printButtons();

	printBorder();
	centerText("Enter your choice: ");
    }

    private void handleInput() {
	String input = scanner.nextLine();
	try {
	    int choice = Integer.parseInt(input) - 1;
	    if (choice >= 0 && choice < this.buttonCount+1 && this.buttons[choice] != null ) {
		this.buttons[choice].press();
	    } else {
		centerText("Invalid choice! Press enter to try again.\n");
		scanner.nextLine();
	    }
	} catch (NumberFormatException e) {
	    centerText("Invalid Input. Press enter to try again.\n");
	    scanner.nextLine();
	}
    }
    
    private void printTop() {
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

    private void printButtons() {
	for (int i=0; i<=this.buttonCount; i++) {
	    if (buttons[i] != null) {
		centerText((i+1) + ". " + buttons[i].getLabel() + "\n");
	    }
	}
    }

    private void clearScreen() {
	// ANSI escape code to clear the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void printBorder() {
	System.out.print("=".repeat(this.width));
    }

    private void centerText(String text) {
	int padding = (this.width - text.length()) / 2;
	System.out.print(" ".repeat(Math.max(padding,0)) + text);
    }

    private void exit() {
	centerText("Exiting the menu. Goodbye.");
	this.isRunning = false;
    }

}

    
