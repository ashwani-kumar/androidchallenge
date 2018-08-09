package news.agoda.com.sample;

import android.app.Activity;
import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import news.agoda.com.sample.di.component.DaggerNewsFeedComponent;
import news.agoda.com.sample.di.component.NewsFeedComponent;
import news.agoda.com.sample.di.module.ContextModule;

public class NewsFeedApplication extends Application {
    private NewsFeedComponent newsFeedComponent;

    public static NewsFeedApplication get(Activity activity){
        return (NewsFeedApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        newsFeedComponent = DaggerNewsFeedComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public NewsFeedComponent getNewFeedApplicationComponent(){
        return newsFeedComponent;
    }
}
