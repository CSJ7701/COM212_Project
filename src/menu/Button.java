package menu;

public class Button {
    private String label;
    private Runnable action;

    public Button(String label, Runnable action) {
	this.label = label;
	this.action = action;
    }

    public String getLabel() {
	return label;
    }

    public void press() {
	if (action != null) {
	    action.run();
	}
    }
}
