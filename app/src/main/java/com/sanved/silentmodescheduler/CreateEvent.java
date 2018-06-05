package com.sanved.silentmodescheduler;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;


/**
 * Created by Sanved on 17-02-2017.
 */
public class CreateEvent extends AppCompatActivity implements View.OnClickListener{

    static final int TIME_DIALOG_ID = 1111;
    static private int hour, minutes, hourEnd, minutesEnd;

    Button from, to;
    Toolbar toolbar;
    TextView tvTimeTo, tvTimeFrom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);

        initVals();

    }

    public void initVals(){

        from = (Button) findViewById(R.id.bFrom);
        to = (Button) findViewById(R.id.bTo);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTimeFrom = (TextView) findViewById(R.id.tvTimeFrom);
        tvTimeTo = (TextView) findViewById(R.id.tvTimeTo);

        // Listeners
        from.setOnClickListener(this);
        to.setOnClickListener(this);

        // Toolbar
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setTitle("Create Event");
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white_36dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bFrom:
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                tvTimeFrom.setText(hourTextCorrection(hourOfDay) + ":" + minuteTextCorrection(minute) + " " + isItAmPM(hourOfDay));
                            }
                        }, hour, minutes, false);
                timePickerDialog.show();
                break;
            case R.id.bTo:
                TimePickerDialog timePickerDialog2 = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                tvTimeTo.setText(hourTextCorrection(hourOfDay) + ":" + minuteTextCorrection(minute) + " " + isItAmPM(hourOfDay));
                            }
                        }, hourEnd, minutesEnd, false);
                timePickerDialog2.show();
                break;
        }
    }


    /*
    *
    *  Methods assisting the TextView displaying the numbers.
    *
    */

    public String minuteTextCorrection(int min){

        String minute = "";

        if(min < 10){
            minute = "0"+min;
        }
        else minute = ""+min;

        //if

        return minute;
    }

    public String hourTextCorrection(int ho){

        int tHour = ho;

        if(ho > 12){
            tHour = ho - 12;
        }

        if(ho == 0){
            tHour = 12;
        }

        return ""+tHour;
    }

    public String isItAmPM(int hour){

        if(hour > 11){
            return "pm";
        }else{
            return "am";
        }

    }

}
