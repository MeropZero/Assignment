package com.example.final_project.favorite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.final_project.R;
import com.example.final_project.VideoPlayer;
import com.example.final_project.model.Video;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter <FavoriteAdapter.FavoriteViewHolder> {
    private Context mContext;
    private List<Video> VideoList;

    public FavoriteAdapter(Context context, List<Video> videoList) {
        mContext = context;
        VideoList = videoList;
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_h;
        private TextView title_h;
        private TextView user_h;
        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            img_h = itemView.findViewById(R.id.img_h);
            title_h = itemView.findViewById(R.id.title_h);
            user_h = itemView.findViewById(R.id.user_h);
        }
    }

    @NonNull
    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_h,parent,false);
        FavoriteAdapter.FavoriteViewHolder holder = new FavoriteAdapter.FavoriteViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.FavoriteViewHolder holder, int position) {
        Glide.with(mContext)
                .load(VideoList.get(position).getImageUrl())
                .into(holder.img_h);
        holder.title_h.setText(VideoList.get(position).getExtraValue());
        holder.user_h.setText(VideoList.get(position).getUserName());
        //点击跳转视频播放页
        //todo:这里可以update历史记录数据库，实现新点击的历史记录移动到最上方||可以考虑点击button删除单条历史记录
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoPlayer.class);
                intent.putExtra("Video", VideoList.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return VideoList.size();
    }
}
