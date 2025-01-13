# MiniPostman

Une application de bureau légère inspirée de Postman pour tester les API REST, construite avec JavaFX et Spring Boot.

## 🚀 Fonctionnalités

- Supporte les méthodes HTTP principales (GET, POST, PUT, DELETE)
- Gestion dynamique des headers
- Affichage des réponses JSON

## 🛠 Technologies Utilisées

- Java 17
- JavaFX - Interface utilisateur
- Spring Boot 3.4.1 - Framework backend
- Spring RestClient - Client HTTP
- Lombok - Réduction du boilerplate
- Maven - Gestion des dépendances

## 📋 Prérequis

- Java 17 ou supérieur
- Maven 3.6 ou supérieur

## 🔧 Installation

1. Cloner le repository
```bash
git clone https://github.com/TheScattyPotty/MiniPostman.git
```

2. Compiler le projet
```bash
mvn clean install
```

3. Lancer l'application
```bash
mvn javafx:run
```

## 💡 Utilisation

1. Sélectionnez la méthode HTTP (GET, POST, PUT, DELETE)
2. Entrez l'URL de l'API
3. Ajoutez des headers si nécessaire (utilisez le bouton "Add Header")
4. Pour POST/PUT, entrez le corps de la requête au format JSON
5. Cliquez sur "Send Request"
6. La réponse s'affichera dans une fenêtre de dialogue

## 🏗 Structure du Projet

```
miniPostman/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/urlTester/miniPostman/
│   │   │       ├── Controller/
│   │   │       │   └── MainController.java
|   |   |       ├── Configuration/
│   │   │       │   └── RestClientConfiguration.java
│   │   │       └── MiniPostmanApplication.java
│   │   └── resources/
│   │       └── fxml/
│   │           └── main.fxml
└── pom.xml
```

### Have Fun :)