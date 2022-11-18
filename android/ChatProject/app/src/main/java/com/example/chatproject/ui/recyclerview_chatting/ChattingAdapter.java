package com.example.chatproject.ui.recyclerview_chatting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatproject.MainActivity;
import com.example.chatproject.R;
import com.example.chatproject.data.model.ChatMessage;
import com.example.chatproject.ui.recyclerview_contract.RecyclerViewInterface;

import java.util.List;

public class ChattingAdapter extends RecyclerView.Adapter<RecyclerViewHolder> implements RecyclerViewInterface {
    Context context;
    List<ChatMessage> chatMessages;

    public ChattingAdapter() {
    }

    public ChattingAdapter(Context context, List<ChatMessage> chatsList) {
        this.context = context;
        this.chatMessages = chatsList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_chatting_view, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        ChatMessage message = this.chatMessages.get(position);

        System.out.println("----Alert. onBindViewHolder ignoring date layout");
        holder.dateLayout.setVisibility(LinearLayout.GONE);
        holder.dateTextView.setVisibility(LinearLayout.GONE);

        if (MainActivity.loggedUser.getUser().getLoggedUserId().equals(message.getProfileId()))
        {
            holder.rightMsgLayout.setVisibility(LinearLayout.VISIBLE);
            holder.rightMsgTextView.setText(message.getMessageText());
            holder.messageRightDateTextView.setText(message.getMessageSendDate());
            holder.leftMsgLayout.setVisibility(LinearLayout.GONE);
        }
        else
        {
            holder.leftMsgLayout.setVisibility(LinearLayout.VISIBLE);
            holder.leftMsgTextView.setText(message.getMessageText());
            holder.messageLeftDateTextView.setText(message.getMessageSendDate());
            holder.rightMsgLayout.setVisibility(LinearLayout.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (chatMessages!=null) {
            return chatMessages.size();
        }

        return 0;
    }

    @Override
    public void onItemClick(int position) {

    }
}
