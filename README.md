# my-house

Created
from [Spring Initialzr](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=2.7.1&packaging=jar&jvmVersion=11&groupId=org.mocuishla&artifactId=my-house&name=my-house&description=Demo%20project%20for%20Spring%20Boot&packageName=org.mocuishla.myhouse&dependencies=web,data-jpa,liquibase,postgresql)

## Docker

Using Colima instead of Docker Desktop.
See [these instructions](https://docs.google.com/document/d/1BlIf5PuRl8hgW2NTQ8AIAWeT1UwvpPA5pUNTU2EGPhE/edit)

### Useful commands

- To start Colima (needed of each mac reboot):

```shell
colima start
```

- To stop Colima (needed of each mac reboot):

```shell
colima stop
```

- To start database docker image, from project folder (where [docker-compose.yml](./docker-compose.yml) is) run:

```shell
docker-compose up -d
```

- To check that the database is ready:

```shell
docker ps
```

- To stop the database docker image, from project folder (where [docker-compose.yml](./docker-compose.yml) is) run:

```shell
docker-compose down
```

### Tooling

Install [DBeaver](https://dbeaver.io/) to open database manually:

```shell
brew install --cask dbeaver-community
```