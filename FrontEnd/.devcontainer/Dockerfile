# This is development docker
# for deployment, use the installer found
# in the parent directory
FROM ubuntu:jammy
LABEL author="Dustin Tracy"
LABEL email="dtracy.uf@gmail.com"

# Shared Requirements
RUN apt update
RUN apt upgrade -y
RUN apt update
RUN apt install build-essential -y

# Development Environment
# OH-MY-ZSH
RUN apt update
RUN apt install wget -y
RUN sh -c "$(wget -O- https://github.com/deluan/zsh-in-docker/releases/download/v1.1.5/zsh-in-docker.sh)" -- \
   -t robbyrussell

# Clojure
RUN apt install -y npm
RUN apt update
RUN apt install -y default-jre
RUN apt install -y leiningen
RUN npm install -g n
RUN n lts
RUN npm install -g shadow-cljs
