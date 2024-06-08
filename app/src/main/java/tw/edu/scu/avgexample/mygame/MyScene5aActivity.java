package tw.edu.scu.avgexample.mygame;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.model.KWCharacterModel;
import tw.edu.scu.avgexample.framework.model.KWFirstPersonEventModel;
import tw.edu.scu.avgexample.framework.model.KWFullScreenEventModel;
import tw.edu.scu.avgexample.framework.model.KWThirdPersonEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

public class MyScene5aActivity extends KWBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_scene5a);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void initializeEvent() {
        super.initializeEvent();

        eventManager.play("Scene5a_Start");

    }

    @Override
    protected void didFinishAllEvent(String eventIdentifier) {
        super.didFinishAllEvent(eventIdentifier);

        if ("Scene5a_Start".equals(eventIdentifier)) {

            Drawable background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.outdoor);
            KWCharacterModel mickey = new KWCharacterModel(this, "test", "米奇");
            KWCharacterModel minnie = new KWCharacterModel(this, "minnie", "米妮");

            KWFullScreenEventModel event1 = new KWFullScreenEventModel("米奇米妮都在尋找玩偶，要不要問一下是怎麼樣的玩偶。");
            KWFirstPersonEventModel event2 = new KWFirstPersonEventModel("我", "米奇米妮，我們要尋找的玩偶是什麼樣的呢？");
            KWThirdPersonEventModel event3 = new KWThirdPersonEventModel(mickey, "我們要找一個蝴蝶結的玩偶。");
            //TODO change text
            KWThirdPersonEventModel event4 = new KWThirdPersonEventModel(minnie, "今天早上不小心弄丟了一下，是x（依找到的圖片更改）色的。");

            event1.setBackgroundDrawable(background);

            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.addEvent(event4);
            eventManager.play("Scene5a-1");

        } else if ("Scene5a-1".equals(eventIdentifier)) {

            Drawable background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.outdoor);
            KWCharacterModel mickey = new KWCharacterModel(this, "test", "米奇");
            KWCharacterModel minnie = new KWCharacterModel(this, "minnie", "米妮");

            //TODO change text
            KWFullScreenEventModel event0 = new KWFullScreenEventModel("在你左顧右盼尋找玩偶的時候瞥到了樹枝上掛著的x色蝴蝶結。");
            KWFirstPersonEventModel event1 = new KWFirstPersonEventModel("我", "米奇米妮快看！是x色的蝴蝶結！");
            KWThirdPersonEventModel event2 = new KWThirdPersonEventModel(minnie, "哇！太好了是我早上弄丟的那個！");
            KWThirdPersonEventModel event3 = new KWThirdPersonEventModel(mickey, "那我們要怎麼拿下來呢？");
            KWFirstPersonEventModel event4 = new KWFirstPersonEventModel("我", "樹邊有一個梯子可以用嗎？");
            KWThirdPersonEventModel event5 = new KWThirdPersonEventModel(minnie, "可以！那米奇米妮幫你固定好梯子可以上去拿下來吧！");
            KWFirstPersonEventModel event6 = new KWFirstPersonEventModel("(我？？)");
            KWFirstPersonEventModel event7 = new KWFirstPersonEventModel("我", "好吧！");

            eventManager.addEvent(event0);
            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.addEvent(event4);
            eventManager.addEvent(event5);
            eventManager.addEvent(event6);
            eventManager.addEvent(event7);
            eventManager.play("Scene5a-2");

        } else if ("Scene5a-2".equals(eventIdentifier)) {

            KWFullScreenEventModel event0 = new KWFullScreenEventModel("把蝴蝶結順利拿下來後一個沒站穩摔了。");
            KWFullScreenEventModel event1 = new KWFullScreenEventModel("再度張開眼主角發現自己是在床上醒來．");
            KWFullScreenEventModel event2 = new KWFullScreenEventModel("(原來只是夢嗎？好真實啊⋯啊頭好痛)");
            KWFullScreenEventModel event3 = new KWFullScreenEventModel("THE END!");

            eventManager.addEvent(event0);
            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.play("Scene5a_End");

        } else if ("Scene5a_End".equals(eventIdentifier)) {

            finish();

        }
    }

    @Override
    protected void onOptionSelected(String identifier, int index) {
        super.onOptionSelected(identifier, index);
    }
}