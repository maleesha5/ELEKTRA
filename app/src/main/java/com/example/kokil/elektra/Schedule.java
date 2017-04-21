package com.example.kokil.elektra;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

import static android.R.id.toggle;
import static android.content.Context.ALARM_SERVICE;
//import static com.example.kokil.elektra.R.id.time;

/**
 * Created by kokil on 22/01/2017.
 */

public class Schedule extends Fragment {
    TimePicker alarmTimePicker;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    long time;

    Button b_pick,cancel;
    TextView result;
    TextView result2;
    TextView result3;
    TextView result4;
    CountDownTimer countDownTimer;

    int day,month,year,hour,minute,second;                      //current date variables
    int dayFinal,monthFinal,yearFinal,hourFinal,minuteFinal;    //user selected date variables
    int dayPre,monthPre,yearPre,hourPre,minutePre,secondPre;     // current date in miliseconds variables
    int hourPas,minutePas,monthPas,dayPas;                      //user selected date in miliseconds
    int totalPre,totalPas,difference;                           //date difference and total variables



    private View.OnClickListener btnOnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {  // checking button is clicked
            switch(v.getId()){
                case R.id.b_pick:
                    start();             //start button
                    break;
                case R.id.cancel:
                    cancel();              //cancel button
                    break;

            }

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b_pick=(Button) findViewById(R.id.b_pick);
        b_pick.setOnClickListener(btnOnClickListener);
        cancel=(Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(btnOnClickListener);
        result=(TextView) findViewById(R.id.result);
        result2=(TextView) findViewById(R.id.result2);
        result3=(TextView) findViewById(R.id.result3);
        result4=(TextView) findViewById(R.id.result4);

      /*  b_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                start();



            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cancel();



            }
        });*/
    }


    private void start(){   // setting date in date picker to current date

        Calendar c= Calendar.getInstance();
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH)+1;
        day=c.get(Calendar.DAY_OF_MONTH);
        hour=c.get(Calendar.HOUR_OF_DAY);
        minute=c.get(Calendar.MINUTE);
        second=c.get(Calendar.SECOND);




        result2.setText(year+" "+month+" "+day+ " "+hour+ " "+minute+ " "
                +second);

        DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this, MainActivity.this, year, month, day);
        datePickerDialog.show();

    }
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {  //user selected month day

        // yearFinal=year;
        monthFinal=monthOfYear;
        dayFinal=dayOfMonth;

        //Calendar c=Calendar.getInstance();

        TimePickerDialog timePickerDialog=new TimePickerDialog(MainActivity.this, MainActivity.this,hour, minute,
                DateFormat.is24HourFormat(this));
        timePickerDialog.show();


    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {  // user selected hour and minute
        hourFinal=hourOfDay;
        minuteFinal=minute;








        result.setText("Year :"+yearFinal+"\nMonth :"+monthFinal+"\nDay :"+day+"\nHour :"+hourFinal+"\nMinute :"+minuteFinal);
        //result2.setText(yearFinal);

        current();
    }

    private void current() {

        Calendar c= Calendar.getInstance();
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH)+1;
        day=c.get(Calendar.DAY_OF_MONTH);
        hour=c.get(Calendar.HOUR_OF_DAY);
        minute=c.get(Calendar.MINUTE);
        second=c.get(Calendar.SECOND);

        //default values to convert month,day,hour,minute and seconds into miliseconds

        final int month_def=262800288*10;
        final int day_def=86400000;
        final int hour_def=3600000;
        final int minute_def=60000;
        final int sec_def=1000;




        result4.setText(year+" "+month+" "+day+ " "+hour+ " "+minute+ " "
                +second);

        monthPre=month*month_def;       //current month in miliseconds
        dayPre=day*day_def;             //current day in miliseconds
        hourPre=hour*hour_def;          //current hour in miliseconds
        minutePre=minute*minute_def;    //current minute in miliseconds
        secondPre=second*sec_def;       //current second in miliseconds

        totalPre=hourPre+minutePre+secondPre+monthPre+dayPre;   //current date in miliseconds

        monthPas=monthFinal*month_def;              //user entered month in miliseconds
        dayPas=dayFinal*day_def;                    //user entered day in miliseconds
        hourPas=hourFinal*hour_def;                 //user entered hour in miliseconds
        minutePas=minuteFinal*minute_def;           //user entered minute in miliseconds

        totalPas=hourPas+minutePas+monthPas+dayPas;  // user entered date in miliseconds

        difference=(totalPas-totalPre);         //date difference in miliseconds
        result3.setText("the dif ="+difference);


        if(difference>0) {
            count();
        }else{
            result3.setText("Incorrect Input,,");
        }


    }

    private void count() {
        countDownTimer : new CountDownTimer(difference,1000) {      // setting countdown time as date difference in miliseconds
            @Override
            public void onTick(long millisUntilFinished) {

                result3.setText("count"+millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                result3.setText("finished"); // on finish set text to finished
                //  Vibrator v=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                // v.vibrate(500);
                // countDownTimer.cancel();

            }
        }.start();  // starting the countdown timer
    }


    // cancel countdown
    public void cancel(){
        result2.setText("canceled");
        countDownTimer.cancel();
/*if(countDownTimer!=null) {
    countDownTimer.cancel();
    result3.setText("canceled");
    countDownTimer = null;
}*/





    }
}
