# API Craft

## Description
Exemple de projet présentant une API Craft dans un contexte de service REST.
On qualifie de craft une API dont la signature est définie par des objets métiers.

## Règles
Liste des règles utilisées pour le développement de ce projet:
- La signature des méthodes du contrôleur contient que des objets métiers
- Les objets métiers n'ont aucune dépendance à un framework particulier
- Le retour d'un appel REST est dans le format JSON
- An cas d'erreur lors d'un appel REST, le JSON retourné contient une propriété `code` qualifiant la cause de l'erreur

## Résultat
- Les objets métiers sont de POJO indépendants de tout framework
- Les objets métiers gèrent leur validation (principe de "locality")
- Les opérations de conversion reposent sur la validation des objets métiers
- Toute erreur de validation lève une exception de type `InvalidDomainValueExeption`
- L'exception est interceptée au niveau infrastructure pour retourner une erreur de type HTTP Rest
