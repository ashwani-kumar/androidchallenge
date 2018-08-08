package news.agoda.com.sample.view.feed.fragment;

import android.support.v4.app.ListFragment;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import news.agoda.com.sample.domain.mapper.NewsFeedEntityDomainMapper;
import news.agoda.com.sample.domain.usecase.NewsFeedInteractor;
import news.agoda.com.sample.domain.usecase.NewsFeedInteractorImpl;
import news.agoda.com.sample.domain.usecase.NewsFeedRepository;
import news.agoda.com.sample.entity.model.NewsEntity;
import okhttp3.ResponseBody;

public class NewsFeedFragmentPresenter {

    private NewsFeedFragment newsFeedView;
    private NewsFeedRepository newsFeedRepository;
    private NewsFeedEntityDomainMapper newsFeedEntityDomainMapper;

    @Inject
    public NewsFeedFragmentPresenter(NewsFeedFragment newsFeedFragment, NewsFeedRepository newsFeedRepository, NewsFeedEntityDomainMapper newsFeedEntityDomainMapper) {
        this.newsFeedView = newsFeedFragment;
        this.newsFeedRepository = newsFeedRepository;
        this.newsFeedEntityDomainMapper = newsFeedEntityDomainMapper;
    }

    public void getNewsFeeds() {
        newsFeedView.showProgress();
        NewsFeedInteractor newsFeedInteractor = new NewsFeedInteractorImpl(newsFeedRepository);
        newsFeedInteractor.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody newsEntity) throws Exception {
                if (newsFeedView != null) {
                    newsFeedView.hideProgress();
                    NewsEntity parsedNewsEntity = newsFeedEntityDomainMapper.parseResponse(newsEntity.string());
                    newsFeedView.newsFeedResponse(newsFeedEntityDomainMapper.mapResults(parsedNewsEntity.getResults()));
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                newsFeedView.hideProgress();
                newsFeedView.showError(throwable.getMessage());
            }
        });
    }
}
