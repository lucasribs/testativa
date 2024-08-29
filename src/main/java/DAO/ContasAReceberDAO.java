package DAO;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.ContasAReceber;
import util.Recursos;

import java.util.List;

public class ContasAReceberDAO {
    private EntityManager entityManager;

    public ContasAReceberDAO() {
        entityManager = Recursos.getRecursos().getEntityManager();
    }

    @Transactional
    public void salvarConta(ContasAReceber conta) {
        entityManager.getTransaction().begin();
        entityManager.persist(conta);
        entityManager.getTransaction().commit();
    }

    // Método para buscar uma conta a receber por ID
    public ContasAReceber buscarContaPorId(Long id) {
        return entityManager.find(ContasAReceber.class, id);
    }

    // Método para buscar todas as contas a receber
    public List<ContasAReceber> buscarTodasContas() {
        return entityManager.createQuery("SELECT c FROM ContasAReceber c", ContasAReceber.class).getResultList();
    }

    // Método para atualizar uma conta a receber
    public void atualizarConta(ContasAReceber conta) {
        entityManager.getTransaction().begin();
        entityManager.merge(conta);
        entityManager.getTransaction().commit();
    }

    // Método para excluir uma conta a receber
    public void excluirConta(Long id) {
        entityManager.getTransaction().begin();
        ContasAReceber conta = entityManager.find(ContasAReceber.class, id);
        if (conta != null) {
            entityManager.remove(conta);
        }
        entityManager.getTransaction().commit();
    }

    public void fechar() {
        Recursos.getRecursos().fechar();
    }
}
