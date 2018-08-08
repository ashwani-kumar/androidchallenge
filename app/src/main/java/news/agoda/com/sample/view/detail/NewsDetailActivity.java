package news.agoda.com.sample.view.detail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import news.agoda.com.sample.R;
import news.agoda.com.sample.constants.ApplicationConstants;
import news.agoda.com.sample.view.detail.fragment.NewsDetailFragment;
import news.agoda.com.sample.view.feed.NewsFeedActivity;

/**
 * News detail view
 */
public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
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
            //arguments.putString(NewsDetailFragment.ARG_ITEM_ID, getIntent().getStringExtra(NewsDetailFragment.ARG_ITEM_ID));
            NewsDetailFragment fragment = new NewsDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(R.id.item_detail_container, fragment).commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            //NavUtils.navigateUpTo(this, new Intent(this, NewsFeedActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}