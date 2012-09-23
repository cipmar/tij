package ro.rmarius.tij.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectJdbc {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException,
			IllegalAccessException {

		DriverManager.registerDriver((Driver) Class.forName(" com.mysql.jdbc.Driver").newInstance());
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/");
		PreparedStatement ps = conn.prepareStatement("select * from users");
		ps.executeQuery();
	}
}
