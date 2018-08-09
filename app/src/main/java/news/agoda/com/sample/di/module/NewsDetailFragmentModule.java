package news.agoda.com.sample.di.module;

import dagger.Module;
import dagger.Provides;
import news.agoda.com.sample.di.scope.MainActivityScope;
import news.agoda.com.sample.view.detail.fragment.NewsDetailFragment;
import news.agoda.com.sample.view.detail.fragment.NewsDetailPresenter;

@Module
public class NewsDetailFragmentModule {
    private final NewsDetailFragment newsDetailFragment;

    public NewsDetailFragmentModule(NewsDetailFragment newsDetailFragment) {
        this.newsDetailFragment = newsDetailFragment;
    }

    @Provides
    @MainActivityScope
    public NewsDetailPresenter newsFeedPresenter() {
        return new NewsDetailPresenter(newsDetailFragment);
    }
}
