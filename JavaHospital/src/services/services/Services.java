package services.services;

import java.util.LinkedHashSet;
import java.util.Set;

// Clasa ce contine servicii specifice fiecarei entitati si functionalitati comune in aplicatie
public class Services
{
    private final MediciServices mediciServices;
    private final PacientiServices pacientiServices;
    private final ConsultatieServices consultatieServices;
    private final SalonServices salonServices;
    private final InternareServices internareServices;
    private final IstoricMedicalServices istoricMedicalServices;
    //private final DatabaseService databaseService;

    public Services()
    {
        this.mediciServices = MediciServices.getMediciServices();
        this.pacientiServices = PacientiServices.getPacientiServices();
        this.consultatieServices = ConsultatieServices.getConsultatieServices();
        this.salonServices = SalonServices.getSalonServices();
        this.internareServices = InternareServices.getInternareServices();
        this.istoricMedicalServices = IstoricMedicalServices.getIstoricMedicalServices();

    }

    // Servicii petru clasa Medic
    public MediciServices getMediciServices() {
        return mediciServices;
    }

    // Servicii petru clasa Pacient
    public PacientiServices getPacientiServices() {
        return pacientiServices;
    }

    // Servicii petru clasa Consultatie
    public ConsultatieServices getConsultatieServices() {
        return consultatieServices;
    }

    // Servicii petru clasa Salon
    public SalonServices getSalonServices() {
        return salonServices;
    }

    // Servicii petru clasa Internare
    public InternareServices getInternareServices(){
        return internareServices;
    }

    // Servicii petru clasa IstorcPacient
    public IstoricMedicalServices getIstoricMedicalService() {
        return istoricMedicalServices;
    }

    // functie ce returneaza departamentele mediale ale spitalului
    public Set<String> getDepartamente()
    {
        Set<String> departamente = new LinkedHashSet<>();
        departamente.add("Cardiologie");
        departamente.add("Neurologie");
        departamente.add("Chirurgie");
        departamente.add("Pediatrie");
        departamente.add("Ortopedie");
        return departamente;
    }
}
