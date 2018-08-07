package news.agoda.com.sample.domain.usecase;

import io.reactivex.Observable;
import news.agoda.com.sample.entity.model.NewsEntity;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class NewsFeedInteractorImpl extends AbstractInteractor implements NewsFeedInteractor{
    private FeedRepository mFeedRepository;

    public NewsFeedInteractorImpl(FeedRepository feedRepository) {
        super();
        mFeedRepository = feedRepository;
    }

    @Override
    public Observable<ResponseBody> run() {
        Observable<ResponseBody> obj = mFeedRepository.getNewsFeed();
        return obj;
    }
}
