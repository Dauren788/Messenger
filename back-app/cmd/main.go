package main

import (
	"chat-project-go/internal/app"
	"chat-project-go/internal/drivers/mssql"
	"chat-project-go/internal/repository"
	"chat-project-go/internal/service"
	"chat-project-go/pkg/websocket"
	"fmt"
	"net/http"

	"github.com/gin-gonic/gin"
)

const (
	signingKey = "qrkjk#4#%35FSFJlja#4353KSFjH"
)

func main() {
	userRepository := repository.NewUserRepository(mssql.Connect)
	chatRepository := repository.NewChatRepository(mssql.Connect)
	feedRepository := repository.NewFeedsRepository(mssql.Connect)
	friendshipRepository := repository.NewFriendshipRepository(mssql.Connect)

	jwtTokenService := service.NewTokenManager(signingKey)
	authService := service.NewAuthService(jwtTokenService, userRepository)
	chatService := service.NewChatService(chatRepository)
	feedService := service.NewFeedService(feedRepository)
	friendship := service.NewFriendsService(friendshipRepository, userRepository)

	pool := websocket.NewPool()

	services := app.NewServices(authService, chatService, feedService, friendship, jwtTokenService, pool)

	go pool.Start()

	router := gin.Default()
	router.GET("/ping/", func(ctx *gin.Context) {
		ctx.String(http.StatusOK, "Pong")
	})

	router.POST("/feeds/", services.GetFeeds)
	router.POST("/feeds/post/", services.GetFeeds)
	router.GET("/friends/search/", services.SearchFriend)
	router.GET("/friends/pending/", services.FriendshipPending)
	router.POST("/friends/invite/", services.SendFriendshipInvite)
	router.POST("/friends/accept/", services.AcceptInvite)
	router.POST("/register/", services.Register)
	router.POST("/login/", services.Login)
	router.GET("/ws/chats/", func(ctx *gin.Context) {
		id, err := jwtTokenService.Parse(ctx.GetHeader("AuthToken"))
		if err != nil {
			fmt.Println(err)
		}

		services.ServeWs(pool, ctx, *id)
	})

	router.Run()
}
