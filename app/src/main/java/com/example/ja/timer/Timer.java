package com.example.ja.timer;

import android.os.CountDownTimer;

/**
 * Created by Szymon on 2016-11-14.
 */

public class Timer {
    private int timeSpan;
    private int currentTime;

    public Timer(){
        timeSpan = 0;
        currentTime = 0;
    }

    public void setSpan(int span){
        timeSpan = span;
        currentTime = span;
    }

    public void setCurrentTime(int time){
        currentTime = time;
    }

    public int getETValue1(){
        return currentTime%10;
    }

    public int getETValue2(){
        return (currentTime%60)/10;
    }

    public int getETValue3(){
        return (currentTime/60)%10;
    }

    public int getETValue4(){
        return (currentTime/600)%10;
    }

    public int getETValue1Reset(){
        return timeSpan%10;
    }

    public int getETValue2Reset(){
        return (timeSpan%60)/10;
    }

    public int getETValue3Reset(){
        return (timeSpan/60)%10;
    }

    public int getETValue4Reset(){
        return (timeSpan/600)%10;
    }

    public int getValue(){
        return currentTime;
    }

    public void tick(){
        currentTime -= 1;
    }

    public int getSpan(){
        return timeSpan;
    }


}
