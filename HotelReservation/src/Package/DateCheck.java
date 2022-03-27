package Package;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;


public class DateCheck
{
	
	//	Use this to check for time different
	//	If dateMargin == 0 ---> Same date
	//	If dateMargin == -1 --> dt is earlier than date1
	//	If dateMargin == 1 ---> dt is later than date1
    //	int dateMargin = getZeroTimeDate(dt).compareTo(getZeroTimeDate(date1));

   
	
	public static Date getZeroTimeDate(Date date1) {
        Date datereturned = date1;
        Calendar calendar = Calendar.getInstance();

        calendar.setTime( date1 );
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        datereturned = calendar.getTime();

        return datereturned;
    }

    
    
}