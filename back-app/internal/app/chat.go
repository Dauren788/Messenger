package app

import (
	"chat-project-go/internal/dto"
	"chat-project-go/pkg/websocket"
	"fmt"
)

func (s *Services) StartConversationWithUser(message websocket.Message) {

}

func (s *Services) SendMessageToUser(message websocket.Message) {
	senderUserId := message.UserID
	conversationId := message.Body["conversation_id"].(string)
	text := message.Body["text"].(string)

	success, err := s.chatService.SendMessageToConversation(senderUserId, conversationId, text)

	if err != nil {
		fmt.Println(err)
	}

	response := dto.WebsocketMsg{
		Type:    GetConversationMsgs,
		Message: success,
	}

	s.wsPool.Clients[senderUserId].WriteJSON(response)
}

func (s *Services) GetConversationMsgs(message websocket.Message) {
	userId := message.UserID
	conversationId := message.Body["conversation_id"].(string)

	result, err := s.chatService.GetChatMessages(userId, conversationId)

	if err != nil {
		fmt.Println(err)
	}

	response := dto.WebsocketMsg{
		Type:    GetConversationMsgs,
		Message: result,
	}

	s.wsPool.Clients[userId].WriteJSON(response)

}

func (s *Services) GetAllChatsLastMsg(message websocket.Message) {
	userId := message.UserID

	result, err := s.chatService.GetAllChatsById(userId)

	if err != nil {
		return
	}

	response := dto.WebsocketMsg{
		Type:    GettAllChatsLastMsg,
		Message: result,
	}

	s.wsPool.Clients[userId].WriteJSON(response)
}
