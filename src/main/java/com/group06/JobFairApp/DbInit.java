package com.group06.JobFairApp;

import com.group06.JobFairApp.model.Booth;
import com.group06.JobFairApp.model.Company;
import com.group06.JobFairApp.repository.BoothRepository;
import com.group06.JobFairApp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DbInit implements CommandLineRunner {

    private final int numberOfBooths = 15;
    private final CompanyRepository companyRepository;
    private final BoothRepository boothRepository;

    @Autowired
    public DbInit(CompanyRepository companyRepository, BoothRepository boothRepository) {
        this.companyRepository = companyRepository;
        this.boothRepository = boothRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Init companies
        List<Company> companies = List.of(
                new Company("Przemysłowy Instytut Automatyki i Pomiarów", "https://creative.piap.pl/img/logo/lukasiewicz/lukasiewicz_piap_green_black.png", "https://piap.lukasiewicz.gov.pl/wp-content/uploads/2023/08/salon-robotow-mobilnych-w-korei.jpg", "Robotyka", 1, "https://piap.lukasiewicz.gov.pl/", "Sieć Badawcza Łukasiewicz – Przemysłowy Instytut Automatyki i Pomiarów PIAP od ponad 50 lat oferuje polskim podmiotom i partnerom zagranicznym rozwiązania wykorzystujące najnowsze technologie z dziedziny robotyki, automatyki, technik pomiarowych i druku 3D, a także technologii kosmicznych. Instytut powstał w 1965 roku, a od kwietnia 2019 roku jest częścią Sieci Badawczej Łukasiewicz.", "Debili po liceum", "Sieć Badawcza Łukasiewicz –  Przemysłowy Instytut Automatyki i Pomiarów PIAP został utworzony w 1965 roku jako instytut przemysłowy, którego podstawowym zadaniem było opracowywanie i wdrażanie w różnych gałęziach przemysłu nowych technologii, systemów automatyki, urządzeń produkcyjnych i specjalistycznej aparatury kontrolno-pomiarowej. Ponad pięćdziesiąt lat naszej bliskiej współpracy z przemysłem zaowocowało szeregiem nowych opracowań i znaczących wdrożeń. Posiada bogate doświadczenie w pozyskiwaniu finansowania dla projektów B+R. Dzięki ciągłości prac badawczych i rozwojowych osiągnęliśmy bardzo wysoki poziom naszych opracowań. Korzyściami ze współpracy z nami  w obszarze badań i rozwoju jest: dostęp do specjalistycznych zespołów badawczych, infrastruktury laboratoryjnej i sieci współpracujących instytucji naukowych, wszechstronne doradztwo w procesie opracowywania ścieżki badań i wdrożenia oraz konceptualizacji projektu jak również wsparcie w procesie pozyskiwania, kontraktowania i rozliczania środków publicznych."),
                new Company("Samsung", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Samsung_Logo.svg/1280px-Samsung_Logo.svg.png", "https://ocs-pl.oktawave.com/v1/AUTH_2887234e-384a-4873-8bc5-405211db13a2/spidersweb/2023/05/samsung-blokuje-sztuczna-inteligencje-pracownikom.jpg", "Informatyka", 2, "https://www.samsung.com/pl/", "Jedna z największych południowokoreańskich grup biznesowych (czeboli) zrzeszających przedsiębiorstwa produkcyjne i usługowe działające w wielu branżach, oraz instytucje finansowe.", "Pani, która nie przyszła na rozmowę kwalifikacyjną.", "Grupa Samsung, będąca dawniej konglomeratem (czebolem), zatrudnia obecnie na całym świecie ponad 400 tysięcy pracowników w przemyśle samochodowym, elektronicznym, chemicznym, lotniczym, stoczniowym, w handlu, hotelarstwie, parkach rozrywki, przy projektowaniu i budowie wysokościowców oraz w przemyśle tekstylnym i spożywczym. Po azjatyckim kryzysie finansowym, oddziały przedsiębiorstwa Samsung, zajmujące się poszczególnymi sektorami rynku, są osobnymi spółkami, podlegającymi Grupie Samsung. Działalność przedsiębiorstwa w Korei jest zlokalizowana głównie w mieście Suwŏn, zwanym potocznie miastem Samsunga."),
                new Company("British American Tobacco Poland", "https://grubber.gpcdn.pl/companies/20063136/employer-profile-logos/9b030000-5dac-0015-fbcb-08daf2ed4112.png", "https://upload.wikimedia.org/wikipedia/commons/e/e2/British_American_Tobacco_Headquarters.jpg", "Automatyka", 3, "https://www.bat.com/", "Jesteśmy fabryką szlugów z Augustowa. Trujemy ludzi i nie potrafimy poznać się na kandydatach.", "Palaczy i automatyków", "Spółka British American Tobacco Ltd. została założona w 1902 jako wspólne przedsięwzięcie brytyjskiego Imperial Tobacco Company i amerykańskiego American Tobacco Company. Interesy prowadziła m.in. w Chinach, Kanadzie, Nowej Zelandii i Australii, nie działała jednak w Wielkiej Brytanii ani USA. W 1911 American Tobacco sprzedało udziały, natomiast Imperial Tobacco stopniowo redukowało swoje zaangażowanie w spółce aż do 1980.\n" +
                        "\n" +
                        "Po reorganizacji przeprowadzonej w 1976, przedsiębiorstwa należące do grupy były zarządzane przez nową spółkę holdingową – B.A.T. Industries. W 1994 BAT przejął swoją dawną spółkę-matkę, American Tobacco, dzięki czemu zyskał marki Lucky Strike i Pall Mall. W 1999 nabył spółkę Rothmans International, posiadającą udziały w fabryce w Mjanmie, co stało się powodem krytyki ze strony obrońców praw człowieka. Pod naciskiem rządu brytyjskiego BAT sprzedał w 2003 udziały w tej wytwórni. W tym samym roku przejął włoskie przedsiębiorstwo tytoniowe Ente Tabacchi Italiani. W 2007 zakończył działalność produkcyjną w Wielkiej Brytanii. W 2008 roku BAT przejął Scandinavian Tobacco\uFEFF[w innych językach]. 17 stycznia 2017 uzgodniono, że BAT przejmie całkowicie konkurencyjny koncern Reynolds American\uFEFF[w innych językach] za 49,4 mld dolarów.")
        );

        // Init booths
        List<Booth> booths = new ArrayList<>();
        for (int i = 0; i < numberOfBooths; i++) {
            booths.add(new Booth(i + 1));
        }

        // Add companies to repository without duplicates
        List<Company> existingCompanies = companyRepository.findAll();
        List<Company> newCompanies = new ArrayList<>();
        for (Company company : companies) {
            if (existingCompanies.stream().noneMatch(existingCompany -> existingCompany.getName().equals(company.getName()))) {
                booths.get(company.getBoothId() - 1).setCompanyName(company.getName());
                newCompanies.add(company);
            }
        }

        boothRepository.saveAll(booths);
        companyRepository.saveAll(newCompanies);
    }
}
