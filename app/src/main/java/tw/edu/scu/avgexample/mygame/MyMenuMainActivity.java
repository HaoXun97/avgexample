package tw.edu.scu.avgexample.mygame;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWBaseActivity;
import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.library.KWSoundManager;
import tw.edu.scu.avgexample.sample.SampleMenuSwitchSceneActivity;
import tw.edu.scu.avgexample.sample.SampleScene_1_Activity;

public class MyMenuMainActivity extends KWBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_menu_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initLayout();
        KWSoundManager.sharedInstance(this).playBgm(R.raw.kw_bgm_main);
        playShowAnimation();

    }

    private void initLayout() {
        Button menuSampleButton = findViewById(R.id.menuSampleButton);
        Button menuSwitchSceneButton = findViewById(R.id.menuSwitchSceneButton);
        Button menuExitButton = findViewById(R.id.menuExitButton);

        menuSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSample();
            }
        });

        menuSwitchSceneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //switchScene();
                playHideAimationAndSwitchScene();
            }
        });

        menuExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitApp();
            }
        });
    }

    private void startSample() {
        KWSoundManager.sharedInstance(this).stopBgm();
        KWSoundManager.sharedInstance(this).playSound(R.raw.kw_sound_button_click);
        switchSceneActivity(MyScene1Activity.class, R.anim.kw_scene_animation_fade_in, R.anim.kw_scene_animation_zoom_out, 0);
    }

    private void switchScene() {
        //KWSoundManager.sharedInstance(this).stopBgm();
        KWSoundManager.sharedInstance(this).playSound(R.raw.kw_sound_button_click);
        switchSceneActivity(MyMenuSwitchSceneActivity.class, R.anim.kw_scene_animation_slide_in_right, R.anim.kw_scene_animation_slide_out_left, 0);
    }

    private void exitApp() {
        KWSoundManager.sharedInstance(this).playSound(R.raw.kw_sound_button_click);
        KWSoundManager.sharedInstance(this).stopBgm();
        this.finish();
        System.exit(0);
    }

    private void playShowAnimation() {
        startAnimation(findViewById(R.id.menuLogoImageView), R.anim.kw_scene_animation_bounce_slow, 150, null);
        startAnimation(findViewById(R.id.menuCharacterImageView), R.anim.kw_scene_animation_slide_in_down, 250, null);
        startAnimation(findViewById(R.id.menuButtonLinearLayout), R.anim.kw_scene_animation_bounce_slow, 450, null);
    }

    private void playHideAimationAndSwitchScene() {
        startAnimation(findViewById(R.id.menuButtonLinearLayout), R.anim.kw_scene_animation_bounce_hide_slow, 0, null);
        startAnimation(findViewById(R.id.menuLogoImageView), R.anim.kw_scene_animation_bounce_hide_slow, 250, null);
        startAnimation(findViewById(R.id.menuCharacterImageView), R.anim.kw_scene_animation_slide_out_down, 450, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                findViewById(R.id.menuLogoImageView).setVisibility(View.INVISIBLE);
                findViewById(R.id.menuCharacterImageView).setVisibility(View.INVISIBLE);
                findViewById(R.id.menuButtonLinearLayout).setVisibility(View.INVISIBLE);

                switchScene();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void startAnimation(View view, int animId, long delay, Animation.AnimationListener listener) {
        Animation animation = AnimationUtils.loadAnimation(this, animId);
        view.requestLayout();
        view.setAnimation(animation);
        animation.setStartOffset(delay);
        animation.setAnimationListener(listener);
        animation.startNow();
    }

    @Override
    protected void initializeExitListener() {
        //super.initializeExitListener();
        super.confirmString = "再按一次返回鍵，將立即離開遊戲哦";
        super.setOnExitListener(new KWBaseActivity.KWOnExitListener() {
            @Override
            public void onExit() {
                exitApp();
            }
        });
    }
}