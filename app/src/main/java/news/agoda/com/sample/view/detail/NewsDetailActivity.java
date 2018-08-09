package news.agoda.com.sample.view.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import news.agoda.com.sample.R;
import news.agoda.com.sample.constants.ApplicationConstants;
import news.agoda.com.sample.view.detail.fragment.NewsDetailFragment;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            Bundle extras = getIntent().getExtras();
            String storyURL = extras.getString(ApplicationConstants.BUNDLE_CONSTANT_FULL_STORY_URL);
            String title = extras.getString(ApplicationConstants.BUNDLE_CONSTANT_TITLE);
            String summary = extras.getString(ApplicationConstants.BUNDLE_CONSTANT_DESCRIPTION);
            String imageURL = extras.getString(ApplicationConstants.BUNDLE_CONSTANT_TITLE_IMAGE_URL);
            arguments.putString(ApplicationConstants.BUNDLE_CONSTANT_FULL_STORY_URL, storyURL);
            arguments.putString(ApplicationConstants.BUNDLE_CONSTANT_TITLE, title);
            arguments.putString(ApplicationConstants.BUNDLE_CONSTANT_DESCRIPTION, summary);
            arguments.putString(ApplicationConstants.BUNDLE_CONSTANT_TITLE_IMAGE_URL, imageURL);
            NewsDetailFragment fragment = new NewsDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(R.id.item_detail_container, fragment).commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}