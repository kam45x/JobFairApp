## Połączenie projektu z bazą danych PostgreSQL
### Windows
1. Pobierz bazę danych [PostgreSQL](https://www.postgresql.org/download/windows/).
2. Zainstaluj PostgreSQL.
3. Otwórz Windows Terminal i wpisz:
```
cd "C:\Program Files\PostgreSQL\{number}\bin"
psql -h localhost -U postgres
```
4. Wpisz hasło do bazy.
5. Stwórz bazę danych 'jobdb':
```SQL
CREATE DATABASE jobdb;
```
Zweryfikuj poprawność stworzenia bazy danych:
```
\l
```
6. Teraz możesz uruchomić projekt.

### Ważna informacja
Jeżeli podczas testów powstało Tobie kilku użytkowników o tych samych e-mailach, usuń wszystkie, pozostawiając co
najwyżej jednego. W przeciwnym razie logowanie może nie działać.

### Przydatne komendy do bazy danych
Uruchamianie interfejsu bazy danych na systemie Ubuntu
```
psql -U postgres -d jobdb -h localhost -W
```

Wyświetl dostępne tabele
```
\dt
```

Pokaż użytkowników
```
SELECT * FROM users;
```

Usuń użytkowników od `id` wynoszącym 2 oraz 5
```
DELETE FROM users
WHERE id in (2,5);
```
