package services.services;
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
        return read();
    }


    // se verifica cel mai bun salon disponibil pentru internarea unui pacient
    public Salon verificaDisponibilitate()
    {
        List<Salon> saloane = read();
        if (saloane == null || saloane.isEmpty())
            return null;


        Salon salonCuCeiMaiPutiniPacienti = saloane.get(0);
//        for (Salon salon : saloane) {
//            if (salon.getCapacitateCurenta() < salonCuCeiMaiPutiniPacienti.getCapacitateCurenta() && salon.getCapacitateCurenta()<salon.getCapacitateMaxima()) {
//                salonCuCeiMaiPutiniPacienti = salon;
//            }
//        }
//        if (salonCuCeiMaiPutiniPacienti.getCapacitateCurenta() == salonCuCeiMaiPutiniPacienti.getCapacitateMaxima()-1)
//            return null;
        return salonCuCeiMaiPutiniPacienti;
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
