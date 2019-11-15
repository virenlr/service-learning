package in.christuniversity.servicelearning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    public static final String FIRST_OPEN = "first_open";
    public static final String PREFERENCE_FILE = "preference_file";
    public static final String LANGUAGE_SELECTED = "language_selected";
    public static final String MENU_ITEM_SELECTED = "menu_item_selected";

    private static final int DELAY_TIME_SHORT = 1000;
    private static final int DELAY_TIME_LONG = 3000;

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final SharedPreferences sharedpreferences = getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        int firstOpen = sharedpreferences.getInt(FIRST_OPEN, 0);

        if (firstOpen == 0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, IntroductionActivity.class);
                    startActivity(i);
                    finish();
                }
            }, DELAY_TIME_LONG);


        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }, DELAY_TIME_SHORT);
        }
    }
}
