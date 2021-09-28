import java.util.Scanner;

public class principal {

    public static void main(String[] args) {
        System.out.println("Menu Principal");
        System.out.println(" ");
        System.out.println("Digite a opcao correta");
        System.out.println(" ");
        System.out.println("1 - Adiciona usuario");
        System.out.println("2 - Consulta usuario");
        System.out.println("3 - Altera usuario");
        System.out.println("4 - Remove usuario");
        System.out.println("5 - Testa login e senha do usuario");
        System.out.println(" ");
        System.out.println("Digite a opcao desejada: ");
        Scanner sc = new Scanner(System.in);

        int escolhe1 = sc.nextInt();

        switch(escolhe1) {
            case 1:
                System.out.println("Insercao de usuario");
                break;
            case 2:
                System.out.println("Consulta de usuarios");
                System.out.println(" ");
                System.out.println("Digite o usuario a ser exibido ");
                String pegauser = sc.next();
                consultaUsuario.checaUser(pegauser);
                sc.close();
                break;
            case 3:
                System.out.println(escolhe1);
                break;
            case 4:
                System.out.println(escolhe1);
                break;
            case 5:
                System.out.println(escolhe1);
                break;
            default:
                System.out.println(escolhe1);


        }

    }

}
