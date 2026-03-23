# carcassonne_connection_library

TODO : introduction rapide

# Table des matières

- [Installation](#installation)
- [Utilisation](#utilisation)
- [Fonctionnement](#fonctionnement)
    + [Envoi et réception de messages](#envoi-et-réception-de-messages)
        * [Envoi d'un message X](#envoi-dun-message-x)
        * [Réception d'un message Y](#réception-dun-message-y)

# Installation

*Pour les prérequis ou une installation globale du projet, se référer au Readme du dépot participant_infos. [Lien](https://gitlab-etu.fil.univ-lille.fr/l3s6-projet-g6-star/participant_infos)*

Cloner le dépôt :  
Avec SSH : `git clone git@gitlab-etu.fil.univ-lille.fr:l3s6-projet-g6-star/carcassonne_connection_library.git`  
Avec HTTPS : `git clone https://gitlab-etu.fil.univ-lille.fr/l3s6-projet-g6-star/carcassonne_connection_library.git`

Installer la librairie :  
`mvn clean install`

# Utilisation

## Se connecter avec une interface basique

Dans le dossier `target` se trouvent 3 exécutables jar correspondant aux 3 types de rang intégrés dans cette librairie : `SpectatorMain`, `PlayerMain` et `AdminMain`.  
Ces exécutables correspondent à des interfaces basiques en ligne de commande. Se référer à la section [TODO]() pour une explication sur les différents rangs.
Pour lancer les exécutables, placez vous dans le dossier `target` et exécutez les commandes suivantes :  
Pour `SpectatorMain` : `java -jar SpectatorMain.jar <Host IP> <Host Port>`  
Pour `PlayerMain` : `java -jar PlayerMain.jar <Host IP> <Host Port> <Your ID>`  
Pour `AdminMain` : `java -jar AdminMain.jar <Host IP> <Host Port> <Your ID>`  

## Créer sa propre interface

# Fonctionnement

## Envoi et réception de messages

![schéma envoi reception](./images/schema_envoi_reception.jpg)

### Envoi d'un message X

1. La *View* demande au *Client* qui lui est associé d'envoyer un message X (avec les éventuels arguments).
2. Le *Client* vérifie qu'il possède la commande X dans sa liste de *Command* (autrement dit que l'utilisateur a le droit  d'envoyer cette commande).
3. Dans ce cas le *Client* demande à la *Command* de s'envoyer sur le socket.
4. Si le nombre d'argument passé est bon, la *Command* construit le message correspondant et demande au socket de l'envoyer vers le réflecteur.

### Réception d'un message Y

1. Le socket reçoit un message du réflecteur et demande au *Router* de le traiter.
2. Le *Router* vérifie qu'il possède la commande Y dans sa liste de *Command* (autrement dit que l'utilisateur a le droit de recevoir cette commande).
3. Dans ce cas le *Routeur* demande à la *Command* d'éxécuter la méthode correspondante dans la *View*.
4. Si le nombre d'argument reçu est bon, la *Command* appelle la méthode *updateOnY(arguments)* de la *View*.