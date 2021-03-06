package com.example.newframe.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.newframe.R;
import com.example.newframe.bean.VideoBean;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created   by    Dewey
 * RecyclerView 展示视频数据
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {
    private Context context;
    private List<VideoBean.DataBean> list;

    public VideoAdapter(Context context, List<VideoBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    //清空原有数据，再次添加
    public void addData(List<VideoBean.DataBean> data){
        if (list != null){
            list.clear();
            list.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //添加布局视图
        View view = View.inflate(context, R.layout.video_adapter, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //设置参数数据
        holder.ding.setText(list.get(position).getDing());
        holder.cai.setText(list.get(position).getCai());
        holder.bookmark.setText(list.get(position).getBookmark());
        holder.title.setText(list.get(position).getText());
        holder.name.setText(list.get(position).getName());
        holder.time.setText(list.get(position).getCreated_at());
        holder.headImage.setImageURI(list.get(position).getProfile_image());

        //普通页面播放视频，显示视频标题
        holder.videoPlayer.setUp(list.get(position).getVideouri(), JZVideoPlayer.SCREEN_WINDOW_NORMAL);
        //为播放视频设置封面图
        Glide.with(context).load(list.get(position).getCdn_img()).into(holder.videoPlayer.thumbImageView);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.headImage)
        SimpleDraweeView headImage;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.ding)
        TextView ding;
        @BindView(R.id.cai)
        TextView cai;
        @BindView(R.id.bookmark)
        TextView bookmark;
        @BindView(R.id.videoPlayer)
        JZVideoPlayerStandard videoPlayer;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
