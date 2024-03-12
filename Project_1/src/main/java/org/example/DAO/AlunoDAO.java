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

    public void cadastrar(Aluno aluno){
        this.em.persist(aluno);
    }

    public Aluno buscarPorID(Double id){
        return em.find(Aluno.class, id);
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
