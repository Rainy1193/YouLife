package rain.endless.youlife.di.module;

import android.app.Application;

import dagger.Module;

@Module
public class ApplicationModule {
    public final Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;
    }
}
