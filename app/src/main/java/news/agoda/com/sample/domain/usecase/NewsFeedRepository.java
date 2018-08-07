package news.agoda.com.sample.domain.usecase;

import javax.inject.Inject;

import io.reactivex.Observable;
import news.agoda.com.sample.entity.model.NewsEntity;
import news.agoda.com.sample.network.APIInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class NewsFeedRepository implements FeedRepository {

    APIInterface apiInterface;
    @Inject
    public NewsFeedRepository(APIInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    @Override
    public Observable<ResponseBody> getNewsFeed() {
        return apiInterface.getNewsFeed();
    }

}
