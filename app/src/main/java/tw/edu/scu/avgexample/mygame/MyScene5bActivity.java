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

public class MyScene5bActivity extends KWBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_scene5b);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void initializeEvent() {
        super.initializeEvent();

        eventManager.play("Scene5b_Start");

    }

    @Override
    protected void didFinishAllEvent(String eventIdentifier) {
        super.didFinishAllEvent(eventIdentifier);

        if ("Scene5b_Start".equals(eventIdentifier)) {

            Drawable background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.slide);
            KWCharacterModel mickey = new KWCharacterModel(this, "test", "米奇");
            KWCharacterModel minnie = new KWCharacterModel(this, "minnie", "米妮");

            KWFullScreenEventModel event1 = new KWFullScreenEventModel("米奇米妮都在尋找玩偶，要不要問一下是怎麼樣的玩偶。");
            KWFirstPersonEventModel event2 = new KWFirstPersonEventModel("我", "米奇米妮，我們要尋找的玩偶是什麼樣的呢？");
            KWThirdPersonEventModel event3 = new KWThirdPersonEventModel(mickey, "我們要找一隻小狗的玩偶。");
            KWThirdPersonEventModel event4 = new KWThirdPersonEventModel(minnie, "不如我們從溜滑梯上面溜下來看看有沒有在裡面吧！");

            event1.setBackgroundDrawable(background);

            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.addEvent(event4);
            eventManager.play("Scene5b-1");

        } else if ("Scene5b-1".equals(eventIdentifier)) {

            KWCharacterModel mickey = new KWCharacterModel(this, "test", "米奇");
            KWCharacterModel minnie = new KWCharacterModel(this, "minnie", "米妮");

            KWFullScreenEventModel event1 = new KWFullScreenEventModel("你跟著米奇到了溜滑梯口");
            KWFirstPersonEventModel event2 = new KWFirstPersonEventModel("我", "哇！已經好久沒有玩過溜滑梯了！");
            KWThirdPersonEventModel event3 = new KWThirdPersonEventModel(mickey, "那我先下去啦！米妮下來的時候記得看看有沒有玩偶在裡面喔！");
            KWThirdPersonEventModel event4 = new KWThirdPersonEventModel(minnie, "嗯！");
            KWFirstPersonEventModel event5 = new KWFirstPersonEventModel("兩個人都溜下去了，我也溜下去吧！");

            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.addEvent(event4);
            eventManager.addEvent(event5);
            eventManager.play("Scene5b-2");

        } else if ("Scene5b-2".equals(eventIdentifier)) {

            KWFullScreenEventModel event1 = new KWFullScreenEventModel("就這樣三個人玩了一下午都沒有發現小狗玩偶。");
            KWFullScreenEventModel event2 = new KWFullScreenEventModel("在主角還在思考的時候一陣白光閃過，再次睜眼主角已經回到自己的書桌上了。");
            KWFullScreenEventModel event3 = new KWFullScreenEventModel("THE END!");

            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.play("Scene5b_End");

        } else if ("Scene5b_End".equals(eventIdentifier)) {

            finish();

        }
    }

    @Override
    protected void onOptionSelected(String identifier, int index) {
        super.onOptionSelected(identifier, index);
    }
}