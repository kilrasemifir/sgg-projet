# Projet d'exemple pour la formation SGG

## Description

Ce projet est un exemple de projet pour la formation SGG. Il met en place 3 microservices.

## Prérequis

- [Docker](https://docs.docker.com/engine/install/)
- Docker Compose
- un IDE java
- Java 11

## Installation

- Cloner le projet
- Lancer la commande `docker-compose up -d` à la racine du projet

Si vous venez mettre à jour le projet, 
il faut lancer la commande `docker-compose up -d --build` pour reconstruire les images.

## Deploiement d'un container

- Créer les images avec la commande `docker-compose build`
- Changer le om pour specifier l'adresse du registry avec la commande `docker image tag`
- Pusher l'image sur le registry avec la commande `docker push`