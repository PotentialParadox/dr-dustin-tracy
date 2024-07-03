# This is development docker
# for deployment, use the installer found
# in the parent directory
FROM ubuntu:jammy-20230624 as build-stage
LABEL author="Dustin Tracy"
LABEL email="dtracy.uf@gmail.com"

# System
RUN apt-get -y update
RUN apt install build-essential -y
RUN apt install -y curl
RUN apt install -y iputils-ping

RUN apt update
RUN apt install -y npm
RUN apt-get update && apt-get install wget
RUN npm install -g n
RUN n lts

# Main
WORKDIR /app/FrontEnd
COPY dr-dustin-tracy/package.json .
RUN apt update
RUN npm config rm proxy
RUN npm config rm https-proxy
RUN npm install
RUN apt update
RUN apt install -y default-jre
RUN npm install -g shadow-cljs
COPY dr-dustin-tracy/. .
RUN shadow-cljs release app

FROM nginx:1.25.2 as production
# COPY dr-dustin-tracy/nginx/default.conf /etc/nginx/conf.d/default.conf
COPY --from=build-stage /app/FrontEnd/dist /usr/share/nginx/html
COPY dr-dustin-tracy/resources/public/images /usr/share/nginx/html/images

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]