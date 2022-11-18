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
    LinearLayout dateLayout;
    TextView dateTextView;
    TextView messageLeftDateTextView;
    TextView messageRightDateTextView;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        if(itemView!=null) {
            leftMsgLayout = (LinearLayout) itemView.findViewById(R.id.chat_left_msg_layout);
            leftMsgTextView = (TextView) itemView.findViewById(R.id.chat_left_msg_text_view);
            messageLeftDateTextView = (TextView) itemView.findViewById(R.id.chat_left_msg_text_date);

            rightMsgLayout = (LinearLayout) itemView.findViewById(R.id.chat_right_msg_layout);
            rightMsgTextView = (TextView) itemView.findViewById(R.id.chat_right_msg_text_view);
            messageRightDateTextView = (TextView) itemView.findViewById(R.id.chat_right_msg_text_date);

            dateLayout = (LinearLayout) itemView.findViewById(R.id.date_layout);
            dateTextView = (TextView) itemView.findViewById(R.id.date_text_view);
        }
    }
}