package tw.edu.scu.avgexample.mygame;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

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
import tw.edu.scu.avgexample.framework.model.KWPictureEventModel;
import tw.edu.scu.avgexample.framework.model.KWThirdPersonEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

public class MyScene4cActivity extends MyBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_scene4c);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void initializeEvent() {
        super.initializeEvent();

        eventManager.play("Scene4c_Start");

    }

    @Override
    protected void didFinishAllEvent(String eventIdentifier) {
        super.didFinishAllEvent(eventIdentifier);

        if ("Scene4c_Start".equals(eventIdentifier)) {

            Drawable background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.kitchen);
            Drawable picture = KWResourceUtils.getDrawableByResourceId(this, R.drawable.tudou);
            KWCharacterModel mickey = new KWCharacterModel(this, "test", "米奇");

            KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(mickey, "我們要幫狗狗做午餐但是好像沒有看到可以用的廚具");
            KWFirstPersonEventModel event2 = new KWFirstPersonEventModel("我", "嗯…看來可以找土豆幫幫忙了");
            KWThirdPersonEventModel event3 = new KWThirdPersonEventModel(mickey, "好辦法！那我來叫土豆來吧！oh土豆～");
            KWPictureEventModel event4 = new KWPictureEventModel(picture, "米奇", "就交給你來選擇要哪樣工具了！");

            event1.setBackgroundDrawable(background);

            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.addEvent(event4);
            eventManager.play("Scene4c-1");

        } else if ("Scene4c-1".equals(eventIdentifier)) {

            ArrayList<String> optionArrayList = new ArrayList<String>();
            optionArrayList.add("鍋鏟");
            optionArrayList.add("膠水");
            optionArrayList.add("皮球");
            optionArrayList.add("神秘工具");

            KWOptionEventModel event = new KWOptionEventModel("Scene4c-2_Option", "請選擇適合的工具", optionArrayList);

            eventManager.addEvent(event);
            eventManager.play("Scene4c-2");

        } else if ("Scene4c-2_correct".equals(eventIdentifier)) {

            KWCharacterModel mickey = new KWCharacterModel(this, "test", "米奇");

            KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(mickey, "太好了是鍋鏟！可以用鍋鏟幫狗狗做一頓美食了");

            eventManager.addEvent(event1);
            eventManager.play("Scene4c-3");

        } else if ("Scene4c-2_wrong".equals(eventIdentifier)) {

            KWCharacterModel mickey = new KWCharacterModel(this, "test", "米奇");

            KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(mickey, "選擇好像有點奇怪喔，要不要重新選呢？");

            eventManager.addEvent(event1);
            eventManager.play("Scene4c-1");

        } else if ("Scene4c-3".equals(eventIdentifier)) {

            KWFullScreenEventModel event1 = new KWFullScreenEventModel("米奇和小狗在妙妙屋裡面開心的吃著午餐，主角則在自己的床上醒來");
            KWFirstPersonEventModel event2 = new KWFirstPersonEventModel("我", "(原來只是夢嗎？好真實啊⋯)");
            KWFirstPersonEventModel event3 = new KWFirstPersonEventModel("THE END!");

            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.play("Scene4c_End");

        } else if ("Scene4c_End".equals(eventIdentifier)) {

            Log.d("MyScene4cActivity", "onFinish");
            switchSceneActivity(MyMenuMainActivity.class, R.anim.kw_scene_animation_zoom_in, R.anim.kw_scene_animation_fade_out);

        }
    }

    @Override
    protected void onOptionSelected(String identifier, int index) {
        super.onOptionSelected(identifier, index);

        if ("Scene4c-2_Option".equals(identifier)) {

            switch (index) {

                case 0:
                    eventManager.play("Scene4c-2_correct");
                    break;

                default:
                    eventManager.play("Scene4c-2_wrong");
                    break;

            }
        }
    }
}