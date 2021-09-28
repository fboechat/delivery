import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class consultaUsuario {

    public static void checaUser(String user) {
        String resulta = null;
        // inicia as variaveis
        // connection eh aq variavel que armazena dados de conexao ao BD
        Connection connection = null;
        // stat armazena os comandos SQL que seroa executados no BD
        Statement stat = null;
        // res armazena o resultado do comando executado via variavel stat
        ResultSet res = null;

        try {
            // chama o metodo que abre o banco de dados
            connection = BD.abreConexao();
            // inicia uma instancia do tipo Statement
            stat = connection.createStatement();
            // executa a query que esta entre aspas e armazena na variavel res

            res = stat.executeQuery("select * from seller where Name = '"+user+"'");
            //
            // checa se o usuario foi localizado pela quantidade de registros localizados
            int pegaquantos = res.getRow();
            if (pegaquantos == 0 ) {
               System.out.println("Usuario nao encontrado");
            }

            //while que percorre o resultado da query ate que next() seja false
            while (res.next()) {
                // res.getInt se o campo for inteiro, res.getString se for String
                // entre aspas o nome do campo
                resulta = "ID: "+res.getInt("Id") + " Nome: " + res.getString("Name") + " E-mail: " + res.getString("Email");
                System.out.println(resulta);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BD.fechaResultSet(res);
            BD.fechaStatement(stat);
            BD.fechaConexao();
        }

    }

}
