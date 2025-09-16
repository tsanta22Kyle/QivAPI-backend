#!/bin/bash
VERSION=$1
if [ -z "$VERSION" ]; then
  echo "Usage: ./upgrade.sh <version>"
  exit 1
fi

docker build -t tsanta22kyle/springboot-qivapi:$VERSION .
docker push tsanta22kyle/springboot-qivapi:$VERSION
echo "Image pushed: tsanta22kyle/springboot-qivapi:$VERSION"
