# nFlow POC

As the tile says, Im using the nFlow library for this POC https://github.com/NitorCreations/nflow
nFlow is a solution for orchestrating business processes. Depending on where you're coming from, you can view nFlow as any of the following:

- Microservices orchestrator (as in Saga-pattern)
- Guaranteed delivery computing
- Replacement for business process engine
- Persistent finite-state machine

Key Features
- Non-declarative — workflows are defined as code
- Visualization — workflows can be visualized in nFlow Explorer
- Embeddable — usually embedded as a library, but a standalone server is also provided
- High availability — the same workflows can be processed by multiple deployments
- Fault tolerant — automatic recovery if runtime environment crashes
- Atomic state updates — uses and requires a relational database for atomic state updates and locking
- Multiple databases supported — PostgreSQL, MySQL, MariaDB, Oracle, Microsoft SQL Server, DB2, H2

### Usecase and goal

Imagine an usecase where you need to process some data. The processing should be done by multiple instances and if some of them, or all, fail - the new ones should pick up the work and process the unfinished data.
Basically we want high available and recoverable processing service.

The POC have one simple service that is producing 200 IDs on startap and nFlow is processing them.

The goal of the POC is to demonstrate that several instances can start the work , and if they restart the new one will recover the work.

### Starting the services 
run this command to start the database and 5 application instances

`docker-compose up -d --scale app=5`

After successful startup the app instances will start producing and working on dummy data.

To test the recoverability of the app you can kill all app instance with the command

`docker kill $(docker ps -a --format '{{.ID}}' --filter "ancestor=NAME_OF_THE_APP_DOCKER_IMAGE")`

and start 3 new instances again to pick up the unfinished work, again with the comand

`docker-compose up -d --scale app=3`


### Checking the database 

To check the status of the registered workflows run this query

``SELECT count(*), wf.status  from nflow_workflow wf group by wf.status``

if you start 8 app instances in total, and they all simulate 200 ids , the expected output should be __1600 workflows as finished__

### nFlow maintenance
deletes all data older than : __nflow.maintenance.initial.delete.olderThan__

puts data in archive after some time : __nflow.maintenance.initial.archive.olderThan__

deletes the archive after some time : __nflow.maintenance.initial.deleteArchived.olderThan__