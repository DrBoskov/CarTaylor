import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import Category.*;

class testCarTaylor {

	@Test
	void test() {

		LinkedList<PartImpl> allParts = new LinkedList<PartImpl>();
		LinkedList<PartImpl> chosenParts = new LinkedList<PartImpl>();
		LinkedList<PartImpl> engines = new LinkedList<PartImpl>();
		LinkedList<PartImpl> compare1 = new LinkedList<PartImpl>();

		PartImpl tmp = new PartImpl(new EngineImpl(), "EG100", "Gasoline, 100 kW", 100); // 0
		allParts.add(tmp);
		engines.add(tmp);
		tmp = new PartImpl(new EngineImpl(), "EG133", "Gasoline, 133 kW", 110); // 1
		allParts.add(tmp);
		engines.add(tmp);
		tmp = new PartImpl(new EngineImpl(), "EG210", "Gasoline, 210 kW", 120); // 2
		allParts.add(tmp);
		engines.add(tmp);
		tmp = new PartImpl(new EngineImpl(), "ED110", "Diesel, 110 kW", 130); // 3
		allParts.add(tmp);
		engines.add(tmp);
		tmp = new PartImpl(new EngineImpl(), "ED180", "diesel, 180 kW", 140); // 4
		allParts.add(tmp);
		engines.add(tmp);
		tmp = new PartImpl(new EngineImpl(), "EH120", "Gasoline/electric hybrid, 120 kW", 150); // 5
		allParts.add(tmp);
		engines.add(tmp);
		tmp = new PartImpl(new TransmissionImpl(), "TM5", "Manual, 5 gears", 100); // 6
		allParts.add(tmp);
		tmp = new PartImpl(new TransmissionImpl(), "TM6", "Manual, 6 gears", 110); // 7
		allParts.add(tmp);
		tmp = new PartImpl(new TransmissionImpl(), "TA5", "Automatic, 5 gears", 120);// 8
		allParts.add(tmp);
		tmp = new PartImpl(new TransmissionImpl(), "TS6", "Automatic, 6 gears", 130);// 9
		allParts.add(tmp);
		tmp = new PartImpl(new TransmissionImpl(), "TSF7", "Sequential, 7 gears, 4 wheels drive", 140);// 10
		allParts.add(tmp);
		tmp = new PartImpl(new TransmissionImpl(), "TC120", "Converter, 120 kW", 150);// 11
		allParts.add(tmp);

		tmp = new PartImpl(new ExteriorImpl(), "XC", "Classic paint", 100);// 12
		allParts.add(tmp);
		tmp = new PartImpl(new ExteriorImpl(), "XM", "Metallic paint", 200);// 13
		allParts.add(tmp);
		tmp = new PartImpl(new ExteriorImpl(), "XS", "Red paint and sport decoration", 300);// 14
		allParts.add(tmp);

		tmp = new PartImpl(new InteriorImpl(), "IN", "Standard interior", 100);// 15
		allParts.add(tmp);
		tmp = new PartImpl(new InteriorImpl(), "IH", "High-end Interior", 200);// 16
		allParts.add(tmp);
		tmp = new PartImpl(new InteriorImpl(), "IS", "Sport finish", 300);// 17
		allParts.add(tmp);

		List<Constraint> constraints = new LinkedList<>(Arrays.asList(
				new requirement(allParts.get(5), allParts.get(11)), new requirement(allParts.get(11), allParts.get(5)),
				new requirement(allParts.get(14), allParts.get(17)),
				new requirement(allParts.get(17), allParts.get(14)),

				new incompatibility(allParts.get(8), allParts.get(0)),
				new incompatibility(allParts.get(10), allParts.get(0)),
				new incompatibility(allParts.get(10), allParts.get(1)),
				new incompatibility(allParts.get(10), allParts.get(3)),

				new incompatibility(allParts.get(12), allParts.get(2)),
				new incompatibility(allParts.get(13), allParts.get(0)),
				new incompatibility(allParts.get(14), allParts.get(0)),
				new incompatibility(allParts.get(17), allParts.get(0)),
				new incompatibility(allParts.get(17), allParts.get(6))));

		ConfigurationImpl config = new ConfigurationImpl(allParts, chosenParts, constraints);

		// TEST USER STORIES

		// STORY #1
		assertEquals(config.getCategory(new EngineImpl()), engines, "User story 1");
		// STORY #2
		config.addPart(allParts.get(0));
		compare1.add(allParts.get(0));
		assertEquals(config.parts, compare1, "User story 2");

		// STORY #3

		// 3.1 Invalid Configuration - Required Parts
		config.addPart(allParts.get(11));
		config.addPart(allParts.get(12));
		config.addPart(allParts.get(15));
		assertEquals(config.verify(), false, "User story 3");

		// 3.2 Invalid Configuration - Incompatible Parts
		config.removePart(allParts.get(11));
		config.addPart(allParts.get(10));
		assertEquals(config.verify(), false, "User story 3");

		// 3.3 Valid Configuration
		config.removePart(allParts.get(10));
		config.addPart(allParts.get(7));
		assertEquals(config.verify(), true, "User story 3");

		// STORY #4
		
		config.removePart(allParts.get(7));
		config.removePart(allParts.get(12));
		config.removePart(allParts.get(15));
		assertEquals(config.parts, compare1, "User story 4");

		// STORY #5
		// 5.1 add incompatibility
		
		Constraint c = new incompatibility(allParts.get(0), allParts.get(15));
		config.addRestriction(c);
		config.addPart(allParts.get(7));
		config.addPart(allParts.get(12));
		config.addPart(allParts.get(15));
		assertEquals(config.verify(), false, "User story 5");
		
		// 5.2 remove incompatibility
		config.removeRestriction(c);
		assertEquals(config.verify(), true, "User story 5");
		// 5.3 add requirement
		c = new requirement(allParts.get(0), allParts.get(14));
		config.addRestriction(c);
		assertEquals(config.verify(), false, "User story 5");
		// 5.4 remove requirement
		config.removeRestriction(c);
		assertEquals(config.verify(), true, "User story 5");
		
	
		// //STORY #6
		PrintStream writer;
		try {
			writer = new PrintStream(new File ("description.html"));
			config.printDescription(writer);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//STORY #7
		assertEquals(config.totalCost(), 410, "User story 5");
		
		// //STORY #8
		
		Exterior e = (Exterior)config.parts.get(2).getCategory();
		
		assertEquals(e.getColour(),"black" , "User story 8");
		e.setColour("white");
		assertEquals(e.getColour(),"white" , "User story 8");
		

	}

}
