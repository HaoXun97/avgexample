package tw.edu.scu.avgexample.framework.utility;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;

public class KWAnimationUtils {
    public interface ObjectAnimatorCallBackInterface {
        void onAnimationStart(Animator animator);
        void onAnimationEnd(Animator animator);
        void onAnimationCancel(Animator animator);
        void onAnimationEndOrCancel(Animator animator);
        void onAnimationRepeat(Animator animator);
    }

    //Option CallBack Method
    //https://stackoverflow.com/questions/31484650/android-interface-make-callback-function-optional/31484968
    public abstract static class BaseObjectAnimatorCallBack implements ObjectAnimatorCallBackInterface {
        @Override
        public void onAnimationStart(Animator animator) {}

        @Override
        public void onAnimationEnd(Animator animator) {}

        @Override
        public void onAnimationCancel(Animator animator) {}

        @Override
        public void onAnimationEndOrCancel(Animator animator) {}

        @Override
        public void onAnimationRepeat(Animator animator) { }
    }

    public static ObjectAnimator ofIntWithDp(Context context, Object target, String propertyName, int... values) {
        int[] dpValues = new int[values.length];

        for (int i = 0; i < values.length; i++) {
            dpValues[i] = (int)KWDensityUtils.dpToPx(values[i], context);
        }
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(target, propertyName, dpValues);
        return objectAnimator;
    }

    public static void playAnimation(ObjectAnimator objectAnimator, final BaseObjectAnimatorCallBack callBack) {

        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                callBack.onAnimationStart(animator);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                callBack.onAnimationEnd(animator);
                callBack.onAnimationEndOrCancel(animator);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                callBack.onAnimationCancel(animator);
                callBack.onAnimationEndOrCancel(animator);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                callBack.onAnimationRepeat(animator);
            }
        });
        objectAnimator.start();
    }
}
