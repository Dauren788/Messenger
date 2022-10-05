package repository

import (
	"chat-project-go/internal/datastruct"
	"database/sql"
	"fmt"
)

type ChatRepositoryContract interface {
	GetAllChatsByUserId(userId string) ([]datastruct.ChatsLastMsgs, error)
	CreateMessage(from_user string, conversation_id int64, text string) error
	CreateNewConverasation(firstUserID string, secondUserID string) (int64, error)
	GetConversationMessages(conversationId string) ([]datastruct.ChatMessage, error)
}

type ChatRepository struct {
	db func() *sql.DB
}

func NewChatRepository(db func() *sql.DB) ChatRepositoryContract {
	return ChatRepository{db: db}
}

func (u ChatRepository) CreateNewConverasation(firstUserID string, secondUserID string) (int64, error) {
	var err error
	var stmt *sql.Stmt
	query := fmt.Sprintf(`INSERT INTO dbo.conversation DEFAULT VALUES; SELECT SCOPE_IDENTITY() AS conversation_id;`)

	if stmt, err = u.db().Prepare(query); err != nil {
		fmt.Println(err)
		return 0, err
	}
	defer stmt.Close()

	var conversationId int64

	if err := u.db().QueryRow(query).Scan(&conversationId); err != nil {
		fmt.Println(err)
		return 0, err
	}

	query = fmt.Sprintf(`INSERT INTO users_conversations(conversation_id, user_id) VALUES(%d, %s),(%d, %s);`, conversationId, firstUserID, conversationId, secondUserID)

	if stmt, err = u.db().Prepare(query); err != nil {
		fmt.Println(err)
		return 0, err
	}

	if _, err := u.db().Exec(query); err != nil {
		fmt.Println(err)
		return 0, err
	}

	return conversationId, nil
}

func (u ChatRepository) GetAllChatsByUserId(userId string) ([]datastruct.ChatsLastMsgs, error) {
	var msgs []datastruct.ChatsLastMsgs

	query := fmt.Sprintf(`SELECT * FROM (
		SELECT ROW_NUMBER() OVER (PARTITION BY message.conversation_id ORDER BY message.created_at DESC) AS LastMessage, message.conversation_id, message.message_id, message.from_user, message.text, message.created_at
		FROM users_conversations
		JOIN message
		ON users_conversations.conversation_id = message.conversation_id
		WHERE users_conversations.user_id=%s) AS a WHERE a.LastMessage = 1`, userId)

	rows, err := u.db().Query(query)

	if err != nil {
		fmt.Println(err)
		return nil, err
	}

	defer rows.Close()

	for rows.Next() {
		var msg datastruct.ChatsLastMsgs
		err = rows.Scan(&msg.LastMessage, &msg.ConversationID, &msg.MessageID, &msg.FromUser, &msg.Text, &msg.CreatedAt)

		if err != nil {
			fmt.Println(err)
			continue
		}

		msgs = append(msgs, msg)
	}

	return msgs, nil
}

func (u ChatRepository) CreateMessage(from_user string, conversation_id int64, text string) error {
	var err error

	query := fmt.Sprintf(`INSERT INTO dbo.message(from_user, conversation_id, text) VALUES('%s', %d, '%s')`, from_user, conversation_id, text)

	fmt.Println(query)

	if _, err := u.db().Exec(query); err != nil {
		fmt.Println(err)
		return err
	}

	return err
}

func (u ChatRepository) GetConversationMessages(conversationId string) ([]datastruct.ChatMessage, error) {
	var msgs []datastruct.ChatMessage

	query := fmt.Sprintf(`SELECT * FROM message WHERE conversation_id=%s ORDER BY created_at;`, conversationId)

	rows, err := u.db().Query(query)

	if err != nil {
		fmt.Println(err)
		return nil, err
	}

	defer rows.Close()

	for rows.Next() {
		var msg datastruct.ChatMessage
		err = rows.Scan(&msg.MessageId, &msg.FromUser, &msg.Text, &msg.CreatedAt, &msg.ConversationId)

		if err != nil {
			fmt.Println(err)
			continue
		}

		msgs = append(msgs, msg)
	}

	return msgs, nil
}
