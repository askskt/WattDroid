package edu.hawaii.wattdroid;


import java.util.ArrayList;
import java.util.List;

import edu.hawaii.wattdroid.BaseFeedParser;
import edu.hawaii.wattdroid.Message;
import edu.hawaii.wattdroid.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

/**
 * ListView  is a ViewGroup  that creates a list of scrollable items.
 * The list items are automatically inserted to the list using a ListAdapter.
 * http://developer.android.com/resources/tutorials/views/hello-listview.html
 * Code adapted from:
 * http://www.warriorpoint.com/blog/2009/07/19/android-simplified-source-
 * code-for-parsing-and-working-with-xml-data-and-web-services-in-android/
 */
public class WattDroid extends ListActivity {
	
	private List<Message> messages;
	
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        loadFeed();
    }

	private void loadFeed(){
    	try{
	    	BaseFeedParser parser = new BaseFeedParser();
	    	messages = parser.parse();
	    	List<String> titles = new ArrayList<String>(messages.size());
	    	for (Message msg : messages){
	    		titles.add(msg.getTitle());
	    	}
	    	ArrayAdapter<String> adapter = 
	    		new ArrayAdapter<String>(this, R.layout.list,titles);
	    	this.setListAdapter(adapter);
    	} catch (Throwable t){
    		Log.e("AndroidNews",t.getMessage(),t);
    	}
    }
    
}