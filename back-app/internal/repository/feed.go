package repository

import (
	"database/sql"
)

type FeedRepositoryContract interface {
	CreateFeed()
}

type FeedRepository struct {
	db func() *sql.DB
}

func NewFeedsRepository(db func() *sql.DB) FeedRepositoryContract {
	return FeedRepository{db: db}
}

func (f FeedRepository) CreateFeed() {

}
