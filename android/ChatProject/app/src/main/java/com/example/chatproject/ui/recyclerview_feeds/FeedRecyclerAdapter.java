package com.example.chatproject.ui.recyclerview_feeds;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatproject.R;
import com.example.chatproject.data.model.Feed;

import java.util.ArrayList;

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.ViewHolder>{
    private ArrayList<Feed>arrayList;

    public FeedRecyclerAdapter(ArrayList<Feed>arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.feed_item_view, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Feed feed = arrayList.get(position);
        holder.title.setText(feed.getTitle());
        holder.message.setText(feed.getMessage());
        holder.profileImage.setImageResource(feed.getProfileIcon());
        holder.postImage.setImageResource(feed.getPostImage());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView profileImage;
        ImageView postImage;
        TextView title;
        TextView message;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            profileImage = itemView.findViewById(R.id.ivProfile);
            postImage = itemView.findViewById(R.id.feedPost);
            title = itemView.findViewById(R.id.feedTitle);
            message = itemView.findViewById(R.id.feedMessage);
        }
    }

}
