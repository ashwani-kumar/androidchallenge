package news.agoda.com.sample.view.feed;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import news.agoda.com.sample.domain.mapper.NewsFeedEntityDomainMapper;
import news.agoda.com.sample.entity.model.NewsEntity;
import news.agoda.com.sample.domain.usecase.NewsFeedInteractor;
import news.agoda.com.sample.domain.usecase.NewsFeedInteractorImpl;
import news.agoda.com.sample.domain.usecase.NewsFeedRepository;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFeedPresenter {
    private NewsFeedView newsFeedView;
    private NewsFeedRepository newsFeedRepository;
    private NewsFeedEntityDomainMapper newsFeedEntityDomainMapper;

    @Inject
    public NewsFeedPresenter(NewsFeedView newsFeedView, NewsFeedRepository newsFeedRepository, NewsFeedEntityDomainMapper newsFeedEntityDomainMapper) {
        this.newsFeedView = newsFeedView;
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
                    newsFeedEntityDomainMapper.parseResponse(newsEntity.string());
                    //newsFeedView.newsFeedResponse(newsFeedEntityDomainMapper.mapResults(newsEntity));
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
