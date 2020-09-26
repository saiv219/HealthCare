# HealthCare

- Add a new enrollee  -------/enrolleesService/addEnrollees
- Modify an existing enrollee---------/enrolleesService/modifyEnrollees/{id}
- Remove an enrollee entirelyGET ---------------/enrolleesService/removeEnrollees/{id}
- Add dependents to an enrollee   --------------- /dependentService/addDependentToEnrol/{id}
- Remove dependents from an enrollee--------------/dependentService/removeDependentFromEnrollees/{eid}/{did}
- Modify existing dependents----------------------/dependentService/modifyDependentFromEnrol/{eid}/{did}


The application helps us to add enrollees and its dependents.
There are API's in Controller package which gives us list, update, add and delete of enrollees and dependents.

In src/test/java, there are testcases for the possible scenarios.
In src/main/java/com.crud.exception, there are exceptions for all the exceptions of the application.

In Application.properties file, there are database properties.

Dockerfile and docker-compose.yml files are also added
