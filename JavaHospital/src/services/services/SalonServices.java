package services.services;
import entities.Internare;
import entities.Salon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Servicii (functionalitati) pentru entitatea Salon
public class SalonServices implements DatabaseService<Salon>
{
    private static SalonServices salonServices;

    private SalonServices() {}

    public static SalonServices getSalonServices(){
        if(salonServices==null)
            salonServices = new SalonServices();
        return salonServices;
    }


    @Override
    public String getTableName(){
        return "saloane";
    }

    @Override
    public String getIdColumnName() {
        return "idSalon";
    }

    @Override
    public Salon mapResultSetToEntity(ResultSet rs) throws SQLException{
        int idSalon = rs.getInt("idSalon");
        String locatie = rs.getString("locatie");
        int capacitateMaxima = rs.getInt("capacitateMaxima");
        return new Salon(idSalon,locatie,capacitateMaxima);

    }

    public List<Salon> getSaloane() {
        CvsServices.log("READ - Salon");

        return read();
    }


    // se verifica cel mai bun salon disponibil pentru internarea unui pacient
    public Salon verificaDisponibilitate()
    {
        List<Salon> saloane = read();
        if (saloane == null || saloane.isEmpty())
            return null;

        Salon salonOptim = null;
        int maxLocuriLibere = -1;

        for (Salon salon : saloane) {
            int capacitateCurenta = getCapacitateCurenta(salon);
            int locuriLibere = salon.getCapacitateMaxima() - capacitateCurenta;

            if (locuriLibere <= 0)
                continue;

            if (locuriLibere > maxLocuriLibere) {
                maxLocuriLibere = locuriLibere;
                salonOptim = salon;
            }
        }

        return salonOptim; // va fi null dacă toate saloanele sunt pline
    }


    public int getCapacitateCurenta(Salon salon)
    {
       List<Internare> internari = InternareServices.getInternareServices().read();
       int cnt  = 0;
       for (Internare internare : internari)
           if(internare.getSalon().getId() == salon.getId())
               ++cnt;
       return cnt;
    }


    // Pentru saloane avem doar operatie READ din CRUD
    @Override
    public void setParameters(PreparedStatement stmt, Salon salon, String operatie) throws SQLException {}
    @Override
    public String getInsertQuery() {return null;}
    @Override
    public String getUpdateQuery() {return null;}
    @Override
    public String getDeleteQuery() {return null;}

}
