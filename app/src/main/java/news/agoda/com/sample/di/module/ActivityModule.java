package news.agoda.com.sample.di.module;

import android.app.Activity;
import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import news.agoda.com.sample.di.scope.NewsFeedApplicationScope;

@Module
public class ActivityModule {
    Context context;

    public ActivityModule(Activity context){
        this.context = context;
    }

    @Named("activity_context")
    @NewsFeedApplicationScope
    @Provides
    public Context context(){
        return this.context;
    }
}
