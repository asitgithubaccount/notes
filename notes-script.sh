#!/usr/bin/env bash

bodypost=$(cat << EOF
{

"notes" : [
	{
	"title":"abc",
	"note_text":"test note"
	}
]
}
EOF
)

echo "$bodypost"

curl -v -u notesuser:notespassword -X POST -H "Content-Type: application/json" -d "$bodypost" http://localhost:8080/notes/notes
