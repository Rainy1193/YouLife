package rain.endless.youlife.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;
import rain.endless.youlife.YouLifeApplication;
import rain.endless.youlife.di.ApplicationContext;
import rain.endless.youlife.di.module.ApplicationModule;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(YouLifeApplication application);

    @Provides
    @ApplicationContext
    Context context();

    @Provides
    Application application();

}
