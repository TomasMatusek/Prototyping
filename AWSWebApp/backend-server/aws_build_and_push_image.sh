# Build image, tag it with lates and push to AWS repository
docker build -t matusek-solutions-backend-server .
docker matuseksolutions tag matusek-solutions-backend-server:latest 555037798326.dkr.ecr.eu-central-1.amazonaws.com/matusek-solutions-backend-server:latest
docker push 555037798326.dkr.ecr.eu-central-1.amazonaws.com/matusek-solutions-backend-server:latest