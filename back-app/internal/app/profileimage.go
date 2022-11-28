package app

import "github.com/gin-gonic/gin"

func (s *Services) ProfileImage(c *gin.Context) {
	location := c.Query("imagePath")

	s.profileService.GetFromPath(location)
}
