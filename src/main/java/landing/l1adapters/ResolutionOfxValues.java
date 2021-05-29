package landing.l1adapters;

public enum ResolutionOfxValues {
	CUSTOMIZED("User-specific rendering"),
	ACCORDING_TO_PIXEL("Every Pixel gets rendered"),
	LOW("Every tenth Pixel is rendered"),
	HIGH("Three values per Pixel are rendered");
	
	ResolutionOfxValues(String resolutionOfxValuesDefinition) {
	}

}
