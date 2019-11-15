package in.christuniversity.servicelearning;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import static in.christuniversity.servicelearning.MainActivity.mBackCount;
import static in.christuniversity.servicelearning.SplashActivity.LANGUAGE_SELECTED;
import static in.christuniversity.servicelearning.SplashActivity.PREFERENCE_FILE;

public class SettingsFragment extends Fragment {
    private SharedPreferences sharedpreferences;

    public SettingsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBackCount = 0;
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.settings_toolbar);
        ((MainActivity) Objects.requireNonNull(getActivity())).setToolbar(toolbar, getString(R.string.settings));

        sharedpreferences = Objects.requireNonNull(getContext()).getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);

        RadioButton mRadioButton1 = view.findViewById(R.id.kannada_button_settings);
        RadioButton mRadioButton2 = view.findViewById(R.id.tamil_button_settings);
        RadioButton mRadioButton3 = view.findViewById(R.id.hindi_button_settings);

        int languageSelected = sharedpreferences.getInt(LANGUAGE_SELECTED, 1);

        if (languageSelected == 3) {
            mRadioButton3.setChecked(true);
        } else if (languageSelected == 2) {
            mRadioButton2.setChecked(true);
        } else {
            mRadioButton1.setChecked(true);
        }

        mRadioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton) view).isChecked();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                if (view.getId() == R.id.kannada_button_settings) {
                    if (checked) {
                        editor.putInt(LANGUAGE_SELECTED, 1);
                        editor.apply();
                    }
                }
            }
        });

        mRadioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton) view).isChecked();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                if (view.getId() == R.id.tamil_button_settings) {
                    if (checked) {
                        editor.putInt(LANGUAGE_SELECTED, 2);
                        editor.apply();
                    }
                }
            }
        });

        mRadioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton) view).isChecked();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                if (view.getId() == R.id.hindi_button_settings) {
                    if (checked) {
                        editor.putInt(LANGUAGE_SELECTED, 3);
                        editor.apply();
                    }
                }
            }
        });

    }
}