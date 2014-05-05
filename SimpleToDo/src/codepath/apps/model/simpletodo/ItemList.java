package codepath.apps.model.simpletodo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ItemList {
	private HashMap<String, Item> toDoItems;
	private File toDoFile;

	public ItemList(File saveFile){
		this.toDoFile = saveFile;	//Set the file path
		this.toDoItems = new HashMap<String,Item>();
		readItems();
	}

	public void addItem(Item item){
		this.toDoItems.put(item.getName(), item);
		saveItems();
	}
	
	public Item getItem(String name){
		return this.toDoItems.get(name);
	}
	
	public void removeItem(String itemName){
		this.toDoItems.remove(itemName);
		saveItems();
	}
	
	public void setItem(String name, Item item){
		this.toDoItems.put(name, item);
		saveItems();
	}
	
	public ArrayList<String> getListNames(){
		return new ArrayList<String>(this.toDoItems.keySet());
	}
	
	public ArrayList<String> getListDescriptions(){
		ArrayList<String> desc = new ArrayList<String>();
		for(Item item : toDoItems.values()){
			desc.add(item.getDescription());
		}
		return desc;
	}
	
    private void readItems() {
        FileInputStream fstream;
        try{
       	 fstream = new FileInputStream(toDoFile);
       	 Scanner sc = new Scanner(new InputStreamReader(fstream));
       	 while(sc.hasNext()){
       		 String line = sc.nextLine();
       		 String[] arr = line.split(":");
       		 String description = "";
       		 if(arr.length > 1){
       			 description = arr[1];
       		 }
       		 Item newItem = new Item(arr[0], description);
       		 
       		 toDoItems.put(arr[0],newItem);
       	 }
        }catch(IOException e){
       	 e.printStackTrace();
        }
      }
       
       public void saveItems() {
           try{
           FileWriter fw = new FileWriter(toDoFile);
       	BufferedWriter bw = new BufferedWriter(fw);
       	for (Item item : toDoItems.values()){
       		bw.write(item.getName() + ":" + item.getDescription());
       		bw.newLine();
       	}
       	bw.close();
       		
       	}catch(IOException e){
       		e.printStackTrace();
       	}
       	
       }
	
}



