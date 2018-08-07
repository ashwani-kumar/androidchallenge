package news.agoda.com.sample.di.component;

import android.content.Context;

import dagger.Component;
import news.agoda.com.sample.di.module.ContextModule;
import news.agoda.com.sample.di.module.NewsFeedModule;
import news.agoda.com.sample.di.scope.NewsFeedApplicationScope;
import news.agoda.com.sample.network.APIInterface;

@NewsFeedApplicationScope
@Component(modules = {NewsFeedModule.class, ContextModule.class})
public interface NewsFeedComponent {
    APIInterface getApiClient();
}
