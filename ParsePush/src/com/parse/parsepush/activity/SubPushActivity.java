package com.parse.parsepush.activity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.parsepush.R;

public abstract class SubPushActivity extends ActionBarActivity {

	protected static int totalSteps = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		stepInit();
		viewInit();
    
    initialize();
	}

	private void stepInit() {
		totalSteps = onStepsCreated();
	}

	protected abstract int onStepsCreated();

	private void viewInit() {
		ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar1);
		pb.setIndeterminate(false);
		pb.setMax(totalSteps);
	}

	private void asyncInit() {
		AsyncTask<Void, Integer, Void> a = new AsyncTask<Void, Integer, Void>() {

			@Override
      protected void onPreExecute() {
        super.onPreExecute();
      }
      
      @Override
			protected Void doInBackground(Void... params) {
				for (int step = 0; step < totalSteps; step++) {
					final int currentStep = step + 1;
					// Log.d("TAG", "STEP " + currentStep);
					performInBackground(currentStep, totalSteps);
					publishProgress(currentStep);
				}
				return null;
			}

			@Override
			protected void onProgressUpdate(Integer... values) {
				super.onProgressUpdate(values);
				update(values[0]);
			}

			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				done();
			}
		};
		a.execute();
	}

	private void update(int value) {
		ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar1);
		pb.setProgress(value);
	}

  protected abstract void initialize();
	/**
	 * STARTS AT 1, NOT 0
	 */
	protected abstract void performInBackground(int currentStep, int totalSteps);

	protected abstract void done();

	protected void deleteColumn(String className, String columnName, String matchesString, String columnToDelete) {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(className);
		query.whereContains(columnName, matchesString);
		try {
			List<ParseObject> foundRecords;
			foundRecords = query.find();

			for (ParseObject record : foundRecords) {
				record.remove(columnToDelete);
				record.save();
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
  protected void addUserRelationToCharacter(String suser, String character) {
		try {
			// final String ameron32Id = "wnSDZMoHvX";
			// final String razId = "OKh7j0PD7A";
			// final String sephiusId = "OEcYqjmqo6";

			ParseUser parseUser = new ParseQuery<ParseUser>("_User")
					.whereContains("username", suser).getFirst();
			Log.d("PIA",
					"PUser.id = "
							+ ((parseUser != null) ? parseUser.getObjectId()
									: "null"));
			ParseObject targetCharacter1 = ParseQuery.getQuery("Character")
					.whereContains("name", character).getFirst();
			ParseRelation<ParseUser> relation1 = targetCharacter1
					.getRelation("owner");
			relation1.add(parseUser);
			targetCharacter1.save();
			Log.d("PIA", "relation [" + suser + " to " + character + "] added");
		} catch (ParseException pEx) {
			pEx.printStackTrace();
			Log.d("PIA", "relation [" + suser + " to " + character + "] failed");
		}
	}
  
  public ParseObject addUserRelationToObject(String object1Type, String object1Name, String relation, String user2Type, String user2Name) {
	  ParseObject targetCharacter1 = null;
		try {
			// final String ameron32Id = "wnSDZMoHvX";
			// final String razId = "OKh7j0PD7A";
			// final String sephiusId = "OEcYqjmqo6";

			ParseUser parseUser = new ParseQuery<ParseUser>(user2Type)
					.whereContains("username", user2Name).getFirst();
			Log.d("PIA",
					"PUser.id = "
							+ ((parseUser != null) ? parseUser.getObjectId()
									: "null"));
			targetCharacter1 = ParseQuery.getQuery(object1Type)
					.whereContains("name", object1Name).getFirst();
			ParseRelation<ParseUser> relation1 = targetCharacter1
					.getRelation(relation);
			relation1.add(parseUser);
//			targetCharacter1.save();
			Log.d("PIA", relation + " [" + user2Name + "{" +user2Type+ "} into " + object1Name + "{" +object1Type+ "}] added");
		} catch (ParseException pEx) {
			pEx.printStackTrace();
			Log.d("PIA", relation + " [" + user2Name + "{" +user2Type+ "} into " + object1Name + "{" +object1Type+ "}] failed");
		}
		return targetCharacter1;
	}
  
  public ParseObject addObjectRelationToObject(String object1Type, String object1Name, String relation, String object2Type, String object2Name) {
	  ParseObject targetCharacter1 = null;
		try {
			// final String ameron32Id = "wnSDZMoHvX";
			// final String razId = "OKh7j0PD7A";
			// final String sephiusId = "OEcYqjmqo6";

			ParseObject parseUser = new ParseQuery<ParseObject>(object2Type)
					.whereContains("name", object2Name).getFirst();
			targetCharacter1 = ParseQuery.getQuery(object1Type)
					.whereContains("name", object1Name).getFirst();
			ParseRelation<ParseObject> relation1 = targetCharacter1
					.getRelation(relation);
			relation1.add(parseUser);
//			targetCharacter1.save();
			Log.d("PIA", relation + " [" + object2Name + "{" +object2Type+ "} into " + object1Name + "{" +object1Type+ "}] added");
		} catch (ParseException pEx) {
			pEx.printStackTrace();
			Log.d("PIA", relation + " [" + object2Name + "{" +object2Type+ "} into " + object1Name + "{" +object1Type+ "}] failed");
		}
		return targetCharacter1;
	}
  
//  protected void addObjectRelationToObject(String objectToAdd, String onKey, String childKey, String objectToAddInto,
//      String onParentKey, String parentKey, String relationship) {
//    try {
//      // final String ameron32Id = "wnSDZMoHvX";
//      // final String razId = "OKh7j0PD7A";
//      // final String sephiusId = "OEcYqjmqo6";
//      
//      ParseObject child = new ParseQuery<ParseObject>(objectToAdd).whereContains(onKey, childKey).getFirst();
//      // Log.d("PIA", "PUser.id = " + ((parseUser != null) ?
//      // parseUser.getObjectId() : "null"));
//      ParseObject parent = ParseQuery.getQuery(objectToAddInto).whereContains(onParentKey, parentKey).getFirst();
//      ParseRelation<ParseObject> relation1 = parent.getRelation(relationship);
//      relation1.add(child);
//      parent.save();
//      // Log.d("PIA", "relation [" + suser + " to " + character + "] added");
//    }
//    catch (ParseException pEx) {
//      pEx.printStackTrace();
//      // Log.d("PIA", "relation [" + suser + " to " + character + "] failed");
//    }
//  }

	protected void pushCharacterImage(String character, String imageName) {
		try {
			ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
					"Character");
			ParseObject parseCharacter = query.whereContains("name", character)
					.getFirst();
			ParseFile imageData = new ParseFile("pic.png", loadFile(imageName));
			parseCharacter.put("profilePic", imageData);
			parseCharacter.save();
			Log.d("PIA", "profilePic [" + imageName + "] for [" + character
					+ "] complete");
		} catch (ParseException e) {
			e.printStackTrace();
			Log.d("PIA", "profilePic [" + imageName + "] for [" + character
					+ "] failed");
		}
	}

	protected void pushActionImage(String action, String imageName) {
		try {
			ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
					"CAction");
			ParseObject parseAction = query.whereContains("action", action)
					.getFirst();
			ParseFile imageData = new ParseFile("pic.png", loadFile(imageName));
			parseAction.put("actionPic", imageData);
			parseAction.save();
			Log.d("PIA", "actionPic [" + imageName + "] for [" + action
					+ "] complete");
		} catch (ParseException e) {
			e.printStackTrace();
			Log.d("PIA", "actionPic [" + imageName + "] for [" + action
					+ "] failed");
		}
	}

	// load file from apps res/raw folder or Assets folder
	private byte[] loadFile(String fileName) {
		byte[] array = null;
		try {
			Resources resources = getResources();
			// Create a InputStream to read the file into
			InputStream iS;

			// get the file as a stream
			iS = resources.getAssets().open(fileName);

			// create a buffer that has the same size as the InputStream
			byte[] buffer = new byte[iS.available()];
			// read the text file as a stream, into the buffer
			iS.read(buffer);
			// create a output stream to write the buffer into
			ByteArrayOutputStream oS = new ByteArrayOutputStream();
			// write this buffer to the output stream
			oS.write(buffer);
			// Close the Input and Output streams
			oS.close();
			iS.close();

			// return the output stream as a String
			array = oS.toByteArray();

			Log.d("PIA", fileName + ": success");
		} catch (IOException e) {
			e.printStackTrace();
			Log.d("PIA", fileName + ": failed");
		}
		return array;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
  protected void doContinue() {
    asyncInit();
  }
}
