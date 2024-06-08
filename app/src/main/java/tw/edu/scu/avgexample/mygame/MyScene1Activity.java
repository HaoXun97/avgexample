package tw.edu.scu.avgexample.mygame;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.model.KWFirstPersonEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

public class MyScene1Activity extends MyBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_scene1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d("MyScene1Activity", "onCreate");

    }

    @Override
    protected void initializeEvent() {
        super.initializeEvent();

        eventManager.play("Scene1_Start");

    }

    @Override
    protected void didFinishAllEvent(String eventIdentifier) {
        super.didFinishAllEvent(eventIdentifier);

        if ("Scene1_Start".equals(eventIdentifier)) {

            Drawable background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.outdoor);

            KWFirstPersonEventModel event1 = new KWFirstPersonEventModel("我", "(奇怪我怎麼醒在一個長得很像米奇妙妙屋的地方)").setBackgroundDrawable(background);
            KWFirstPersonEventModel event2 = new KWFirstPersonEventModel("我", "(最近是不是發生了很多穿越事件，我不會也穿越了吧)").setBackgroundDrawable(background);

            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.play("Scene1-1");

        } else if ("Scene1-1".equals(eventIdentifier)) {

            eventManager.play("Scene1_End");

        } else if ("Scene1_End".equals(eventIdentifier)) {

            //切換 Scene2
            switchSceneActivity(MyScene2Activity.class);

        }

    }

}