package database;
// Use the JDBC driver
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLDatabaseConnection {

	public static void main(String[] args) {
		getCollectionCardNames("jim");
	}
	public static String[] getCollectionCardNames(String player){
		String connectionString =
				"jdbc:sqlserver://titan.csse.rose-hulman.edu;"
						+ "database=!MTGO;"
						+ "user=malinocr;"
						+ "password=#blue1baby1;"
						+ "encrypt=true;"
						+ "trustServerCertificate=true;"
						+ "hostNameInCertificate=*.database.windows.net;"
						+ "loginTimeout=30;";

		// Declare the JDBC objects.
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(connectionString);
			DatabaseMetaData meta = connection.getMetaData();
			Statement st = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			String query = "SELECT * from jimsCollection()";

			ResultSet rs = st.executeQuery(query);
			String ans[] = new String[rs.getMetaData().getColumnCount() + 1];

			rs.first();
			ans[0] = rs.getObject(1).toString();
			while (rs.next()) {
				for(int i = 1; i < (rs.getMetaData().getColumnCount() + 1); i++) {
					ans[i] = rs.getObject(1).toString();

				}
			}
			connection.close();
			for(String s: ans)
				System.out.println(s);

			return ans;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (connection != null) try { connection.close(); } catch(Exception e) {}
		}

		return null;
	}

	public static String[] getCardList() throws SQLException{
		String connectionString =
				"jdbc:sqlserver://titan.csse.rose-hulman.edu;"
						+ "database=!MTGO;"
						+ "user=malinocr;"
						+ "password=#blue1baby1;"
						+ "encrypt=true;"
						+ "trustServerCertificate=true;"
						+ "hostNameInCertificate=*.database.windows.net;"
						+ "loginTimeout=30;";

		// Declare the JDBC objects.
		Connection connection = null;
		connection = DriverManager.getConnection(connectionString);
		DatabaseMetaData meta = connection.getMetaData();
		Statement st = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String query = "SELECT Name from Card Order by name ASC";

		ResultSet rs = st.executeQuery(query);
		
		rs.last();
		int size = rs.getRow();
		rs.beforeFirst();
		String[] ret = new String[size];
		
		int index = 0;
		while(rs.next()){
			ret[index] = rs.getString(1);
			index++;
		}
		
		return ret;
	}
}