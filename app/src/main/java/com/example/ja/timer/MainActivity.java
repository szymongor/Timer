package com.example.ja.timer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Timer timer;
    private CountDownTimer cdTimer;
    private boolean disableButtons = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();

        timer = new Timer();





    }

    private void initButtons(){
        Button addBtn1 = (Button) findViewById(R.id.addButton1);
        addBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add1(1, (EditText) findViewById(R.id.editText1));
            }
        });

        Button addBtn2 = (Button) findViewById(R.id.addButton2);
        addBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add2(1,(EditText) findViewById(R.id.editText2));
            }
        });

        Button addBtn3 = (Button) findViewById(R.id.addButton3);
        addBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add1(1,(EditText) findViewById(R.id.editText3));
            }
        });

        Button addBtn4 = (Button) findViewById(R.id.addButton4);
        addBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add1(1,(EditText) findViewById(R.id.editText4));
            }
        });

        Button subBtn1 = (Button) findViewById(R.id.subButton1);
        subBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add1(9, (EditText) findViewById(R.id.editText1));
            }
        });

        Button subBtn2 = (Button) findViewById(R.id.subButton2);
        subBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add2(5,(EditText) findViewById(R.id.editText2));
            }
        });

        Button subBtn3 = (Button) findViewById(R.id.subButton3);
        subBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add1(9,(EditText) findViewById(R.id.editText3));
            }
        });

        Button subBtn4 = (Button) findViewById(R.id.subButton4);
        subBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add1(9,(EditText) findViewById(R.id.editText4));
            }
        });

        Button startBtn = (Button) findViewById(R.id.startButton);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        Button stopBtn = (Button) findViewById(R.id.stopButton);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });

        Button resetBtn = (Button) findViewById(R.id.resetButton);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    private void setTime(){
        int value1 = Integer.parseInt(((EditText) findViewById(R.id.editText1)).getText().toString() );
        int value2 = 10*Integer.parseInt(((EditText) findViewById(R.id.editText2)).getText().toString() );
        int value3 = 60*Integer.parseInt(((EditText) findViewById(R.id.editText3)).getText().toString() );
        int value4 = 600*Integer.parseInt(((EditText) findViewById(R.id.editText4)).getText().toString() );
        timer.setSpan(value1+value2+value3+value4);
    }

    private void setCurrentTime(){
        int value1 = Integer.parseInt(((EditText) findViewById(R.id.editText1)).getText().toString() );
        int value2 = 10*Integer.parseInt(((EditText) findViewById(R.id.editText2)).getText().toString() );
        int value3 = 60*Integer.parseInt(((EditText) findViewById(R.id.editText3)).getText().toString() );
        int value4 = 600*Integer.parseInt(((EditText) findViewById(R.id.editText4)).getText().toString() );
        timer.setCurrentTime(value1+value2+value3+value4);
    }

    private void reset(){
        if(cdTimer != null) {
            cdTimer.cancel();
            cdTimer = null;
        }
        disableButtons = false;
        ((EditText) findViewById(R.id.editText1)).setText(""+timer.getETValue1Reset(), TextView.BufferType.EDITABLE);
        ((EditText) findViewById(R.id.editText2)).setText(""+timer.getETValue2Reset(), TextView.BufferType.EDITABLE);
        ((EditText) findViewById(R.id.editText3)).setText(""+timer.getETValue3Reset(), TextView.BufferType.EDITABLE);
        ((EditText) findViewById(R.id.editText4)).setText(""+timer.getETValue4Reset(), TextView.BufferType.EDITABLE);
        setTime();
    }

    private void add1(int i, EditText et){
        if(disableButtons) return;
        int value = Integer.parseInt( et.getText().toString() );
        value = (value+i)%10;
        String str = Integer.toString(value);
        et.setText(str, TextView.BufferType.EDITABLE);
        setTime();
    }

    private void add2(int i, EditText et){
        if(disableButtons) return;
        int value = Integer.parseInt( et.getText().toString() );
        value = (value+i)%6;
        String str = Integer.toString(value);
        et.setText(str, TextView.BufferType.EDITABLE);
        setTime();
    }

    private void start(){
        if(disableButtons) return;
        cdTimer = new CountDownTimer(timer.getSpan()*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.tick();
                ((EditText) findViewById(R.id.editText1)).setText(""+timer.getETValue1(), TextView.BufferType.EDITABLE);
                ((EditText) findViewById(R.id.editText2)).setText(""+timer.getETValue2(), TextView.BufferType.EDITABLE);
                ((EditText) findViewById(R.id.editText3)).setText(""+timer.getETValue3(), TextView.BufferType.EDITABLE);
                ((EditText) findViewById(R.id.editText4)).setText(""+timer.getETValue4(), TextView.BufferType.EDITABLE);
                Log.d("Timer: ", timer.getValue()+":"+timer.getETValue4()+":"+timer.getETValue3()+":"+timer.getETValue2()+":"+timer.getETValue1());
            }

            @Override
            public void onFinish() {

            }
        };
        cdTimer.start();
        disableButtons = true;
    }

    private void stop(){
        if(!disableButtons) return;
        cdTimer.cancel();
        cdTimer = null;
        setCurrentTime();
        disableButtons = false;
    }



}
