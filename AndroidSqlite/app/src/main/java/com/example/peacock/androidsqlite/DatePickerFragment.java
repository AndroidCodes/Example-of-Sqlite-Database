package com.example.peacock.androidsqlite;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;


/**
 * Created by jahid on 12/10/15.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.
        OnDateSetListener {

    private static TextView tv_date = null;

    public static String getDate() {

        if (tv_date != null) {

            String date = tv_date.getText().toString();

            if (date.equals("Select Date")) {

                return "";

            } else {

                return date;

            }
        } else {

            final Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return String.valueOf(day) + "-" + String.valueOf(month) + "-" + String.valueOf(year);

        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);

    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        // Do something with the date chosen by the user
        tv_date = (TextView) getActivity().findViewById(R.id.tv_Date);

        tv_date.setText(view.getDayOfMonth() + "-" + view.getMonth() + "-" + view.getYear());

    }
}