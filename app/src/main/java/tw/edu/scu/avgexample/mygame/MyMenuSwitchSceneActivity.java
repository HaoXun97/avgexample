package tw.edu.scu.avgexample.mygame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWBaseApplication;
import tw.edu.scu.avgexample.sample.SampleGlobalData;
import tw.edu.scu.avgexample.sample.SampleMenuMainActivity;
import tw.edu.scu.avgexample.sample.SampleScene_1_Activity;
import tw.edu.scu.avgexample.sample.SampleScene_2_Activity;
import tw.edu.scu.avgexample.sample.SampleScene_3_Activity;
import tw.edu.scu.avgexample.sample.SampleScene_4_Activity;
import tw.edu.scu.avgexample.sample.SampleScene_5_Activity;
import tw.edu.scu.avgexample.sample.SampleScene_6_Activity;
import tw.edu.scu.avgexample.sample.SampleScene_7_Activity;

public class MyMenuSwitchSceneActivity extends MyBaseSceneActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_menu_switch_scene);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initLayout();

    }

    private void initLayout() {

        Button switchScene1Button = findViewById(R.id.switchScene1Button);
        switchScene1Button.setOnClickListener(this);

        Button switchScene2Button = findViewById(R.id.switchScene2Button);
        switchScene2Button.setOnClickListener(this);

        Button switchScene3Button = findViewById(R.id.switchScene3Button);
        switchScene3Button.setOnClickListener(this);

        Button switchScene4Button = findViewById(R.id.switchScene4Button);
        switchScene4Button.setOnClickListener(this);

        Button switchScene5Button = findViewById(R.id.switchScene5Button);
        switchScene5Button.setOnClickListener(this);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Class targetClass = null;

        final int vId = v.getId();

        if (vId == R.id.switchScene1Button) {
            targetClass = MyScene1Activity.class;
        }
        else if (vId == R.id.switchScene2Button) {
            targetClass = MyScene2Activity.class;
        }
        else if (vId == R.id.switchScene3Button) {
            targetClass = MyScene3Activity.class;
        }
        else if (vId == R.id.switchScene4Button) {
            targetClass = MyScene4Activity.class;
        }
        else if (vId == R.id.switchScene5Button) {
            targetClass = MyScene5Activity.class;
        }
        else if (vId == R.id.backButton) {
            ((ScrollView)findViewById(R.id.scrollView)).smoothScrollTo(0, 0);
            switchSceneActivity(MyMenuMainActivity.class, R.anim.kw_scene_animation_slide_in_left, R.anim.kw_scene_animation_slide_out_right, 0);
        }

//
//        switch (vId) {
//            case R.id.switchAdButton:
//                if (((KWBaseApplication)getApplication()).rewardedVideoAd.isLoaded()) {
//                    ((KWBaseApplication)getApplication()).rewardedVideoAd.show();
//                } else {
//                    ((KWBaseApplication)getApplication()).loadRewardedVideoAd();
//                }
//                break;
//            case R.id.switchScene1Button:
//                targetClass = SampleScene_1_Activity.class;
//                break;
//            case R.id.switchScene2Button:
//                targetClass = SampleScene_2_Activity.class;
//                break;
//            case R.id.switchScene3Button:
//                targetClass = SampleScene_3_Activity.class;
//                break;
//            case R.id.switchScene4Button:
//                targetClass = SampleScene_4_Activity.class;
//                break;
//            case R.id.switchScene5Button:
//                targetClass = SampleScene_5_Activity.class;
//                break;
//            case R.id.switchScene6Button:
//                targetClass = SampleScene_6_Activity.class;
//                break;
//            case R.id.switchScene7Button:
//                if (SampleGlobalData.bmiValue == 0) {
//                    SampleGlobalData.bmiValue = 24;
//                }
//                targetClass = SampleScene_7_Activity.class;
//                break;
//            case R.id.backButton:
//                ((ScrollView)findViewById(R.id.scrollView)).smoothScrollTo(0, 0);
//                switchSceneActivity(SampleMenuMainActivity.class, R.anim.kw_scene_animation_slide_in_left, R.anim.kw_scene_animation_slide_out_right, 0);
//                break;
//        }

        if ( targetClass != null ) {
            switchSceneActivity(targetClass, R.anim.kw_scene_animation_fade_in, R.anim.kw_scene_animation_zoom_out, 0);
        }
    }

}