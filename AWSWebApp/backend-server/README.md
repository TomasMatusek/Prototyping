# Backend Server

Backend server for personal website.

### Build

Compile, test and package

```shell
mvn package
```

Then run build to build image and push to AWS

```shell
docker build -t matusek-solutions-backend-server .
docker matuseksolutions tag matusek-solutions-backend-server:latest 555037798326.dkr.ecr.eu-central-1.amazonaws.com/matusek-solutions-backend-server:latest
docker push 555037798326.dkr.ecr.eu-central-1.amazonaws.com/matusek-solutions-backend-server:latest
```