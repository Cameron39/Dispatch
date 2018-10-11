package com.example.cpitcel.dispatch;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.util.Log;
import android.widget.ListAdapter;

public class MainActivity extends ListActivity {

    private static final String[] users = {"Doug","Bill", "Matt","Chris", "Shaq","Nathan","Tim",
                                            "Alex","Brandon"};
    private static final String tag = "Dev:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);
        Log.i(tag, "onCreate");
        setListAdapter(new RowAdapter());
    }

    public class RowAdapter extends ArrayAdapter<String> {
        RowAdapter() {
            super(MainActivity.this, R.layout.myrow, users);
        }

        public View getView(int position, View theView, ViewGroup parentView){
            View myView = theView;

            if (myView == null) { //only do if it does not exist, so no resource wasting
                LayoutInflater inflaterator = getLayoutInflater();
                myView = inflaterator.inflate(R.layout.myrow, parentView, false);
            }

            RowView theRow = (RowView) myView.getTag();

            if (theRow == null){
                theRow = new RowView(myView);
                myView.setTag(theRow);
            }

            //Assign values for theRow
            theRow.userName.setText(users[position]);

            return theView;
        }
    }
}
