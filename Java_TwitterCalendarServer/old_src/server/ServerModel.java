package de.fhbrandenburg.kalenderapp.server;

import java.util.Date;

public class ServerModel {

	public ServerModel(String fuser, int art, Date datum, String Content, int command ){
	
		
		
		Thread serverCheckup = new Thread( new ServerThread() );
		serverCheckup.start();
//		Thread dateCheckup = new Thread( new Datecheck(fuser,art,datum) );
//		dateCheckup.start();
//		Thread jdbcCheckup = new Thread( new JdbcSqlQuery(fuser,art,datum,Content,command) );
//		jdbcCheckup.start();
//		try{
//			jdbcChec kup.wait();
//		}catch(InterruptedException e){
//			e.printStackTrace();
//		}		



		
		
	}
}
