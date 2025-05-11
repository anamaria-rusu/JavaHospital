package services.services;

import java.util.LinkedHashSet;
import java.util.Set;
import entities.DatabaseConnection;
import entities.Medic;

// Clasa ce contine servicii specifice fiecarei entitati si functionalitati comune in aplicatie
public class Services
{
    private final MediciServices mediciServices;
    private final PacientiServices pacientiServices;
    private final ConsultatieServices consultatieServices;
    private final SalonServices salonServices;
    private final InternareServices internareServices;
    private final IstoricMedicalService istoricMedicalService;
    //private final DatabaseService databaseService;

    public Services()
    {
        this.mediciServices = MediciServices.getMediciServices();
        this.pacientiServices = PacientiServices.getPacientiServices();
        this.consultatieServices = ConsultatieServices.getConsultatieServices();
        this.salonServices = new SalonServices();
        this.internareServices = new InternareServices();
        this.istoricMedicalService= new IstoricMedicalService();

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
    public IstoricMedicalService getIstoricMedicalService() {
        return istoricMedicalService;
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
