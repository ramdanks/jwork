/**
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 030421
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class JWork
{
    public static void main(String[] args) {

        Calendar cal = new GregorianCalendar(2021, 4, 8);

        ArrayList<Jobseeker> arrJS = new ArrayList<Jobseeker>();
        arrJS.add(new Jobseeker(1, "Yogie", ".yogie.wisesa@ui.ac.id", "admin1234", cal));
        arrJS.add(new Jobseeker(1, "Ramadhan", "ramadhanks1@gmail.com", "Power69Ranger", 2021, 1, 1));
        arrJS.add(new Jobseeker(1, "Ailsa", "ailsa.sy@ui.ac.id", "aaaIIIIssHHAA"));

        arrJS.forEach((js) -> { System.out.println(js.toString()); });

        Jobseeker jsChange = arrJS.get(0);
        jsChange.setEmail("yogie.wisesa@gmail.com");
        jsChange.setPassword("123@#PaSs");
        System.out.println(jsChange.toString());
    }
}
