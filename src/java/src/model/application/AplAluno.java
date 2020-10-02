/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.model.application;

import java.util.List;
import javax.persistence.EntityManager;
import src.model.domain.Aluno;

/**
 *
 * @author Carlos
 */
public abstract class AplAluno {

    public static void save(String nome, String idade) {
        //construção
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setIdade(Integer.valueOf(idade));

        //persistencia
        DAO.save(aluno);
    }

    public static Aluno get(String id) {
        return DAO.getById(Aluno.class, Integer.valueOf(id));
    }

    public static Aluno delete(String id) {
        return DAO.delete(Aluno.class, Integer.valueOf(id));
    }
    
    public static void update(String id, String nome, String idade) {
        Aluno aluno = DAO.getById(Aluno.class, Integer.valueOf(id));
        
        EntityManager manager = DAO.abrirConexao();

        aluno.setNome(nome);
        aluno.setIdade(Integer.valueOf(idade));

        manager.merge(aluno);
        DAO.fecharConexao();
    }
    
    public static List<Aluno> getAll() {
        return DAO.getAllNativeQuery(Aluno.class, "aluno");
    }

}
