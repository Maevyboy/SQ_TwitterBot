package de.fhbrandenburg.kalenderapp.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JdbcSqlQuery implements Runnable {

 
	private String command;
	private String fuser;
	private int art;
	private Date datum;
	private Date insertDate;
	private String content;
	private String DATE_FORMAT = "yyyy-MM-dd";
	Calendar c = Calendar.getInstance();
	
	public JdbcSqlQuery(String fuser, int art, Date datum, String content,String command) {
		this.command = command;
		this.fuser = fuser;
		this.art = art;
		this.datum = datum;
		this.content = content;
		

	}

	@Override
	public void run() {
//		lock.lock();
//		while(true){			
				
			try{
				
				Class.forName(DatabaseConstants.DRIVER);
			}catch(ClassNotFoundException e){
				System.out.println( "Ist keine Treiber Class !" );
			}
			Connection con = null;
			try{
				con = DriverManager.getConnection(DatabaseConstants.DATABASE_URL, DatabaseConstants.USER, DatabaseConstants.PASS);
				Statement mystatement = con.createStatement();
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				System.out.println("Heute ist der "+sdf.format(datum));
				c.getTime();
				System.out.println(c.get(Calendar.DAY_OF_MONTH));
				switch (art) {
				case 1:
					c.set(Calendar.DATE, (c.get(Calendar.DAY_OF_MONTH)+1));
					System.out.println(c.get(Calendar.DAY_OF_MONTH));
					break;
				case 2:
					c.set(Calendar.DAY_OF_MONTH, (c.get(Calendar.DATE)+3));						
					System.out.println("2");
					break;						
				case 3:
					c.set(Calendar.DAY_OF_MONTH, (c.get(Calendar.DATE)+7));
					System.out.println("3");
					break;
				case 4:
//					c.setTime(datum);
//					
//					c.set(Calendar.DAY_OF_MONTH, (c.setTime(datum)-1));
//					System.out.println("4");
					break;
				default:
					break;						
				}
				insertDate = c.getTime();
								
				switch (command) {
				case "insert":
					mystatement.executeUpdate( "INSERT INTO `kalenderapp`.`remind` (`ID`, `Fuser`, `Datum`, `RemindDatum`, `Art`, `Content`) VALUES (NULL, '"+fuser+"', '"+sdf.format(datum)+"', '"+sdf.format(insertDate)+"', '"+art+"', '"+content+"')" );
					mystatement.close();
					break;
				case "delete":
					mystatement.executeUpdate( "DELETE FROM remind WHERE Fuser ='"+fuser+"' AND Datum = '"+sdf.format(datum)+"'" );
					mystatement.close();
					break;
				case "update":
					mystatement.executeQuery( "SELECT * FROM remind" );
					mystatement.close();
					break;

				default:
					System.out.println("nix passiert");
					break;
				}

			}catch( SQLException e){
			e.printStackTrace();
			}finally{
				if(con != null){
					try
					{
						con.close();
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
			}
//				lock.unlock();
				
//		}
	}
	
}
