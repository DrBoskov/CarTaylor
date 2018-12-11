package Category;

import java.util.List;

public interface Exterior extends Category{
	
	public String getColour();
	public void setColour(String col);
	public List<String> getAvailableColours();
	
}
