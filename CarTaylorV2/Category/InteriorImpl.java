package Category;

public class InteriorImpl implements Category, Interior {
	private String category;

	public InteriorImpl() {
		category = "Interior";
	}

	public String getName() {

		return category;
	}
}