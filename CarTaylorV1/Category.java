
public class Category {
	private int cat;
	private int error = -1;
	public int engine = 0;
	public int transmission = 1;
	public int interior = 2;
	public int exterior = 3;

	public Category() {
		cat = -1;
	}

	public Category(int i) {
		cat = i;
	}

	public int getCategory() {
		return cat;
	}

	public void setCategory(int i) {
		cat = i;
	}

}
