package tw.edu.scu.avgexample.mygame;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWBaseActivity;
import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.model.KWCharacterModel;
import tw.edu.scu.avgexample.framework.model.KWFirstPersonEventModel;
import tw.edu.scu.avgexample.framework.model.KWFullScreenEventModel;
import tw.edu.scu.avgexample.framework.model.KWOptionEventModel;
import tw.edu.scu.avgexample.framework.model.KWPictureEventModel;
import tw.edu.scu.avgexample.framework.model.KWThirdPersonEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

public class MyScene4Activity extends KWBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_scene4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void initializeEvent() {
        super.initializeEvent();

        eventManager.play("Scene4_Start");

    }

    @Override
    protected void didFinishAllEvent(String eventIdentifier) {
        super.didFinishAllEvent(eventIdentifier);

        if ("Scene4_Start".equals(eventIdentifier)) {

            Drawable background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.door_a);

            KWFullScreenEventModel event1 = new KWFullScreenEventModel("跟著米奇進入房間後看見房間還有一隻可愛的小狗");
            event1.setBackgroundDrawable(background);

            eventManager.addEvent(event1);
            eventManager.play("Scene4-1");

        } else if ("Scene4-1".equals(eventIdentifier)) {

            KWCharacterModel character = new KWCharacterModel(this, "test", "米奇");
            Drawable background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.mickey_paper);
            Drawable picture = KWResourceUtils.getDrawableByResourceId(this, R.drawable.paper);

            KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(character, "這是今天我們的朋友送來寄放的小狗");
            KWPictureEventModel event2 = new KWPictureEventModel(picture, "米奇", "可以請你為她制定一個行程嗎？");

            event1.setBackgroundDrawable(background);

            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.play("Scene4-2");

        } else if ("Scene4-2".equals(eventIdentifier)) {

            ArrayList<String> optionArrayList = new ArrayList<>();
            optionArrayList.add("陪他玩");
            optionArrayList.add("洗澡");
            optionArrayList.add("吃午飯");

            KWCharacterModel character = new KWCharacterModel(this, "test", "米奇");

            KWOptionEventModel event1 = new KWOptionEventModel("Scene4-3_Option", "選擇一個行程", optionArrayList);
            KWThirdPersonEventModel event2 = new KWThirdPersonEventModel(character, "太好了！謝謝你為小狗制定了計畫，就讓我們度過這愉快的一天吧！");
            KWFirstPersonEventModel event3 = new KWFirstPersonEventModel("我", "我認為只有我們可能沒辦法好好照顧好小狗");
            KWThirdPersonEventModel event4 = new KWThirdPersonEventModel(character, "我覺得你說的有道理，那我們去拿 mouseketools 吧！");
            KWThirdPersonEventModel event5 = new KWThirdPersonEventModel(character);

            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.addEvent(event4);
            eventManager.addEvent(event5);
            eventManager.play("Scene4-3");

        } else if ("Scene4a".equals(eventIdentifier)) {

            switchSceneActivity(MyScene4aActivity.class);

        } else if ("Scene4b".equals(eventIdentifier)) {

            switchSceneActivity(MyScene4bActivity.class);

        } else if ("Scene4c".equals(eventIdentifier)) {

            switchSceneActivity(MyScene4cActivity.class);

        } else if ("Scene4_End".equals(eventIdentifier)) {

            finish();

        }

    }

    @Override
    protected void onOptionSelected(String identifier, int index) {
        super.onOptionSelected(identifier, index);

        if ("Scene4-3_Option".equals(identifier)) {
            if (index == 0) {
                eventManager.play("Scene4a");
            } else if (index == 1) {
                eventManager.play("Scene4b");
            } else if (index == 2) {
                eventManager.play("Scene4c");
            }
        }
    }
}