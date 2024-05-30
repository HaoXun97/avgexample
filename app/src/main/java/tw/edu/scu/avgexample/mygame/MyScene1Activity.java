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

public class MyScene1Activity extends KWBaseSceneActivity {

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
    protected void initializeEvent () {
        super.initializeEvent();

        Drawable background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.desk__1_);

        KWFirstPersonEventModel event = new KWFirstPersonEventModel("我", "奇怪我怎麼醒在一個長得很像米奇妙妙屋的地方").setBackgroundDrawable(background);
        KWFirstPersonEventModel event2 = new KWFirstPersonEventModel("我", "最近是不是發生了很多穿越事件，我不會也穿越了吧").setBackgroundDrawable(background);
        eventManager.addEvent(event);
        eventManager.addEvent(event2);
        eventManager.play("我的第一個事件");

    }

}