package news.agoda.com.sample.domain.usecase;

import io.reactivex.Observable;
import news.agoda.com.sample.entity.model.NewsEntity;
import okhttp3.ResponseBody;
import retrofit2.Call;

public interface Interactor {
    Observable<ResponseBody> execute();
}
