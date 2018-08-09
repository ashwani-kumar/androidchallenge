package news.agoda.com.sample.di.component;

import dagger.Component;
import news.agoda.com.sample.di.module.ActivityModule;
import news.agoda.com.sample.di.module.NewsDetailFragmentModule;
import news.agoda.com.sample.di.module.NewsFeedFragmentModule;
import news.agoda.com.sample.di.scope.MainActivityScope;
import news.agoda.com.sample.network.APIInterface;
import news.agoda.com.sample.view.adapter.NewsListAdapter;
import news.agoda.com.sample.view.detail.NewsDetailActivity;
import news.agoda.com.sample.view.detail.fragment.NewsDetailFragment;
import news.agoda.com.sample.view.feed.fragment.NewsFeedFragment;

@Component(modules = {NewsDetailFragmentModule.class}, dependencies = NewsFeedComponent.class)
@MainActivityScope
public interface NewDetailActivityComponent {
    void injectNewsDetailFragment(NewsDetailFragment newsDetailFragment);
}
