package news.agoda.com.sample.view.detail.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class NewsDetailFragment extends Fragment {

    @BindView(R.id.title)
    TextView titleView;
    @BindView(R.id.news_image)
    DraweeView imageView;
    @BindView(R.id.summary_content)
    TextView summaryView;

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    private String storyURL;
    private String imageURL;
    private String title;
    private String summary;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ApplicationConstants.BUNDLE_CONSTANT_FULL_STORY_URL)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            storyURL = getArguments().getString(ApplicationConstants.BUNDLE_CONSTANT_FULL_STORY_URL);
            title = getArguments().getString(ApplicationConstants.BUNDLE_CONSTANT_TITLE);
            summary = getArguments().getString(ApplicationConstants.BUNDLE_CONSTANT_DESCRIPTION);
            imageURL = getArguments().getString(ApplicationConstants.BUNDLE_CONSTANT_TITLE_IMAGE_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
        // Show the dummy content as text in a TextView.
        ButterKnife.bind(this, rootView);
        titleView.setText(title);
        summaryView.setText(summary);

        if(imageURL != null) {
            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(ImageRequest.fromUri(Uri.parse(imageURL)))
                    .setOldController(imageView.getController()).build();
            imageView.setController(draweeController);
        }
        return rootView;
    }

    @OnClick(R.id.full_story_link)
    public void onFullStoryClicked(View view) {
        if(storyURL != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(storyURL));
            startActivity(intent);
        }else{
            Toast.makeText(getContext(), "invalid story link", Toast.LENGTH_SHORT).show();
        }
    }

}
