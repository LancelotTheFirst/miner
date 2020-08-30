> ## Project definition
> Проект представляет собой упрощённую имитацию клиента-майнера распределённой сети блокчейн по типу клиента сети BitCoin.
>
> Реализация основывается на Spring Framework.
>
> Далёкая цель - реализовать сеть взаимодействующих между собой клиентов с возможностью добавлять к работающей сети 
> клиентов и отключать их в онлайне. На текущий момент майнер работает как в одиночном экземпляре, 
> так и в формате двух обменивающихся результатами майнинга клиентов. 
>
> Приложение в бесконечном цикле получает полезную нагрузку (payload) блока, майнит из неё блок, добавляет его 
> в блокчейн, информирует другого майнера о созданном блоке, периодически проверяет не пришёл ли смайненный блок от 
> другого майнера, чтобы добавить его в свой блокчейн. Майнинг блоков происходит при помощи консенсуса Proof of Work. 
> При перезапуске майнер начинает работать с чистого листа - состояние пока не сохраняется.
>
> В программе реализованы два вида асинхронного взаимодействия: 
> 1) входящие блоки, которые намайнил другой майнер сохраняются в бд (Mongo), затем чтобы ядро - MiningEngine
> само решало когда приостановить майнинг чтобы проверить нет ли входящих блоков, 
> 2) распространение блоков происходит в событийном формате - как только блок смайнен, он отправляется в очередь, а слушатель тут же запускает отправку по HTTP блока другому майнеру.

![Demo Animation](../assets/MinerVersionOn-30-08-2020.png)

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