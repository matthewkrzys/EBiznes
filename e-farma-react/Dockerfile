FROM ubuntu:18.04

ENV TZ=Europe/Warsaw

RUN apt-get update && apt-get upgrade -y

RUN apt-get install -y vim git wget unzip

RUN apt-get install -y openjdk-8-jdk

RUN useradd -ms /bin/bash krzysmateusz

RUN adduser krzysmateusz sudo

EXPOSE 3000

USER krzysmateusz
WORKDIR /home/krzysmateusz

RUN mkdir /home/krzysmateusz/tools
RUN wget https://downloads.lightbend.com/scala/2.13.5/scala-2.13.5.tgz
RUN tar xfz /home/krzysmateusz/scala-2.13.5.tgz -C /home/krzysmateusz/tools/
RUN rm /home/krzysmateusz/scala-2.13.5.tgz
ENV PATH="/home/krzysmateusz/tools/scala-2.13.5/bin:${PATH}"

RUN wget https://github.com/sbt/sbt/releases/download/v1.5.1/sbt-1.5.1.tgz
RUN tar xfz /home/krzysmateusz/sbt-1.5.1.tgz -C /home/krzysmateusz/tools/
RUN rm /home/krzysmateusz/sbt-1.5.1.tgz
ENV PATH="/home/krzysmateusz/tools/sbt/bin:${PATH}"

RUN wget https://nodejs.org/dist/v15.11.0/node-v15.11.0-linux-x64.tar.xz
RUN tar xf /home/krzysmateusz/node-v15.11.0-linux-x64.tar.xz -C /home/krzysmateusz/tools/
RUN rm /home/krzysmateusz/node-v15.11.0-linux-x64.tar.xz
ENV PATH="/home/krzysmateusz/tools/node-v15.11.0-linux-x64/bin:${PATH}"

RUN mkdir /home/krzysmateusz/projekt/

VOLUME /home/krzysmateusz/projekt

RUN mkdir /home/krzysmateusz/projekt/frontend

ADD . /home/krzysmateusz/projekt/frontend

WORKDIR /home/krzysmateusz/projekt/frontend

RUN npm install
RUN npm run build

CMD bash -c "npm start"