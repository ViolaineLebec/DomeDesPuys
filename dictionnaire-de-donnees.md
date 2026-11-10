# Projet "Le Dôme des Puys" — Dictionnaire de données

Ce document décrit les informations à conserver pour chaque notion du domaine.

Il ne contient **aucun nom d'annotation ni de type Java** : les types indiqués sont des types métier, à vous de choisir leur équivalent dans le langage et de déterminer la façon dont chaque relation se traduit techniquement.

---

## Conventions de lecture

| Colonne | Signification |
|---|---|
| **Attribut** | Nom suggéré de la propriété. Vous pouvez l'adapter, à condition de rester cohérent sur l'ensemble du projet. |
| **Type métier** | La nature de l'information, pas le type Java. |
| **Obligatoire** | « Oui » signifie qu'une instance ne peut pas exister sans cette information. |
| **Remarques** | Contraintes, valeurs attendues, cas particuliers. |

Chaque entité possède un **identifiant technique**, auto-généré, distinct des données métier. Il n'est pas répété dans les tableaux ci-dessous.

---

## 1. User

L'utilisateur du site : celui qui consulte le catalogue, enregistre ses adresses et passe des commandes.

| Attribut | Type métier | Obligatoire | Remarques |
|---|---|---|---|
| `email` | Texte court | Oui | Unique sur l'ensemble du site. Sert d'identifiant de connexion. |
| `password` | Texte court | Oui | Ne sera jamais stocké en clair. Prévoir une longueur suffisante pour accueillir une empreinte, pas un mot de passe. |
| `firstname` | Texte court | Oui | |
| `lastname` | Texte court | Oui | |
| `phone` | Texte court | Non | Tous les comptes n'ont pas de numéro renseigné. |
| `createdAt` | Date et heure | Oui | Date de création du compte. Renseignée automatiquement, jamais saisie par l'utilisateur. |

---

## 2. Address

Une adresse enregistrée par un utilisateur, utilisable pour la livraison ou la facturation.

| Attribut | Type métier | Obligatoire | Remarques |
|---|---|---|---|
| `line` | Texte court | Oui | Numéro et nom de voie. |
| `extraLine` | Texte court | Non | Complément : bâtiment, étage, digicode. Souvent vide. |
| `city` | Texte court | Oui | |
| `zipCode` | Texte court | Oui | Un code postal n'est pas un nombre : il peut commencer par un zéro et contenir des lettres selon le pays. |
| `country` | Texte court | Oui | |
| `phone` | Texte court | Non | Numéro du destinataire, qui n'est pas forcément celui du compte. |
| `firstname` | Texte court | Oui | Le destinataire n'est pas forcément le titulaire du compte. |
| `lastname` | Texte court | Oui | Idem. |
| `createdAt` | Date et heure | Oui | Renseignée automatiquement. |

---

## 3. Brand

La marque d'un produit.

| Attribut | Type métier | Obligatoire | Remarques |
|---|---|---|---|
| `label` | Texte court | Oui | Nom commercial de la marque. Unique. |
| `logo` | Texte court | Non | Chemin vers le fichier image. Certaines marques n'ont pas encore de logo. |
| `slug` | Texte court | Oui | Version du libellé utilisable dans une URL : minuscules, sans accent ni espace. Unique. Généré à partir du libellé, jamais saisi à la main. |

---

## 4. Product

Un article du catalogue.

| Attribut | Type métier | Obligatoire | Remarques |
|---|---|---|---|
| `label` | Texte court | Oui | Nom commercial du produit. |
| `description` | Texte long | Non | Peut faire plusieurs milliers de caractères. Les nouveaux produits en sont parfois dépourvus. |
| `price` | Montant | Oui | En euros, avec les centimes. Toujours strictement positif. Un montant ne se stocke pas dans un type à virgule flottante : les arrondis successifs y sont imprévisibles. |
| `slug` | Texte court | Oui | Même principe que pour la marque. Unique. |
| `createdAt` | Date et heure | Oui | Renseignée automatiquement. |

---

## 5. Image

Une photographie illustrant un produit.

| Attribut | Type métier | Obligatoire | Remarques |
|---|---|---|---|
| `path` | Texte court | Oui | Chemin du fichier, sans l'extension. |
| `extension` | Texte court | Oui | `jpg`, `png`, `webp`. |
| `isDefault` | Booléen | Oui | Indique l'image principale, celle affichée dans les listes. Un produit n'en a qu'une. |

---


## 6. Order

Une commande passée par un utilisateur.

| Attribut | Type métier | Obligatoire | Remarques |
|---|---|---|---|
| `number` | Texte court | Oui | Référence lisible communiquée au client, par exemple `CMD-2026-000142`. Unique. Distincte de l'identifiant technique. |
| `status` | Valeur parmi une liste fermée | Oui | `PENDING`, `PAID`, `SHIPPED`, `DELIVERED`, `CANCELLED`. Aucune autre valeur n'est acceptable. Une commande qui vient d'être créée est `PENDING`. |
| `createdAt` | Date et heure | Oui | Renseignée automatiquement. |

> `order` est un mot réservé du langage SQL. Si vous laissez le framework nommer la table d'après la classe, la création du schéma échouera. À vous de trouver comment imposer un autre nom de table.

---

## 7. OrderProduct

La présence d'un produit dans une commande, avec la quantité commandée.

| Attribut | Type métier | Obligatoire | Remarques |
|---|---|---|---|
| `quantity` | Nombre entier | Oui | Toujours supérieur ou égal à 1. |
| `price` | Nombre entier | Oui | Toujours supérieur ou égal à 0. |

---

## Les liaisons entre notions

À vous de déterminer la nature exacte de chaque relation à partir de ces phrases, et la façon de l'implémenter.

- Un utilisateur peut enregistrer plusieurs adresses ; une adresse appartient à un seul utilisateur.
- Une marque regroupe plusieurs produits ; un produit porte une seule marque.
- Un produit est illustré par plusieurs images ; une image illustre un seul produit.
- Un produit peut être décrit par plusieurs caractéristiques, et une même caractéristique peut décrire plusieurs produits. Pour chaque couple produit / caractéristique, une valeur est enregistrée.
- Un utilisateur peut passer plusieurs commandes ; une commande est passée par un seul utilisateur.
- Une commande porte sur plusieurs produits, et un même produit peut figurer dans plusieurs commandes. Pour chaque couple commande / produit, une quantité est enregistrée.
