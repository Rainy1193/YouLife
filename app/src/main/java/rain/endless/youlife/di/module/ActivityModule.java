package rain.endless.youlife.di.module;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;

@Module
public class ActivityModule {
    private final AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity appCompatActivity) {
        this.mActivity = appCompatActivity;
    }
}
