package Category;

public class EngineImpl implements Category, Engine {
	private String category;

	public EngineImpl() {
		category = "Engine";
	}

	public String getName() {
		return category;
	}

}
