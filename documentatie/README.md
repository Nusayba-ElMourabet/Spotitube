# README

# Spotitube 23/24 S2

| Voorblad gegevens |  |
| --- | --- |
| Naam | Nusayba El Mourabet |
| Student nr | 211303 |
| Datum/periode | 24/05/2024 |
| Docent | Bart van der Wal |
| Klas | OOSE-ITN-BF |
| Vak | DEA |

## 1. Inleiding

Dit overdrachtsdocument beschrijft de technische en ontwerpkeuzes die zijn gemaakt tijdens de ontwikkeling van het Spotitube-project. Spotitube is een webapplicatie die gebruikers in staat stelt om in te loggen, playlists te beheren en tracks toe te voegen aan deze playlists. Het document omvat een gedetailleerde analyse van de gebruikte object-georiënteerde principes, een package diagram en een deployment diagram. Daarnaast worden de ontwerp-/craftsmanship-keuzes toegelicht, waarmee de kwaliteit en onderhoudbaarheid van de code worden aangetoond.

---

## 2. Package diagram
![Spotitube Package diagram ](README%20imgs%2FPackaging_diagram.png)

Spotitube Package diagram 

### **Toelichting**

**Requirement:**
Het pakketdiagram raakt meerdere requirements, waaronder het structureren van de applicatie in logische eenheden, het scheiden van verantwoordelijkheden en het faciliteren van onderhoud en uitbreidbaarheid.

### **2.1 Design Patterns en Ontwerpprincipes:**

1. **Single Responsibility Principle (SRP):**
Elk pakket heeft een specifieke verantwoordelijkheid. Bijvoorbeeld, het **`resources`**pakket bevat alle REST API-resources, terwijl het **`services`**pakket de businesslogica bevat.
2. **Open Closed Principle (OCP):**
Door gebruik te maken van interfaces en abstracties, zoals in het **`interfaces`**pakket, kunnen nieuwe functionaliteiten worden toegevoegd zonder bestaande code te wijzigen.
3. **Dependency Inversion Principle (DIP):**
De hogere modules, zoals **`services`**, zijn niet direct afhankelijk van lagere modules (**`datasource`**), maar van abstracties (**`interfaces`**). Dit bevordert de loskoppeling en testbaarheid.
4. **Factory Pattern:**
Het gebruik van een centrale plek (**`datasource`**pakket) voor het beheren van databaseconnecties kan worden gezien als een toepassing van het Factory Pattern.
5. **Singleton Pattern:**
De **`DatabaseProperties`**klasse zorgt ervoor dat er slechts één instantie van databaseconfiguraties is, wat consistentie en eenvoudig beheer bevordert.

### 2.2 Alternatieve oplossing

**Dependency Inversion Principle (DIP):**
We hadden ervoor kunnen kiezen om geen abstracties te gebruiken en in plaats daarvan directe afhankelijkheden te hebben tussen de hogere en lagere modules. Dit zou echter leiden tot sterk gekoppelde code die moeilijk te testen en te onderhouden is.

### 2.3 **Reden voor de Huidige Oplossing:**

De huidige oplossing is gekozen vanwege het Dependency Inversion Principle (DIP). Door interfaces en abstracties te gebruiken, zorgen we ervoor dat hogere modules niet direct afhankelijk zijn van de implementaties van lagere modules. Dit bevordert de loskoppeling tussen de verschillende lagen van de applicatie, waardoor de code beter testbaar en onderhoudbaar wordt.

Bovendien maakt deze aanpak het mogelijk om eenvoudig nieuwe functionaliteiten toe te voegen of bestaande onderdelen te vervangen zonder dat dit invloed heeft op de rest van de applicatie. Dit zorgt voor een flexibele en uitbreidbare architectuur die kan meegroeien met de behoeften van de applicatie. Bijvoorbeeld, als we een nieuwe manier willen implementeren om data op te slaan, kunnen we eenvoudig een nieuwe implementatie van de interface maken en deze injecteren zonder de bestaande services of andere modules aan te passen.

---

## 3. Deployment diagram
![Spotitube deployment diagram](README%20imgs%2FUntitled.png)

Spotitube deployment diagram

### **Toelichting:**

**Requirement:**
Het deployment diagram raakt meerdere user stories zoals:

- Als gebruiker wil ik kunnen inloggen zodat ik toegang krijg tot mijn gepersonaliseerde playlists.
- Als gebruiker wil ik playlists kunnen aanmaken en beheren zodat ik mijn muziek kan organiseren.
- Als gebruiker wil ik tracks aan playlists kunnen toevoegen zodat ik mijn favoriete muziek kan verzamelen.

### **3.1 OO Patterns of andere ontwerpprincipes:**

- **Layered Architecture**: De applicatie is opgedeeld in verschillende lagen (presentation, service, data access) wat de leesbaarheid en onderhoudbaarheid van de code bevordert.
    - **Voorbeeld**: In Spotitube hebben we lagen zoals **`LoginResource`**, **`LoginService`**, en **`LoginDAO`**. **`LoginResource`** handelt de HTTP-verzoeken af, **`LoginService`** bevat de logica, en **`LoginDAO`** behandelt de database interacties.
- **Dependency Injection (DI)**: Dit patroon is gebruikt om de afhankelijkheden tussen objecten te beheren en te injecteren, wat zorgt voor losse koppeling en testbaarheid.
    - **Voorbeeld**: De **`LoginService`** krijgt de **`ILoginDAO`** geïnjecteerd, waardoor we verschillende implementaties van **`ILoginDAO`** kunnen gebruiken zonder de **`LoginService`** te hoeven wijzigen.
- **Singleton Pattern**: Gebruikt voor het beheren van database verbindingen, zodat er slechts één instantie van de databaseconnectie bestaat gedurende de levensduur van de applicatie.
    - **Voorbeeld**: De databaseverbinding in **`DatabaseProperties`** is geïmplementeerd als een singleton om te zorgen dat er slechts één verbinding is voor de hele applicatie.

### **3.2 Alternatieve oplossing:**

Een alternatieve oplossing zou een microservices architectuur kunnen zijn, waarbij elke service zijn eigen database en server heeft. Dit kan schaalbaarheid en fouttolerantie verder verbeteren, maar voegt ook complexiteit toe in termen van beheer en communicatie tussen services.

- **Voorbeeld**: In plaats van een enkele Wildfly server voor alle services, zouden we aparte services kunnen hebben voor **`LoginService`**, **`PlaylistService`**, en **`TrackService`**, elk met hun eigen databases.

### **3.3 Reden voor de huidige oplossing:**

De keuze voor de huidige oplossing is gebaseerd op het Layered Architecture patroon. Dit patroon zorgt voor een duidelijke scheiding van verantwoordelijkheden en maakt de codebase gemakkelijker te onderhouden en uit te breiden. Door lagen te gebruiken kunnen we wijzigingen in één laag doorvoeren zonder de andere lagen te beïnvloeden, wat de flexibiliteit en uitbreidbaarheid van de applicatie verhoogt.

- **Voorbeeld**: Door het gebruik van lagen zoals **`LoginResource`**, **`LoginService`**, en **`LoginDAO`** kunnen we eenvoudig nieuwe functionaliteiten toevoegen of bestaande onderdelen vervangen zonder dat dit invloed heeft op de rest van de applicatie. Dit zorgt voor een flexibele en uitbreidbare architectuur die kan meegroeien met de behoeften van de applicatie.

---

## 4. **Ontwerp-/Craftsmanship-keuzes**

### **Implementatiekeuzes en Clean Code Principes**

**1. Gebruik van Interfaces voor Losse Koppeling:**

- **Beschrijving:** Door interfaces te gebruiken, wordt de afhankelijkheid van concrete implementaties verminderd, wat de flexibiliteit en testbaarheid verhoogt.
- **Voorbeeld:** **`ILoginDAO`** interface geïmplementeerd door **`LoginDAO`**.
- **Illustratie:**

**2. Dependency Injection (DI):**

- **Beschrijving:** DI maakt het mogelijk om afhankelijkheden van buitenaf in te voeren, wat zorgt voor een losse koppeling en betere testbaarheid.
- **Voorbeeld:** Injectie van **`ILoginDAO`** in **`LoginService`** via de constructor.
- **Illustratie:**

**3. Single Responsibility Principle (SRP):**

- **Beschrijving:** Elke klasse heeft slechts één verantwoordelijkheid, wat de leesbaarheid en onderhoudbaarheid van de code verbetert.
- **Voorbeeld:** **`LoginService`** handelt alleen inloggerelateerde logica af, terwijl **`LoginDAO`** zich richt op database-interacties.
- **Illustratie:**

**4. Consistentie en Leesbaarheid:**

- **Beschrijving:** Code wordt geschreven volgens consistente stijlrichtlijnen om de leesbaarheid te vergroten.
- **Voorbeeld:** Het gebruik van betekenisvolle namen voor variabelen en methoden, zoals **`authenticateUser`** in plaats van **`authUser`**.
- **Illustratie:** N/A

**5. KISS (Keep It Simple, Stupid):**

- **Beschrijving:** Houd de code eenvoudig en vermijd onnodige complexiteit.
- **Voorbeeld:** Eenvoudige if-else structuren in plaats van complexe nested condities.
- **Illustratie:** N/A

**6. Unit Testing:**

- **Beschrijving:** Schrijven van eenheden van code om de functionaliteit van de afzonderlijke onderdelen van de applicatie te verifiëren.
- **Voorbeeld:** Unit tests voor **`LoginService`** met mock objecten voor **`ILoginDAO`**.
- **Illustratie:**

**7. Hoge Code Coverage:**

- **Beschrijving:** De code coverage van het project zit boven de 80%, zoals gevraagd bij domain laag, met uitzondering van de datasource laag, omdat deze onder unit testing valt.
- **Voorbeeld:** Unit tests voor services en resources resulteren in een hoge dekkingsgraad.
- **Illustratie:**
- 
![code coverage illustratie](README%20imgs%2FUntitled%201.png)


code coverage illustratie

![claase diagram toelichting voor clean code](README%20imgs%2FUntitled%202.png)


claase diagram toelichting voor clean code

Deze ontwerp- en craftsmanship-keuzes demonstreren hoe het Spotitube-project is ontwikkeld met oog voor Clean Code-principes, zoals losse koppeling, een enkele verantwoordelijkheid per klasse, en consistentie in code-stijl. De implementatie van deze principes zorgt voor een onderhoudbare, flexibele en goed testbare applicatie.

## 5. Conclusie

In dit document hebben we de belangrijkste ontwerp- en implementatiekeuzes van het Spotitube-project besproken. De toepassing van object-georiënteerde principes zoals het Open-Closed Principle, Dependency Injection en het Single Responsibility Principle hebben bijgedragen aan een flexibele en uitbreidbare architectuur. De hoge code coverage toont de betrouwbaarheid van de code aan, terwijl de deployment architectuur zorgt voor een efficiënte en robuuste runtimeomgeving. Door deze keuzes voldoet Spotitube aan de gestelde eisen en biedt het een solide basis voor toekomstige uitbreidingen en onderhoud.

---

## 6. Bronnen

- HAN AIM (2023) *Spotitube*. GitHub, geraadpleegd 18-5-2024 op [https://github.com/HANICA-DEA/spotitube](https://github.com/HANICA-DEA/spotitube)
- HAN Onderwijsonline (2023) *Spotitube*. OnderwijsOnline, geraadpleegd 18-5-2024 op [https://han.onderwijsonline.nl/elearning/lesson/VqdQ5GwN](https://han.onderwijsonline.nl/elearning/lesson/VqdQ5GwN)
- HAN AIM (2023) *Spotitube*. Github, geraadpleegd 18-05-2024 op [https://github.com/orgs/HANICA-DEA/projects/4/views/1](https://github.com/orgs/HANICA-DEA/projects/4/views/1)
- Fowler, M. (2002) *Patterns of Enterprise Application Architecture*, geraadpleegd 22-05-2024 op [https://martinfowler.com/books/eaa.html](https://martinfowler.com/books/eaa.html)
- HAN Onderwijsonline (2023) *CORS Filter*. OnderwijsOnline, geraadpleegd 15-04-2024 op [https://han.onderwijsonline.nl/elearning/lesson/VqdQ5GwN](https://han.onderwijsonline.nl/elearning/lesson/VqdQ5GwN)