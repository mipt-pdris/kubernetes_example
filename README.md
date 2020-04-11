# How to setup

1. Create postgres pod, service and persistent volume with configuration `postgres.yaml`:\
`kubectl create -f kube/postgres.yaml`
2. Package application into jar:\
`./gradlew clean build`
3. Create image of application:\
`docker build -t docker-boot .`
4. Create pod with deployment config for spring boot application with configuration `sp-deployment.yaml`:\
`kubectl create -f kube/sp-deployment.yaml`
5. Expose service for created deployment `kube-boot`:
`kubectl expose deployment kube-boot --type=LoadBalancer --port=8080` 
6. Get external url of `kube-boot` service:
`minikube service docker-boot --url`
7. Copy url with port that you get on previous stage and insert it in browser. You should see `Hello World! profile: prod`

# Troubleshoot
If your pod didn't start and stay in status `ErrImagePull`, then execute in terminal `eval $(minikube docker-env)`. 
This command will point your docker client to your machine's docker daemon. To undo that execute `eval $(minikube docker-env -u)`. 

To check logs of starting of your spring boot, get your pod name from your cluster(ex. `kube-boot-697fdf6ff4-jb6qc`) and execute command `kubectl logs <YOUR_POD_NAME>`

