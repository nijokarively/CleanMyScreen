package com.nvk.cleanmyscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;

public class IntroActivity extends AppIntro2 {
    private Handler h = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //To enable Wizard mode
        setWizardMode(true);

        SliderPage sliderWelcome = new SliderPage();
        sliderWelcome.setTitle(getString(R.string.intro_welcome_title));
        sliderWelcome.setDescription(getString(R.string.intro_welcome_msg));
        sliderWelcome.setImageDrawable(R.drawable.flat_intro_welcome);
        sliderWelcome.setBgColor(getResources().getColor(R.color.flat_gray));

        SliderPage sliderSwipes = new SliderPage();
        sliderSwipes.setTitle(getString(R.string.intro_swipe_title));
        sliderSwipes.setDescription(getString(R.string.intro_swipe_msg));
        sliderSwipes.setImageDrawable(R.drawable.flat_intro_swipes);
        sliderSwipes.setBgColor(getResources().getColor(R.color.flat_orange));

        SliderPage sliderBack = new SliderPage();
        sliderBack.setTitle(getString(R.string.intro_back_title));
        sliderBack.setDescription(getString(R.string.intro_back_msg));
        sliderBack.setImageDrawable(R.drawable.flat_intro_back2);
        sliderBack.setBgColor(getResources().getColor(R.color.flat_red));

        SliderPage sliderWarning = new SliderPage();
        sliderWarning.setTitle(getString(R.string.intro_warning_title));
        sliderWarning.setDescription(getString(R.string.intro_warning_msg));
        sliderWarning.setImageDrawable(R.drawable.flat_intro_warning);
        sliderWarning.setBgColor(getResources().getColor(R.color.flat_blue));

        SliderPage sliderFeature = new SliderPage();
        sliderFeature.setTitle(getString(R.string.intro_features_title));
        sliderFeature.setDescription(getString(R.string.intro_features_msg));
        sliderFeature.setImageDrawable(R.drawable.flat_intro_features);
        sliderFeature.setBgColor(getResources().getColor(R.color.flat_pink));

        SliderPage sliderDone = new SliderPage();
        sliderDone.setTitle(getString(R.string.intro_done_title));
        sliderDone.setDescription(getString(R.string.intro_done_msg));
        sliderDone.setImageDrawable(R.drawable.flat_intro_done);
        sliderDone.setBgColor(getResources().getColor(R.color.flat_green));

        addSlide(AppIntroFragment.newInstance(sliderWelcome));
        addSlide(AppIntroFragment.newInstance(sliderSwipes));
        addSlide(AppIntroFragment.newInstance(sliderBack));
        addSlide(AppIntroFragment.newInstance(sliderWarning));
        addSlide(AppIntroFragment.newInstance(sliderFeature));
        addSlide(AppIntroFragment.newInstance(sliderDone));



        // OPTIONAL METHODS
        // Override bar/separator color.
//        setBarColor(Color.parseColor("#3F51B5"));
//        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        //showSkipButton(true);
        setProgressButtonEnabled(true);
        setBackButtonVisibilityWithDone(true); //false to hide

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        launchIntroPopup();
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }


    private void launchIntroPopup(){
        new MaterialStyledDialog.Builder(this)
                .setTitle(getString(R.string.disable_intro_title))
                .setDescription(getString(R.string.disable_intro_content))
                .setPositiveText(R.string.yes)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        SharedPreferences getPrefs = PreferenceManager
                                .getDefaultSharedPreferences(getBaseContext());

                        SharedPreferences.Editor e = getPrefs.edit();

                        //  Edit preference to make it false because we don't want this to run again
                        e.putBoolean("firstStart", false);

                        //  Apply changes
                        e.apply();
                        goToMain();
                    }})
                .setNegativeText(R.string.no)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        goToMain();
                    }
                })
                //.setHeaderDrawable(R.drawable.header)
                .setHeaderColor(R.color.flat_blue)
                .setIcon(R.drawable.monkey2)
                .withIconAnimation(true)
                .setCancelable(false)
                .withDialogAnimation(true)
                .show();
    }

    private void goToMain(){
        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
