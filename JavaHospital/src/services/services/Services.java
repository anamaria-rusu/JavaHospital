package services.services;

import entities.Internare;
import entities.Salon;

import java.util.LinkedHashSet;
import java.util.Set;

public class Services
{
    private MediciServices mediciServices;
    private PacientiServices pacientiServices;
    private ConsultatieService consultatieService;
    private SalonServices salonServices;
    private InternareServices internareServices;
    private IstoricMedicalService istoricMedicalService;

    public Services() {
        this.mediciServices = new MediciServices();
        this.pacientiServices = new PacientiServices();
        this.consultatieService = new ConsultatieService(this.mediciServices);
        this.salonServices = new SalonServices();
        this.internareServices = new InternareServices();
        this.istoricMedicalService= new IstoricMedicalService();
    }

    public MediciServices getMediciServices() {
        return mediciServices;
    }

    public PacientiServices getPacientiServices() {
        return pacientiServices;
    }

    public ConsultatieService getConsultatieService() {
        return consultatieService;
    }
    public SalonServices getSalonServices() {
        return salonServices;
    }

    public InternareServices getInternareServices(){
        return internareServices;
    }

    public IstoricMedicalService getIstoricMedicalService() {
        return istoricMedicalService;
    }

    public Set<String> getDepartamente() {

        // Inițializăm un Set cu departamente predefinite
        Set<String> departamente = new LinkedHashSet<>();
        departamente.add("Cardiologie");
        departamente.add("Neurologie");
        departamente.add("Chirurgie");
        departamente.add("Pediatrie");
        departamente.add("Ortopedie");
        return departamente;

    }
}
