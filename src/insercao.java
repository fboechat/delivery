import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class insercao {

    public static void main(String[] args) {

        // instancia e formata a data no formato especificado nas aspas
        SimpleDateFormat datacompleta = new SimpleDateFormat("dd/MM/yyyy");
        // istancia a variavel da conexao
        Connection conn = null;
        // instancia a variavel dos statement
        PreparedStatement stat = null;
        try {
            // abre a conexao
            conn = BD.abreConexao();
            // monta a query SQL responsavel pelo insert
            // os ?, ?, ?, ?, ? sao posicoes que serao preenchidas logo abaixo
            stat = conn.prepareStatement(
                    "insert into seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                            + "values (?, ?, ?, ?, ?)");
            // preenche o primeiro ?
            stat.setString(1, "Frederico");
            // preenche o segundo ?
            stat.setString(2, "fboechat@yahoo.com");
            // preenche o terceiro ?, note que nao usamos java.util.Date e sim
            // javaq.sql.Date
            stat.setDate(3, new java.sql.Date(datacompleta.parse("29/04/1977").getTime()));
            // preenche o quarto ?
            stat.setDouble(4, 15000);
            // preenche o quinto ?
            stat.setInt(5, 4);

            // executa a query sql e armazena na variavel a quantidade
            // de registros inseridos
            int afetados= stat.executeUpdate();

            System.out.println("Feito! Registros afetados: " + afetados);

        } catch (SQLException e) {
            e.printStackTrace();
            // captura o erro caso a data inserida seja invalida
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            BD.fechaStatement(stat);
            BD.fechaConexao();
        }

    }

}

