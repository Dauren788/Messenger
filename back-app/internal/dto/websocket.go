package dto

type WebsocketMsg struct {
	Type    float64     `json:"type"`
	Message interface{} `json:"message"`
}
