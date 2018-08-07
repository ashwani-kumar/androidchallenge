package news.agoda.com.sample.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import news.agoda.com.sample.di.scope.MainActivityScope;
import news.agoda.com.sample.domain.mapper.NewsFeedEntityDomainMapper;
import news.agoda.com.sample.domain.usecase.NewsFeedRepository;
import news.agoda.com.sample.network.APIInterface;
import news.agoda.com.sample.view.adapter.NewsListAdapter;
import news.agoda.com.sample.view.feed.NewsFeedActivity;
import news.agoda.com.sample.view.feed.NewsFeedPresenter;
import news.agoda.com.sample.view.feed.NewsFeedView;

@Module
public class NewsFeedActivityModule {
    private final NewsFeedActivity newsFeedActivity;

    public NewsFeedActivityModule(NewsFeedActivity newsFeedActivity) {
        this.newsFeedActivity = newsFeedActivity;
    }

    @Provides
    @MainActivityScope
    public NewsListAdapter newsFeedAdapter() {
        return new NewsListAdapter(newsFeedActivity);
    }

    @Provides
    @MainActivityScope
    public NewsFeedPresenter newsFeedPresenter(NewsFeedRepository newsFeedRepository, NewsFeedEntityDomainMapper newsFeedEntityDomainMapper) {
        return new NewsFeedPresenter(newsFeedActivity, newsFeedRepository, newsFeedEntityDomainMapper);
    }

    @Provides
    @MainActivityScope
    public NewsFeedRepository newFeedRepository(APIInterface apiInterface) {
        return new NewsFeedRepository(apiInterface);
    }

    @Provides
    @MainActivityScope
    public NewsFeedEntityDomainMapper newsFeedEntityDomaninMapper() {
        return new NewsFeedEntityDomainMapper();
    }
}
