import Category.*;

public class PartImpl implements Part {

	private Category cat;
	private String name;
	private String description;
	private int price;

	public PartImpl(Category c, String s, String d, int p) {
		cat = c;
		name = s;
		description = d;
		price = p;
	}
	
	public Category getCategory() {
		return cat;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public int getPrice() {
		return price;
	}
}
