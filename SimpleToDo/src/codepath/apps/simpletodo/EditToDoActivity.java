package codepath.apps.simpletodo;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import codepath.apps.model.simpletodo.Item;
import codepath.apps.model.simpletodo.ItemList;

public class EditToDoActivity extends Activity {

	Item item;
	public ItemList items; 
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_to_do);
		items = new ItemList(new File(getFilesDir() + "todo.txt"));
		Intent i = getIntent();
		String key = i.getStringExtra(ToDoActivity.NAME_KEY);
		item = items.getItem(key);
		EditText name = (EditText)findViewById(R.id.name);
		EditText description = (EditText)findViewById(R.id.description);
		name.setText(item.getName());
		description.setText(item.getDescription());
    	intent =  new Intent();
    	intent.setClass(this, ToDoActivity.class);
	}

	public void deleteItem(View v){
		items.removeItem(item.getName());
		returnToList();
	}
	
	public void saveItem(View v){
		EditText name = (EditText)findViewById(R.id.name);
		EditText description = (EditText)findViewById(R.id.description);
		if(name.getText().toString().length() < 1 || description.getText().toString().length() < 1){
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
    		item.setName(name.getText().toString());
    		item.setDescription(description.getText().toString());
    		items.setItem(name.getText().toString(), item);
    		returnToList();
    	}
	}
	
	public void returnToList(){
		startActivity(intent);
	}
}
