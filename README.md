# deckOfCardsUppsala
---

# README – De 40 Rövarna

## Projektbeskrivning
Det här projektet är en implementation av patiensen **De 40 Rövarna** i Java med Swing och AWT. Spelet använder två kortlekar (104 kort) och följer standardreglerna för "De 40 Rövarna".

## Funktioner
- Två kompletta kortlekar används.
- 8 foundationhögar där kort byggs uppåt i samma svit (ess → kung).
- 10 tableauhögar där kort byggs nedåt i alternerande färg.
- Kort vänds från Stock till Waste vid klick.
- Klick för att välja och flytta kort.
- Markerar vald hög med en blå rektangel.

## Spelregler
- I foundationhögar får endast ess placeras först, sedan måste svit och stigande ordning följas.
- I tableauhögar kan kort placeras om de har exakt en lägre rang och alternerande färg jämfört med kortet under.
- Om Stock är tom kan kort återföras från Waste till Stock.

## Design och struktur
- Klassen `Pile` är bas för alla olika högar.
- `FoundationPile`, `TableauPile`, `StockPile`, `ThrowPile` är specialiserade högar.
- `Card` representerar ett spelkort med bild, svit och rang.
- `SimpleGame` hanterar GUI och speluppställning.

## Tekniker
- Java 22
- Swing och AWT för GUI
- Objektorienterad design med arv och polymorfi

## Övrigt
- Programmet använder en klick-baserad interaktion (klicka för att välja kort, klicka för att flytta).
- Blip och Blup-knapparna är från testkoden och påverkar inte spelet.

---
