package datastruct

type PossibleFriend struct {
	Id       int64  `json:"id"`
	Username string `json:"Username"`
	Name     string `json:"Name"`
	Surname  string `json:"Surname"`
}

type PendingFriends struct {
	FromUser string `json:"from_user"`
	ToUser   string `json:"to_user"`
}

type PendingInvite struct {
	FromUser string `json:"from_user"`
	ToUser   string `json:"to_user"`
	Confimed bool   `json:confirmed`
}
