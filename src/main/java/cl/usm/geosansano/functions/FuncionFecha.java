/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.functions;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author Alexander Riquelme
 */
public class FuncionFecha implements Serializable {

    private static final long serialVersionUID = 1L;

    public static Date hoy() {
        return new Date();
    }

    @SuppressWarnings("deprecation")
    public static Date fechaSinHrMinSeg(Date date) {
        return new Date(date.getYear(), date.getMonth(), date.getDate());
    }

    public static String traduceMes(Integer mes) {
        String mesStr = "";
        switch (mes) {
            case 1:
                mesStr = "Enero";
                break;
            case 2:
                mesStr = "Febrero";
                break;
            case 3:
                mesStr = "Marzo";
                break;
            case 4:
                mesStr = "Abril";
                break;
            case 5:
                mesStr = "Mayo";
                break;
            case 6:
                mesStr = "Junio";
                break;
            case 7:
                mesStr = "Julio";
                break;
            case 8:
                mesStr = "Agosto";
                break;
            case 9:
                mesStr = "Septiembre";
                break;
            case 10:
                mesStr = "Octubre";
                break;
            case 11:
                mesStr = "Noviembre";
                break;
            case 12:
                mesStr = "Diciembre";
                break;
        }
        return mesStr;
    }

    public static int obtenerUltimoDiaMes(int anio, int mes) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(anio, mes - 1, 1);
        return calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Date fechaIniMes(int anio, int mes) {
        return new Date(anio - 1900, mes - 1, 1);
    }

    public static Date fechaFinMes(int anio, int mes) {
        return new Date(anio - 1900, mes - 1, obtenerUltimoDiaMes(anio, mes));
    }

    public static Date fecha(int anio, int mes, int dia) {
        return new Date(anio - 1900, mes - 1, dia);
    }

    public static List<SelectItem> selectMeses() {
        List<SelectItem> meseSelectItem = new ArrayList<>();
        meseSelectItem.add(new SelectItem(1, "Enero"));
        meseSelectItem.add(new SelectItem(2, "Febrero"));
        meseSelectItem.add(new SelectItem(3, "Marzo"));
        meseSelectItem.add(new SelectItem(4, "Abril"));
        meseSelectItem.add(new SelectItem(5, "Mayo"));
        meseSelectItem.add(new SelectItem(6, "Junio"));
        meseSelectItem.add(new SelectItem(7, "Julio"));
        meseSelectItem.add(new SelectItem(8, "Agosto"));
        meseSelectItem.add(new SelectItem(9, "Septiembre"));
        meseSelectItem.add(new SelectItem(10, "Octubre"));
        meseSelectItem.add(new SelectItem(11, "Noviembre"));
        meseSelectItem.add(new SelectItem(12, "Diciembre"));
        return meseSelectItem;

    }

    public static String fechaStrFormat(Date fecha) {
        if (fecha == null) {
            return "";
        } else {
            DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            return formato.format(fecha);
        }
    }

    public static String fechaStrFormatDay(Date fecha) {
        if (fecha == null) {
            return "";
        } else {
            DateFormat formato = new SimpleDateFormat("dd/MM");
            return formato.format(fecha);
        }
    }

    public static String fechaStrFormatHraMin(Date fecha) {
        if (fecha == null) {
            return "";
        } else {
            DateFormat formato = new SimpleDateFormat("HH:mm");
            return formato.format(fecha) + " Hrs.";
        }
    }

    public static Date diaDeLaSemana(Date date, Integer dia) {
        Calendar calendarioActual = Calendar.getInstance();
        calendarioActual.setTime(date);

        if (dia == Calendar.SUNDAY) {
            if (calendarioActual.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                calendarioActual.add(Calendar.DATE, Calendar.SUNDAY - calendarioActual.get(Calendar.DAY_OF_WEEK));
                //calendarioActual.add(Calendar.DATE, -7);
            } else {
                calendarioActual.add(Calendar.DATE, Calendar.SUNDAY - calendarioActual.get(Calendar.DAY_OF_WEEK));
                calendarioActual.add(Calendar.DATE, 7);
            }
        } else {
            if (calendarioActual.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                calendarioActual.add(Calendar.DATE, dia - calendarioActual.get(Calendar.DAY_OF_WEEK));
                calendarioActual.add(Calendar.DATE, -7);
            } else {
                calendarioActual.add(Calendar.DATE, dia - calendarioActual.get(Calendar.DAY_OF_WEEK));
            }
        }
        return calendarioActual.getTime();
    }

    // Suma los días recibidos a la fecha  
    public static Date sumarRestarDiasFecha(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }

    public static Integer anioIntFormat(Date fecha) {
        DateFormat formato = new SimpleDateFormat("yyyy");
        if (fecha == null) {
            return Integer.parseInt(formato.format(new Date()));
        } else {
            return Integer.parseInt(formato.format(fecha));
        }
    }

    public static int getDayOfTheWeek(Date d) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static String diaDeLaSemanaStr(Date d) {
        return diaDeLaSemanaStr(getDayOfTheWeek(d));
    }

    public static String diaDeLaSemanaStr(Integer d) {
        String dia = "";
        switch (d) {
            case 1:
                dia = "Domingo";
                break;
            case 2:
                dia = "Lunes";
                break;
            case 3:
                dia = "Martes";
                break;
            case 4:
                dia = "Miércoles";
                break;
            case 5:
                dia = "Jueves";
                break;
            case 6:
                dia = "Viernes";
                break;
            case 7:
                dia = "Sábado";
                break;
        }
        return dia;
    }
}
