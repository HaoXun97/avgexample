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

public class MyScene3Activity extends KWBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_scene3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void initializeEvent() {
        super.initializeEvent();

        eventManager.play("Scene3_Start");

    }

    @Override
    protected void didFinishAllEvent(String eventIdentifier) {
        super.didFinishAllEvent(eventIdentifier);

        if (("Scene3_Start").equals(eventIdentifier)) {

            Drawable background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.indoor);

            KWFullScreenEventModel event1 = new KWFullScreenEventModel("你跟著米奇一起走進米奇妙妙屋");
            event1.setBackgroundDrawable(background);

            eventManager.addEvent(event1);
            eventManager.play("Scene3-1");

        } else if (("Scene3-1").equals(eventIdentifier)) {

            KWCharacterModel character = new KWCharacterModel(this, "test", "米奇");

            KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(character, "歡迎來到米奇妙妙屋！");
            KWFirstPersonEventModel event2 = new KWFirstPersonEventModel("我", "(就這樣進來了？我都不用付去 Disney Land 的錢嗎？)");
            KWThirdPersonEventModel event3 = new KWThirdPersonEventModel(character, "跟我來吧！有沒有想去哪間房間呢？");
            KWThirdPersonEventModel event4 = new KWThirdPersonEventModel(character, "每一間房間（或空間）都有不同有趣的事情哦！");

            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.addEvent(event4);
            eventManager.play("Scene3-2");

        } else if (("Scene3-2").equals(eventIdentifier)) {

            ArrayList<String> optionArrayList = new ArrayList<>();
            optionArrayList.add("房間 A");
            optionArrayList.add("房間 B");

            KWOptionEventModel event = new KWOptionEventModel("Scene3-3_Option", "請選擇要參觀的房間", optionArrayList);

            eventManager.addEvent(event);
            eventManager.play("Scene3-3");

        } else if (("Scene3-3_a").equals(eventIdentifier)) {

            switchSceneActivity(MyScene4Activity.class);

        } else if (("Scene3-3_b").equals(eventIdentifier)) {

            switchSceneActivity(MyScene5Activity.class);

        }
//        else if (("Scene3_End").equals(eventIdentifier)) {
//            finish();
//        }
    }

    @Override
    protected void onOptionSelected(String identifier, int index) {
        super.onOptionSelected(identifier, index);

        if (("Scene3-3_Option").equals(identifier)) {
            if (index == 0) {
                eventManager.play("Scene3-3_a");
            } else if (index == 1) {
                eventManager.play("Scene3-3_b");
            }
        }
    }

}