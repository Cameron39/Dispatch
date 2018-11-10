//CREDIT TO https://github.com/llollox/Android-Toggle-Switch
//Other possible: https://github.com/Angads25/android-toggle

package com.example.cpitcel.dispatch;

import android.app.ListActivity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.graphics.ColorUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.llollox.androidtoggleswitch.widgets.ToggleSwitch;

import java.lang.reflect.Array;
import java.util.List;

public class MainActivity extends ListActivity {

    private static final String[] users = {"Doug","Bill", "Matt","Chris", "Shaq","Nathan","Tim",
                                            "Alex","Brandon"};
    private static final String tag = "DISPATCH:";

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

            if (theRow == null){ //only do if it does not exist, so no resource wasting
                theRow = new RowView(myView);
                myView.setTag(theRow);
            }

            //Assign values for theRow
            //Cannot use theRow directly, else will only work for the last row
            final TextView theName = theRow.userName;
            final ToggleButton userStatus = theRow.userStatus;
            final Spinner goneOptions = theRow.goneOption; //final or global, pick one

            theName.setText(users[position]);
            goneOptions.setEnabled(false);

            userStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goneOptions.setEnabled(!goneOptions.isEnabled());
                    if (!goneOptions.isEnabled()) {
                        goneOptions.setSelection(0, true);
                    } else {
                        goneOptions.performClick();
                    }
                }
            });

            goneOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   switch (position) {
                       case 0: //"Nothing"
                           goneOptions.setBackgroundColor(Color.alpha(900));
                           userStatus.setChecked(false);
                           goneOptions.setEnabled(false);
                           break;
                       case 1: //All Day
                           goneOptions.setBackgroundColor(Color.RED);
                           break;
                       case 2: //Morning
                           goneOptions.setBackgroundColor(Color.argb(	255,255,140,0));
                           break;
                       case 3: //Afternoon
                           goneOptions.setBackgroundColor(Color.MAGENTA);
                           break;
                       case 4: //Sporatic
                           goneOptions.setBackgroundColor(Color.BLUE);
                           break;
                       default: //None
                           goneOptions.setBackgroundColor(Color.alpha(900));
                           break;
                   }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            return myView;
        }
    }
}
