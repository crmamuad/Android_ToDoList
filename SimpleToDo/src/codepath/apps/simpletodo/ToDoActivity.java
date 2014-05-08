package codepath.apps.simpletodo;

import java.io.File;

import android.app.Activity;
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
        setupListViewListener();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data ){
    	if(resultCode == RESULT_OK && requestCode == Constants.REQUEST_CODE){
    		Bundle b = data.getExtras();
    		Item item = b.getParcelable(Constants.NAME_KEY);
    		String action = b.getString(Constants.ACTION_KEY);
    		String key = b.getString(Constants.KEY);
    		if(action.equals(Constants.SAVE)){
    			if(key.equals(item.getName())){
    				//key is the same as the item name, so just save item
    				items.setItem(key, item);
    			}else{
    				//remove old key/item
    				itemsAdapter.remove(key);
    				items.removeItem(key);
    				
    				//add new item
    				itemsAdapter.add(item.getName());
    				items.addItem(item);
    			}
    			
    		}else if(action.equals(Constants.DELETE)){
    			items.removeItem(item.getName());
    			itemsAdapter.remove(item.getName());
    		}
    	}
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
    	editIntent = new Intent();
    	editIntent.setClass(this, EditToDoActivity.class);
    	editIntent.putExtra(Constants.NAME_KEY, item);
		startActivityForResult(editIntent, Constants.REQUEST_CODE);
    }
    
	public void addToDoItem(View v){
    	EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
    	EditText etDescriptionItem = (EditText)findViewById(R.id.descriptionItem);
    	String name = etNewItem.getText().toString();
    	String description = etDescriptionItem.getText().toString();
    	if(name.length() < 1 || description.length() < 1){
    		MissingNameDescriptionDialog.displayDialog(this);
    	}else{
    		Item item = new Item(name, description);
	    	items.addItem(item);
	    	itemsAdapter.add(name);
	    	etNewItem.setText("");
	    	etDescriptionItem.setText("");
	   }
    }
}
