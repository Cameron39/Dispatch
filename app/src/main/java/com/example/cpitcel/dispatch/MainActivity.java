package com.example.cpitcel.dispatch;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends ListActivity {

    private static final String[] users = {"Doug","Bill", "Matt","Chris", "Shaq","Nathan","Tim",
                                            "Alex","Brandon"};
    private static final String tag = "Dev:";

    RowView theRow; //Needs to be global. Other option is final, which will not work

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);

        RowAdapter myRA = new RowAdapter();
        setListAdapter(myRA);
    }

    //TODO: If the userStatus = Gone, then enable the goneRange
    //TODO: If the goneRange = Half, enable the swAMPM
    public void goneClick(View theView) {
        Log.i(tag, "Click 1");
        //View myView = theView;
        //RowView theRow = (RowView) myView.getTag();
        //theRow.goneRange.setEnabled(theRow.goneRange.isEnabled());
    }

    public class RowAdapter extends ArrayAdapter<String> {
        RowAdapter() {
            super(MainActivity.this, R.layout.myrow, users);
        }

        public View getView(final int position, View theView, ViewGroup parentView){
            View myView = theView;

            if (myView == null) { //only do if it does not exist, so no resource wasting
                LayoutInflater inflaterator = getLayoutInflater();
                myView = inflaterator.inflate(R.layout.myrow, parentView, false);
            }

            theRow = (RowView) myView.getTag();

            if (theRow == null){
                theRow = new RowView(myView);
                myView.setTag(theRow);
            }

            //Assign values for theRow
            TextView theName = theRow.userName;
            ToggleButton userStatus = theRow.userStatus;
            final ToggleButton goneStatus = theRow.goneRange;
            Switch AMPM = theRow.AMorPM;

            theName.setText(users[position]);

            userStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(tag, "Click 4");
                    //theRow.goneRange.setEnabled(!theRow.goneRange.isEnabled());
                    goneStatus.setEnabled(!goneStatus.isEnabled());
                    Log.i(tag, "Click 5");

                }
            });

            return myView;
        }
    }
}
