package model;

import java.sql.Date;
import java.sql.Time;

class DescuentosTest {

    @org.junit.jupiter.api.Test
    void testDescuentos() {
        /*
        Descuentos descuentos = new Descuentos();
        descuentos.loadDefaultValues();

        float desc = descuentos.getDescuento( Date.valueOf("2022-07-10") );

        System.out.println("Descuento: "+(desc * 100)+"%");

         */

        Date fecha = new Date( System.currentTimeMillis() );
        Time hora = new Time( System.currentTimeMillis() );
        System.out.println(fecha.toString()+"\n");
        System.out.println(hora.toString()+"\n");

    }

}