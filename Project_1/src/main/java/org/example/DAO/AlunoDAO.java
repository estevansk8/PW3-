package org.example.DAO;

import jakarta.persistence.EntityManager;
import org.example.model.Aluno;

public class AlunoDAO {
    private EntityManager em;

    public AlunoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Aluno aluno){
        this.em.persist(aluno);
    }
}
