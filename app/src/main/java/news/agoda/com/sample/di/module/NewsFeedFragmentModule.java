package news.agoda.com.sample.di.module;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import news.agoda.com.sample.di.scope.MainActivityScope;
import news.agoda.com.sample.domain.mapper.NewsFeedEntityDomainMapper;
import news.agoda.com.sample.domain.usecase.NewsFeedRepository;
import news.agoda.com.sample.network.APIInterface;
import news.agoda.com.sample.view.adapter.NewsListAdapter;
import news.agoda.com.sample.view.feed.fragment.NewsFeedFragment;
import news.agoda.com.sample.view.feed.fragment.NewsFeedFragmentPresenter;

@Module(includes = ActivityModule.class)
public class NewsFeedFragmentModule {
    private final NewsFeedFragment newsFeedFragment;

    public NewsFeedFragmentModule(NewsFeedFragment newsFeedActivity) {
        this.newsFeedFragment = newsFeedActivity;
    }

    @Provides
    @MainActivityScope
    public NewsListAdapter newsFeedAdapter(@Named("activity_context") Context context) {
        return new NewsListAdapter(context);
    }

    @Provides
    @MainActivityScope
    public NewsFeedFragmentPresenter newsFeedPresenter(NewsFeedRepository newsFeedRepository, NewsFeedEntityDomainMapper newsFeedEntityDomainMapper) {
        return new NewsFeedFragmentPresenter(newsFeedFragment, newsFeedRepository, newsFeedEntityDomainMapper);
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
