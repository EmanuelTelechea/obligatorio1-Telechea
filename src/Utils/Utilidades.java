package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utilidades {

    public static LocalDate validarFecha(String pFecha) throws AppException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha;

        try {
            fecha = LocalDate.parse(pFecha, formatter);
            LocalDate hoy = LocalDate.now();

            // Validar que la fecha no sea futura
            if (fecha.isAfter(hoy)) {
                throw new AppException("La fecha debe ser anterior al día de hoy");
            }

        }
        catch (DateTimeParseException e) {
            // La fecha no está en el formato correcto o es inválida
            throw new AppException("El formato de la fecha no es válido");
        }

        return fecha;
    }

}
