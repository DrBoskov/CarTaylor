package Category;

import java.util.Arrays;
import java.util.List;

public class ExteriorImpl implements Category, Exterior {
	private String category;
	private List<String> colours;
	private String colour;
	private List<String> def = Arrays.asList("black", "white", "red");

	public ExteriorImpl(List<String> all, String col) {
		category = "Exterior";
		colours = all;
		colour = col;
	}
	
	public ExteriorImpl(String col) {
		category = "Exterior";
		colours = def;
		colour = col;
	}
	public ExteriorImpl() {
		category = "Exterior";
		colours = def;
		colour = colours.get(0);
	}
	public ExteriorImpl(List<String> all) {
		category = "Exterior";
		colours = all;
		colour = colours.get(0);
	}

	public String getName() {

		return category;
	}

	public String getColour() {
		return colour;
	}
	public void setColour(String col) {
		colour = col;
	}

	public List<String> getAvailableColours() {
		return colours;
	}
}
