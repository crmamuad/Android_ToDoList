package codepath.apps.model.simpletodo;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable{
	private String name;
	private String description;
	private Boolean flag;
	
	public Item(String name, String description){
		this.name = name;
		this.description = description;
		this.flag = false;
	}
	
	public Item(Parcel in) {
		this.name = in.readString();
		this.description = in.readString();
		this.flag = (in.readString().equals("true")) ? true:false;
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

	public static final Parcelable.Creator<Item> CREATOR
		= new Parcelable.Creator<Item>(){
		public Item createFromParcel(Parcel in){
			return new Item(in);
		}
		public Item[] newArray(int size){
			return new Item[size];
		}
	};
		
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(getName());
		dest.writeString(getDescription());
		dest.writeString(getFlag().toString());
		
	}

}
