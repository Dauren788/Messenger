FROM golang

WORKDIR /app

COPY . .

RUN go mod download

RUN go build cmd/main.go

EXPOSE 8080

CMD ["./main"]