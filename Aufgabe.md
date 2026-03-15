# Aufgabe 1: Bibliothekssystem

Eine Stadtbibliothek möchte ihr Ausleihsystem digitalisieren.

In der Bibliothek gibt es Medien, die ausgeliehen werden können. Zu den Medien gehören Bücher, DVDs und Zeitschriften. Jedes Medium besitzt eine eindeutige Mediennummer, einen Titel und ein Erscheinungsjahr.

Ein Buch hat zusätzlich einen Autor und eine ISBN.
Eine DVD besitzt eine Laufzeit sowie ein Genre.
Eine Zeitschrift besitzt eine Ausgabenummer.

Personen können sich als Mitglieder in der Bibliothek registrieren. Ein Mitglied hat eine Mitgliedsnummer, einen Namen und eine Adresse.

Mitglieder können mehrere Medien ausleihen. Jede Ausleihe speichert:

- das ausgeliehene Medium
- das Mitglied
- das Ausleihdatum
- das Rückgabedatum

Ein Medium kann gleichzeitig nur einmal ausgeliehen werden.

---

# Aufgabe 2: Online-Shop

Ein Unternehmen entwickelt einen einfachen Online-Shop.

Der Shop verwaltet Produkte. Jedes Produkt besitzt eine Produktnummer, einen Namen und einen Preis.

Es gibt zwei spezielle Produktarten:

- Physische Produkte, die ein Gewicht besitzen
- Digitale Produkte, die eine Dateigröße und einen Downloadlink besitzen
  
Kunden können sich im Shop registrieren. Ein Kunde hat eine Kundennummer, einen Namen und eine E-Mail-Adresse.
Ein Kunde kann mehrere Bestellungen aufgeben. Jede Bestellung hat eine Bestellnummer und ein Bestelldatum.

Eine Bestellung besteht aus mehreren Bestellpositionen.
Jede Bestellposition enthält:

- ein Produkt
- eine Menge
- den Einzelpreis zum Zeitpunkt der Bestellung

---

# Aufgabe 3: Carsharing-System

Ein Unternehmen betreibt ein digitales Carsharing-System in mehreren Städten.

Das Unternehmen besitzt verschiedene Fahrzeuge.
Jedes Fahrzeug hat eine Fahrzeug-ID, eine Marke, ein Modell und einen aktuellen Kilometerstand.

Es gibt zwei Arten von Fahrzeugen:

- Elektroautos, die zusätzlich eine Batteriekapazität und einen aktuellen Ladezustand besitzen
- Verbrennerfahrzeuge, die zusätzlich einen Tankinhalt und einen Kraftstofftyp besitzen

Die Fahrzeuge befinden sich an verschiedenen Stationen.
Eine Station hat eine Stationsnummer, einen Namen und eine Adresse.

Eine Station kann mehrere Fahrzeuge enthalten.
Ein Fahrzeug befindet sich immer an genau einer Station, wenn es nicht ausgeliehen ist.

## Nutzer des Systems

Personen können sich im System registrieren.
Jede Person besitzt:

- eine Personen-ID
- einen Namen
- eine E-Mail-Adresse

Es gibt zwei Arten von Nutzern:

### Normale Kunden

- besitzen zusätzlich eine Führerscheinnummer

### Firmenkunden

- besitzen zusätzlich einen Firmennamen
- eine Steuernummer
 
## Buchungen

Ein Kunde kann ein Fahrzeug reservieren und ausleihen.

Eine Buchung speichert:

- den Startzeitpunkt
- den Endzeitpunkt
- den Start-Kilometerstand
- den End-Kilometerstand

Eine Buchung gehört immer zu:

- genau einem Kunden
- genau einem Fahrzeug

Ein Fahrzeug kann im Laufe der Zeit viele Buchungen haben.

##Bewertungen

Nach einer Fahrt kann ein Kunde eine Bewertung für das Fahrzeug abgeben.

Eine Bewertung enthält:

- eine Sternebewertung (1–5)
- einen Kommentar
- ein Datum

Ein Kunde kann mehrere Bewertungen schreiben.
Ein Fahrzeug kann mehrere Bewertungen erhalten.

Eine Bewertung gehört immer zu:

- genau einem Kunden
- genau einem Fahrzeug

---

# Aufgabe

Erstellen Sie ein UML-Klassendiagramm für die jeweiligen Systeme.
Berücksichtigen Sie dabei:

- Klassen
- Attribute
- Vererbungsbeziehungen
- Assoziationen
- Kardinalitäten
- geeignete Assoziationsklassen, sofern diese sinnvoll sind
