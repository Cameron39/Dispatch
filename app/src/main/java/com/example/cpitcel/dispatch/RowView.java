package com.example.cpitcel.dispatch;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class RowView {

    TextView userName = null;
    ToggleButton userStatus = null;
    Spinner goneOption = null;

    //ToggleButton goneRange = null;
    //Switch AMorPM = null;

    //TODO: Clean this up!
    RowView (View theView) {
        this.userName = theView.findViewById(R.id.txtName);
        this.userStatus = theView.findViewById(R.id.btnStatus);
        this.goneOption = theView.findViewById(R.id.goneOptions);
        //this.goneRange = theView.findViewById(R.id.goneOptions);
        //this.AMorPM = theView.findViewById(R.id.swAMPM);
    }
}
