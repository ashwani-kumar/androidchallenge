package news.agoda.com.sample.view.detail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import butterknife.BindView;
import butterknife.OnClick;
import news.agoda.com.sample.R;
import news.agoda.com.sample.constants.ApplicationConstants;

/**
 * News detail view
 */
public class NewsDetailActivity extends Activity {

    private String storyURL = null;
    @BindView(R.id.title)
    TextView titleView;
    @BindView(R.id.news_image)
    DraweeView imageView;
    @BindView(R.id.summary_content)
    TextView summaryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        storyURL = extras.getString(ApplicationConstants.BUNDLE_CONSTANT_FULL_STORY_URL);
        String title = extras.getString(ApplicationConstants.BUNDLE_CONSTANT_TITLE);
        String summary = extras.getString(ApplicationConstants.BUNDLE_CONSTANT_DESCRIPTION);
        String imageURL = extras.getString(ApplicationConstants.BUNDLE_CONSTANT_TITLE_IMAGE_URL);

        titleView.setText(title);
        summaryView.setText(summary);

        if(imageURL != null) {
            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(ImageRequest.fromUri(Uri.parse(imageURL)))
                    .setOldController(imageView.getController()).build();
            imageView.setController(draweeController);
        }
    }

    @OnClick(R.id.full_story_link)
    public void onFullStoryClicked(View view) {
        if(storyURL != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(storyURL));
            startActivity(intent);
        }else{
            Toast.makeText(this, "invalid story link", Toast.LENGTH_SHORT).show();
        }
    }
}
