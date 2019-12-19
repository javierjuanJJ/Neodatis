package com.mycompany.Controlador;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;

public class Conexion {

    static ODB odb;
    private static final String url = "empresa2.neo";

    public static ODB getConnection() throws Exception {

        if (odb == null) {

            odb = ODBFactory.open(url);

        }

        return odb;

    }

    public static void cerrar() {

        if (odb != null) {
            try {
                odb.close();

            } catch (ODBRuntimeException ex) {
                System.err.println("Error " + ex.getMessage());
            } catch (Exception ex) {
                System.err.println("Error " + ex.getMessage());
            }
        }

    }

}
