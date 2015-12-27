package dblab.hello;

public class Key {

	String index;
	String keyName;
	
	
	public Key(String index, String keyName) {
		this.index = index;
		this.keyName = keyName;
	}
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	
	
}
