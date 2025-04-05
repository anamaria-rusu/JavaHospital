package services.services;

public class Services
{
    private MediciServices mediciServices;
    private PacientiServices pacientiServices;
    private ConsultatieService consultatieService;

    public Services() {
        this.mediciServices = new MediciServices();
        this.pacientiServices = new PacientiServices();
        this.consultatieService = new ConsultatieService(this.mediciServices);
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
}
