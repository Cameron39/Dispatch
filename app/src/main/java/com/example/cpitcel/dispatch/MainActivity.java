//CREDIT TO https://github.com/Angads25/android-toggle

package com.example.cpitcel.dispatch;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.llollox.androidtoggleswitch.widgets.ToggleSwitch;

import java.lang.reflect.Array;

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

        final ToggleSwitch swDesktop = findViewById(R.id.swDesktop);
        ToggleSwitch swMIS = findViewById(R.id.swMIS);

        swDesktop.setCheckedPosition(0);
        swMIS.setCheckedPosition(0);

        swDesktop.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int i) {
                //swDesktop.setCheckedPosition(Math.abs(i - 1));
                Log.i(tag, "Switching the Desktop Support: " + i);
            }
        });
    }

    //TODO: Adjust name width/text size
    //TODO: Reset the spinner when disabled. Set the value to be "blank", position 0
    //TODO: for people gone, change their name to crossed out?
    //MAYBE: if one of the people in thw switch is GONE, disable their part? Hmmmm....?
    //  NO!  How the hell to deal with AM/PM? Not worth it!
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

            if (theRow == null){ //only do if it does not exist, so no resource wasting
                theRow = new RowView(myView);
                myView.setTag(theRow);
            }

            //Assign values for theRow
            //Cannot use theRow directly, else will only work for the last row
            TextView theName = theRow.userName;
            ToggleButton userStatus = theRow.userStatus;
            final Spinner goneOptions = theRow.goneOption; //final or global, pick one

            theName.setText(users[position]);
            goneOptions.setEnabled(false);

            userStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goneOptions.setEnabled(!goneOptions.isEnabled());
                }
            });

            return myView;
        }
    }
}
