import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import Category.Category;

public class ConfigurationImpl implements Configuration {

	public List<PartImpl> parts;

	public List<Constraint> constraints;
	public List<PartImpl> allParts;

	public ConfigurationImpl(List<PartImpl> a, List<PartImpl> p, List<Constraint> c) {
		parts = p;
		constraints = c;
		allParts = a;
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

	private boolean isIn(List<String> tab, String s) {
		for (int i = 0; i < tab.size(); i++) {
			if (tab.get(i).equals(s)) {
				return true;
			}
		}
		return false;
	}

	public boolean verify() {
		// check if there are no broken constraints

		List<String> tmp = new ArrayList<String>();
		for (int i = 0; i < constraints.size(); i++) {
			for (int j = 0; j < parts.size(); j++) {
				if (constraints.get(i).getPartOne() == parts.get(j).getName()) {
					for (int k = 0; k < parts.size(); k++) {
						if (k != i) {
							tmp.add(parts.get(k).getName());
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

	public List<PartImpl> getCategory(Category c) {
		List<PartImpl> rep = new ArrayList<PartImpl>();
		for (int i = 0; i < allParts.size(); i++) {
			if (allParts.get(i).getCategory().getName() == c.getName()) {
				rep.add(allParts.get(i));
			}
		}
		return rep;
	}

}
