package entities;

import java.time.LocalDate;
import java.time.LocalTime;

public record SugestieProgramare(Medic medic, LocalDate data, LocalTime ora) {}
