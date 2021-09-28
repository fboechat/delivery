import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class alteracao {

    public static void main(String[] args) {

        // inicializa uma variavel para as conexoes e pra query SQL
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            // abre q conexao com o MySQL
            conn = BD.abreConexao();
            // monta a query SQL, cada ? representa um valor a ser setado
            // logo abaixo
            stat = conn.prepareStatement(
                    "update seller set BaseSalary = BaseSalary + ? "
                            + "where (DepartmentId = ?)"
            );
            // preenche o primeiro ?
            stat.setDouble(1, 500);
            // preenche o segundo ?
            stat.setInt(2, 2);
            // armazena na variavel afetados  quantidade de registros alterados
            // com sucesso e executa a query
            int afetados = stat.executeUpdate();
            // imprime a quantidade de registros alterados com sucesso
            System.out.println("Feito! Linhas afetadas: " + afetados);
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            BD.fechaStatement(stat);
            BD.fechaConexao();
        }
    }

}
