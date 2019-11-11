# A helm chart for fake-rabbit

These files show a minimum example of a [helm](https://helm.sh/) chart, which installs
a container of this application to a [kubernetes](https://kubernetes.io/) cluster with
[nginx](https://nginx.org/) as
[layer 7](https://en.wikipedia.org/wiki/OSI_model#Layer_7:_Application_Layer)
loadbalancer.

## helm commands

```bash
# install application to cluster
helm install fake-rabbit --name fake-rabbit

# remove application from cluster
helm delete --purge fake-rabbit

```

## connect with kubectl

```bash
# list pods of service
kubectl get pod | grep fake-rabbit

# forward localhost port 28080 to container port 8080 of a single pod
kubectl port-forward pod/fake-rabbit-69bd9dbf7-gbkw8 28080:8080

# list service
kubectl get svc | grep hello

# get first pod with 'glowroot' in name
kubectl get pod --output=custom-columns=NAME:.metadata.name | grep fake-rabbit | head -1

# run bash on pod
kubectl exec -it fake-rabbit-69bd9dbf7-gbkw8 -- bash

# open local proxy (e.g. to connect to kubernetes dashboard)
kubectl proxy
```
