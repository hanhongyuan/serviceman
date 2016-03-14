sudo mvn package docker:build -Dmaven.test.skip=true -DpushImage
cd docker
sudo docker-compose up
