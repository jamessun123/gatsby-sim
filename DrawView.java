package lul.gatsbysimulator;

/**
 * Created by James on 2/7/2017.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.MotionEvent;
import java.util.*;

public class DrawView extends View{
    private Paint bobRoss;
    private Gatsby gatsby; // this contains the coordinates of his location
    private Timer time;

    private String aMessage;
    private boolean hasFinished;
    private int numTaps;

    public DrawView(Context context){
        super(context);
        bobRoss = new Paint();
        gatsby = new Gatsby();

        hasFinished = false;
        aMessage = "Help Gatsby get to the green light by tapping on the screen!";
        numTaps = 0;

        time = new Timer();
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(hasFinished){
            drawEndMessage(canvas);
        }
        else {
            drawEncouragingMessage(canvas); //prints message at top of screen
            drawGatsby(canvas); // paint gatsby
            drawLight(canvas);  // paint light
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent){
        if(!hasFinished) {
            gatsby.moveGatsby(); // move him to the right when you click

            numTaps++;
            if(numTaps == 1){ // if first tap, then start timer
                scheduleTimer(time, 1500); // you get 1.5 sec for first tap
            }
        }
        invalidate(); // the new repaint?
        return true;
    }

    private void scheduleTimer(final Timer time, final int mili){ // recursively call timer so that you have infinite timer
        if(hasFinished){  // if we're finished, then end the timer task
            System.out.println("end timer");
            return;
        }
        Handler mHandler = new Handler(Looper.getMainLooper());
        Runnable myRunnable = new Runnable(){ // run the feelsbadman, move gatsby back
            @Override
            public void run(){
                gatsby.feelsBadMan();
                if(gatsby.getXBox() <= -200){
                    hasFinished = true;
                }
                System.out.println("right before invalidate");
                invalidate(); // connected to UI thread, repaints
            }
        };

        time.schedule(new TimerTask(){ // now, schedule next move
            @Override
            public void run(){
                scheduleTimer(time, ((750)/numTaps));
            }
        }, mili);
        mHandler.post(myRunnable); // execute the runnable above to move gatsby
    }


    private void drawEndMessage(Canvas canvas){ // game's over, we're going to print the message
        System.out.println("its overrrrr");
        bobRoss.setColor(Color.BLACK);
        bobRoss.setTextSize(80);
        canvas.drawText("Gatsby believed in the green light, the orgastic", 20, 100, bobRoss);
        canvas.drawText("future that year by year recedes before us. It ", 20 ,200, bobRoss);
        canvas.drawText("eluded us then, but that's no matter—", 20, 300, bobRoss);
        canvas.drawText("tomorrow we will run faster, stretch out our arms ", 20, 400, bobRoss);
        canvas.drawText("farther. . . . And one fine morning——", 20, 500, bobRoss);
        canvas.drawText("So we beat on, boats against the current, ", 20, 800, bobRoss);
        canvas.drawText("borne back ceaselessly into the past.", 20, 900, bobRoss);
    }

    private void drawEncouragingMessage(Canvas canvas){ // prints message at top, BMing you
        bobRoss.setColor(Color.BLACK);
        bobRoss.setTextSize(65);
        if(numTaps > 40){
            canvas.drawText("Life was never easy, gotta work hard!", 50, 100, bobRoss);
        }
        else{
            canvas.drawText(aMessage, 50, 100, bobRoss);
        }
    }

    private void drawGatsby(Canvas canvas){ //draw a gatsby, lul
        bobRoss.setColor(Color.rgb(251, 171, 210)); // my specific shade of pink
        canvas.drawRect(gatsby.getXBox(), gatsby.getYBox(),
                200+ gatsby.getXBox(), 200 + gatsby.getYBox(), bobRoss);
        bobRoss.setColor(Color.BLACK);
        bobRoss.setTextSize(50);
        canvas.drawText("Gatsby", gatsby.getXText(),
                gatsby.getYText(), bobRoss);  // middle of box
    }

    private void drawLight(Canvas canvas){ // draw the green light
        bobRoss.setColor(Color.GREEN);
        canvas.drawCircle(1500, 600, 100, bobRoss); // hard coded to go to the right bottom of screen
    }
}
