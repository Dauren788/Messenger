package datastruct

import (
	"time"
)

type ChatsLastMsgs struct {
	LastMessage    int64     `json:"LastMessage"`
	ConversationID int64     `json:"conversation_id"`
	MessageID      int64     `json:"message_id"`
	FromUser       int64     `json:"from_user"`
	Text           string    `json:"text"`
	CreatedAt      time.Time `json:"created_at"`
}

type ChatMessage struct {
	MessageId      int64     `json:"message_id"`
	FromUser       int64     `json:"from_user"`
	Text           string    `json:"text"`
	CreatedAt      time.Time `json:"created_at"`
	ConversationId int64     `json:"conversation_id"`
}
