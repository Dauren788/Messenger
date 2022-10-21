package app

import (
	"chat-project-go/pkg/websocket"
	"encoding/json"
	"fmt"
	"log"
	"strconv"

	"github.com/gin-gonic/gin"
)

func (s *Services) ServeWs(pool *websocket.Pool, ctx *gin.Context) {
	authToken := ctx.Request.Header.Get("AuthToken")

	userId, _ := s.jwtTokenService.Parse(authToken)

	userIdStr := strconv.FormatInt(int64(*userId), 10)

	wsConn, err := websocket.Upgrade(ctx.Writer, ctx.Request)

	if err != nil {
		fmt.Fprintf(ctx.Writer, "%+v\n", err)
	}

	client := &websocket.Client{
		ID:   userIdStr,
		Conn: wsConn,
		Pool: pool,
	}

	pool.Register <- client

	for {
		msgType, p, err := client.Conn.ReadMessage()

		if err != nil || msgType == 0 {
			pool.Unregister <- client
			client.Conn.Close()
			log.Println(err)
			break
		}

		result := make(map[string]interface{})
		json.Unmarshal([]byte(p), &result)

		message := websocket.Message{UserID: userIdStr, Body: result}

		go s.ProcessMessage(message)
	}
}

var (
	GettAllChatsLastMsg       float64 = 0
	SendMessageToUser         float64 = 1
	GetConversationMsgs       float64 = 2
	StartConversationWithUser float64 = 3
)

func (s *Services) ProcessMessage(message websocket.Message) {
	switch message.Body["type"].(float64) {
	case GettAllChatsLastMsg:
		// Get all last messages from everyone
		s.GetAllChatsLastMsg(message)
	case SendMessageToUser:
		// Send message to user
		s.SendMessageToUser(message)
	case GetConversationMsgs:
		// Get conversation messages
		s.GetConversationMsgs(message)
	case StartConversationWithUser:
		// Start new conversation
		s.StartConversationWithUser(message)
	}
}
