package com.example.chatproject.ui.recyclerview_chatting;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatproject.R;
import com.example.chatproject.ui.recyclerview_contract.RecyclerViewInterface;

class RecyclerViewHolder extends RecyclerView.ViewHolder {
    LinearLayout leftMsgLayout;
    LinearLayout rightMsgLayout;
    TextView leftMsgTextView;
    TextView rightMsgTextView;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        if(itemView!=null) {
            leftMsgLayout = (LinearLayout) itemView.findViewById(R.id.chat_left_msg_layout);
            rightMsgLayout = (LinearLayout) itemView.findViewById(R.id.chat_right_msg_layout);
            leftMsgTextView = (TextView) itemView.findViewById(R.id.chat_left_msg_text_view);
            rightMsgTextView = (TextView) itemView.findViewById(R.id.chat_right_msg_text_view);
        }
    }
}