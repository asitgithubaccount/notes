#Postgres insert into person table
INSERT INTO person
(id, create_time, email, last_updated_time, password)
VALUES('notesuser', current_timestamp, 'asgupta@abc.com', current_timestamp, 'notespassword');

# create docker image
mvn clean install package

docker build . -t notes-app

# update env file for host, db name, user name and password and run the image as below
docker run -p 8080:8080 --env-file ./env.properties --name notes-app -d notes-app:latest


#execute notes-script.sh to run the post request for notes
# you should get output for example
{

"notes" : [
        {
        "title":"abc",
        "note_text":"test note"
        }
]
}
*   Trying ::1...
* Connected to localhost (::1) port 8080 (#0)
* Server auth using Basic with user 'notesuser'
> POST /notes/notes HTTP/1.1
> Host: localhost:8080
> Authorization: Basic bm90ZXN1c2VyOm5vdGVzcGFzc3dvcmQ=
> User-Agent: curl/7.43.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 65
>
* upload completely sent off: 65 out of 65 bytes
< HTTP/1.1 201
< Set-Cookie: JSESSIONID=1FDF2606AF1E0BDE4CFDB9C6B21A014E; Path=/notes; HttpOnly
< X-Content-Type-Options: nosniff
< X-XSS-Protection: 1; mode=block
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Frame-Options: DENY
< Transfer-Encoding: chunked
< Date: Fri, 06 Jul 2018 05:05:17 GMT
<
* Connection #0 to host localhost left intact
