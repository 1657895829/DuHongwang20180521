package com.example.oldframe_week2demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.oldframe_week2demo.R;
import com.example.oldframe_week2demo.bean.NewsBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created   by    Dewey
 * RecyclerView 展示图片数据
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {
    private Context context;
    private List<NewsBean.DataBean> list;

    public ImageAdapter(Context context, List<NewsBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //添加布局视图
        View view = View.inflate(context, R.layout.image_adapter, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //设置参数数据,判断是否为动图
        boolean is_gif = list.get(position).isIs_gif();
        if (is_gif){
            holder.ding.setText(list.get(position).getDing());
            holder.cai.setText(list.get(position).getCai());
            holder.bookmark.setText(list.get(position).getBookmark());
            holder.title.setText(list.get(position).getText());
            holder.name.setText(list.get(position).getName());
            holder.time.setText(list.get(position).getCreated_at());
            holder.headImage.setImageURI(list.get(position).getProfile_image());
            holder.draweeView.setImageURI(list.get(position).getImage0());
        }else {
            PipelineDraweeController controller =  (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                            .setUri(list.get(position).getProfile_image())
                            .setAutoPlayAnimations(true)     //自动播放gif动画
                            .build();

            holder.draweeView.setController(controller);
            holder.draweeView.setImageURI(list.get(position).getImage0());
            holder.ding.setText(list.get(position).getDing());
            holder.cai.setText(list.get(position).getCai());
            holder.bookmark.setText(list.get(position).getBookmark());
            holder.title.setText(list.get(position).getText());
            holder.name.setText(list.get(position).getName());
            holder.time.setText(list.get(position).getCreated_at());
            holder.headImage.setImageURI(list.get(position).getProfile_image());
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.draweeView)
        SimpleDraweeView draweeView;
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

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
