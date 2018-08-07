package news.agoda.com.sample.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.agoda.com.sample.R;
import news.agoda.com.sample.domain.model.NewsDomain;
import news.agoda.com.sample.domain.model.ResultDomain;

public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<ResultDomain> mNewsFeedResults;
    private NewsFeedClickListener mClickListener;

    @Inject
    public NewsListAdapter(Context context) {
        this.mContext = context;
    }

    public void setNewsFeedModel(List<ResultDomain> results) {
        mNewsFeedResults = results;
    }

    public void setClickListener(NewsFeedClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.list_item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        ResultDomain mediaEntity = mNewsFeedResults.get(position);
        String thumbnailURL = "";
        thumbnailURL = mediaEntity.getUrl();
        Uri uri = Uri.parse(thumbnailURL);
        viewHolder.newsTitle.setText(mediaEntity.getTitle());
        viewHolder.imageView.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        @BindView(R.id.news_title)
        TextView newsTitle;
        @BindView(R.id.news_item_image)
        SimpleDraweeView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mView = itemView;
            this.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.listItemClicked(mNewsFeedResults.get(getAdapterPosition()));
                }
            });
        }
    }
}
