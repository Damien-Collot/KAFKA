# KAFKA

## Coordonnées ##

### Damien COLLOT, damien.collot@uphf.fr, 21902598

### Nicolas WROBEL, nicolas.wrobel@uphf.fr, 22205970

## Question 1: Alternative à Kafka

### Architecture RESTful Proposée

#### Service de Lecture et Conversion

- **Fonction :** Surveille un dossier pour les nouveaux fichiers HL7.
- **Traitement :** Lit, convertit les fichiers en JSON, et les envoie au service d'intégration.
- **Communication :** Utilise HTTP POST pour envoyer les données converties.

#### Service d'Intégration dans la Base de Données

- **Fonction :** Réceptionne les données JSON.
- **Traitement :** Insère les données dans PostgreSQL.
- **Activation :** Se déclenche sur réception de données du service de lecture.

#### Application Console

- **Fonction :** Permet l'interaction utilisateur via des commandes.
- **Traitement :** Les commandes sont converties en requêtes HTTP.
- **Communication :** Envoie des requêtes au service d'intégration ou à un service de traitement.

### Avantages et Inconvénients

**Avantages :**

- Facilité de mise en place et d'interprétation.
- Indépendance des services communiquant via des APIs REST standards.

**Inconvénients :**

- Performances potentiellement réduites par rapport à Kafka.
- Les appels synchrones peuvent introduire des latences.

### Applicabilité

Convient pour des applications de taille moyenne ou pour des équipes sans expertise Kafka, mais peut être limitée pour des charges lourdes ou des besoins de traitement en temps réel.

## Question 2: Comparaison des Architectures

### Architecture avec Kafka

- **Complexité :** Plus élevée, nécessite une expertise spécifique.
- **Performances :** Excellentes pour les gros volumes de données et le traitement en temps réel.
- **Scalabilité :** Très bonne pour les applications à grande échelle.

### Architecture RESTful

- **Simplicité :** Plus facile à comprendre et à implémenter.
- **Maintenance :** Plus aisée pour des projets de petite à moyenne taille.
- **Limitations :** Potentielles difficultés de gestion avec augmentation du trafic.

### Résumé

Pour des besoins modérés, l'architecture RESTful est préférable. Kafka est recommandé pour des besoins de haute disponibilité et performance.

## Question 3: Sécurisation des Échanges dans Kafka

### Méthodes de Sécurisation

- **Authentification :** SASL/PLAIN, SASL/SCRAM, ou SSL/TLS pour l'authentification par certificats.
- **Autorisation :** ACLs pour définir les permissions sur les topics.
- **Chiffrement :** SSL/TLS pour sécuriser les données en transit.

### Mise en Place de SSL/TLS

1. **Génération des Certificats :** Pour tous les brokers et clients.
2. **Configuration de Kafka :** Utilisation de SSL/TLS, spécification des chemins vers les certificats et clés.
3. **Configuration des Clients :** Les producteurs et consommateurs doivent également utiliser SSL/TLS.
4. **Tests :** Validation du bon fonctionnement, du chiffrement des données, et de l'authentification des utilisateurs.

### Conclusion

L'utilisation de SSL/TLS garantit la sécurité des données durant le transport et l'accès restreint aux utilisateurs authentifiés.
