package datastruct

import (
	"time"
)

type AllChats struct {
	ChatId    int64
	Id        int64
	UserId    int64
	LineText  string
	CreatedAt time.Time
}

type ChatsLastMsgs struct {
	LastMessage    int64
	Username       string
	ConversationID int64
	MessageID      int64
	FromUser       int64
	Text           string
	CreatedAt      time.Time
}

type ChatMessage struct {
	Username       string
	MessageId      int64
	FromUser       int64
	Text           string
	CreatedAt      time.Time
	ConversationId int64
}
