package news.agoda.com.sample.view.feed.fragment;

import java.util.List;

import news.agoda.com.sample.domain.model.ResultDomain;
import news.agoda.com.sample.view.BaseView;

public interface NewsFeedFragView extends BaseView{
    void newsFeedResponse(List<ResultDomain> results);
}
