package tw.edu.scu.avgexample.mygame;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
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
import tw.edu.scu.avgexample.sample.SampleMenuMainActivity;

public class MyScene4aActivity extends MyBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_scene4a);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void initializeEvent() {
        super.initializeEvent();

        eventManager.play("Scene4a_Start"); // 開始播放 Scene4a_Start 事件

    }

    @Override
    protected void didFinishAllEvent(String eventIdentifier) {
        super.didFinishAllEvent(eventIdentifier);

        if ("Scene4a_Start".equals(eventIdentifier)) {

            // Set background
            Drawable background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.tudou_machine);
            // Add character events
            KWCharacterModel character = new KWCharacterModel(this, "test", "米奇");

            KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(character, "讓我看看有什麼工具可以選擇呢？");
            KWThirdPersonEventModel event2 = new KWThirdPersonEventModel(character, "我們有鍋鏟、膠水、皮球、神秘工具");
            KWThirdPersonEventModel event3 = new KWThirdPersonEventModel(character, "oh神秘工具是一個驚喜道具之後可以幫助我們");
            KWThirdPersonEventModel event4 = new KWThirdPersonEventModel(character, "嗨土豆，土豆是可以當我嗎需要工具時讓我們選擇工具");

            event1.setBackgroundDrawable(background);

            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.addEvent(event4);
            eventManager.play("Scene4a-1");

        } else if ("Scene4a-1".equals(eventIdentifier)) {

            Drawable background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.outdoor_grass);
            Drawable tudou = KWResourceUtils.getDrawableByResourceId(this, R.drawable.tudou);
            Drawable dog = KWResourceUtils.getDrawableByResourceId(this, R.drawable.dog);

            // Hide character
            KWThirdPersonEventModel event0 = new KWThirdPersonEventModel((new KWCharacterModel(this, "test"))).setCharacterImageVisibility(false);

            //狗狗出現
            KWPictureEventModel event1 = new KWPictureEventModel(dog, "我", "我們要陪狗狗玩但我們好像沒有工具可以陪她玩");
            KWPictureEventModel event2 = new KWPictureEventModel(dog, "我", "不然找土豆好了，剛剛好像有一個皮球選項");
            KWPictureEventModel event3 = new KWPictureEventModel(dog, "米奇", "好辦法！那我來叫土豆來吧！oh土豆～");

            //土豆出現
            KWPictureEventModel event4 = new KWPictureEventModel(tudou, "米奇", "就交給你來選擇要哪樣工具了！");

            event0.setBackgroundDrawable(background);

            eventManager.addEvent(event0);
            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.addEvent(event4);
            eventManager.play("Scene4a_ToolSelection");

        } else if ("Scene4a_ToolSelection".equals(eventIdentifier)) {

            // Example of adding options
            ArrayList<String> options = new ArrayList<>();
            options.add("鍋鏟");
            options.add("膠水");
            options.add("皮球");
            options.add("神秘工具");

            KWOptionEventModel optionEvent = new KWOptionEventModel("tool_selection", "選擇一個工具", options);

            eventManager.addEvent(optionEvent);
            eventManager.play("tool_selection");

        } else if ("Scene4a-2".equals(eventIdentifier)) {

            Drawable background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.bedroom);

            // Hide character
            KWThirdPersonEventModel event0 = new KWThirdPersonEventModel((new KWCharacterModel(this, "test"))).setCharacterImageVisibility(false);

            //米奇、狗消失
            KWFullScreenEventModel event1 = new KWFullScreenEventModel("米奇和小狗在妙妙屋外面開心的玩耍，我則在自己的床上醒來");
            event1.setBackgroundDrawable(background);
            KWFirstPersonEventModel event2 = new KWFirstPersonEventModel("我", "(原來只是夢嗎？好真實啊⋯)");
            KWFirstPersonEventModel event3 = new KWFirstPersonEventModel("THE END!");

            eventManager.addEvent(event0);
            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.play("Scene4a_End");

        } else if ("Scene4a_End".equals(eventIdentifier)) {

            Log.d("MyScene4aActivity", "onFinish");
            switchSceneActivity(MyMenuMainActivity.class, R.anim.kw_scene_animation_zoom_in, R.anim.kw_scene_animation_fade_out);

        }
    }

    @Override
    protected void onOptionSelected(String identifier, int index) {
        super.onOptionSelected(identifier, index);

        if ("tool_selection".equals(identifier)) {

            KWCharacterModel character = new KWCharacterModel(this, "test", "米奇");
            KWThirdPersonEventModel eventRetry = new KWThirdPersonEventModel(character, "選擇好像有點奇怪喔，要不要重新選呢？");

            switch (index) {
                case 0:
                    KWThirdPersonEventModel eventPot = new KWThirdPersonEventModel(character, "選擇了鍋鏟！");
                    eventManager.addEvent(eventPot);
                    eventManager.addEvent(eventRetry);
                    eventManager.play("Scene4a_ToolSelection");
                    break;
                case 1:
                    KWThirdPersonEventModel eventGlue = new KWThirdPersonEventModel(character, "選擇了膠水！");
                    eventManager.addEvent(eventGlue);
                    eventManager.addEvent(eventRetry);
                    eventManager.play("Scene4a_ToolSelection");
                    break;
                case 2:
                    KWThirdPersonEventModel eventBall = new KWThirdPersonEventModel(character, "選擇了皮球！");

                    //土豆消失
                    KWThirdPersonEventModel eventNext = new KWThirdPersonEventModel(character, "太好了是皮球！可以用皮球陪狗狗玩！");
                    eventManager.addEvent(eventBall);
                    eventManager.addEvent(eventNext);
                    eventManager.play("Scene4a-2");
                    break;
                case 3:
                    KWThirdPersonEventModel eventMystery = new KWThirdPersonEventModel(character, "選擇了神秘工具！");
                    eventManager.addEvent(eventMystery);
                    eventManager.addEvent(eventRetry);
                    eventManager.play("Scene4a_ToolSelection");
                    break;
                default:
                    eventRetry = new KWThirdPersonEventModel(character, "選擇好像有點奇怪喔，要不要重新選呢？");
                    eventManager.addEvent(eventRetry);
                    eventManager.play("Scene4a_ToolSelection");
                    break;

            }

        }
    }
}
