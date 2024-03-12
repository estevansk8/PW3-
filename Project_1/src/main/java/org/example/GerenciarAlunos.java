package org.example;

import jakarta.persistence.EntityManager;
import org.example.DAO.AlunoDAO;
import org.example.model.Aluno;
import org.example.util.JPAUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;
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
                    Aluno novoAluno = new Aluno();

                    System.out.println("Cadastrar aluno:\n");
                    System.out.println("Digite o nome: ");
                    String nome = scanner.next();
                    novoAluno.setName(nome);

                    System.out.println("Digite o RA: ");
                    String ra = scanner.next();
                    novoAluno.setRa(ra);

                    System.out.println("Digite o email: ");
                    String email = scanner.next();
                    novoAluno.setEmail(email);

                    System.out.println("Digite a nota 1: ");
                    BigDecimal nota1 = scanner.nextBigDecimal();
                    novoAluno.setNote1(nota1);

                    System.out.println("Digite a nota 2: ");
                    BigDecimal nota2 = scanner.nextBigDecimal();
                    novoAluno.setNote2(nota2);

                    System.out.println("Digite a nota 3: ");
                    BigDecimal nota3 = scanner.nextBigDecimal();
                    novoAluno.setNote3(nota3);

                    dao.cadastrar(novoAluno);
                    em.persist(novoAluno);
                    break;

                case 2:
                    System.out.println("Excluir aluno:\n");
                    System.out.println("Buscar aluno pelo nome:\n");
                    System.out.println("Digite o nome: ");
                    String nomeExclusao = scanner.next();
                    List<Aluno> alunosExcluidos = dao.buscarPorNome(nomeExclusao);

                    if (alunosExcluidos.isEmpty()){
                        System.out.println("ALUNO NÃO ENCONTRADO!!!");
                    }else {
                        for (Aluno aluno : alunosExcluidos){
                            em.remove(aluno);
                            System.out.println(aluno.getName() + " excluido com sucesso!!");
                        }
                    }
                    break;

                case 3:
                    System.out.println("Alterar aluno:\n");
                    System.out.println("Buscar aluno pelo nome:\n");
                    System.out.println("Digite o nome: ");
                    String nomeAlteracao = scanner.next();
                    List<Aluno> alunosAlteracao = dao.buscarPorNome(nomeAlteracao);

                    if (alunosAlteracao.isEmpty()) System.out.println("ALUNO NÃO ENCONTRADO!!!");
                    else{
                        for (Aluno aluno : alunosAlteracao){
                            System.out.println("DADOS ALUNO: \n");
                            System.out.println("Nome: " + aluno.getName());
                            System.out.println("RA: " + aluno.getRa());
                            System.out.println("Email: " +aluno.getEmail());
                            System.out.println("Notas: " + aluno.getNote1() +" - "+
                                    aluno.getNote2() + " - "+ aluno.getNote3());

                            System.out.println("Digite o nome: ");
                            String nomeNovo = scanner.next();
                            aluno.setName(nomeNovo);

                            System.out.println("Digite o RA: ");
                            String raNovo = scanner.next();
                            aluno.setRa(raNovo);

                            System.out.println("Digite o email: ");
                            String emailNovo = scanner.next();
                            aluno.setEmail(emailNovo);

                            System.out.println("Digite a nota 1: ");
                            BigDecimal nota1Novo = scanner.nextBigDecimal();
                            aluno.setNote1(nota1Novo);

                            System.out.println("Digite a nota 2: ");
                            BigDecimal nota2Novo = scanner.nextBigDecimal();
                            aluno.setNote2(nota2Novo);

                            System.out.println("Digite a nota 3: ");
                            BigDecimal nota3Novo = scanner.nextBigDecimal();
                            aluno.setNote3(nota3Novo);

                            em.persist(aluno);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Buscar aluno pelo nome:\n");
                    System.out.println("Digite o nome: ");
                    String nomeBusca = scanner.next();
                    List<Aluno> alunos = dao.buscarPorNome(nomeBusca);

                    if (alunos.isEmpty()) System.out.println("ALUNO NÃO ENCONTRADO!!!");
                    else{
                        for (Aluno alunoNome : alunos){
                            System.out.println("DADOS DO ALUNO:\n");
                            System.out.println("Nome: " + alunoNome.getName());
                            System.out.println("RA: " + alunoNome.getRa());
                            System.out.println("Email: " +alunoNome.getEmail());
                            System.out.println("Notas: " + alunoNome.getNote1() +" - "+
                                    alunoNome.getNote2() + " - "+ alunoNome.getNote3());
                        }
                    }
                    break;

                case 5:
                    System.out.println("Listar alunos:\n");
                    List<Aluno> todos = dao.buscarTodos();

                    if (todos.isEmpty()) System.out.println("LISTA VAZIA!!!");
                    else{
                        for (Aluno aluno: todos){
                            double media = (aluno.getNote1().doubleValue() + aluno.getNote2().doubleValue() + aluno.getNote3().doubleValue()) / 3;

                            System.out.println("Exibindo todos alunos: \n");
                            System.out.println("Nome: " + aluno.getName());
                            System.out.println("RA: " + aluno.getRa());
                            System.out.println("Email: " +aluno.getEmail());
                            System.out.println("Notas: " + aluno.getNote1() +" - "+
                                    aluno.getNote2() + " - "+ aluno.getNote3());
                            System.out.printf("Media: %.2f\n", (media));

                            if (media < 4) System.out.println("Situacao: Reprovado");
                            if (media >= 4 && media < 6) System.out.println("Situacao: Recuperacao");
                            if (media >= 6) System.out.println("Situacao: Aprovado");
                        }
                    }
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

    public void cadastrarAluno(){
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

        BigDecimal nota1 = sc.nextBigDecimal();
        aluno.setNote1(nota1);

        System.out.println("Digite a nota 2: ");
        BigDecimal nota2 = sc.nextBigDecimal();
        aluno.setNote2(nota2);

        System.out.println("Digite a nota 3: ");
        BigDecimal nota3 = sc.nextBigDecimal();
        aluno.setNote3(nota3);

        dao.cadastrar(aluno);
        em.getTransaction().commit();
        // Fechando este EntityManager, já que não precisaremos mais dele
        em.close();
    }
}