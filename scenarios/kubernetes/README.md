# Kubernetes installation

This folder contains some kubernetes object files for a deployment of fake-rabbit to a kubernetes cluster.

The files are mainly derived from the helm scenario in `scenarios/helm`.

## Install

```sh
kubectl apply -f configMap.yaml deployment.yaml service.yaml
```

## Uninstall

```sh
kubectl delete -f configMap.yaml deployment.yaml service.yaml
```
