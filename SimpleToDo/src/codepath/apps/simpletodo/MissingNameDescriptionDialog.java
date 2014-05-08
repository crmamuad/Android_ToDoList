package codepath.apps.simpletodo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class MissingNameDescriptionDialog {

	public static void displayDialog(Context c){
		AlertDialog.Builder builder = new AlertDialog.Builder(c);
		builder.setMessage(c.getString(R.string.task_name_required));
		builder.setTitle(c.getString(R.string.missing_task_description));
		builder.setPositiveButton(c.getString(R.string.ok), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
}
