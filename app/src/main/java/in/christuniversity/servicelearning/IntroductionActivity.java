package in.christuniversity.servicelearning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static in.christuniversity.servicelearning.SplashActivity.FIRST_OPEN;
import static in.christuniversity.servicelearning.SplashActivity.LANGUAGE_SELECTED;
import static in.christuniversity.servicelearning.SplashActivity.PREFERENCE_FILE;

public class IntroductionActivity extends AppCompatActivity {
    private int mBackCount = 0;
    SharedPreferences sharedpreferences;

    @Override
    public void onBackPressed() {
        mBackCount++;

        if (mBackCount == 1) {
            Toast.makeText(this, getString(R.string.back_to_exit), Toast.LENGTH_SHORT).show();
        } else if (mBackCount == 2) {
            finishAffinity();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        sharedpreferences = getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putInt(FIRST_OPEN, 1);
        editor.apply();

        switch (view.getId()) {
            case R.id.kannada_button:
                if (checked) {
                    editor.putInt(LANGUAGE_SELECTED, 1);
                    editor.apply();
                }
                break;
            case R.id.tamil_button:
                if (checked) {
                    editor.putInt(LANGUAGE_SELECTED, 2);
                    editor.apply();
                }
                break;
            case R.id.hindi_button:
                if (checked) {
                    editor.putInt(LANGUAGE_SELECTED, 3);
                    editor.apply();
                }
                break;
        }

        Intent i = new Intent(IntroductionActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
