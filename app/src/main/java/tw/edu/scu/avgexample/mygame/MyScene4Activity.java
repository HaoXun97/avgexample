package tw.edu.scu.avgexample.mygame;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWBaseActivity;
import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.model.KWFullScreenEventModel;

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
            KWFullScreenEventModel event = new KWFullScreenEventModel("SCENE4_START");
            eventManager.addEvent(event);
            eventManager.play("Scene4-1");
        }
        else if ("Scene4-1".equals(eventIdentifier)) {
            eventManager.play("Scene4_End");
        }
        else if ("Scene4_End".equals(eventIdentifier)) {
            finish();
        }

    }

    @Override
    protected void onOptionSelected(String identifier, int index) {
        super.onOptionSelected(identifier, index);

    }
}