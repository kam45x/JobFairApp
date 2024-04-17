## Dokumentacja wstępna

### Cel aplikacji
Aplikacja ma służyć do łatwiejszego poruszania się po targach pracy – wydarzeniu, w którym studenci i pracodawcy mogą spotkać się, porozmawiać oraz nawiązać współpracę. Firmy biorące udział w targach wystawiają swoje stanowiska i dążą do zachęcenia studentów do aplikowania na proponowane przez nie stanowiska pracy. Studenci mogą zdobyć informacje o potencjalnym pracodawcy, skonsultować swoje CV, a także wziąć udział w organizowanych podczas wydarzenia warsztatach. 

Do stworzenia aplikacji zainspirowało nas uczestnictwo w Inżynierskich Targach Pracy organizowanych w Gmachu Głównym Politechniki Warszawskiej. Zauważyliśmy kilka trudności związanych z poruszaniem się po wydarzeniu. Przede wszystkim dotyczyły one przejrzystości oficjalnej ulotki, szczególnie pojawiły się problemy z uzyskaniem informacji o zainteresowaniu firm studentami konkretnych kierunków. Znalezienie danego stanowiska firmy także zajmowało chwilę. Pojawił się w związku z tym pomysł stworzenia aplikacji, która usprawniłaby te procesy, a także pomogła w zebraniu informacji o uczestniczących firmach w jednym miejscu. Pojawiły się także projekty kolejnych funkcjonalności aplikacji. Pomysł spotkał się ze wstępnym zainteresowaniem studentów. 

### Opis aplikacji
Podstawową funkcjonalnością aplikacji jest pomoc w zorientowaniu się w dostępnych stanowiskach na wydarzeniu, poznanie zainteresowania firmy oraz jej lokalizacji w miejscu wydarzenia. Dlatego też jako główny widok aplikacji planowana jest uproszczona mapa, przedstawiająca rozkład stoisk firm. Po nakierowaniu kursora na symbol stanowiska powinny pojawić się wstępne informacje o firmie, kierunki studiów, których absolwenci bądź studenci są mile widziani w firmie. Planowane jest także stosowanie filtrów (kierunek studiów, oferowane przez firmę praktyki/staże), po których zastosowaniu na mapce powinny podświetlić się interesujące studenta firmy. 

Aplikacja może służyć studentom nie tylko podczas przebywania na wydarzeniu, ale także przed nim lub po nim. Powinna umożliwić także uzyskanie bardziej szczegółowych informacji o firmie. Planowane jest też wprowadzenie opcji przeglądania planu warsztatów, a także znalezienia najbardziej dopasowanych do oczekiwań studenta firm po wypełnieniu prostej ankiety. Wiąże się z tym wprowadzenie opcji logowania. Dzięki temu student może zapisać proponowane firmy na swoim koncie. Pomoże to w późniejszym wysyłaniu aplikacji o praktyki/staż/pracę. 

Administrator strony powinien mieć możliwość dodawania firm do wydarzenia, musi zatem istnieć baza danych przechowująca informacje o firmie. Planowane jest także wprowadzenie bazy użytkowników aplikacji. 

Pojawiły się pomysły rozszerzenia aplikacji o dodatkowe funkcjonalności, takie jak wysyłanie CV do firm bezpośrednio przez aplikację, bardziej szczegółowe algorytmy dopasowania ofert pracy do studenta. 

### Środowisko
Aplikacja zostanie napisana w języku Java. Używanym przez nas frameworkiem będzie framework Spring. Do komunikacji z bazą danych będzie używany język SQL. Do stworzenia części frontendowej posłużą nam technologie HTML, CSS i Thymeleaf. 

### Wstępny podział zadań
* Katarzyna Wójtowicz – frontend 
* Piotr Sienkiewicz – backend 
* Kamil Cisek – backend 