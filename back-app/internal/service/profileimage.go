package service

import (
	"chat-project-go/internal/repository"
	"fmt"
	"image"
	"image/png"
	"os"
)

type ProfileImageService interface {
	GetFromPath(location string)
}

type profileImageService struct {
	userRepository repository.UserRepositoryContract
	tokenManager   TokenManager
}

func NewProfileImageService() ProfileImageService {
	return &profileImageService{}
}

func (s *profileImageService) GetFromPath(location string) {
	existingImageFile, err := os.Open("/../../storage/2b.png")
	if err != nil {
		fmt.Println(err)
	}
	defer existingImageFile.Close()
	imageData, imageType, err := image.Decode(existingImageFile)
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(imageData)
	fmt.Println(imageType)
	existingImageFile.Seek(0, 0)
	loadedImage, err := png.Decode(existingImageFile)
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(loadedImage)
	// imgFile, err := os.Open("/../../storage/se.jpg")
	// defer imgFile.Close()
	// if err != nil {
	// 	log.Println("Cannot read file:", err)
	// }

	// img, _, err := image.Decode(imgFile)
	// if err != nil {
	// 	log.Println("Cannot decode file:", err)
	// }

	// fmt.Println(img)
}
