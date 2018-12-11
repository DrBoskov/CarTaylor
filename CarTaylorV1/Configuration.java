import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Configuration {

	private List<Part> chosenParts;
	private List<Part> allParts;
	private Constraints constraint;

	public Configuration() {
		allParts = new ArrayList<Part>();
		constraint = new Constraints();
		chosenParts = new ArrayList<Part>();
	}

	public Configuration(List<Part> a, List<Part> p, Constraints c) {
		allParts = a;
		chosenParts = p;
		constraint = c;
	}

	public List<Part> getPartList() {
		return allParts;
	}

	public List<Part> getChosenParts() {
		return chosenParts;
	}

	public boolean addChosenPart(Part p) {
		for (Part tmp : this.getChosenParts()) {
			if (tmp.getCategory().getCategory() == p.getCategory().getCategory()) {
				return false;
			}
		}
		chosenParts.add(p);
		return true;

	}

	public boolean removeChosenPart(Part p) {
		for (int i = 0; i < chosenParts.size(); i++) {
			if (chosenParts.get(i).getName() == p.getName()) {
				chosenParts.remove(i);
				return true;
			}
		}
		return false;

	}

	public Constraints getConstraints() {
		return constraint;
	}

	public void choosePart(Part p) {
		this.addChosenPart(p);

	}

	public boolean ValidateConfiguration() {
		// check if part is in part list
		boolean found = true;

		if (this.getChosenParts().size() != this.getConstraints().getNumberOfParts()) {

			return false;
		}

		for (Part p : this.getChosenParts()) {
			// check incompatibility
			for (Part tmp : this.getChosenParts()) {
				if (!this.getConstraints().areCompatible(p, tmp)) {
					System.out.println("incompatibility");
					return false;
				}
			}
			// check requirement
			for (int i = 0; i < this.getConstraints().getRequirements().size(); i++) {
				if (this.getConstraints().getRequirements().get(i).getPart1().getName() == p.getName()) {
					found = false;

					for (Part tmp2 : this.getChosenParts()) {
						if (tmp2.getName() == this.getConstraints().getRequirements().get(i).getPart2().getName()) {

							found = true;

						}
					}

					if (found == false) {
						return false;
					}
				}
			}

		}

		return true;
	}
}
