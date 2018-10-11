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

    //TODO: If the goneRange = Half, enable the swAMPM
    //TODO: Transition the gone status button AND AM/PM switch to spinner
    //TODO: Status button text: Here or Gone for
    //TODO: Spinner options: All day, Morning, Afternoon, Random
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
            //Cannot use theRow directly, else will only work for the last row
            TextView theName = theRow.userName;
            ToggleButton userStatus = theRow.userStatus;
            final ToggleButton goneStatus = theRow.goneRange;
            Switch AMPM = theRow.AMorPM;

            theName.setText(users[position]);

            userStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goneStatus.setEnabled(!goneStatus.isEnabled());
                    Log.i(tag, "Click 5");

                }
            });

            return myView;
        }
    }
}
