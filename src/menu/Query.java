package menu;

import java.util.Scanner;
import java.lang.reflect.Field;

public class Query {
    private final String prompt;
    private final String validationRegex; // Optional - idea. May not work?
    private final Object target;
    private final String attribute;

    public Query(String prompt, Object target, String attribute, String validationRegex) {
	this.prompt = prompt;
	this.target = target;
	this.attribute = attribute;
	this.validationRegex = validationRegex;
    }

    public Query(String prompt, Object target, String attribute) {
	this.prompt = prompt;
	this.target = target;
	this.attribute = attribute;
	this.validationRegex = null;
    }

    public String getPrompt() {
	return this.prompt;
    }

    public void execute() {
	Scanner scanner = new Scanner(System.in);
	
	while (true) {
	    System.out.print(prompt + ": ");
	    String input = scanner.nextLine();

	    if (validationRegex == null || input.matches(validationRegex)) {
		try {
		    Field field = target.getClass().getDeclaredField(attribute);
		    field.setAccessible(true);
		    field.set(target, input);
		    break;
		} catch (NoSuchFieldException | IllegalAccessException e) {
		    System.out.println("Error setting field: " + e.getMessage());
		    break;
		}
	    } else {
		System.out.println("Invalid input. Please try again");
	    }
	}
    }

    public boolean execute(String input) {
	if (validationRegex == null || input.matches(validationRegex)) {
	    try {
		Field field = target.getClass().getDeclaredField(attribute);
		field.setAccessible(true);
		field.set(target, input);
		return true;
	    } catch (NoSuchFieldException | IllegalAccessException e) {
		System.out.println("Error setting field: " + e.getMessage());
		return false;
	    }
	} else {
	    System.out.println("Invalid input. Please try again");
	    return false;
	}
    }
}
