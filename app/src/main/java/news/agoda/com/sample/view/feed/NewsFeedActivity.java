package news.agoda.com.sample.view.feed;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import news.agoda.com.sample.constants.ApplicationConstants;
import news.agoda.com.sample.domain.model.ResultDomain;
import news.agoda.com.sample.util.AppUtils;
import news.agoda.com.sample.R;
import news.agoda.com.sample.view.detail.NewsDetailActivity;
import news.agoda.com.sample.view.detail.fragment.NewsDetailFragment;
import news.agoda.com.sample.view.feed.fragment.NewsFeedFragment;

public class NewsFeedActivity extends FragmentActivity implements NewsFeedFragment.Callbacks{

    private boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(ResultDomain result) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(ApplicationConstants.BUNDLE_CONSTANT_TITLE, result.getTitle());
            arguments.putString(ApplicationConstants.BUNDLE_CONSTANT_DESCRIPTION, result.getAbstract());
            arguments.putString(ApplicationConstants.BUNDLE_CONSTANT_TITLE_IMAGE_URL, AppUtils.getImageUrlFromResult(result));
            arguments.putString(ApplicationConstants.BUNDLE_CONSTANT_FULL_STORY_URL, result.getUrl());
            NewsDetailFragment fragment = new NewsDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, fragment).commit();

        } else {
            if(result != null){
                Intent detailIntent = new Intent(this, NewsDetailActivity.class);
                detailIntent.putExtra(ApplicationConstants.BUNDLE_CONSTANT_TITLE, result.getTitle());
                detailIntent.putExtra(ApplicationConstants.BUNDLE_CONSTANT_DESCRIPTION, result.getAbstract());
                detailIntent.putExtra(ApplicationConstants.BUNDLE_CONSTANT_TITLE_IMAGE_URL, AppUtils.getImageUrlFromResult(result));
                detailIntent.putExtra(ApplicationConstants.BUNDLE_CONSTANT_FULL_STORY_URL, result.getUrl());
                startActivity(detailIntent);
            }
        }

    }
}
