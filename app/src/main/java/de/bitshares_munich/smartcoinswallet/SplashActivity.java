package de.bitshares_munich.smartcoinswallet;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Locale;

import de.bitshares_munich.models.AccountDetails;
import de.bitshares_munich.utils.Helper;
import de.bitshares_munich.utils.SupportMethods;
import de.bitshares_munich.utils.TinyDB;

/**
 * Created by qasim on 5/19/16.
 */
public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 500;
    //TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        //supportInvalidateOptionsMenu();
        //this.invalidateOptionsMenu();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkWhereToGo();
            }
        }, SPLASH_TIME_OUT);
    }

    private void checkWhereToGo ()
    {
        TinyDB tinyDB = new TinyDB(getApplicationContext());

        ArrayList<AccountDetails> arrayList = tinyDB.getListObject(getString(R.string.pref_wallet_accounts), AccountDetails.class);
        if (arrayList.size() > 0) {
            moveToMainScreen();
        } else {
            moveToAccountScreen();
        }
    }

    private void moveToMainScreen() {
        /*
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(SplashActivity.this, TabActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
        */

        String pin = Helper.fetchStringSharePref(getApplicationContext(),"pin");

        Intent i = new Intent(SplashActivity.this, TabActivity.class);

        if ( pin != null && !pin.isEmpty() )
        {
            i.putExtra("ask_for_pin",true);
        }
        else
        {
            i.putExtra("ask_for_pin",false);
        }

        startActivity(i);
        finish();
    }

    private void moveToAccountScreen() {

        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, AccountActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
        */

        Intent i = new Intent(SplashActivity.this, AccountActivity.class);
        startActivity(i);
        finish();
    }


}
