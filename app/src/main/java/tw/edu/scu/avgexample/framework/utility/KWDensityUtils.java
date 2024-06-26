package tw.edu.scu.avgexample.framework.utility;

import android.content.Context;
import android.util.DisplayMetrics;

public class KWDensityUtils {


    /**
     * Covert dp to px
     * @param dp
     * @param context
     * @return pixel
     */
    public static float dpToPx(float dp, Context context){
        float px = dp * getDensity(context);
        return px;
    }

    /**
     * Covert px to dp
     * @param px
     * @param context
     * @return dp
     */
    public static float pxToDp(float px, Context context){
        float dp = px / getDensity(context);
        return dp;
    }

    /**
     * 取得螢幕密度
     * 120dpi = 0.75
     * 160dpi = 1 (default)
     * 240dpi = 1.5
     * @param context
     * @return
     */
    public static float getDensity(Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.density;
    }
}
