package Utils;


import java.sql.SQLException;

public class AppSQLException extends Exception {
    private int codigoError;

    public AppSQLException(String mensaje, SQLException causa) {
        super(mensaje, causa);
        this.codigoError = causa.getErrorCode();
    }

    @Override
    public String getMessage() {
        String mensajeOriginal = super.getMessage();
        // Traducción del mensaje según el código de error de MySQL
        switch (codigoError) {
            case 1045:
                return "Acceso denegado. Verifique sus credenciales de usuario y contraseña.";
            case 1049:
                return "Base de datos no encontrada. Verifique el nombre de la base de datos.";
            case 1062:
                return "Entrada duplicada. El dato que está intentando ingresar ya existe.";
            case 1146:
                return "La tabla especificada no existe. Verifique el nombre de la tabla.";
            case 1216:
            case 1217:
                return "Violación de restricción de clave foránea.";
            default:
                return "Error en la base de datos (Código: " + codigoError + "): " + mensajeOriginal;
        }
    }
}