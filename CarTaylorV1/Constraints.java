import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constraints {

	private int numberOfParts;
	private List<Incompatibility> incomp;
	private List<Requirement> require;

	public Constraints() {
		numberOfParts = 0;
		incomp = new ArrayList<Incompatibility>();
		require = new ArrayList<Requirement>();
	}

	public Constraints(int x, List<Incompatibility> i, List<Requirement> r) {
		numberOfParts = x;
		incomp = i;
		require = r;
	}

	public int getNumberOfParts() {
		return numberOfParts;
	}

	public List<Incompatibility> getIncompatibilities() {
		return incomp;
	}

	public List<Requirement> getRequirements() {
		return require;
	}

	public void setRequirements(List<Requirement> r) {
		require = r;
	}

	public void setIncompatibilities(List<Incompatibility> i) {
		incomp = i;
	}

	public void setNumberOfParts(int i) {
		numberOfParts = i;
	}

	public boolean addRequirements(Requirement r) {
		return require.add(r);
	}

	public boolean addIncompatibilities(Incompatibility i) {
		return incomp.add(i);
	}

	public boolean removeRequirements(Requirement r) {
		return require.remove(r);
	}

	public boolean removeIncompatibilities(Incompatibility i) {
		return incomp.remove(i);
	}

	public boolean areCompatible(Part a, Part b) {
		for(Incompatibility i : this.getIncompatibilities()) {
			if(i.getPart1().getName() == a.getName() && i.getPart2().getName() == b.getName()) {
				return false;
			}
		}
		return true;

	}
}
