package de.fhbrandenburg.kalenderapp.server;

import java.util.Calendar;
import java.util.Date;

public class Start {

	public static void main(String argv[]){
		Calendar c = Calendar.getInstance();
		final String fuser="xxxxx";
		final Date datum=c.getTime();
		final int art = 1;
		final String content="xxxContentxx";
		final int command=0;
		c.set(Calendar.DAY_OF_MONTH, +10);
		
		new ServerModel(fuser, art, datum, content, command );
		
	}
}
