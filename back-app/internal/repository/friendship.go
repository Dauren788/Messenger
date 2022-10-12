package repository

import (
	"chat-project-go/internal/datastruct"
	"database/sql"
	"fmt"
)

type FriendshipRepositoryContract interface {
	Create(userID1, userID2 string) error
	GetPending(userID1, userID2 string) ([]datastruct.PendingInvite, error)
}

type FriendshipRepository struct {
	db func() *sql.DB
}

func NewFriendshipRepository(db func() *sql.DB) FriendshipRepositoryContract {
	return FriendshipRepository{db: db}
}

func (f FriendshipRepository) Create(userID1, userID2 string) error {
	query := fmt.Sprintf("INSERT INTO friends(userID1, userID2) VALUES(%s, %s)", userID1, userID2)

	if _, err := f.db().Exec(query); err != nil {
		fmt.Println(err)
		return err
	}

	return nil
}

func (f FriendshipRepository) GetPending(userID1, userID2 string) ([]datastruct.PendingInvite, error) {
	var pendindInvites []datastruct.PendingInvite

	query := fmt.Sprintf(`SELECT * FROM friends WHERE userID1=%s AND userID2=%s AND confirmed=false`, userID1, userID2)

	rows, err := f.db().Query(query)

	if err != nil {
		fmt.Println(err)
		return nil, err
	}

	defer rows.Close()

	for rows.Next() {
		var invite datastruct.PendingInvite
		err = rows.Scan(invite.FromUser, invite.ToUser)

		if err != nil {
			fmt.Println(err)
			continue
		}

		pendindInvites = append(pendindInvites, invite)
	}

	return pendindInvites, nil
}
