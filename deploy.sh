echo "DEPLOY START"

echo "STOP & REMOVE ALL DOCKER CONTAINERS"
docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
echo "STOP ALL DOCKER CONTAINERS -> DONE"

timestamp=`date +%Y%m%d%H%M`
docker_image_name=atmo-server-"$timestamp"

echo "docker iamge name is " + "$docker_image_name"

docker build -t "$docker_image_name" .

docker run -d -p 8080:8080 "$docker_image_name"

echo "DEPLOY END"
