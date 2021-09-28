import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class insercao_pegando_id {

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
            // a diferenca aqui eh: Statement.RETURN_GENERATED_KEYS);
            stat = conn.prepareStatement(
                    "insert into seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                            + "values (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            // preenche o primeiro ?
            stat.setString(1, "Priscilla");
            // preenche o segundo ?
            stat.setString(2, "saboechat@gmail.com");
            // preenche o terceiro ?, note que nao usamos java.util.Date e sim
            // javaq.sql.Date
            stat.setDate(3, new java.sql.Date(datacompleta.parse("26/08/1987").getTime()));
            // preenche o quarto ?
            stat.setDouble(4, 15000);
            // preenche o quinto ?
            stat.setInt(5, 4);

            // executa a query sql e armazena na variavel a quantidade
            // de registros inseridos
            int afetados= stat.executeUpdate();
            // aqui outra diferenca, um if para mostrar o Id gerado na insercao
            if (afetados > 0) {
                System.out.println("Feito! Registros afetados: " + afetados);
                // para pegar o id temos que instanciar um novo ResultSet
                ResultSet res = stat.getGeneratedKeys();
                // e ter um loop caso tenham mais de um registros inseridos ao mesmo tempo
                while (res.next()) {
                    // (1) porque o numero eh a posicao e o resultado sera somente 1 coluna,
                    int aidi = res.getInt(1);
                    System.out.println("Pronto! Id gerado: "+ aidi);
                }
            } else {
                System.out.println("Nada foi inserido!");

            }


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

