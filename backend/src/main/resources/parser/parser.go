package main

import (
	"C"
	"encoding/json"
	"fmt"
	"log"
	"os"
	"path/filepath"
	"strings"
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
	Children []DirObject `json:"childrens"`
}

func (f File) GetName() string   { return f.Name }
func (f File) IsDir() bool       { return false }
func (f Folder) GetName() string { return f.Name }
func (f Folder) IsDir() bool     { return true }

// name - имя проекта (для вывода json ), pathTo - путь до директории парсинга, pathAfter - куда создать json файл
func parser(name string, pathTo string, pathAfter string) {
	rootPath := pathTo

	// создание корневой папки
	root := &Folder{
		Name:     name,
		Children: []DirObject{},
	}

	foldersMap := map[string]*Folder{
		rootPath: root,
	}

	err := filepath.Walk(pathTo,
		func(path string, info os.FileInfo, err error) error {

			// проверка на ошибки
			if err != nil {
				return err
			}

			// для того, чтоб не дублировалась корневая папка
			if rootPath == path {
				return nil
			}

			if strings.HasPrefix(info.Name(), ".") {
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
				if err == nil {
					file := File{
						Name:    info.Name(),
						Content: string(content),
					}
					parent.Children = append(parent.Children, file)
				} else {
					fmt.Println("файл " + info.Name() + " проблемный")
				}

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
	// проверка на ошибки
	if err != nil {
		log.Println(err)
	}

	createJSON(name, pathAfter, root)
}

func createJSON(name string, pathAfter string, root *Folder) {

	folderJSON, err := json.MarshalIndent(root, "", "	")

	// проверка на ошибки
	if err != nil {
		return
	}

	err = os.WriteFile(pathAfter+"/"+name+".json", folderJSON, 0644)
}

func main() {

}
