# ProductAPI - TP1 Spring Boot

## Description

**ProductAPI** est une API REST de gestion de produits développée avec Spring Boot.
Elle permet de lister les produits, d'en créer de nouveaux et de récupérer un produit
par son identifiant. Le projet suit une architecture en couches (model, repository,
service, controller) et propose également une interface web simple réalisée avec
Thymeleaf pour visualiser et ajouter des produits.

## Technologies utilisées

- **Spring Boot** — framework principal de l'application
- **Spring Web** — création de l'API REST
- **Spring Data JPA** — accès et persistance des données
- **H2 Database** — base de données en mémoire (profil `dev`)
- **MySQL** — base de données de production (profil `prod`)
- **Thymeleaf** — interface web de visualisation
- **Swagger / OpenAPI** — documentation interactive de l'API
- **Spring Security** — protection des endpoints (authentification Basic)
- **Git / GitHub** — gestion de versions
- **Render** — hébergement et déploiement continu

## Liens du projet

- **Dépôt GitHub :** https://github.com/mariemyahya06-jpg/ProductAPI
- **Application déployée (Render) :** https://productapi-v8u4.onrender.com
- **API REST :** https://productapi-v8u4.onrender.com/api/products
- **Interface Thymeleaf :** https://productapi-v8u4.onrender.com/products
- **Documentation Swagger :** https://productapi-v8u4.onrender.com/swagger-ui/index.html

> Remarque : l'offre gratuite de Render met le service en veille après une période
> d'inactivité. Le premier appel peut donc prendre 30 à 60 secondes (réveil du service).

## Étude comparative des hébergeurs

| Critère | Render | Railway | Vercel | Netlify |
|---|---|---|---|---|
| Facilité d'utilisation | Très simple (connexion GitHub + Dockerfile) | Simple, orienté développeurs | Très simple mais orienté frontend | Très simple mais orienté frontend |
| Intégration avec GitHub | Oui, déploiement automatique | Oui, déploiement automatique | Oui, déploiement automatique | Oui, déploiement automatique |
| Support du backend (Spring Boot) | Oui, via Docker (Java 21) | Oui, supporte Java / Docker | Non adapté (serverless JS, pas de backend persistant) | Non adapté (sites statiques / JAMstack) |
| Support des bases de données | PostgreSQL gérée (gratuit, limité) ; MySQL via service externe | Bases intégrées (MySQL, PostgreSQL) | Aucune base gérée | Aucune base gérée |
| Limites de la version gratuite | Service mis en veille après inactivité ; ressources limitées | Crédit d'essai limité, plus de réel palier gratuit permanent | Pensé pour le frontend ; pas de backend Java persistant | Pensé pour le frontend ; pas de backend Java persistant |

### Justification du choix : Render

Nous avons choisi **Render** pour plusieurs raisons adaptées à notre projet Spring Boot :

- Il permet le **déploiement directement depuis GitHub**, sans configuration complexe.
- Il prend en charge **Docker**, ce qui est nécessaire pour héberger une application
  **Spring Boot (Java 21)**, contrairement à Vercel et Netlify qui sont conçus pour le
  frontend et ne supportent pas un backend Java persistant.
- Il offre un **déploiement automatique** à chaque mise à jour de la branche `main`.
- Son **offre gratuite** est suffisante pour un projet académique et ne nécessite pas de
  carte bancaire.
- Sa prise en main est **simple et rapide**, idéale dans un contexte universitaire.

Railway reste une bonne alternative (support natif du backend et bases de données
intégrées), mais son offre gratuite est devenue plus restreinte. Vercel et Netlify, eux,
ne conviennent pas à un backend Spring Boot persistant.

## Gestion du code avec Git

Le projet utilise deux branches, conformément aux consignes du TP :

- **`dev`** : branche de développement, sur laquelle se font les modifications et les
  tests réguliers.
- **`main`** : branche stable correspondant à la version de production.

Une fois les fonctionnalités validées sur `dev`, elles sont fusionnées (`merge`) vers
`main`. Render est connecté à la branche `main` et **redéploie automatiquement**
l'application à chaque mise à jour de celle-ci.

## Exécution en local

Pour lancer l'application localement :

```bash
mvnw.cmd spring-boot:run
```

- Le profil actif par défaut est **`dev`** (base de données H2 en mémoire).
- Interface web : http://localhost:8085/products
- API REST : http://localhost:8085/api/products
- Console H2 : http://localhost:8085/h2-console (JDBC URL : `jdbc:h2:mem:productdb`, utilisateur : `sa`, mot de passe : vide)

### Profils disponibles

| Profil | Base de données | Usage |
|---|---|---|
| `dev` | H2 (en mémoire) | Développement local (profil par défaut) |
| `prod` | MySQL | Production / déploiement |

Pour lancer en profil production : `-Dspring.profiles.active=prod`.
