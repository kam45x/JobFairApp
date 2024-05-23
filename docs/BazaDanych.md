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