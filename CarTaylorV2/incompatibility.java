
public class incompatibility implements Constraint {
	public PartImpl a;
	public PartImpl b;

	public incompatibility(PartImpl p1, PartImpl p2) {
		a = p1;
		b = p2;
	}

	public String getName() {
		return "incompatibility";
	}
	
	public String getPartOne(){
		return a.getName();
	}

	public String getPartTwo(){
		return b.getName();
	}

}
