package news.agoda.com.sample.domain.usecase;

import io.reactivex.Observable;
import news.agoda.com.sample.entity.model.NewsEntity;
import okhttp3.ResponseBody;
import retrofit2.Call;

public abstract class AbstractInteractor implements Interactor {

    protected volatile boolean mIsCanceled;
    protected volatile boolean mIsRunning;

    public AbstractInteractor() {
    }

    public abstract Observable<ResponseBody> run();

    public void cancel() {
        mIsCanceled = true;
        mIsRunning = false;
    }

    public boolean isRunning() {
        return mIsRunning;
    }

    public void onFinished() {
        mIsRunning = false;
        mIsCanceled = false;
    }

    public Observable<ResponseBody> execute() {

        // mark this interactor as running
        this.mIsRunning = true;

        // start running this interactor in a background thread
        // run the main logic
        Observable<ResponseBody> result = run();
        // mark it as finished
        onFinished();
        return result;
    }

}
