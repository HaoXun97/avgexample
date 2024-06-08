package tw.edu.scu.avgexample.mygame;

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

public class MyScene5Activity extends KWBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_scene5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void initializeEvent() {
        super.initializeEvent();

        eventManager.play("Scene5_Start");

    }

    @Override
    protected void didFinishAllEvent(String eventIdentifier) {
        super.didFinishAllEvent(eventIdentifier);

        if ("Scene5_Start".equals(eventIdentifier)) {

            Drawable background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.outdoor_grass);
            KWCharacterModel mickey = new KWCharacterModel(this, "test", "米奇");
            KWCharacterModel minnie = new KWCharacterModel(this, "minnie", "米妮");

            KWFullScreenEventModel event1 = new KWFullScreenEventModel("你跟著米奇走進門後發現到了妙妙屋外面。");
            KWFirstPersonEventModel event2 = new KWFirstPersonEventModel("(不是進門嗎怎麼到外面來了...)");
            KWThirdPersonEventModel event3 = new KWThirdPersonEventModel(mickey, "這是我們的好朋友米妮！");
            KWThirdPersonEventModel event4 = new KWThirdPersonEventModel(minnie, "歡迎你加入我們一起尋寶的遊戲！");
            KWFirstPersonEventModel event5 = new KWFirstPersonEventModel("我", "(要玩尋寶遊戲嗎??)");
            KWThirdPersonEventModel event6 = new KWThirdPersonEventModel(mickey, "我們要尋找一隻可愛的玩偶，它就在米奇妙妙屋外面。");
            KWThirdPersonEventModel event7 = new KWThirdPersonEventModel(minnie, "你覺得想從哪裡開始尋找呢？");

            event1.setBackgroundDrawable(background);

            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.addEvent(event4);
            eventManager.addEvent(event5);
            eventManager.addEvent(event6);
            eventManager.addEvent(event7);
            eventManager.play("Scene5-1");

        } else if ("Scene5-1".equals(eventIdentifier)) {

            ArrayList<String> optionArrayList = new ArrayList<>();
            optionArrayList.add("樹下");
            optionArrayList.add("溜滑梯");

            KWOptionEventModel event = new KWOptionEventModel("Scene5-2_Option", "選擇地點", optionArrayList);

            eventManager.addEvent(event);
            eventManager.play("Scene5-2");

        } else if ("Scene5a".equals(eventIdentifier)) {

            KWCharacterModel minnie = new KWCharacterModel(this, "minnie", "米妮");

            KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(minnie, "那我們就從「樹下」那邊開始找吧！");

            eventManager.addEvent(event1);
            eventManager.play("Scene5a_switch");

        } else if ("Scene5b".equals(eventIdentifier)) {

            KWCharacterModel minnie = new KWCharacterModel(this, "minnie", "米妮");

            KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(minnie, "那我們就從「溜滑梯」那邊開始找吧！");

            eventManager.addEvent(event1);
            eventManager.play("Scene5b_switch");

        } else if ("Scene5a_switch".equals(eventIdentifier)) {

            switchSceneActivity(MyScene5aActivity.class);

        } else if ("Scene5b_switch".equals(eventIdentifier)) {

            switchSceneActivity(MyScene5bActivity.class);

        } else if ("Scene5_End".equals(eventIdentifier)) {

            finish();

        }
    }

    @Override
    protected void onOptionSelected(String identifier, int index) {
        super.onOptionSelected(identifier, index);

        if ("Scene5-2_Option".equals(identifier)) {
            switch (index) {
                case 0:
                    eventManager.play("Scene5a");
                    break;
                case 1:
                    eventManager.play("Scene5b");
                    break;
                default:
                    break;
            }
        }
    }
}