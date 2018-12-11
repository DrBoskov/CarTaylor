import java.io.IOException;
import java.io.PrintStream;

public interface Configuration {
	public void addPart(PartImpl p);
	public boolean removePart(PartImpl p);
	public void addRestriction(Constraint c);
	public boolean removeRestriction(Constraint c);
	public boolean verify();
	public int totalCost();
	public void printDescription(PrintStream p) throws IOException;
	
}
	
