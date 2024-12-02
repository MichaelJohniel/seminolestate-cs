package chapter17;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAssignment {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://TestUser:test@localhost:3306/COP2805");
		
		Statement statement = connection.createStatement();
		
		ResultSet result = statement.executeQuery("select ID, Song, Album, Artist from MusicDB");
		
		// Print header
        out.printf("%-4s %-32s %-26s %-30s%n", "ID", "Song", "Artist", "Album");
        out.println("---------------------------------------------------------------------------------");
        
        // Print rows
        while(result.next()) {
            int ID = result.getInt("ID");
            String song = result.getString("Song");
            String album = result.getString("Album");
            String artist = result.getString("Artist");
            
            out.printf("%-4d %-32s %-26s %-30s%n", ID, song, artist, album);
        }
	}
}
