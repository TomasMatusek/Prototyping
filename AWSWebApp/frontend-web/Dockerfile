FROM nginx:1.20.0-alpine
COPY ../frontend-client/build /var/www
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
ENTRYPOINT ["nginx","-g","daemon off;"]