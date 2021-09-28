import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class transacao {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stat = null;
        try {
            conn = BD.abreConexao();
            // desativa o commit para ter transacoes seguras
            conn.setAutoCommit(false);

            stat = conn.createStatement();

            int linhas1 = stat.executeUpdate("update seller set BaseSalary "
                    + "= 2900 where DepartmentId = 1");
            //
            // fake error para testar o rollback
            //int x = 1;
            // if (x < 2) {
            // throw new SQLException("Fake error");
            // }
            //
            int linhas2 = stat.executeUpdate("update seller set BaseSalary "
                    + "= 3900 where DepartmentId = 2");
            System.out.println("Primeiro comando: " + linhas1);
            System.out.println("Segundo comando: " + linhas2);
            // executa commit no BD
            conn.commit();

        } catch (SQLException e) {
            try {
                // tenta executar rollback nas changes
                conn.rollback();
                throw new DBException("Erro, ransacao rollbacada. Causa: " + e.getMessage());
            } catch (SQLException e1) {
                throw new DBException("Erro ao tentar rollback! Causa: "+e1.getMessage());

            } finally {
                BD.fechaStatement(stat);
                BD.fechaConexao();
            }


        }

    }
}

