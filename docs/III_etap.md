## Prace podczas III etapu

### Kamil Cisek
1. Zmiana bazy danych H2 na bazę danych PostgreSQL. Jest to duże usprawnienie, ponieważ baza H2 jest 
bazą pamięciową i zapomina zapisane dane wraz z wyłączeniem aplikacji. Baza PostgreSQL umożliwia trwałe przechowywanie danych.
Do połączenia aplikacji z bazą danych są wykorzystywane możliwości Spring Boota i JDBC.
2. Dodanie możliwości filtrowania firm po kierunkach i technologiach. Firma wyświetla się jeśli przynajmniej jeden z jej
obszarów działania spełnia ten zaznaczony w filtrach. Dodano także przycisk, który umożliwia wyświetlanie wszystkich firm.
3. Dodanie funkcjonalności kolorowania stanowisk zależnie od wybranych filtrów. Kolor stanowiska firmy jest zależny od tego,
jak wiele technologii zaznaczonych w filtrach pasuje do technologii mile widzianych przez firmę. Im więcej tym ciemniejsz odcień zielonego.
4. Stworzenie formularza dodawania prelekcji. Stworzony podobnie jak formularz dodawania firm.
5. Naprawienie wyświetlania podpisów stoisk. Wcześniej po dodaniu firmy z formularza podpis stoiska nie wyświetlał się.

### Katarzyna Wójtowicz
* Stworzenie frontendu strony rejestracji,
* stworzenie frontendu strony logowania,
* stworzenie frontendu filtrów,
* funkcje w JavaScript do dynamicznego wyświetlania pola filtrów,
* stylizacja strony - przycisków, kontenerów, zdjęć.

### Piotr Sienkiewicz
* Dodanie możliwości logowania użytkownika,
* funkcjonalności użytkownika - dodawanie firm do ulubionych,
* stworzenie strony administratora,
* możliwość logowania administratora,
* możliwość edytowania firm i warsztatów,
* poprawienie formularzy do dodawania firm i warsztatów,
* poprawienie kolorowania stanowisk.