package repository

import (
	"database/sql"
	"fmt"
)

type FriendshipRepositoryContract interface {
	Create(userID1, userID2 string) error
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
