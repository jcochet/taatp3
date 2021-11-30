# TAA TP3 - ABABOU Sarah & COCHET Julien - M2 Info IL
## Sommaire
- [Lancer le projet](#lancer-le-projet)
- [Requêtes](#requêtes)
- [Diagrammes de classes](#diagrammes-de-classes)
## Lancer le projet
Clonez le projet ou téléchargez la dernière release puis, lancer d'abord le serveur avec cette ligne de commande :
```java -cp hsqldb-2.5.1.jar org.hsqldb.Server```
Ensuite, lancer l'application Spring Boot
```java -jar spring_boot_app.jar```
Le projet n'ayant pas de partie front-end, vous pouvez directement vos requêtes (comme dans les exemples ci-dessous) depuis votre nagivateur depuis [localhost:8080/](localhost:8080/).
## Requêtes
### Utilisateur
#### createUser
Créé un nouvel utilisateur.
##### Paramètres :
- email : Email de l'utilisateur
- name : Nom de l'utilisateur
- password : Mot de passe de l'utilisateur
##### Exemple :
```localhost:8080/createUser?email=julien.cochet@etudiant.univ-rennes1.fr&name=jcochet&password=1234```
#### deleteUser
Supprime un utilisateur.
##### Paramètres :
- id : Identifiant de l'utilisateur
##### Exemple :
```localhost:8080/deleteUser?id=1```
#### getUsers
Affiche tous les utilisateur.
##### Exemple :
```localhost:8080/getUsers```
#### getUserByEmail
Affiche l'utilisateur ayant un email donné.
##### Paramètres :
- email : Email de l'utilisateur
##### Exemple :
```localhost:8080/getUserByEmail?email=julien.cochet@etudiant.univ-rennes1.fr```
#### getUserByName
Affiche l'utilisateur ayant un nom donné.
##### Paramètres :
- name : Nom de l'utilisateur
##### Exemple :
```localhost:8080/getUserByName?name=jcochet```
### Professionnel
#### createWorker
Créé un nouvel professionnel.
##### Paramètres :
- email : Email du professionnel
- name : Nom du professionnel
- password : Mot de passe du professionnel
- job : Travail du professionnel
##### Exemple :
```localhost:8080/createWorker?email=sarah.ababou@etudiant.univ-rennes1.fr&name=sababou&password=istic&job=student```
#### deleteWorker
Supprime un professionnel.
##### Paramètres :
- id : Identifiant du professionnel
##### Exemple :
```localhost:8080/deleteWorker?id=2```
#### getWorkers
Affiche tous les professionnels.
##### Exemple :
```localhost:8080/getWorkers```
#### getWorkerByEmail
Affiche le professionnel ayant un email donné.
##### Paramètres :
- email : Email du professionnel
##### Exemple :
```localhost:8080/getWorkerByEmail?email=sarah.ababou@etudiant.univ-rennes1.fr```
#### getWorkerByName
Affiche le professionnel ayant un nom donné.
##### Paramètres :
- name : Nom du professionnel
##### Exemple :
```localhost:8080/getWorkerByName?name=sababou```
#### getWorkerByJob
Affiche le professionnel ayant un travail donné.
##### Paramètres :
- job : Travail du professionnel
##### Exemple :
```localhost:8080/getWorkerByJob?job=student```
### Rendez-vous
#### createAppointment
Créé un nouveau rendez-vous.
##### Paramètres :
- date : Date du rendez-vous (format YYYY-MM-DD)
- duration : Durée du rendez-vous en minutes
- userId : Identifiant de l'utilisateur du rendez-vous
- workerId : Identifiant du professionnel du rendez-vous
- description : Description du rendez-vous
##### Exemple :
```localhost:8080/createAppointment?date=2021-12-01&duration=60&userId=1&workerId=2&description="RAS"```
#### deleteAppointment
Supprime un rendez-vous.
##### Paramètres :
- id : Identifiant du rendez-vous
##### Exemple :
```localhost:8080/deleteAppointment?id=3```
#### getAppointements
Affiche tous les rendez-vous.
##### Exemple :
```localhost:8080/getAppointements```
#### getUserByEmail
Affiche le rendez-vous ayant une date donnée.
##### Paramètres :
- date : Date du rendez-vous (format YYYY-MM-DD)
##### Exemple :
```localhost:8080/getAppointmentByDate?date=2021-12-01```
## Diagrammes de classes
Le projet comporte trois packages : "domain", "service" et "web" organisé ainsi :
### domain
![domain](https://github.com/jcochet/taatp3/blob/main/img/diagram/domain.png)
### service
![service](https://github.com/jcochet/taatp3/blob/main/img/diagram/service.png)
### web
![web](https://github.com/jcochet/taatp3/blob/main/img/diagram/web.png)