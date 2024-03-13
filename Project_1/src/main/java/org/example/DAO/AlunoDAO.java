package org.example.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.model.Aluno;

import java.util.List;

public class AlunoDAO {
    private EntityManager em;

    public AlunoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Aluno aluno){
        // Iniciando uma transação:
        this.em.getTransaction().begin();
        this.em.persist(aluno);
        this.em.getTransaction().commit();
    }

    public void delete(Aluno aluno){
        if (aluno == null) return;

        // Iniciando uma transação:
        this.em.getTransaction().begin();
        this.em.remove(aluno);
        this.em.getTransaction().commit();
    }

    public List<Aluno> buscarPorNome(String nome) throws NoResultException {
        String jpql = "SELECT a FROM Aluno a WHERE a.name = :n";
        return em.createQuery(jpql, Aluno.class)
                .setParameter("n", nome)
                .getResultList();
    }

    public List<Aluno> buscarTodos() {
        String jpql = "SELECT a FROM Aluno a";
        return em.createQuery(jpql, Aluno.class).getResultList();
    }
}
