docker-compose rm -f
docker rmi miner-one
docker rmi miner-two
mvn package
docker build --rm -t miner-one -f Dockerfile-miner-one .
docker build --rm -t miner-two -f Dockerfile-miner-two .
docker-compose up
