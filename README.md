# nFlow POC

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