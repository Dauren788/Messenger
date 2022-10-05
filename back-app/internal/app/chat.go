package app

import (
	"chat-project-go/pkg/websocket"
	"fmt"
	"strconv"
)

func (s *Services) StartConversationWithUser(message websocket.Message) {

}

func (s *Services) SendMessageToUser(message websocket.Message) {
	senderUserId := message.UserID
	conversationId := (fmt.Sprintf("%s", message.Body["conversation_id"]))
	text := message.Body["text"].(string)

	conversationIdInt64, err := strconv.ParseInt(conversationId, 10, 64)

	if err != nil {
		fmt.Println(err)
	}

	err = s.chatService.SendMessageToConversation(senderUserId, int64(conversationIdInt64), text)

	if err != nil {
		fmt.Println(err)
	}

	s.wsPool.Clients[senderUserId].WriteJSON(err)
}

func (s *Services) GetConversationMsgs(message websocket.Message) {
	userId := message.UserID
	conversationId := (fmt.Sprintf("%s", message.Body["conversation_id"]))

	result, err := s.chatService.GetChatMessages(conversationId)

	if err != nil {
		fmt.Println(err)
	}

	s.wsPool.Clients[userId].WriteJSON(result)

}

func (s *Services) GetAllChatsLastMsg(message websocket.Message) {
	userId := message.UserID

	result, err := s.chatService.GetAllChatsById(userId)

	if err != nil {
		return
	}

	s.wsPool.Clients[userId].WriteJSON(result)
}
