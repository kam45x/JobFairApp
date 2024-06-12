# Projekt z przedmiotu programowania aplikacyjne

## Informacje

### Semestr
2024L

### Zespół
Z06

### Członkowie Zespołu
* Katarzyna Wójtowicz 324899
* Kamil Cisek 324857
* Piotr Sienkiewicz 324887

## Pierwszy etap projektu - dokumentacja wstępna
[Dokumentacja I etapu](docs/I_etap.md)

## Drugi etap projektu - działający prototyp
[Dokumentacja II etapu](docs/II_etap.md)

## Trzeci etap projektu - działająca aplikacja
[Dokumentacja III etapu](docs/III_etap.md)

## Czwarty etap projektu - poprawki
[Dokumentacja IV etapu](docs/IV_etap.md)

## Dokumentacja - inne
[Tworzenia bazy danych PostgreSQL](docs/BazaDanych.md)

## Proces uruchomienia aplikacji
Na repozytorium znajdują się tylko pliki źródłowe aplikacji, a nie pliki `.jar`, stąd też aplikacja przed uruchomieniem
wymaga zbudowania. Najprościej wykonać to z poziomu środowiska programistycznego IDE. Jednak nie jest ono konieczne,
albowiem wszystkie wymagane operacje można wykonać z poziomu terminala. W przypadku systemu operacyjnego Ubuntu proces
wygląda następująco:

### Budowanie aplikacji
Aplikacja korzysta z narzędzia do zarządzania pakietami Maven, stąd też wymagane jest jego wcześniejsze zainstalowanie 
na używanym komputerze. Poniższa komenda przeprowadza proces kompilacji:
```
mvn clean install
```

### Wykonanie programu
Poprzednia komenda tworzy katalog `target`, a w nim plik wykonywalny w formacie `.jar`. Należy go wykonać za pomocą 
zainstalowanego na komputerze interpretera Javy:
```
java -jar target/JobFairApp-0.0.1-SNAPSHOT.jar
```

### Wyłączenie aplikacji
Aplikację można wyłączyć na przykład używając skrótu `Ctrl+C`, bądź szukając nazwy aplikacji wśród uruchomionych
procesów i wywołując na nim komendę `kill`