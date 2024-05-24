[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/J2Nc4wpN)
# Spotitube 23/24 S2

| Voorblad gegevens |                    |
|-------------------|--------------------|
| *Naam*            | Nusayba El Mourabet |
| *Student nr*      | 2111303            |
| *Datum/periode*   | 4/05/2024         |
| *Docent*          | Bart van der Wal   |
| *Klas*            | OOSE-ITN-BF        |
| *Vak*             | DEA                |

<!-- TOC -->
* [Spotitube 23/24 S2](#spotitube-2324-s2)
    * [1. Inleiding](#1-inleiding)
    * [2. Package diagram](#2-package-diagram)
        * [2.1 OO Principles en Patterns](#21-oo-principles-en-patterns)
            * [2.1.1 Open Closed Principle](#211-open-closed-principle)
            * [2.1.2 Interface Segregation Principle](#212-interface-segregation-principle)
            * [2.1.3 Dependency Inversion Principle](#213-dependency-inversion-principle)
        * [2.2 Alternatieve oplossing](#22-alternatieve-oplossing)
        * [2.3 Keuze huidige oplossing](#23-keuze-huidige-oplossing)
    * [3. Deployment diagram](#3-deployment-diagram)
        * [3.1 Jakarta EE](#31-jakarta-ee)
        * [3.2 JDBC](#32-jdbc)
    * [4. Implementatie keuzes](#4-implementatie-keuzes)
        * [4.1 Datamapper Pattern](#41-datamapper-pattern)
        * [4.2 Exception Mapper](#42-exception-mapper)
        * [4.3 ApplicationScoped en Dependency Injection](#43-applicationscoped-en-dependency-injection)
        * [4.4 Tests](#44-tests)
        * [4.5 Beans.xml](#45-beansxml)
        * [4.6 Cors Filter](#46-cors-filter)
        * [4.8 Security](#48-security)
    * [5. Conclusie](#5-conclusie)
    * [6. Bronnen](#6-bronnen)
<!-- TOC -->

## 1. Inleiding
Hieronder vind je de uitwerking van de Spotitube back-end, ontwikkeld in Java (EE, e.g. JakartaEE). Deze back-end vormt een belangrijk onderdeel van de Spotitube applicatie, waarbij de front-end te vinden is in de [HANICA-DEA GitHub organisatie](https://github.com/HANICA-DEA/spotitube) en een gehoste versie hier:
[Spotitube client](https://hanica-dea.github.io/spotitube/).

Spotitube biedt een platform voor het streamen van muziek en het bekijken van videos op een plek. De Spotitube applicatie bestaat uit verschillende onderdelen, zoals het bekijken van playlists, het toevoegen van tracks aan playlists en het verwijderen van playlists.
Zie [opdrachtbeschrijving op onderwijsonline](https://han.onderwijsonline.nl/elearning/lesson/VqdQ5GwN) (HAN, 2023-b) voor meer informatie.

--- 

## 2. Package diagram
![img_6.png](img_6.png)
*1. Package Diagram Spotitube*

Hierboven is het package diagram te zien van de Spotitube applicatie. De applicatie is opgedeeld in verschillende packages/ lagen. De packages zijn als volgt geordend:
- Resources
- Domain (Service, DTO, Exceptions)
- Interfaces
- Datasource (Util, DAO)

Er is gekozen om in het package diagram niet alle methodes en attributen te tonen, maar enkel de belangrijkste voor het "Playlist" gedeelte van de applicatie. Dit is gedaan om het diagram overzichtelijk te houden.

In dit package diagram heb ik alle requirements van het "Playlist" gedeelte van de Spotitube applicatie verwerkt. Onderstaand zijn de nummers van de requirements te vinden (zie [Spotitube Stories](https://github.com/orgs/HANICA-DEA/projects/4/views/1)).
- **W1.4** *"As a User I want to use the Spotitube client to view my playlists."*
- **W1.6** *"As a Developer I want to add a second layer to my Application, so I can easily decouple and test my code."*
- **W2.7** *"As a Developer I want all data to be stored in a SQL database, so I no longer have to have hard-coded data."*
- **W2.9** *"As a User, I want to be able to edit the name of a Playlist."*
- **W2.10** *"As a User, I want to be able to create a new Playlist."*
- **W2.11** *"As a User, I want to be able to delete a Playlist."*
- **W3.12** *"As a Developer, I want to present a correct and complete Package Diagram, so I can give insight into the Application Structure.""*

De overige requirements zijn niet verwerkt in het package diagram, omdat deze niet relevant zijn voor het "Playlist" gedeelte van de applicatie, maar deze zijn wel geïmplementeerd.

Exceptions zijn opgenomen in het package diagram, de services maken gebruik van de exceptions om fouten af te vangen en door te geven aan de resources. Hierdoor is het mogelijk om fouten af te vangen en een correcte response te geven aan de client. De lijnen van exceptions naar services zijn niet opgenomen in het package diagram om het diagram overzichtelijk te houden.

### 2.1 OO Principles en Patterns
#### 2.1.1 Open Closed Principle
De Services maken gebruik van een interface die de DAO's implementeren. Hierdoor is het mogelijk om de DAO's te vervangen zonder dat de Services aangepast hoeven te worden. Dit zorgt voor een flexibele en uitbreidbare applicatie. Hierdoor voldoet deze implementatie aan het Open-Closed principe omdat de DAO's open zijn voor extension maar closed voor modification.

#### 2.1.2 Interface Segregation Principle
De interfaces zijn specifiek voor een DAO, er zijn dus geen methodes die niet gebruikt worden (wat bijvoorbeeld het geval zou zijn bij een generieke interface). Dit zorgt voor een duidelijke en specifieke interface die alleen de methodes bevat die nodig zijn voor de DAO's. Hierdoor voldoet deze implementatie aan het Interface Segregation principe.

#### 2.1.3 Dependency Inversion Principle
Doordat de Services en DAO's afhankelijk zijn van de interfaces, is er sprake van Dependency Inversion. De Services en DAO's zijn afhankelijk van abstracties en niet van concrete implementaties. Hierdoor is het mogelijk om de implementaties te vervangen zonder dat de Services of DAO's aangepast hoeven te worden.

Zie [OOAD Principles](https://han.onderwijsonline.nl/elearning/lesson/QN4k2bEq) en [OOAD Patterns](https://han.onderwijsonline.nl/elearning/lesson/RDa2pevq) (HAN, 2023-a).

### 2.2 Alternatieve oplossing
Een alternatieve oplossing hiervoor is het gebruik van interfaces voor de Services, op dezelfde manier als dat deze bij de DAO's gebruikt worden. Hierdoor zouden de services vervangbaar kunnen zijn zonder enige aanpassing in de resources nodig te hebben.

### 2.3 Keuze huidige oplossing
Er is gekozen om de Services geen interface te laten implementeren omdat de Services niet vervangen hoeven te worden. De Services zijn specifiek voor de applicatie en zullen dus niet vervangen worden. De DAO's zijn wel vervangbaar (om bijvoorbeeld over te stappen naar een andere database) en daarom is er gekozen om de DAO's wel een interface te laten implementeren.

--- 

## 3. Deployment diagram
![img.png](img.png)
*2. Deployment Diagram Spotitube*

In het deployment diagram is te zien hoe de Spotitube applicatie is opgebouwd. De applicatie bestaat uit drie verschillende lagen die met elkaar communiceren.

### 3.1 Jakarta EE
Er is gebruik gemaakt van Jakarta EE voor de applicatie. Jakarta gebruikt @GET en @POST requests om data op te halen en te versturen.

### 3.2 JDBC
Hiernaast is er gebruik gemaakt van JDBC om een connectie met de database te maken. Hierbij wordt er gebruik gemaakt van een "database.properties" file waarin een connection string aan wordt gemaakt. Door middel van deze connection string is het mogelijk van database te wisselen.  Er is gekozen om een SQL-database te gebruiken voor de Spotitube applicatie omdat ik hier de meeste ervaring mee heb. Alternatieven hiervoor zijn bijvoorbeeld MongoDB of MySQL.

Met het deployment diagram zijn een aantal requirements verwerkt van de Spotitube applicatie. Dit zijn de volgende:

- **W1.3** *"As a User, I want my browser to allow requests from the Spotitube Client to reach the JEE Application"*.
- **W2.7** *"As a Developer I want all data to be stored in a SQL database, so I no longer have to have hard-coded data."*
- **W3.14** *"As a Developer, I want to present a correct and complete Deployment Diagram, so I can give insight in how the Application should be deployed."*

(zie [Spotitube Stories](https://github.com/orgs/HANICA-DEA/projects/4/views/1)).

--- 

## 4. Implementatie keuzes

### 4.1 Datamapper Pattern
Er is gekozen om gebruik te maken van het Datamapper pattern voor het mappen van de data naar de DTO's en vice versa. Hierdoor zijn de DTO's en DAO's gescheiden van elkaar en is het mogelijk om de DAO's te vervangen zonder de DTO's aan te passen. Dit zorgt voor flexibiliteit en uitbreidbaarheid. Door dit pattern toe te passen wordt er voldaan aan het Open Closed principe omdat de DAO's open zijn voor extension maar closed voor modification.

Onderstaand zie je hoe dit pattern geïmplementeerd is in de Spotitube applicatie:
![img_5.png](img_5.png)
*3. Datamapper pattern*

Er zijn drie alternatieven voor het Datamapper pattern:
- Table Data Gateway
- Row Data Gateway
- Active Record

zie [Table Data Gateway](https://martinfowler.com/eaaCatalog/tableDataGateway.html), [Row Data Gateway](https://martinfowler.com/eaaCatalog/rowDataGateway.html) en [Active Record](https://martinfowler.com/eaaCatalog/activeRecord.html) (Fowler, 2002).

Naast het feit dat het Datamapper pattern ervoor zorgt dat de DAO's flexibel en uitbreidbaar zijn wordt dit pattern ook bij de opdrachten gebruikt en wordt het aangeleerd bij de course. Hierdoor is er gekozen om dit pattern te gebruiken in plaats van een van bovenstaande alternatieven.

### 4.2 Exception Mapper
Voor het afvangen van fouten en het geven van een correcte response aan de Spotitube client is er gebruik gemaakt van een Exception Mapper.

Door gebruik te maken van deze mapper zijn alle exceptions op een plek te vinden. Deze mapper voldoet echter niet aan het Open Closed principe omdat de mapper open is voor modification. Als er een nieuwe exception wordt toegevoegd moet de mapper aangepast worden. Hiernaast bestaat de mapper uit een lelijke if-else structuur. Voor Spotitube is dit echter geen probleem omdat er maar twee exceptions zijn, als dit uitgebreid wordt, zou dit wel een probleem kunnen worden.

Dit is niet de beste manier om exceptions af te vangen. Een betere manier zou zijn om gebruik te maken van de Interface Segregation Principle. Door gebruik te maken van specifieke interfaces voor de exceptions kan de if-else structuur voorkomen worden. Dit zorgt er echter wel voor dat er veel nieuwe classes aangemaakt moeten worden.

Dit is de if-else structuur van de Exception Mapper:
```java
    public Response toResponse(SpotitubeException e) {
        if (e instanceof LoginException){
            return Response.status(401).build();
        } else if (e instanceof TokenException){
            return Response.status(403).build();
        }
        return Response.status(500).build();
    };
```

### 4.3 ApplicationScoped en Dependency Injection
De TokenService klasse is ApplicationScoped. Dit houdt in dat er maar één instantie van deze service bestaat. De token wordt opgeslagen in de TokenService hashmap, dit moet altijd hetzelfde blijven. Als er meerdere instanties van de TokenService zouden bestaan zou dit problemen opleveren omdat een user dan meerdere tokens kan hebben. (zie [ApplicationScoped](https://docs.oracle.com/javaee/6/api/javax/enterprise/context/ApplicationScoped.html)).

ApplicationScoped wordt als volgt aangegeven:
```java
@ApplicationScoped
public class TokenService {
}
}
```

Hiernaast is er gebruik gemaakt van Dependency Injection. De classes worden geïnjecteerd in de volgende laag door middel van de @Inject annotatie. De classes kunnen dan gebruik maken van de geïnjecteerde klassen zonder dat ze zelf een instantie aan hoeven te maken. "Don't call us we'll call you".

Er is gebruik gemaakt van Setter injection, omdat constructor injection niet mogelijk is met Jakarta. (zie [Dependency Injection](https://www.baeldung.com/java-ee-cdi).


### 4.4 Tests
De datasource laag is niet getest. Dit is gedaan omdat het testen van de datasource laag onder de integratie tests zou vallen. Voor Spotitube is alleen het maken van unittests verplicht.

Hiernaast zijn er voor de volgende classes geen unittests gemaakt:
- Corsfilter
- Main
- DatabaseProperties
- ExceptionMapper

Voor Spotitube was het relevant om een test coverage van minimaal 80% te hebben. Dit is gelukt, de test coverage is 85%.
![img_1.png](img_1.png)

Voor het testen van de applicatie is er gebruik gemaakt van JUnit en Mockito, zoals aangeleerd is bij de course.

### 4.5 Beans.xml
Er is gebruik gemaakt van een beans.xml bestand om de classes te injecteren. Dit bestand is te vinden in de META-INF map binnen de resources directory. Dit bestand is nodig omdat er verschillende beans zijn voor verschillende situaties (bijvoorbeeld voor tests en voor de daadwerkelijke applicatie).
Zo heb ik een PlaylistHardcoded en een PlaylistDao class. De PlaylistDao class wordt gebruikt voor de applicatie en de PlaylistHardcoded class wordt gebruikt voor testdoeleinden. Zonder beans zou het niet mogelijk zijn om de juiste class te injecteren.

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://xmlns.jcp.org/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd"
        bean-discovery-mode="all">
<alternatives>
<class>nl.han.aim.oose.dea.datasource.TrackDao</class>
<class>nl.han.aim.oose.dea.datasource.PlaylistDao</class>
<class>nl.han.aim.oose.dea.datasource.UserDao</class>
</alternatives>
</beans>
```
(Zie [Beans.xml](https://docs.oracle.com/javaee/6/tutorial/doc/gjsdf.html)).

### 4.6 Cors Filter
Er is gebruik gemaakt van een Cors Filter om requests van de Spotitube client toe te staan. Dit is gedaan door middel van de @Provider annotatie. Hierdoor wordt het filter toegepast op alle requests die binnenkomen.
Voordat de GET request naar de backend wordt gedaan zal de browser een OPTIONS naar dit domein doen. Deze OPTIONS verwacht een response met *ACCESS-CONTROL-ALLOW-ORIGIN* in de header. Indien deze afwezig is, zal de browser een foutmelding loggen. Zonder CORS filter zou de Spotitube client geen requests kunnen doen naar de backend en zou de GET request dus niet gedaan worden.
![img_4.png](img_4.png)

Voor het gebruiken van CORS in WildFly zijn er verschillende opties:
- Het web.xml van WildFly aanpassen zodat de CORS-headers aan alle responses worden toegevoegd.
- Een dependency aan de betreffende pom.xml toevoegen, zodat automatisch een filter aan de applicatie wordt toegevoegd, die de CORS-headers toegevoegd aan alle responses van de applicatie.
- Zelf een Filter schrijven voor het toevoegen van de CORS-headers.

Er is gekozen voor de laatste optie, omdat het CORS filter al werd meegeleverd.  
Onderstaand is de implementatie van het CORS filter te vinden:
```java
@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext cres) {
        cres.getHeaders().add("Access-Control-Allow-Origin", "*");
        cres.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
        cres.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        cres.getHeaders().add("Access-Control-Max-Age", "1209600");
    }
}
```

(Zie [CORS Filter](https://han.onderwijsonline.nl/elearning/lesson/VqdQ5GwN)).

### 4.8 Security
Bij het implementeren van Spotitube is er geen rekening gehouden met security. Er is gebruik gemaakt van prepared statements om SQL-injecties te voorkomen. Hiernaast is er gebruik gemaakt van een CORS filter om requests van de Spotitube client toe te staan.
Om de Spotitube applicatie veiliger te maken zou er gebruik gemaakt kunnen worden van het hashen van wachtwoorden, op dit moment worden deze als plain text in de database opgeslagen. Er is gekozen om de tokens in een hashmap op te slaan, dit is niet veilig omdat de tokens niet geëncrypt zijn.

Bij het maken van een applicatie is security een belangrijk onderdeel. Op dit moment is de Spotitube applicatie niet veilig, een volgend team zou hier dus rekening mee moeten houden.

---

### 4.9 Offline Beschikbaar
Uiteindelijk bleek dat het mogelijk moest zijn om tracks offline beschikbaar te maken, ik kwam hier op de laatste dag van de deadline achter. Dit is niet geïmplementeerd in de Spotitube applicatie, maar kan toegevoegd worden door het volgende te doen:
- Een kolom toevoegen aan de database waarin staat of een track offline beschikbaar is, dit moet in de tussen tabel van Playlist en Tracks.
- Wanneer een track toe wordt gevoegd aan een playlist moet er bij de insert meegegeven worden of de track offline beschikbaar is, zie hiervoor de "TrackDao" klasse en hierbinnen de methode "addTrackToPlaylist".

## 5. Conclusie
Ik heb de Spotitube applicatie geïmplementeerd in Java. Hierbij heb ik gebruik gemaakt van Jakarta EE, JDBC, JUnit, Mockito en een SQL-database. De applicatie bestaat uit verschillende lagen die met elkaar communiceren, zoals de opdracht vereist. Hiernaast zijn alle user stories geïmplementeerd en is er een test coverage van 85%. De applicatie maakt gebruik van een aantal design patterns en principes, zoals het Datamapper pattern en het Open Closed principe.

---
## 6. Bronnen

- HAN Onderwijsonline (2023) *Spotitube*. OnderwijsOnline, geraadpleegd 4-3-2024 op <https://han.onderwijsonline.nl/elearning/lesson/VqdQ5GwN>
- HAN AIM (2023) *Spotitube*. GitHub, geraadpleegd 4-3-2024 op <https://github.com/HANICA-DEA/spotitube>
- HAN AIM (2023) *Spotitube*. Github, geraadpleegd 22-03-2024 op <https://github.com/orgs/HANICA-DEA/projects/4/views/1>
- HAN AIM (2023) *OOAD*. OnderwijsOnline, geraadpleegd 22-03-204 op <https://han.onderwijsonline.nl/elearning/content/RDamj6DE>
- Fowler, M. (2002) *Patterns of Enterprise Application Architecture*, geraadpleegd 22-03-2024 op <https://martinfowler.com/books/eaa.html>
- Oracle (2023) *ApplicationScoped*. Oracle, geraadpleegd 22-03-2024 op <https://docs.oracle.com/javaee/6/api/javax/enterprise/context/ApplicationScoped.html>
- Baeldung (2023) *Dependency Injection*. Baeldung, geraadpleegd 22-03-2024 op <https://www.baeldung.com/java-ee-cdi>
- Oracle (2023) *Beans.xml*. Oracle, geraadpleegd 22-03-2024 op <https://docs.oracle.com/javaee/6/tutorial/doc/gjsdf.html>
- HAN Onderwijsonline (2023) *CORS Filter*. OnderwijsOnline, geraadpleegd 22-03-2024 op <https://han.onderwijsonline.nl/elearning/lesson/VqdQ5GwN>
- HAN Onderwijsonline (2023) *Spotitube Stories*. OnderwijsOnline, geraadpleegd 22-03-2024 op <https://github.com/orgs/HANICA-DEA/projects/4/views/1>
