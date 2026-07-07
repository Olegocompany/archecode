package main

import (
	"encoding/json"
	"fmt"
	"log"
	"os"
	"path/filepath"
)

type DirObject interface {
	GetName() string
	IsDir() bool
}

type File struct {
	Name    string `json:"name"`
	Content string `json:"content"`
}

type Folder struct {
	Name     string      `json:"name"`
	Children []DirObject `json:"children"`
}

func (f File) GetName() string   { return f.Name }
func (f File) IsDir() bool       { return false }
func (f Folder) GetName() string { return f.Name }
func (f Folder) IsDir() bool     { return true }

func main() {

	rootPath := "проект"
	root := &Folder{
		Name:     filepath.Base(rootPath),
		Children: []DirObject{},
	}

	foldersMap := map[string]*Folder{
		rootPath: root,
	}

	err := filepath.Walk("проект",
		func(path string, info os.FileInfo, err error) error {
			if err != nil {
				return err
			}

			if rootPath == path {
				return nil
			}

			parentDir := filepath.Dir(path)
			parent, ok := foldersMap[parentDir]
			if !ok {
				return nil
			}

			if !info.IsDir() {
				filePath := filepath.Join(path)
				content, err := os.ReadFile(filePath)
				if err != nil {
					log.Printf("Не удалось прочитать файл по пути %s: %v\n", filePath)
					return nil
				}
				file := File{
					Name:    info.Name(),
					Content: string(content),
				}
				parent.Children = append(parent.Children, file)
			} else {
				folder := &Folder{
					Name:     info.Name(),
					Children: []DirObject{},
				}
				parent.Children = append(parent.Children, folder)
				foldersMap[path] = folder
			}
			return nil
		})
	if err != nil {
		log.Println(err)
	}

	folderJSON, err := json.MarshalIndent(root, "", "	")
	if err != nil {
		fmt.Println("Ошибка при конвертации в JSON:", err)
		return
	}
	err = os.WriteFile("data.json", folderJSON, 0644)
}
