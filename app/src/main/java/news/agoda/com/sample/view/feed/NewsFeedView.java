package news.agoda.com.sample.view.feed;

import java.util.List;

import news.agoda.com.sample.domain.model.ResultDomain;
import news.agoda.com.sample.view.BaseView;

public interface NewsFeedView extends BaseView {
    void newsFeedResponse(List<ResultDomain> results);
}
