package service

import (
	"chat-project-go/internal/datastruct"
	"chat-project-go/internal/repository"
)

type ChatService interface {
	GetAllChatsById(userId string) ([]datastruct.ChatsLastMsgs, error)
	StartNewConversation(fromUser string, toUser string, text string) error
	SendMessageToConversation(userId string, conversation_id string, message string) (bool, error)
	GetChatMessages(userId string, conversationId string) ([]datastruct.ChatMessage, error)
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

func (c chatService) SendMessageToConversation(userId string, conversationId string, message string) (bool, error) {
	userIsInConversation, err := c.chatRepo.CheckUserInConversation(userId, conversationId)

	if err != nil {
		return false, err
	}

	if userIsInConversation {

		err := c.chatRepo.CreateMessage(userId, conversationId, message)

		if err != nil {
			return false, err
		}
	}

	return true, nil
}

func (c chatService) GetAllChatsById(userId string) ([]datastruct.ChatsLastMsgs, error) {
	allChatsMsgs, err := c.chatRepo.GetAllChatsByUserId(userId)

	if err != nil {
		return nil, err
	}

	return allChatsMsgs, nil
}

func (c chatService) GetChatMessages(userId string, conversationId string) ([]datastruct.ChatMessage, error) {
	var chatMsgs []datastruct.ChatMessage

	userIsInConversation, err := c.chatRepo.CheckUserInConversation(userId, conversationId)

	if err != nil {
		return nil, err
	}

	if userIsInConversation {
		chatMsgs, err = c.chatRepo.GetConversationMessages(conversationId)

		if err != nil {
			return nil, err
		}

		return chatMsgs, nil
	}

	return nil, nil
}
