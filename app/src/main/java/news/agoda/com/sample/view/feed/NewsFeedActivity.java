package news.agoda.com.sample.view.feed;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.agoda.com.sample.NewsFeedApplication;
import news.agoda.com.sample.constants.ApplicationConstants;
import news.agoda.com.sample.di.component.DaggerNewFeedActivityComponent;
import news.agoda.com.sample.di.component.NewFeedActivityComponent;
import news.agoda.com.sample.di.module.NewsFeedActivityModule;
import news.agoda.com.sample.domain.model.ResultDomain;
import news.agoda.com.sample.util.AppUtils;
import news.agoda.com.sample.util.PermissionUtils;
import news.agoda.com.sample.view.adapter.NewsFeedClickListener;
import news.agoda.com.sample.view.adapter.NewsListAdapter;
import news.agoda.com.sample.R;
import news.agoda.com.sample.view.detail.NewsDetailActivity;

public class NewsFeedActivity extends Activity implements NewsFeedView, NewsFeedClickListener{

    private static final String TAG = NewsFeedActivity.class.getSimpleName();

    @BindView(R.id.rv_news_list)
    RecyclerView mNewsFeedRecyclerView;
    @BindView(R.id.progress_bar_news_feed)
    ProgressBar mProgressBar;

    @Inject
    NewsFeedPresenter newsFeedPresenter;
    @Inject
    NewsListAdapter newsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        NewFeedActivityComponent mainActivityComponent = DaggerNewFeedActivityComponent.builder()
                .newsFeedActivityModule(new NewsFeedActivityModule(this))
                .newsFeedComponent(NewsFeedApplication.get(this).getRandomUserApplicationComponent())
                .build();
        mainActivityComponent.injectNewsFeedActivity(this);
        if(PermissionUtils.checkInternetPermission(this)){
            newsFeedPresenter.getNewsFeeds();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void listItemClicked(ResultDomain result) {
        if(result != null){
            Intent detailIntent = new Intent(this, NewsDetailActivity.class);
            detailIntent.putExtra(ApplicationConstants.BUNDLE_CONSTANT_TITLE, result.getTitle());
            detailIntent.putExtra(ApplicationConstants.BUNDLE_CONSTANT_DESCRIPTION, result.getAbstract());
            detailIntent.putExtra(ApplicationConstants.BUNDLE_CONSTANT_TITLE_IMAGE_URL, AppUtils.getImageUrlFromResult(result));
            detailIntent.putExtra(ApplicationConstants.BUNDLE_CONSTANT_FULL_STORY_URL, result.getUrl());
            startActivity(detailIntent);
        }
    }

    @Override
    public void newsFeedResponse(List<ResultDomain> results) {
        initializeAdapter(results);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(android.view.View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void initializeAdapter(List<ResultDomain> newsFeeds){
        LinearLayoutManager linearLayoutManagerForNewsFeedList = new LinearLayoutManager(this);
        linearLayoutManagerForNewsFeedList.setOrientation(LinearLayoutManager.VERTICAL);
        mNewsFeedRecyclerView.setLayoutManager(linearLayoutManagerForNewsFeedList);
        mNewsFeedRecyclerView.setItemAnimator(new DefaultItemAnimator());
        newsListAdapter.setClickListener(this);
        newsListAdapter.setNewsFeedModel(newsFeeds);
        mNewsFeedRecyclerView.setAdapter(newsListAdapter);
    }
}
