/*
* View that holds the user name, the status button, and gone options
* */
package com.example.cpitcel.dispatch;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class RowView {

    TextView userName = null;
    ToggleButton userStatus = null;
    Spinner goneOption = null;


    RowView (View theView) {
        this.userName = theView.findViewById(R.id.txtName);
        this.userStatus = theView.findViewById(R.id.btnStatus);
        this.goneOption = theView.findViewById(R.id.goneOptions);

    }
}
