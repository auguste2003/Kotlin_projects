package fr.afrogeek.getthrconnet.util;

import fr.afrogeek.getthrconnet.entity.Employee;
import fr.afrogeek.getthrconnet.enums.Position;
import fr.afrogeek.getthrconnet.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

/**
 * Génerer des employees alleatoires au nombre de 50
 */
@Component
@RequiredArgsConstructor
public class DataBaseInitializer implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private static final Random RANDOM = new Random();


    @Override
    public void run(String... args) throws Exception {
        for(int i=0; i<50; i++){
            Position position = Position.values()[(i==0)?0: RANDOM.nextInt(Position.values().length-1)+1];
            Employee employee = generateEmployee(position, i);
            employeeRepository.save(employee);
        }
    }

    private Employee generateEmployee(Position position, int index) {
        String[] firstNames = {
                "Njoya", "Mballa", "Ngando", "Ewane", "Bikoi", "Efouba", "Dikoume", "Kenmoe", "Yinda", "Manga",
                "Akon", "Beyala", "Zang", "Muna", "Etoundi", "Nkono", "Mbouh", "Eto", "Matip", "Nkoulou",
                "Choupo", "Aboubakar", "Bassogog", "Djemba", "Song", "Makoun", "Njitap", "Kweuke", "Mbia", "Assou",
                "Webo", "Kana-Biyik", "Mandjeck", "Salli", "NGuemo", "Oyongo", "Ndy Assembe", "Zoua", "Ngamaleu", "Tawamba",
                "Kameni", "Meyong", "Nsame", "Ngadeu", "Moukandjo", "Marou", "Enow", "Bahoken", "Nkong", "Tchani"
        };
        String[] lastNames = {
                "Fotso", "Kamga", "Mbappe", "Elong", "Nkodo", "Djomo", "Kouam", "Mbianda", "Ngounou", "Tsogo",
                "Ndi", "Tcham", "Essomba", "Fouda", "Mbarga", "Biya", "Nguini", "Tataw", "Libii",
                "Milla", "Toko", "Ekambi", "Kunde", "Onana", "Fai", "Banana", "Zobo", "Tabi", "Yaya",
                "Ngalle", "Mouelle", "Kotto", "Lottin", "Ntamack", "Ngapeth", "Moundi", "Ndjana", "Tambe", "Achidi",
                "Ngum", "Foe", "Wome", "Nlend","Ndongo", "Ngo", "Ekotto", "Abega", "Mbida", "Emana", "Onguene"
        };
        String[] cities = {"Yaoundé", "Douala", "Bamenda", "Garoua", "Maroua", "Nkongsamba", "Buea", "Ngaoundéré", "Kumba", "Limbe", "Addis Abeba", "Cairo", "Cape Town", "Lagos", "Nairobi"};
        String[] countries = {"Cameroun", "Nigeria", "Ghana", "Kenya", "Égypte", "Afrique du Sud", "Tanzanie", "Côte d'Ivoire", "Sénégal", "Ethiopie", "Maroc", "Algérie", "Uganda", "Mali", "Zimbabwe"};
        String[] genders = {"Male", "Female"};
        String firstName = firstNames[index];
        String lastName = lastNames[index];
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@gmail.com";
        String phone = "2376" + (1000000 + RANDOM.nextInt(9000000));

        LocalDateTime dateOfBirth = LocalDateTime.of(1970 + RANDOM.nextInt(50), 1 + RANDOM.nextInt(12), 1 + RANDOM.nextInt(28), 0, 0);
        String city = cities[RANDOM.nextInt(cities.length)];
        String country = countries[RANDOM.nextInt(countries.length)];
        String gender = genders[RANDOM.nextInt(genders.length)];

        int remainingVacationDays = 5 + RANDOM.nextInt(25);
        boolean isOnVacation = RANDOM.nextBoolean();

        return new Employee(UUID.randomUUID(), gender, firstName, lastName, email, phone, dateOfBirth, city, country, remainingVacationDays, isOnVacation, position);
    }
}
