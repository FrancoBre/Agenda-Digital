package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Fecha{
	
	public static Integer actual_Year() {
		return LocalDate.now().getYear();
	}
	
	public static ArrayList<Integer> lastYears(Integer x){
		ArrayList<Integer> year = new ArrayList<Integer>();
		for (int i = actual_Year(); i > actual_Year()-x ; i--) {
			year.add(i);
		}
		return year;
	}
	
	public static Integer[] getMonths() {
		return new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
	}
	
	public static int numeroDeDiasMes(int mes, int anio){
	    int numeroDias=-1;
	        switch(mes){
	            case 1:
	            case 3:
	            case 5:
	            case 7:
	            case 8:
	            case 10:
	            case 12:
	                numeroDias=31;
	                break;
	            case 4:
	            case 6:
	            case 9:
	            case 11:
	                numeroDias=30;
	                break;
	            case 2:
	                if(esBisiesto(1900 + anio)){
	                    numeroDias=29;
	                }else{
	                    numeroDias=28;
	                }
	                break;
	        }
	    return numeroDias;
	}	
	
	public static boolean esBisiesto(int anio) {
	    boolean esBisiesto = false;
	    if ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0))) {
	        esBisiesto = true;
	    }
	    return esBisiesto;	    
	}
	
	public static java.sql.Date parseNacimiento(String nacimiento) throws ParseException {
		/*SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date parsed = format.parse(nacimiento);
        java.sql.Date date = new java.sql.Date(parsed.getTime());*/
		//nacimiento.replaceAll("\\s+","-");
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
		
        Date date = formato.parse(nacimiento);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}
	
	
	
}