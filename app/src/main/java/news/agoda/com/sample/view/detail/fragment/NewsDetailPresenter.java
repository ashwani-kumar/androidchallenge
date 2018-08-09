package news.agoda.com.sample.view.detail.fragment;

import android.net.Uri;

import javax.inject.Inject;

public class NewsDetailPresenter {
    private final NewsDetailView newsDetailView;

    @Inject
    public NewsDetailPresenter(NewsDetailView newsDetailView) {
        this.newsDetailView = newsDetailView;
    }


    public void onFullStoryButtonClicked(String storyURL) {
        Uri storyUri = null;
        if (storyURL != null){
            storyUri = Uri.parse(storyURL);
        }
        if(newsDetailView != null){
            newsDetailView.openFullStory(storyUri);
        }
    }
}
