
public class Requirement {

	private Part p1;
	private Part p2;

	public Requirement() {
		p1 = new Part();
		p2 = new Part();
	}

	public Requirement(Part a, Part b) {
		p1 = a;
		p2 = b;
	}

	public Part getPart1() {
		return p1;
	}

	public Part getPart2() {
		return p2;
	}

	
}