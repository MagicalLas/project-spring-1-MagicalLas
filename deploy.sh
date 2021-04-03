echo "DEPLOY START"

echo "STOP & REMOVE ALL DOCKER CONTAINERS"
docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
echo "STOP ALL DOCKER CONTAINERS -> DONE"

timestamp=`date +%Y%m%d%H%M`

echo "SERVER DEPLOY START"

server_docker_image_name=atmo-server-"$timestamp"
echo "docker iamge name is " + "$server_docker_image_name"

docker build -t "$server_docker_image_name" .
docker run -d -p 8081:8080 "$server_docker_image_name"
echo "SERVER DEPLOY DONE"

echo "FRONTEND DEPLOY START"

front_docker_image_name=atmo-front-"$timestamp"
echo "docker iamge name is " + "$front_docker_image_name"

docker build -t "$front_docker_image_name" ./frontend/weather/
docker run -d -p 8080:3000 "$front_docker_image_name"
echo "FRONTEND DEPLOY DONE"

echo "DEPLOY END"
