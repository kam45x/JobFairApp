# Aplikacja Targów Pracy Politechniki Warszawskiej

## Wstęp

Między pierwszym a drugim etapem projektu wprowadzono sporo modyfikacji w kodzie, a aplikacja zyskała funkcjonalności,
które umożliwiają już jej praktyczne użycie. Aplikacja przede wszystkim miała ułatwić wyszukiwanie firmy, która jest
zainteresowana studentami po danym kierunku. Na targach pracy jest zwykle kilkadziesiąt firm, każda szuka innych 
kierunków, czasami wszystkie poszukiwane przez daną firmę są z jednej branży, a czasami z różnych. Mało czytelna 
broszurka targów pracy nie ułatwia zadania ustalenia, która firma może być zainteresowana danym kandydatem.
W tej wersji aplikacji udało się to rozwiązać ten problem dzięki mechanizmowi filtrów. Na stronie głównej aplikacji
znajduje się przycisk „Wybierz filtry”. Po jego naciśnięciu rozwija się lista filtrów, które są dwuetapowe. Najpierw 
wybiera się kierunek, a następnie technologie, z których korzysta firma lub umiejętności, których wymaga. Podanie 
w filtrach technologii jest opcjonalne, jednak może ułatwić znalezienie firmy idealnie pasującej do preferencji 
kandydata. Po kliknięciu „Zastosuj filtry” na dole strony głównej wyświetla się lista firm spełniających wymogi
zaznaczone w filtrach. Dodatkowo powyżej na stronie głównej znajduje się symboliczne rozmieszczenie stanowisk na
targach. Po zaznaczeniu odpowiednich filtrów, stanowiska zmieniają kolor – odcienie zielonego pokazują, jak dane
stanowisko odpowiada preferencjom kandydata. Dodatkową zaletą filtrów jest to,  że dostępne filtry są dodawane
dynamicznie na podstawie tego, co zostało wprowadzone przez firmy. Dzięki temu lista dostępnych filtrów nie jest
zaśmiecona przez kierunki i technologie, których nie zgłosiła żadna firma, co dodatkowo ułatwia proces odnalezienia
wymarzonego miejsca pracy.

Wprowadzono także mechanizmy autoryzacji użytkowników korzystające z frameworka Spring Security. Rozróżnia się
dwie role użytkowników: „admin” oraz „user”. Konto „user” daje możliwość dodawania firmy do ulubionych. Ciężko jest
w tego typu prostej aplikacji nadać zalogowanemu użytkownikowi więcej szczególnych możliwości. Po zalogowaniu, gdy
wejdzie się na stronę firmy (dostęp do niej jest z widoku listy firm, które dostępne są po wybraniu filtrów lub
zaznaczeniu opcji „Pokaż wszystkie”). W widoku strony firmy znajduje się pełen opis firmy, a po prawej stronie krótki
opis, kogo ona poszukuje. Po prawej stronie znajduje się przycisk „Więcej” odsyłający do zewnętrznej strony pracodawcy.
W tym miejscu znajduje się też ikonka serduszka służąca do dodawania firmy do ulubionych. Ikonka ta widoczna jest tylko
dla zalogowanych użytkowników typu „user”. Na stronie głównej po zalogowaniu widoczny jest przycisk „Ulubione”, po
kliknięciu na który pokazuje się okienko z listą ulubionych firm. Z poziomu tego okienka można usuwać firmy z ulubionych
bądź też bezpośrednio przejść do strony pracodawcy.

Natomiast zalogowanie na konto z rolą „admin” (logowanie poprzez zwykły formularz logowania, login „admin”, hasło
„wranrostero”) daje dostęp do panelu administratora, który ma już więcej możliwości, takich jak dodawanie i edycja firm.

We wszystkich formularzach zaimplementowano pełną walidację treści. Formularze do dodawania firmy oraz do edycji firmy
korzystają z tego samego skryptu HTML, którego działanie się różni delikatnie ze względu na to, w jakim trybie się
go wywoła. Dodając nową firmę, numery stanowisk dodaje się za pomocą rozwijanej listy z numerami tych, które są wolne.
Nazwy poszukiwanych kierunków są wybierane z listy z odgórnie wprowadzonymi wartościami. Zastosowano bardzo estetyczne
rozwiązanie polegające na tym, że każdy wybrany kierunek jest dodawany w postaci bloczku, który można usunąć za pomocą
symbolu X. Dodatkowo następuje walidacja, aby wśród firm dostępnych w bazie nie było dwóch o takich samych nazwach lub
adresach strony internetowej. Ten filtr jest tak zrobiony, aby w przypadku edycji istniejącej firmy dało się wprowadzić
jej starą nazwę lub adres strony internetowej.

W formularzu do wprowadzania warsztatów datę wybiera się z kalendarza, a godzinę z dedykowanego obiektu do
ustawiania czasu. Nazwę sali wprowadza się ręcznie, gdyż sale mogą mieć nazwy literowe, słowne lub numeryczne. W
formularzu następuje walidacja, czy  dana sala jest wolna o danej godzinie, a jeżeli nie, to wyświetla się komunikat,
w jakich godzinach jest zajęta.

W przypadku formularza dodawania użytkownika dostępnego po kliknięciu przycisku „Zarejestruj się” na stronie głównej,
odbywa się sprawdzanie, czy dany e-mail jest już w bazie danych. Hasło musi być wprowadzone dwukrotnie, a w pola do
jego wprowadzania posiadają przycisk z symbolem oka, który zmienia widoczność hasła. Hasło jest sprawdzane pod względem
spełnienia następujących wymagań: hasło i powtórzone hasło muszą być takie same, hasło nie może być krótsze niż
8 znaków, hasło musi zawierać małą literę, wielką literę, cyfrę i znak specjalny.

Wszystkie formularze zapamiętują wartości wprowadzone w pola, dzięki czemu w przypadku odświeżenia strony lub błędu
walidacji danych wprowadzone zmiany nie przepadają. Jest wykonane z użyciem obiektu `model` udostępnianego przez
Springa. W nim podczas wykonywania żądania POST są przechowywane odpowiednie wartości. Dane wejściowe są odpowiednio
sprawdzane i w przypadku błędu formularz jest wywoływany z powrotem, tym wpisując dane z modelu w poszczególne pola.
Na podobnej zasadzie są przekazywane komunikaty o błędach.

## Uruchomienie aplikacji
Proces uruchomienia aplikacji został opisanny w pliku [README.md](../README.md)

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
1. Stworzenie frontendu strony rejestracji.
2. Stworzenie frontendu strony logowania.
3. Stworzenie frontendu filtrów.
4. Funkcje w JavaScript do dynamicznego wyświetlania pola filtrów.
5. Stylizacja strony - przycisków, kontenerów, zdjęć.
6. Poprawienie frontendu po dodaniu funkcji dodawania do ulubionych.

### Piotr Sienkiewicz
1. Dodanie możliwości logowania użytkowników. Do tego celu użyto frameworka Spring Security. Zadanie to wymagało
poznania zasad działania frameworka oraz jego API. Trudnością było to, że API zmieniło się trochę wraz z którąś z
ostatnich wersji, przez co dużo tutoriali w internecie było nieaktualnych. Dodanie tej funkcji wymagało napisania
klasy Users (liczba mnoga w nazwie, aby uniknąć konfliktu nazw ze słowem kluczowym w bazach danych), konfiguracji bazy
(plik UsersRepository.java), a także szeregu klas pozwalających na uwierzytelnianie użytkowników, pobieranie obiektu
zalogowanego użytkownika z bazy danych itd. (klasy CustomUserDetailsService, AuthenticationService, UserService itd.).
Hasła zapisywane w bazie są poddawane szyfrowaniu. Rozpoznawanie roli użytkownika typu „admin” oraz „user” odbywa się
za pomocą pola „roles” klasy Users.

2. Możliwość logowania administratora. Uznano, że pracodawcy będą pisać maile ze zgłoszeniami na targi pracy, po czym
administrator będzie rozpatrywał te wnioski i ręcznie dodawał firmy. Stąd też jest tylko jedno konto administratora.
Początkowo planowano ustawić jego parametry w klasie SecurityConfig, ale sprawiało to sporo problemów, dlatego
ostatecznie zastosowano podejście, że konto administratora będzie dodawane do bazy danych i w razie potrzeby
inicjalizowane przez klasę DbInit podczas uruchamiania aplikacji.

3. Stworzenie strony administratora. Napisano samodzielnie stronę administratora w odpowiednim pliku HTML. Strona
zawiera przyciski odsyłające do stron dodawania firm lub warsztatów, a także kontener z listą obecnie
zarejestrowanych firm wraz z przyciskami do ich edycji bądź usuwania.

4. Możliwość edytowania firm. Dokonano refaktoringu kodu pozwalającego użyć tego samego formularza HTML do
dodawania firmy oraz do jej edycji. Główna różnica polega na delikatnie innych metodach walidacji wprowadzonych
parametrów.

5. Poprawienie formularzy do dodawania firm i warsztatów. Formularze te były już napisane przez Katarzynę,
jednak dodano do nich kilka ulepszeń. W formularzu do dodawania firmy dodano interfejs do wybierania kierunków
z listy i przedstawianiu tych wybranych jako bloczki, które można usuwać. W przypadku formularza do dodawania
warsztatów zaimplementowano wybieranie daty z kalendarza, a godziny z dedykowanego do tego celu okienka. Do obu
tych formularzy dodano metody obsługujące walidację danych oraz to, aby wypełnione pola nie znikały
po odświeżeniu strony.

6. Poprawienie formularzy do logowania i rejestracji użytkownika. Formularze były napisane przez Katarzynę.
Połączono je z backendem, dodano obsługę walidacji, zachowywanie treści pól po odświeżeniu strony oraz dodano ikonkę
oka pozwalającą zobaczyć wpisywane hasło.