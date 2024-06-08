package tw.edu.scu.avgexample.mygame;

import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.sample.SampleMenuMainActivity;

public class MyBaseSceneActivity extends KWBaseSceneActivity {

    @Override
    protected void initializeExitListener() {
        super.setOnExitListener(new KWOnExitListener() {
            @Override
            public void onExit() {
                switchSceneActivity(MyMenuMainActivity.class);
            }
        });
    }
}
