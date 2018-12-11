


// make UI
// integrate price
// integrate html description of the page
// Color of exterior paint

public class ConfigurationEditor {

	private Configuration configuration;

	public ConfigurationEditor() {
		configuration = new Configuration();
	}

	public ConfigurationEditor(Configuration config) {
		configuration = config;

	}
	public Configuration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Configuration config) {
		configuration = config;
	}

}
