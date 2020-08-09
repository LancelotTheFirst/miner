> ## Launch instruction
> To build application and start two interacting miners in docker-containers just run build-and-start-in-docker.sh
> To just start stopped applications run start-in-docker.sh
>
> To run app in IDEA: first - run start-local-mongo.sh and second - run one miner or both of them by customized IDE
> runner passing command line args. 
>
> ## Development plan
> Plan, current tasks and history are in the [Plan](Plan.md) 
>
> ## Deployment scheme
> Deployment consists of four docker-containers: app and mongo containers for each of two miners, so each miner has
> its own database.