import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class ConfigurationImpl implements Configuration {

	public List<PartImpl> parts;

	public List<Constraint> constraints;

	public ConfigurationImpl(List<PartImpl> p, List<Constraint> c) {
		parts = p;
		constraints = c;
	}

	public void addPart(PartImpl p) {
		parts.add(p);
	}

	public boolean removePart(PartImpl p) {
		return parts.remove(p);

	}

	public void addRestriction(Constraint c) {
		constraints.add(c);
	}

	public boolean removeRestriction(Constraint c) {
		return constraints.remove(c);
	}

	private boolean isIn(String[] tab, String s) {
		for (int i = 0; i < tab.length; i++) {
			if (tab[i].equals(s)) {
				return true;
			}
		}
		return false;
	}

	public boolean verify() {
		// check if there are no broken constraints
		if(parts.size() !=4){
			return false;
		}
		String[] tmp = new String[4];
		for (int i = 0; i < constraints.size(); i++) {
			for (int j = 0; j < parts.size(); j++) {
				if (constraints.get(i).getPartOne() == parts.get(j).getName()) {
					for (int k = 0; k < parts.size(); k++) {
						if (k != i) {
							tmp[k] = parts.get(k).getName();
						}
					}

					if (constraints.get(i).getName() == "incompatibility" && isIn(tmp, constraints.get(i).getPartTwo())
							|| constraints.get(i).getName() == "requirement"
									&& !isIn(tmp, constraints.get(i).getPartTwo())) {
						return false;
					}
				}
			}
		}
		return true;

	}

	public void printDescription(PrintStream writer) throws IOException {
		if (this.verify()) {

			writer.append("<html>" + " <head>" + " <title>My configuration</title>" + " </head>" + " <body> "
					+ "<div> My configuration :	 </div>" + " <br>");
			for (int i = 0; i < parts.size(); i++) {
				writer.append("<div> " + parts.get(i).getCategory().getName() + " : " + parts.get(i).getName());
			}
			writer.append("</body>" + "</html>");
			writer.close();
		}

	}

	public int totalCost() {
		int rep = 0;
		for (int i = 0; i < parts.size(); i++) {
			rep += parts.get(i).getPrice();
		}
		return rep;
	}

}
