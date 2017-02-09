package lul.gatsbysimulator;

/**
 * Created by James on 2/7/2017.
 */

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class StartDraw extends Activity {
    DrawView drawView;
    //GatsbyListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        drawView = new DrawView(this);
        drawView.setBackgroundColor(Color.WHITE);
        setContentView(drawView);

    }


}
