package gfi.gfichallenge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import gfi.gfichallenge.entities.ScheduledSequence;
import gfi.gfichallenge.entities.Sequence;
import gfi.gfichallenge.entities.SequenceFrame;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    ScheduledSequenceClient scheduledSequenceClient = null;
    ScheduledSequence currentScheduledSequence = null;
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;

    private View mContentView;
    private View mControlsView;
    private boolean mVisible;

    private int code;

    private int mInterval = 3000; // 5 seconds by default, can be changed later
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        Intent intent = getIntent();
        code = Integer.parseInt(intent.getStringExtra("pos"));
        scheduledSequenceClient = new ScheduledSequenceClient(code);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);




        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);

        //update();
        mHandler = new Handler();
        startRepeatingTask();

        /*Event e = new Event();
        e.setStartIntant(1L);
        Animation animation = new Animation();
        List<AnimationFrame> animationFrames = new ArrayList<>();
        AnimationFrame af1 = new AnimationFrame();
        AnimationFrame af2 = new AnimationFrame();
        af1.setColor("#ffff00");
        af2.setColor("#ffffff");
        af1.setTime(2000);
        af2.setTime(5);
        animationFrames.add(af1);
        animationFrames.add(af2);*/

        //animation.setAnimationFrames(animationFrames);
        //e.setAnimation(animation);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            //runEvent(scheduledSequenceClient.getEvent());
            return false;
        }
    };

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };

    private final Handler mHideHandler = new Handler();
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    private void runEvent(final ScheduledSequence e) {
        final TextView textView = (TextView) findViewById(R.id.fullscreen_content);
        if(e == null){
            textView.setText("Loading");
            return;
        }else{
            textView.setText("#" + code);
        }
        Long timeToStart = e.getTimeToStart();
        if (timeToStart > 0) {
            Sequence a = e.getSequence();
            final List<SequenceFrame> sequenceFrames = a.getSequenceFrames();
            final Timer timer = new Timer();
            timer.schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            int i = 0;
                            runFrames(sequenceFrames, i);
                        }
                    }
                    , timeToStart
            );
        }
    }


    private void runFrames(final List<SequenceFrame> sequenceFrames, final int i) {
        final SequenceFrame af = sequenceFrames.get(i);
        FullscreenActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mContentView.setBackgroundColor(Color.parseColor(af.getColor()));

                //getWindow().getDecorView().setBackgroundColor(Color.parseColor(af.getColor()));
            }
        });
        if (i < sequenceFrames.size() - 1) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runFrames(sequenceFrames, i+1);
                }
            }, af.getTime());
        }
        return;
    }

    private void updateEvent() {
        scheduledSequenceClient.refresh();
        if (scheduledSequenceClient.getEvent() == null) {
            return;
        }

        if (currentScheduledSequence == null) {
            currentScheduledSequence = scheduledSequenceClient.getEvent();
            runEvent(currentScheduledSequence);
        }
        else {
            if (!currentScheduledSequence.getUuid().equals(scheduledSequenceClient.getEvent().getUuid())) {
                currentScheduledSequence = scheduledSequenceClient.getEvent();
                runEvent(currentScheduledSequence);
            }
        }
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                updateEvent(); //this function can change value of mInterval.
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }
}