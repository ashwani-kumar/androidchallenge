package news.agoda.com.sample.di.component;

import android.content.Context;

import dagger.Component;
import news.agoda.com.sample.di.module.ActivityModule;
import news.agoda.com.sample.di.module.NewsFeedActivityModule;
import news.agoda.com.sample.di.scope.MainActivityScope;
import news.agoda.com.sample.network.APIInterface;
import news.agoda.com.sample.view.adapter.NewsListAdapter;
import news.agoda.com.sample.view.detail.NewsDetailActivity;
import news.agoda.com.sample.view.feed.NewsFeedActivity;

@Component(modules = {NewsFeedActivityModule.class, ActivityModule.class}, dependencies = NewsFeedComponent.class)
@MainActivityScope
public interface NewFeedActivityComponent {
    NewsListAdapter getNewsFeedAdapter();
    APIInterface getApiClient();
    void injectNewsFeedActivity(NewsFeedActivity newsFeedActivity);
    void injectNewsDetailActivity(NewsDetailActivity newsDetailActivity);
}