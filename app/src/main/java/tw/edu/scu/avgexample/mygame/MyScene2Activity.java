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
    protected void initializeEvent () {
        super.initializeEvent();

        KWCharacterModel character = new KWCharacterModel(this, "kw_female_001", "米奇");

        KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(character, "Hello!\n是我！米奇！");
        KWThirdPersonEventModel event2 = new KWThirdPersonEventModel(character, "對了，要不要進我的妙妙屋！");

        Drawable background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.outdoor);
        event1.setBackgroundDrawable(background);

        eventManager.addEvent(event1);
        eventManager.addEvent(event2);

        eventManager.play("Scene2!");

    }

}