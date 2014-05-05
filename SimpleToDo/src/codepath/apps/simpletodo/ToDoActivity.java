package codepath.apps.simpletodo;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
    	EditText etDescriptionItem = (EditText)findViewById(R.id.descriptionItem);
    	String name = etNewItem.getText().toString();
    	String description = etDescriptionItem.getText().toString();
    	if(name.length() < 1 || description.length() < 1){
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		builder.setMessage("Both the task name and description are required.");
    		builder.setTitle("Missing Task Name and Description");
    		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
    		AlertDialog dialog = builder.create();
    		dialog.show();
    	}else{
    		Item item = new Item(name, description);
	    	items.addItem(item);
	    	itemsAdapter.add(name);
	    	etNewItem.setText("");
	    	etDescriptionItem.setText("");
	   }
    }
}
