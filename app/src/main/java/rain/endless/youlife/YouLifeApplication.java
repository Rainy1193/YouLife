package rain.endless.youlife;

import android.app.Activity;
import android.app.Application;

public class YouLifeApplication extends Application {

    private static YouLifeApplication mApplication;

    public static YouLifeApplication getInstance() {
        if (mApplication == null) {
            mApplication = new YouLifeApplication();
        }
        return mApplication;
    }

    public static String getTag(Activity activity) {
        return activity.getClass().getSimpleName();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
