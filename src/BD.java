import java.io.FileInputStream;
import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
public class BD {
    private static Connection conecta = null;

    public static Connection abreConexao() {
        if (conecta == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conecta = DriverManager.getConnection(url,props);
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
        return conecta;
    }

    public static void fechaConexao() {
        if (conecta != null) {
            try {
                conecta.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }

    }
    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DBException(e.getMessage());
        }
    }
    public static void fechaStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }
    public static void fechaResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }
}

