import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

public class Main {

	/*
	create table theentity (
        theid integer not null,
        thevalue timestamp(6),
        primary key (theid)
    )
	 */
	public static void main(String[] args) {
		TimeZone.setDefault( TimeZone.getTimeZone( ZoneId.of( "Europe/Paris" ) ) );
		LocalDateTime d_1900_01_01_T_00_09_22 = LocalDateTime.of( 1900, 1, 1, 0, 9, 22, 0 );
		LocalDateTime d_1900_01_01_T_00_09_21 = LocalDateTime.of( 1900, 1, 1, 0, 9, 21, 0 );
		LocalDateTime d_1900_01_01_T_00_09_20 = LocalDateTime.of( 1900, 1, 1, 0, 9, 20, 0 );

		try(Connection c = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/hibernate_orm_test?preparedStatementCacheQueries=0&escapeSyntaxCallMode=callIfNoReturn",
																										"postgres", "root")) {
			PreparedStatement p = c.prepareStatement( "insert into theentity values(?, ?)" );
			bindAndExecute( p, 1, d_1900_01_01_T_00_09_22 );
			bindAndExecute( p, 2, d_1900_01_01_T_00_09_21 );
			bindAndExecute( p, 3, d_1900_01_01_T_00_09_20 );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void bindAndExecute(PreparedStatement p, int id, LocalDateTime localDateTime)
			throws SQLException {
		p.setInt( 1, id );
		p.setTimestamp(2,
				Timestamp.valueOf( localDateTime ),
				Calendar.getInstance( TimeZone.getTimeZone( ZoneId.of( "GMT" ) ) )
		);
		p.executeUpdate();
	}

}
