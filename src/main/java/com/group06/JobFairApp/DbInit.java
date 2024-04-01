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
                new Company("Przemysłowy Instytut Automatyki i Pomiarów", "https://creative.piap.pl/img/logo/lukasiewicz/lukasiewicz_piap_green_black.png", "Robotyka", 1, "https://piap.lukasiewicz.gov.pl/", "Sieć Badawcza Łukasiewicz – Przemysłowy Instytut Automatyki i Pomiarów PIAP od ponad 50 lat oferuje polskim podmiotom i partnerom zagranicznym rozwiązania wykorzystujące najnowsze technologie z dziedziny robotyki, automatyki, technik pomiarowych i druku 3D, a także technologii kosmicznych. Instytut powstał w 1965 roku, a od kwietnia 2019 roku jest częścią Sieci Badawczej Łukasiewicz." ),
                new Company("Samsung", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Samsung_Logo.svg/1280px-Samsung_Logo.svg.png", "Informatyka", 2, "https://www.samsung.com/pl/", "Jedna z największych południowokoreańskich grup biznesowych (czeboli) zrzeszających przedsiębiorstwa produkcyjne i usługowe działające w wielu branżach, oraz instytucje finansowe."),
                new Company("British American Tobacco Poland", "https://grubber.gpcdn.pl/companies/20063136/employer-profile-logos/9b030000-5dac-0015-fbcb-08daf2ed4112.png", "Automatyka", 3, "https://www.bat.com/", "Jesteśmy fabryką szlugów z Augustowa. Trujemy ludzi i nie potrafimy poznać się na kandydatach.")
        );

        // Init booths
        List<Booth> booths = new ArrayList<>();
        for (int i=0; i < numberOfBooths; i++)
        {
            booths.add(new Booth(i+1));
        }
        for (Company company : companies)
        {
            booths.get(company.getBoothId() - 1).setCompanyName(company.getName());
        }

        companyRepository.saveAll(companies);
        boothRepository.saveAll(booths);
    }
}
