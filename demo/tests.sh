curl -X POST -H "Content-Type: application/json" -d '{"username": "user1", "passwdString": "hahaha"}' http://localhost:35080/user/register
curl -X POST -H "Content-Type: application/json" -d '{"username": "user2", "passwdString": "sdfghjk"}' http://localhost:35080/user/register
curl -X POST -H "Content-Type: application/json" -d '{"name": "album1", "description": "nice pics", "restrictMode": 2}' http://localhost:35080/album/add
curl -X POST -H "Content-Type: application/json" -d '{"name": "2", "description": "nice pics", "restrictMode": 3}' http://localhost:35080/album/add
curl -X POST -H "Content-Type: application/json" -d '{"name": "##", "description": "aahahha", "restrictMode": 1}' http://localhost:35080/album/add
curl -X POST -F "path=@./testpic/avagz.jpg" -F "albumId=3" -F "face=true" http://localhost:35080/image/add
curl -X POST -F "path=@./testpic/avagz.jpg" -F "albumId=3" -F "face=true" http://localhost:35080/image/add
curl -X DELETE http://localhost:35080/album/delete/3
curl -X POST -F "path=@./testpic/avagz.jpg" -F "albumId=1" -F "face=true" http://localhost:35080/image/add
curl -X POST -F "path=@./testpic/avagz.jpg" -F "albumId=2" -F "face=true" http://localhost:35080/image/add
curl -X POST -F "path=@./testpic/avagz.jpg" -F "albumId=1" -F "face=true" http://localhost:35080/image/add
curl -X POST -F "path=@./testpic/avagz.jpg" -F "albumId=2" -F "face=true" http://localhost:35080/image/add
curl -X POST -F "path=@./testpic/avagz.jpg" -F "albumId=1" -F "face=true" http://localhost:35080/image/add
curl -X POST -F "path=@./testpic/avagz.jpg" -F "albumId=2" -F "face=true" http://localhost:35080/image/add
curl -X POST -F "path=@./testpic/avagz.jpg" -F "albumId=1" -F "face=true" http://localhost:35080/image/add
curl -X POST -F "path=@./testpic/avagz.jpg" -F "albumId=2" -F "face=true" http://localhost:35080/image/add
curl -X POST -F "path=@./testpic/avagz.jpg" -F "albumId=2" -F "face=true" http://localhost:35080/image/add
curl -X POST -F "path=@./testpic/avagz.jpg" -F "albumId=1" -F "face=true" http://localhost:35080/image/add
curl -X POST -F "path=@./testpic/avagz.jpg" -F "albumId=2" -F "face=true" http://localhost:35080/image/add
curl -X POST -H "Content-Type: application/json" -d '[2,4,5,6,8,9,11]' http://localhost:35080/album/move\?from\=1\&to\=2
curl -X GET http://localhost:35080/album/1 
curl -X GET http://localhost:35080/album/2
curl -X GET http://localhost:35080/album/3
curl -X DELETE http://localhost:35080/image/delete/2
curl -X DELETE http://localhost:35080/image/delete/4
curl -X POST -H "Content-Type: application/json" -d '{"username": "user1", "picId": 9, "text": "nice pic!!"}' http://localhost:35080/image/comment
curl -X POST -H "Content-Type: application/json" -d '{"username": "user2", "picId": 9, "text": "drop table users;"}' http://localhost:35080/image/comment
curl -X GET http://localhost:35080/image/9/comments


