package codepath.apps.simpletodo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import codepath.apps.model.simpletodo.Item;

public class EditToDoActivity extends Activity {
	String key;
	Item item;
	Intent intent;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_to_do);
		Intent i = getIntent();
		Bundle b = i.getExtras();
		item = b.getParcelable(Constants.NAME_KEY);
		EditText name = (EditText)findViewById(R.id.name);
		EditText description = (EditText)findViewById(R.id.description);
		name.setText(item.getName());
		description.setText(item.getDescription());
		key = item.getName();
 	}

	public void deleteItem(View v){
		submitToList(Constants.DELETE);
	}
	
	public void saveItem(View v){
		EditText name = (EditText)findViewById(R.id.name);
		EditText description = (EditText)findViewById(R.id.description);
		if(name.getText().toString().length() < 1 || description.getText().toString().length() < 1){
			MissingNameDescriptionDialog.displayDialog(this);
		}else{
    		item.setName(name.getText().toString());
    		item.setDescription(description.getText().toString());
    		submitToList(Constants.SAVE);
    	}
	}
	
	public void submitToList(String action){
		intent =  new Intent();
    	intent.setClass(this, ToDoActivity.class);
    	intent.putExtra(Constants.NAME_KEY, item);
    	intent.putExtra(Constants.ACTION_KEY, action);
    	intent.putExtra(Constants.KEY, key);
		setResult(RESULT_OK, intent);
		finish();
	}
}
