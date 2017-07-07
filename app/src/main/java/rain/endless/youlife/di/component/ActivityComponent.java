package rain.endless.youlife.di.component;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;
import rain.endless.youlife.activity.LoginActivity;
import rain.endless.youlife.activity.MainActivity;
import rain.endless.youlife.activity.SplashActivity;
import rain.endless.youlife.di.ActivityContext;
import rain.endless.youlife.di.module.ActivityModule;

@Singleton
@Component(dependencies = {ApplicationComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);

    void inject(SplashActivity splashActivity);

    @Provides
    @ActivityContext
    Context context();

    @Provides
    AppCompatActivity activity();
}
