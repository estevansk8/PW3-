package org.example;

import jakarta.persistence.EntityManager;
import org.example.DAO.AlunoDAO;
import org.example.model.Aluno;
import org.example.util.JPAUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class GerenciarAlunos {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(GerenciarAlunos.class, args);

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
                    saveAluno(scanner, dao, em);
                    break;

                case 2:
                    deleteAluno(scanner, dao, em);
                    break;

                case 3:
                    updateAluno(scanner, dao, em);
                    break;

                case 4:
                    listByName(scanner, dao);
                    break;

                case 5:
                    listAlunos(dao);
                    break;

                case 6:
                    System.out.println("Bye Bye!");
                    break;

                default:
                    System.out.println("OPCAO INVALIDA!!!!");
            }
        }while (option != 6);

        scanner.close();
        em.getTransaction().commit();
        em.close();
        context.close();
    }

    private static void saveAluno(Scanner scanner, AlunoDAO dao, EntityManager em) {

        Aluno novoAluno = new Aluno();

        System.out.println("Cadastrar aluno:\n");
        System.out.println("Digite o nome: ");
        scanner.nextLine();
        String nome = scanner.nextLine();
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

        dao.save(novoAluno);
    }

    private static void deleteAluno(Scanner scanner, AlunoDAO dao, EntityManager em) {
        System.out.println("Excluir aluno:\n");
        System.out.println("Buscar aluno pelo nome:\n");
        System.out.println("Digite o nome: ");
        scanner.nextLine();
        String nomeExclusao = scanner.nextLine();
        List<Aluno> alunosExcluidos = dao.buscarPorNome(nomeExclusao);
        Aluno toDelete = null;
        if (alunosExcluidos.isEmpty()) {
            System.out.println("ALUNO NÃO ENCONTRADO!!!");
            return;
        } else if (alunosExcluidos.size() == 1) {
            toDelete = alunosExcluidos.get(0);
        } else {
            int i = 0;
            for (Aluno aluno : alunosExcluidos){
                System.out.println("["+ (i++) +"]" + aluno.toString());
            }

            System.out.println("Digite o indice do qual deseja excluir: ");
            int numero = scanner.nextInt();

            if (numero <= alunosExcluidos.size() && numero>= 0){
                toDelete = alunosExcluidos.get(numero);
            }

        }
        dao.delete(toDelete);
    }

    private static void listByName(Scanner scanner, AlunoDAO dao) {
        System.out.println("Buscar aluno pelo nome:\n");
        System.out.println("Digite o nome: ");
        scanner.nextLine();
        String nomeBusca = scanner.nextLine();
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
    }

    private static void listAlunos(AlunoDAO dao) {
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
    }

    private static void updateAluno(Scanner scanner, AlunoDAO dao, EntityManager em) {
        System.out.println("Alterar aluno:\n");
        System.out.println("Buscar aluno pelo nome:\n");
        System.out.println("Digite o nome: ");
        scanner.nextLine();
        String nomeAlteracao = scanner.nextLine();
        List<Aluno> alunosAlteracao = dao.buscarPorNome(nomeAlteracao);
        Aluno to_alterar;
        if (alunosAlteracao.isEmpty()){
            System.out.println("ALUNO NÃO ENCONTRADO!!!");
            return;
        }
        else if(alunosAlteracao.size() == 1){
            to_alterar = alunosAlteracao.get(0);
        }
        else{
            int i = 0;
            for (Aluno aluno : alunosAlteracao) {
                System.out.println("DADOS ALUNO: \n");
                System.out.println("["+ (i++) +"] - " + aluno.toString());
            }

            System.out.println("Digite o indice do qual deseja alterar: ");
            int numeroAlteracao = scanner.nextInt();
            while (numeroAlteracao < 0 || numeroAlteracao > alunosAlteracao.size()){
                System.out.println("Digite um indice válido do qual deseja alterar: ");
                numeroAlteracao = scanner.nextInt();
            }
            to_alterar = alunosAlteracao.get(numeroAlteracao);
        }

        System.out.println("Digite o nome ("+ to_alterar.getName() + "):");
        scanner.nextLine();
        String nomeNovo = scanner.nextLine();
        if(nomeNovo.isEmpty()) nomeNovo = to_alterar.getName();
        to_alterar.setName(nomeNovo);

        System.out.println("Digite o RA ("+ to_alterar.getRa() + "):");
        String raNovo = scanner.nextLine();
        if(raNovo.isEmpty()) raNovo = to_alterar.getRa();
        to_alterar.setRa(raNovo);

        System.out.println("Digite o email ("+ to_alterar.getEmail() + "):");
        String emailNovo = scanner.nextLine();
        if(emailNovo.isEmpty()) emailNovo = to_alterar.getEmail();
        to_alterar.setEmail(emailNovo);

        System.out.println("Digite a nota 1 ("+ to_alterar.getNote1() + "):");
        String nota1Nova = scanner.nextLine();
        BigDecimal notaa1;
        if(nota1Nova.isEmpty()) notaa1 = to_alterar.getNote1();
        else notaa1 = new BigDecimal(nota1Nova);
        to_alterar.setNote1(notaa1);

        System.out.println("Digite a nota 2 ("+ to_alterar.getNote2() + "):");
        String nota2Nova = scanner.nextLine();
        BigDecimal notaa2;
        if(nota2Nova.isEmpty()) notaa2 = to_alterar.getNote2();
        else notaa2 = new BigDecimal(nota2Nova);
        to_alterar.setNote1(notaa2);

        System.out.println("Digite a nota 3 ("+ to_alterar.getNote3() + "):");
        String nota3Nova = scanner.nextLine();
        BigDecimal notaa3;
        if(nota3Nova.isEmpty()) notaa3 = to_alterar.getNote3();
        else notaa3 = new BigDecimal(nota3Nova);
        to_alterar.setNote1(notaa3);

        dao.save(to_alterar);
    }
}