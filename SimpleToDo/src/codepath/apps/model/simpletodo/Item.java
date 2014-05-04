package codepath.apps.model.simpletodo;

public class Item {
	private String name;
	private String description;
	private Boolean flag;
	
	public Item(String name, String description){
		this.name = name;
		this.description = description;
		this.flag = false;
	}
	
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
