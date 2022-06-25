package model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Descuentos {

    private final Map<Integer, Float> descuentos;

    public Descuentos() {
        descuentos = new HashMap<>();
        loadDefaultValues();
    }

    public void loadDefaultValues() {
        descuentos.put(1, 10f); //Domingo
        descuentos.put(2, 20f); //Lunes
        descuentos.put(3, 15f); //Martes
        descuentos.put(4, 20f); //Miércoles
        descuentos.put(5, 15f); //Jueves
        descuentos.put(6, 10f); //Viernes
        descuentos.put(7, 10f); //Sábado
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
