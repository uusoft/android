package com.fwhere.michael.yespad;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fwhere.michael.yespad.NotePad.Notes;

/**
 * An activity that will edit the title of a note. Displays a floating window
 * with a text field.
 */
public class TitleEditor extends Activity implements View.OnClickListener {

	/**
	 * This is a special intent action that means "edit the title of a note".
	 */
	public static final String EDIT_TITLE_ACTION = "com.android.notepad.action.EDIT_TITLE";

	/**
	 * An array of the columns we are interested in.
	 */
	private static final String[] PROJECTION = new String[] {
			NotePad.Notes._ID, // 0
			NotePad.Notes.TITLE, // 1
	};
	/** Index of the title column */
	private static final int COLUMN_INDEX_TITLE = 1;

	/**
	 * Cursor which will provide access to the note whose title we are editing.
	 */
	private Cursor mCursor;

	/**
	 * The EditText field from our UI. Keep track of this so we can extract the
	 * text when we are finished.
	 */
	private EditText mText;

	/**
	 * The content URI to the note that's being edited.
	 */
	private Uri mUri;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.title_editor);

		// Get the uri of the note whose title we want to edit
		mUri = getIntent().getData();

		// Get a cursor to access the note
		mCursor = managedQuery(mUri, PROJECTION, null, null, null);

		// Set up click handlers for the text field and button
		mText = (EditText) this.findViewById(R.id.title);
		mText.setOnClickListener(this);

		Button b = (Button) findViewById(R.id.ok);
		b.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();

		// Initialize the text with the title column from the cursor
		if (mCursor != null) {
			mCursor.moveToFirst();
			mText.setText(mCursor.getString(COLUMN_INDEX_TITLE));
		}
	}

	@Override
	protected void onPause() {
		super.onPause();

		if (mCursor != null) {
			// Write the title back to the note
			ContentValues values = new ContentValues();
			values.put(Notes.TITLE, mText.getText().toString());
			getContentResolver().update(mUri, values, null, null);
		}
	}

	public void onClick(View v) {
		// When the user clicks, just finish this activity.
		// onPause will be called, and we save our data there.
		finish();
	}
}
