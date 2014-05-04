package codepath.apps.simpletodo;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import codepath.apps.model.simpletodo.*;

public class ToDoActivity extends Activity
{
	ItemList items;
	ArrayAdapter<String> itemsAdapter;
	ListView lvItems;
	public static final String NAME_KEY = "name_key";
	Intent editIntent;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        lvItems = (ListView) findViewById(R.id.lvItems);
        String fileString = getFilesDir() + "todo.txt";
        System.out.println("TODO FILESTRING: " + fileString);
        items = new ItemList(new File(fileString));
        
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items.getListNames());
        lvItems.setAdapter(itemsAdapter);
    	editIntent = new Intent();
    	editIntent.setClass(this, EditToDoActivity.class);
	    setupListViewListener();
    }
    
   
    private void setupListViewListener() {
    	lvItems.setOnItemClickListener(new OnItemClickListener(){
    		@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String removedItem = itemsAdapter.getItem(position);
				Item adjItem = items.getItem(removedItem);
				editToDoActivity(adjItem);
    		}
    	});	
    }
 
    public void editToDoActivity(Item item){
    	editIntent.putExtra(NAME_KEY, item.getName());
		startActivity(editIntent);
    }
    
	public void addToDoItem(View v){
    	EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
    	String toDoItem = etNewItem.getText().toString();
    	String[] arr = toDoItem.split(":");
    	Item item = new Item(arr[0], arr[1]);
    	items.addItem(item);
    	itemsAdapter.add(arr[0]);
    	etNewItem.setText("");
    }
}
