package news.agoda.com.sample.network;

import io.reactivex.Observable;
import news.agoda.com.sample.entity.model.NewsEntity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("nl6jh")
    Observable<ResponseBody> getNewsFeed();
}
