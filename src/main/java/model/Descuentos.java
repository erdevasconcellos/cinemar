package model;

import java.util.Calendar;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Descuentos {

    private final Map<Integer, Float> descuentos;

    public Descuentos() {
        descuentos = new HashMap<>();
        loadDefaultValues();
    }

    public void loadDefaultValues() {
        descuentos.put(1, 0.1f); //Domingo
        descuentos.put(2, 0.2f); //Lunes
        descuentos.put(3, 0.15f); //Martes
        descuentos.put(4, 0.2f); //Miércoles
        descuentos.put(5, 0.15f); //Jueves
        descuentos.put(6, 0.1f); //Viernes
        descuentos.put(7, 0.1f); //Sábado
    }

    public float getDescuento(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        return this.descuentos.get(day);
    }

    public void setDescuento(int day, float desc) {
        descuentos.replace(day, desc);
    }

}
