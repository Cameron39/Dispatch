package com.example.cpitcel.dispatch;

import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class RowView {

    TextView userName = null;
    ToggleButton userStatus = null;
    ToggleButton goneRange = null;
    Switch AMorPM = null;

    RowView (View theView) {
        this.userName = theView.findViewById(R.id.txtName);
        this.userStatus = theView.findViewById(R.id.btnStatus);
        this.goneRange = theView.findViewById(R.id.btnOff);
        this.AMorPM = theView.findViewById(R.id.swAMPM);
    }
}
