package news.agoda.com.sample.view.feed.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.agoda.com.sample.NewsFeedApplication;
import news.agoda.com.sample.R;
import news.agoda.com.sample.di.component.DaggerNewFeedActivityComponent;
import news.agoda.com.sample.di.component.NewFeedActivityComponent;
import news.agoda.com.sample.di.module.ActivityModule;
import news.agoda.com.sample.di.module.NewsFeedFragmentModule;
import news.agoda.com.sample.domain.model.ResultDomain;
import news.agoda.com.sample.util.PermissionUtils;
import news.agoda.com.sample.view.adapter.NewsFeedClickListener;
import news.agoda.com.sample.view.adapter.NewsListAdapter;

public class NewsFeedFragment extends Fragment implements NewsFeedFragView, NewsFeedClickListener{

    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    private Callbacks mCallbacks = sDummyCallbacks;

    private int mActivatedPosition = ListView.INVALID_POSITION;

    public interface Callbacks {
        void onItemSelected(ResultDomain id);
    }

    @BindView(R.id.rv_news_list)
    RecyclerView mNewsFeedRecyclerView;
    @BindView(R.id.progress_bar_news_feed)
    ProgressBar mProgressBar;

    @Inject
    NewsFeedFragmentPresenter newsFeedFragmentPresenter;
    @Inject
    NewsListAdapter newsListAdapter;
    @Inject
    @Named("activity_context")
    Context context;

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(ResultDomain id) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment (e.g. upon screen orientation changes).
    */
    public NewsFeedFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, rootView);
        NewFeedActivityComponent mainActivityComponent = DaggerNewFeedActivityComponent.builder()
                .activityModule(new ActivityModule(getActivity()))
                .newsFeedFragmentModule(new NewsFeedFragmentModule(this))
                .newsFeedComponent(NewsFeedApplication.get(getActivity()).getRandomUserApplicationComponent())
                .build();
        mainActivityComponent.injectNewsFeedFragment(this);
        if(PermissionUtils.checkInternetPermission(getContext())) {
            newsFeedFragmentPresenter.getNewsFeeds();
        }else{
            Toast.makeText(getContext(), "Internet permission not granted", Toast.LENGTH_LONG).show();
        }
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void newsFeedResponse(List<ResultDomain> results) {
        if(results != null && results.size() > 0 ){
            initializeAdapter(results);
        }else{
            Toast.makeText(getContext(), "Empty List received", Toast.LENGTH_LONG).show();
        }
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
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    public void initializeAdapter(List<ResultDomain> newsFeeds){
        LinearLayoutManager linearLayoutManagerForNewsFeedList = new LinearLayoutManager(getActivity());
        linearLayoutManagerForNewsFeedList.setOrientation(LinearLayoutManager.VERTICAL);
        mNewsFeedRecyclerView.setLayoutManager(linearLayoutManagerForNewsFeedList);
        mNewsFeedRecyclerView.setItemAnimator(new DefaultItemAnimator());
        newsListAdapter.setClickListener(this);
        newsListAdapter.setNewsFeedModel(newsFeeds);
        mNewsFeedRecyclerView.setAdapter(newsListAdapter);
    }

    @Override
    public void listItemClicked(ResultDomain result) {
        mCallbacks.onItemSelected(result);
    }
}
