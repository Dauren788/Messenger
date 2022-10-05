package service

import (
	"chat-project-go/internal/datastruct"
	"chat-project-go/internal/repository"
)

type ChatService interface {
	GetAllChatsById(userId string) ([]datastruct.ChatsLastMsgs, error)
	StartNewConversation(fromUser string, toUser string, text string) error
	SendMessageToConversation(userId string, conversation_id int64, message string) error
	GetChatMessages(conversationId string) ([]datastruct.ChatMessage, error)
}

type chatService struct {
	chatRepo repository.ChatRepositoryContract
}

func NewChatService(chatRepo repository.ChatRepositoryContract) *chatService {
	return &chatService{
		chatRepo: chatRepo,
	}
}

func (c chatService) StartNewConversation(fromUser string, toUser string, text string) error {
	conversationId, err := c.chatRepo.CreateNewConverasation(fromUser, toUser)

	if err != nil {
		return err
	}

	err = c.chatRepo.CreateMessage(fromUser, conversationId, text)

	if err != nil {
		return err
	}

	return nil
}

func (c chatService) SendMessageToConversation(userId string, conversation_id int64, message string) error {
	err := c.chatRepo.CreateMessage(userId, conversation_id, message)

	if err != nil {
		return err
	}

	return nil
}

func (c chatService) GetAllChatsById(userId string) ([]datastruct.ChatsLastMsgs, error) {
	allChatsMsgs, err := c.chatRepo.GetAllChatsByUserId(userId)

	if err != nil {
		return nil, err
	}
	return allChatsMsgs, nil
}

func (c chatService) GetChatMessages(conversationId string) ([]datastruct.ChatMessage, error) {
	chatMsgs, err := c.chatRepo.GetConversationMessages(conversationId)

	if err != nil {
		return nil, err
	}

	return chatMsgs, nil
}
