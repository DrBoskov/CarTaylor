import static org.junit.jupiter.api.Assertions.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class testCarTaylor {

	@Test
	void test() {
		Category cat = new Category();
		ArrayList<Part> allParts = new ArrayList<Part>();

		Part tmp = new Part(new Category(cat.engine), "EG100", "Gasoline, 100 kW");
		allParts.add(tmp);
		tmp = new Part(new Category(cat.engine), "EG133", "Gasoline, 133 kW");
		allParts.add(tmp);
		tmp = new Part(new Category(cat.engine), "EG210", "Gasoline, 210 kW");
		allParts.add(tmp);
		tmp = new Part(new Category(cat.engine), "ED110", "Diesel, 110 kW");
		allParts.add(tmp);
		tmp = new Part(new Category(cat.engine), "ED180", "diesel, 180 kW");
		allParts.add(tmp);
		tmp = new Part(new Category(cat.engine), "EH120", "Gasoline/electric hybrid, 120 kW");
		allParts.add(tmp);
		
		tmp = new Part(new Category(cat.transmission), "TM5", "Manual, 5 gears");
		allParts.add(tmp);
		tmp = new Part(new Category(cat.transmission), "TM6", "Manual, 6 gears");
		allParts.add(tmp);
		tmp = new Part(new Category(cat.transmission), "TA5", "Automatic, 5 gears");
		allParts.add(tmp);
		tmp = new Part(new Category(cat.transmission), "TS6", "Automatic, 6 gears");
		allParts.add(tmp);
		tmp = new Part(new Category(cat.transmission), "TSF7", "Sequential, 7 gears, 4 wheels drive");
		allParts.add(tmp);
		tmp = new Part(new Category(cat.transmission),"TC120","Converter, 120 kW");
		allParts.add(tmp);
		
		tmp = new Part(new Category(cat.exterior), "XC", "Classic paint");
		allParts.add(tmp);
		tmp = new Part(new Category(cat.exterior), "XM", "Metallic paint");
		allParts.add(tmp);
		tmp = new Part(new Category(cat.exterior), "XS", "Red paint and sport decoration");
		allParts.add(tmp);
		
		tmp = new Part(new Category(cat.interior), "IN", "Standard interior");
		allParts.add(tmp);
		tmp = new Part(new Category(cat.interior), "IH", "High-end Interior");
		allParts.add(tmp);
		tmp = new Part(new Category(cat.interior), "IS", "Sport finish");
		allParts.add(tmp);
		
		

		assertEquals(0, 0, "test");
	}

}
