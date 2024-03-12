package org.example;

import jakarta.persistence.EntityManager;
import org.example.DAO.AlunoDAO;
import org.example.model.Aluno;
import org.example.util.JPAUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class GerenciarAlunos {
    public static void main(String[] args) {

        SpringApplication.run(GerenciarAlunos.class, args);

        // Criando um objeto
//        Aluno aluno = new Aluno();
//        aluno.setName("Estevan");
//        aluno.setEmail("estevanmartins03@gmail.com");
//        aluno.setRa("SC3029719");
//        aluno.setNote1(4.65);
//        aluno.setNote2(6.7);
//        aluno.setNote3(4.5);
//
//        Aluno aluno2 = new Aluno();
//        aluno2.setName("Miguel");
//        aluno2.setEmail("miguel03@gmail.com");
//        aluno2.setRa("SC3027717");
//        aluno2.setNote1(4.65);
//        aluno2.setNote2(6.7);
//        aluno2.setNote3(4.5);

        // Utilizando a classe JPAUtil para recuperar um EntityManager
        EntityManager em = JPAUtil.getEntityManager();
        // Criando o ProdutoDao
        AlunoDAO dao = new AlunoDAO(em);
        // Iniciando uma transação:
        em.getTransaction().begin();

        // Gravando o objeto no banco de dados:
//        dao.cadastrar(aluno);
//        dao.cadastrar(aluno2);

        // "Comitando" a transação
//        em.getTransaction().commit();

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println(
                    "**** CADASTRO DE ALUNOS ****\n\n" +
                    "1- Cadastrar aluno\n" +
                    "2- Excluir aluno\n" +
                    "3- Alterar aluno\n" +
                    "4- Buscar aluno pelo nome\n" +
                    "5- Listar alunos \n" +
                    "6- FIM\n"
            );

            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option){
                case 1:
                    Aluno aluno = new Aluno();

                    System.out.println("Cadastrar aluno:\n");
                    System.out.println("Digite o nome: ");
                    String nome = scanner.next();
                    aluno.setName(nome);

                    System.out.println("Digite o RA: ");
                    String ra = scanner.next();
                    aluno.setRa(ra);

                    System.out.println("Digite o email: ");
                    String email = scanner.next();
                    aluno.setEmail(email);

                    System.out.println("Digite a nota 1: ");
                    Double nota1 = scanner.nextDouble();
                    aluno.setNote1(nota1);

                    System.out.println("Digite a nota 2: ");
                    Double nota2 = scanner.nextDouble();
                    aluno.setNote2(nota2);

                    System.out.println("Digite a nota 3: ");
                    Double nota3 = scanner.nextDouble();
                    aluno.setNote3(nota3);

                    dao.cadastrar(aluno);
                    em.persist(aluno);


                    break;
                case 2:
                    System.out.println("Excluir aluno:\n");
                    break;
                case 3:
                    System.out.println("Alterar aluno:\n");
                    break;
                case 4:
                    System.out.println("Buscar aluno pelo nome:\n");
                    break;
                case 5:
                    System.out.println("Listar alunos:\n");
                    break;
                case 6:
                    System.out.println("Bye Bye!");
                    break;
                default:
                    System.out.println("OPCAO INVALIDA!!!!");
            }
        }while (option != 6);

        em.getTransaction().commit();
        em.close();
        scanner.close();

    }

    private void cadastrarAluno(){
        Scanner sc = new Scanner(System.in);
        Aluno aluno = new Aluno();

        // Utilizando a classe JPAUtil para recuperar um EntityManager
        EntityManager em = JPAUtil.getEntityManager();
        // Criando o ProdutoDao
        AlunoDAO dao = new AlunoDAO(em);
        // Iniciando uma transação:
        em.getTransaction().begin();

        System.out.println("Cadastrar aluno:\n");
        System.out.println("Digite o nome: ");
        String nome = sc.next();
        aluno.setName(nome);

        System.out.println("Digite o RA: ");
        String ra = sc.next();
        aluno.setRa(ra);

        System.out.println("Digite o email: ");
        String email = sc.next();
        aluno.setEmail(email);

        System.out.println("Digite a nota 1: ");
        Double nota1 = sc.nextDouble();
        aluno.setNote1(nota1);

        System.out.println("Digite a nota 2: ");
        Double nota2 = sc.nextDouble();
        aluno.setNote2(nota2);

        System.out.println("Digite a nota 3: ");
        Double nota3 = sc.nextDouble();
        aluno.setNote3(nota3);

        dao.cadastrar(aluno);
        em.getTransaction().commit();
        // Fechando este EntityManager, já que não precisaremos mais dele
        em.close();
    }
}