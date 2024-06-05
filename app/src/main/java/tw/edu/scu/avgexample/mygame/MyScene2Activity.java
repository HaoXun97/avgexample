package tw.edu.scu.avgexample.mygame;

import static tw.edu.scu.avgexample.framework.model.KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT;
import static tw.edu.scu.avgexample.framework.model.KWEventModel.KW_EVENT_CHARACTER_FACING_RIGHT;
import static tw.edu.scu.avgexample.framework.model.KWEventModel.KW_EVENT_CHARACTER_FACING_UNDEFINED;
import static tw.edu.scu.avgexample.framework.model.KWEventModel.KW_EVENT_CHARACTER_POSITION_CENTER;
import static tw.edu.scu.avgexample.framework.model.KWEventModel.KW_EVENT_CHARACTER_POSITION_LEFT;
import static tw.edu.scu.avgexample.framework.model.KWEventModel.KW_EVENT_CHARACTER_POSITION_RIGHT;
import static tw.edu.scu.avgexample.framework.model.KWEventModel.KW_EVENT_CHARACTER_POSITION_UNDEFINED;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.model.KWCharacterModel;
import tw.edu.scu.avgexample.framework.model.KWFirstPersonEventModel;
import tw.edu.scu.avgexample.framework.model.KWFullScreenEventModel;
import tw.edu.scu.avgexample.framework.model.KWOptionEventModel;
import tw.edu.scu.avgexample.framework.model.KWThirdPersonEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

public class MyScene2Activity extends KWBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_scene2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void didFinishAllEvent(String eventIdentifier) {
        super.didFinishAllEvent(eventIdentifier);

        if ("Scene2_Start".equals(eventIdentifier)){

            //背景設定
            Drawable background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.outdoor);

            //角色設定
            KWCharacterModel character = new KWCharacterModel(this, "test", "米奇");

            KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(character, "Hello!\n是我！米奇！");
            event1.setBackgroundDrawable(background);
            KWThirdPersonEventModel event2 = new KWThirdPersonEventModel(character, "對了，要不要進我的妙妙屋！");

            KWFirstPersonEventModel event3 = new KWFirstPersonEventModel("我", "(什麼鬼東西，真的穿越了還是我在做夢？)");

            event1.setBackgroundDrawable(background);
            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);

            eventManager.play("Scene2-1");

        }
        else if ("Scene2-1".equals(eventIdentifier)){

            ArrayList<String> optionArrayList = new ArrayList<>();
            optionArrayList.add("是");
            optionArrayList.add("否");

            KWOptionEventModel event1 = new KWOptionEventModel("Scene2-2_Option", "進去米奇妙妙舞參觀看看嗎？", optionArrayList);

            eventManager.addEvent(event1);
            eventManager.play("Scene2-2");
        }
        else if ("Scene2-2_Yes".equals(eventIdentifier)){
            KWCharacterModel character = new KWCharacterModel(this, "test", "米奇");
            KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(character, "吼吼太好了，我們走！");
            KWThirdPersonEventModel event2 = new KWThirdPersonEventModel(character, "我差點忘記了，要讓妙妙屋出現，我們必須要念奇妙的咒語");
            KWThirdPersonEventModel event3 = new KWThirdPersonEventModel(character, "米斯嘎，木斯嘎，米老鼠！");
            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.play("Scene2-3");
        }
        else if ("Scene2-2_No".equals(eventIdentifier)){
            KWFirstPersonEventModel event1 = new KWFirstPersonEventModel("不要就會失去一個的體驗妙妙屋的機會，請重新選擇");
            eventManager.addEvent(event1);
            eventManager.play("Scene2-2_retry");
        }
        else if ("Scene2-2_retry".equals(eventIdentifier)) {
            eventManager.play("Scene2-1");
        }
        else if ("Scene2-3".equals(eventIdentifier)){
            ArrayList<String> optionArrayList = new ArrayList<>();
            optionArrayList.add("木斯嘎，米斯嘎，米老鼠！");
            optionArrayList.add("米斯嘎，木斯嘎，米老鼠！");
            optionArrayList.add("米老鼠，米斯嘎，木斯嘎！");
            KWOptionEventModel event = new KWOptionEventModel("Scene2-4_Option", "哪個咒語是正確的呢？", optionArrayList);
            eventManager.addEvent(event);
            eventManager.play("Scene2-4");
        }
        else if ("Scene2-4_correct".equals(eventIdentifier)){
            KWCharacterModel character = new KWCharacterModel(this, "test", "米奇");
            KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(character, "回答正確！");
            eventManager.addEvent(event1);
            eventManager.play("Scene2-5");
        }
        else if ("Scene2-4_wrong".equals(eventIdentifier)){
            KWCharacterModel character = new KWCharacterModel(this, "test", "米奇");
            KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(character, "回答錯誤，請重新選擇！");
            eventManager.addEvent(event1);
            eventManager.play("Scene2-4_wrong_retry");
        }
        else if ("Scene2-4_wrong_retry".equals(eventIdentifier)){
            eventManager.play("Scene2-3");
        }
        else if ("Scene2-5".equals(eventIdentifier)){
            KWFullScreenEventModel event1 = new KWFullScreenEventModel("Scene2_End");
            eventManager.addEvent(event1);
            eventManager.play("Scene2_End");
        }
        else if ("Scene2_End".equals(eventIdentifier)){
            switchSceneActivity(MyScene3Activity.class);
        }


    }

    @Override
    protected void onOptionSelected(String identifier, int index) {
        super.onOptionSelected(identifier, index);

        if ("Scene2-2_Option".equals(identifier)) {
            if (index == 0) {
                eventManager.play("Scene2-2_Yes");
            }
            else if (index == 1) {
                eventManager.play("Scene2-2_No");
            }
        }
        else if ("Scene2-4_Option".equals(identifier)) {
            if (index == 0) {
                eventManager.play("Scene2-4_wrong");
            }
            else if (index == 1) {
                eventManager.play("Scene2-4_correct");
                }
            else if (index == 2) {
                eventManager.play("Scene2-4_wrong");
            }
        }

    }

    @Override
    protected void initializeEvent () {
        super.initializeEvent();

        eventManager.play("Scene2_Start");

    }

}