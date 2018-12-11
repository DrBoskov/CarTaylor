
public class Part {

	private Category cat;
	private String name;
	private String description;

	public Part() {
		cat = new Category();
		name = null;
		description = null;
	}

	public Part(Category c, String s, String d) {
		cat = c;
		name = s;
		description = d;
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
}
