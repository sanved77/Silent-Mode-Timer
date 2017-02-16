package com.sanved.silentmodescheduler.launchAct;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sanved.silentmodescheduler.R;
import com.sanved.silentmodescheduler.StartScreen;

/**
 * Created by Sanved on 15-02-2017.
 */
public class LaunchScreen extends AppCompatActivity implements View.OnClickListener{

    RelativeLayout rl;
    LinearLayout silvibiv;
    TextView welcometv, infotv, silvibtv, silvibtv2, exctv, exittv;
    ImageView welcomeiv, infoiv, exitiv;
    Button welcomeb, infob, silvib_vib, silvib_sil, excb1, excb2, exitb;

    SharedPreferences prefs;
    SharedPreferences.Editor ed;

    ValueAnimator colorAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_screen);

        initVals();

        boolean isOpeningForTheFirstTime = prefs.getBoolean("firstTime", true);

        if(!isOpeningForTheFirstTime) {
            Intent i = new Intent(this, StartScreen.class);
            startActivity(i);
            finish();
        }

    }

    public void initVals(){

        rl = (RelativeLayout) findViewById(R.id.rl1);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        ed = prefs.edit();

        // Welcome Screen
        welcometv = (TextView) findViewById(R.id.welcome_tv);
        welcomeiv = (ImageView) findViewById(R.id.welcome_iv);
        welcomeb = (Button) findViewById(R.id.welcome_b);
        welcomeb.setOnClickListener(this);

        // Info Screen
        infotv = (TextView) findViewById(R.id.info_tv);
        infoiv = (ImageView) findViewById(R.id.info_iv);
        infob = (Button) findViewById(R.id.info_b);
        infob.setOnClickListener(this);

        // Silvib Screen
        silvibtv = (TextView) findViewById(R.id.silvib_tv);
        silvibtv2 = (TextView) findViewById(R.id.silvib_tv2);
        silvib_vib = (Button) findViewById(R.id.silvib_vib);
        silvib_sil = (Button) findViewById(R.id.silvib_sil);
        silvibiv = (LinearLayout) findViewById(R.id.silvib_iv);
        silvib_vib.setOnClickListener(this);
        silvib_sil.setOnClickListener(this);

        // Exception Screen
        exctv = (TextView) findViewById(R.id.exc_tv);
        excb1 = (Button) findViewById(R.id.exc_b1);
        excb2 = (Button) findViewById(R.id.exc_b2);
        excb1.setOnClickListener(this);
        excb2.setOnClickListener(this);

        // Exit Screen
        exittv = (TextView) findViewById(R.id.exit_tv);
        exitiv = (ImageView) findViewById(R.id.exit_iv);
        exitb = (Button) findViewById(R.id.exit_b);
        exitb.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

        switch(v.getId()){



            // Welcome Screen
            case R.id.welcome_b:
                welcomeToInfo();
                break;



            // Info Screen
            case R.id.info_b:
                infoToSilvib();
                break;



            /* Silvib screen */
            // Silvib Vib
            case R.id.silvib_vib:
                ed.putBoolean("vibrate", true);
                ed.commit();
                silvibToExc();
                break;
            // Silvib Sil
            case R.id.silvib_sil:
                ed.putBoolean("vibrate", false);
                ed.commit();
                silvibToExc();
                break;
            /* Silvib screen ends */



            /* Exception Screen */
            // Add Exception
            case R.id.exc_b1:

                // TODO: 15-02-2017 Add Exception class and remove this block comment
                /*
                Intent i = new Intent(LaunchScreen.this, ExceptionList.class);
                startActivity(i);
                */
                excb2.setText("Next");
                break;
            // Skip pressed
            case R.id.exc_b2:
                excToExit();
                break;
            /* Exception Screen ends */



            // Exit
            case R.id.exit_b:
                exitToStartScreen();
                break;
        }

    }


    public void welcomeToInfo(){

        colorChange(ContextCompat.getColor(this, R.color.colorPrimary), ContextCompat.getColor(this, R.color.purple));

        welcomeb.setVisibility(View.GONE);
        welcomeiv.setVisibility(View.GONE);
        welcometv.setVisibility(View.GONE);

        infob.setVisibility(View.VISIBLE);
        infoiv.setVisibility(View.VISIBLE);
        infotv.setVisibility(View.VISIBLE);

    }

    public void infoToSilvib(){

        infob.setVisibility(View.GONE);
        infoiv.setVisibility(View.GONE);
        infotv.setVisibility(View.GONE);

        silvibtv.setVisibility(View.VISIBLE);
        silvibtv2.setVisibility(View.VISIBLE);
        silvibiv.setVisibility(View.VISIBLE);

    }

    public void silvibToExc(){

        colorChange(ContextCompat.getColor(this, R.color.purple), ContextCompat.getColor(this, R.color.coffee));

        silvibtv.setVisibility(View.GONE);
        silvibtv2.setVisibility(View.GONE);
        silvibiv.setVisibility(View.GONE);

        exctv.setVisibility(View.VISIBLE);
        excb1.setVisibility(View.VISIBLE);
        excb2.setVisibility(View.VISIBLE);

    }

    public void excToExit(){

        colorChange(ContextCompat.getColor(this, R.color.coffee), ContextCompat.getColor(this, R.color.red));

        exctv.setVisibility(View.GONE);
        excb1.setVisibility(View.GONE);
        excb2.setVisibility(View.GONE);

        exitb.setVisibility(View.VISIBLE);
        exitiv.setVisibility(View.VISIBLE);
        exittv.setVisibility(View.VISIBLE);

    }

    public void exitToStartScreen(){

        ed.putBoolean("firstTime", false);
        ed.commit();
        Intent i = new Intent(this, StartScreen.class);
        startActivity(i);
        finish();

    }

    public void colorChange(int colorFrom, int colorTo) {
        colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(400);
        colorAnimation.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                rl.setBackgroundColor((int)animator
                        .getAnimatedValue());
            }

        });
        colorAnimation.start();
    }



}

