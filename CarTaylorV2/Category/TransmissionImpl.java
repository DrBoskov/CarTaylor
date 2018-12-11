package Category;

public class TransmissionImpl implements Category, Transmission {
	private String category;

	public TransmissionImpl() {
		category = "Transmission";
	}

	public String getName() {

		return category;
	}

}