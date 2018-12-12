import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class testCarTaylor {

	@Test
	void test() {
		Category cat = new Category();
		ArrayList<Part> chosenParts = new ArrayList<Part>();

		ArrayList<Part> allParts = new ArrayList<Part>();
		// CREATING THE LIST OF AVAILABLE PARTS
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
		tmp = new Part(new Category(cat.transmission), "TC120", "Converter, 120 kW");
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

		// CREATE INCOMPATIBILITIES
		ArrayList<Incompatibility> incomp = new ArrayList<>();
		incomp.add(new Incompatibility(new Part(new Category(cat.transmission), "TA5", "Automatic, 5 gears"),
				new Part(new Category(cat.engine), "EG100", "Gasoline, 100 kW")));
		incomp.add(new Incompatibility(
				new Part(new Category(cat.transmission), "TSF7", "Sequential, 7 gears, 4 wheels drive"),
				new Part(new Category(cat.engine), "EG100", "Gasoline, 100 kW")));
		incomp.add(new Incompatibility(
				new Part(new Category(cat.transmission), "TSF7", "Sequential, 7 gears, 4 wheels drive"),
				new Part(new Category(cat.engine), "EG133", "Gasoline, 133 kW")));
		incomp.add(new Incompatibility(
				new Part(new Category(cat.transmission), "TSF7", "Sequential, 7 gears, 4 wheels drive"),
				new Part(new Category(cat.engine), "ED110", "Diesel, 110 kW")));
		incomp.add(new Incompatibility(new Part(new Category(cat.exterior), "XC", "Classic paint"),
				new Part(new Category(cat.engine), "EG210", "Gasoline, 210 kW")));
		incomp.add(new Incompatibility(new Part(new Category(cat.exterior), "XM", "Metallic paint"),
				new Part(new Category(cat.engine), "EG100", "Gasoline, 100 kW")));
		incomp.add(new Incompatibility(new Part(new Category(cat.exterior), "XS", "Red paint and sport decoration"),
				new Part(new Category(cat.engine), "EG100", "Gasoline, 100 kW")));
		incomp.add(new Incompatibility(new Part(new Category(cat.interior), "IS", "Sport finish"),
				new Part(new Category(cat.engine), "EG100", "Gasoline, 100 kW")));
		incomp.add(new Incompatibility(new Part(new Category(cat.interior), "IS", "Sport finish"),
				new Part(new Category(cat.transmission), "TM5", "Manual, 5 gears")));

		// CREATE REQUIREMENTS
		ArrayList<Requirement> require = new ArrayList<>();
		require.add(new Requirement(new Part(new Category(cat.engine), "EH120", "Gasoline/electric hybrid, 120 kW"),
				new Part(new Category(cat.transmission), "TC120", "Converter, 120 kW")));
		require.add(new Requirement(new Part(new Category(cat.transmission), "TC120", "Converter, 120 kW"),
				new Part(new Category(cat.engine), "EH120", "Gasoline/electric hybrid, 120 kW")));
		require.add(new Requirement(new Part(new Category(cat.exterior), "XS", "Red paint and sport decoration"),
				new Part(new Category(cat.interior), "IS", "Sport finish")));
		require.add(new Requirement(new Part(new Category(cat.interior), "IS", "Sport finish"),
				new Part(new Category(cat.exterior), "XS", "Red paint and sport decoration")));
		// CREATE CONSTRAINTS
		Constraints con = new Constraints(4, incomp, require);
		// CREATE CONFIGURATION
		Configuration config = new Configuration(allParts, chosenParts, con);
		// CREATE CONFIGURATION EDITOR
		ConfigurationEditor configEdit = new ConfigurationEditor(config);

		assertEquals(allParts, configEdit.getConfiguration().getPartList(), "User can view the list of parts");
		// add a part
		configEdit.getConfiguration().addChosenPart(new Part(new Category(cat.engine), "EG100", "Gasoline, 100 kW"));
		assertEquals(1, configEdit.getConfiguration().getChosenParts().size(),
				"Tests if when the user selects a part it adds it to the configuration");

		// add a second engine
		assertEquals(false,
				configEdit.getConfiguration()
						.addChosenPart(new Part(new Category(cat.engine), "EG210", "Gasoline, 210 kW")),
				"Will refuse to add 2 engines");

		// try to pass validation -> fail
		assertEquals(false, configEdit.getConfiguration().ValidateConfiguration(),
				"added only one part it fails validation");

		// add parts with no problems
		configEdit.getConfiguration().choosePart(new Part(new Category(cat.transmission), "TM5", "Manual, 5 gears"));
		configEdit.getConfiguration().choosePart(new Part(new Category(cat.exterior), "XC", "Classic paint"));
		configEdit.getConfiguration().choosePart(new Part(new Category(cat.interior), "IN", "Standard interior"));
		assertEquals(true, configEdit.getConfiguration().ValidateConfiguration(),
				"Test validation with a correct setup");

		// remove a part
		assertEquals(true,
				configEdit.getConfiguration()
						.removeChosenPart(new Part(new Category(cat.exterior), "XC", "Classic paint")),
				"test that part was correctly removed");

		// add incorrect paint
		configEdit.getConfiguration().addChosenPart(new Part(new Category(cat.exterior), "XM", "Metallic paint"));
		assertEquals(false, configEdit.getConfiguration().ValidateConfiguration(),
				"Test validation with incorrect setup");

		// remove a part and add a part with a requirement

		configEdit.getConfiguration().removeChosenPart(new Part(new Category(cat.engine), "EG100", "Gasoline, 100 kW"));
		configEdit.getConfiguration()
				.addChosenPart(new Part(new Category(cat.engine), "EH120", "Gasoline/electric hybrid, 120 kW"));
		assertEquals(false, configEdit.getConfiguration().ValidateConfiguration(),
				"Test validation with incorrect setup");

		assertEquals(con, configEdit.getConfiguration().getConstraints(), "tests that the constraints can be fetched");
		assertEquals(true,
				configEdit.getConfiguration().getConstraints().addIncompatibilities(
						new Incompatibility(new Part(new Category(cat.exterior), "XC", "Classic paint"),
								new Part(new Category(cat.interior), "IN", "Standard interior"))));
		
		configEdit.getConfiguration().removeChosenPart(new Part(new Category(cat.engine), "EH120", "Gasoline/electric hybrid, 120 kW"));
		configEdit.getConfiguration().addChosenPart(new Part(new Category(cat.engine), "EG100", "Gasoline, 100 kW"));
		assertEquals(false, configEdit.getConfiguration().ValidateConfiguration(),
				"Test validation with incorrect setup");

	}

}
