# Demo of app deployment in AWS env

```sh

# Build application
./gradlew clean buildJar

# Login to AWS for push
$(aws ecr get-login --no-include-email --region eu-central-1)

# Build docker image
cd docker
./clean.sh
./build.sh
./push.sh

# Show latest image in docker hub
# https://hub.docker.com/r/db8os/fake-rabbit

# Switch to scenario helm
cd ../scenarios/helm/
# Show + explain kubectl
kubectl get svc
# Explain helm
# Show folder fake-rabbit
find fake-rabbit -type f

# Show kubernetes entities
helm template fake-rabbit/ > out.yaml
code out.yaml

# Install demo service
helm install fake-rabbit/ --name k8s-demo
# Show status
helm status k8s-demo

# Go to kubernetes control plane and show service running with AWS LB
# show app + env.txt
# copy url
# start curl-loop in other terminal
watch -n 1 'curl http://af3964f622a1923e98564045b26147c0-123456789.eu-central-1.elb.amazonaws.com/env.txt | grep HOSTNAME'

# Change replicaCount in values.yaml to 3
# ... and upgrade application
helm upgrade k8s-demo fake-rabbit/
# Delete service
helm delete --purge k8s-demo

```
