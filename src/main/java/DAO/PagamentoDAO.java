package DAO;


import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Pagamento;
import util.Recursos;

import java.util.List;

public class PagamentoDAO {

    private EntityManager entityManager;

    public PagamentoDAO() {
        entityManager = Recursos.getRecursos().getEntityManager();
    }

    @Transactional
    public void salvarPagamento(Pagamento pagamento) {
        entityManager.getTransaction().begin();
        entityManager.persist(pagamento);
        entityManager.getTransaction().commit();
    }

    public Pagamento buscarPagamentoPorId(Long id) {
        return entityManager.find(Pagamento.class, id);
    }

    public void atualizarPagamento(Pagamento pagamento) {
        entityManager.getTransaction().begin();
        entityManager.merge(pagamento);
        entityManager.getTransaction().commit();
    }

    public void excluirPagamento(Long id) {
        entityManager.getTransaction().begin();
        Pagamento pagamento = entityManager.find(Pagamento.class, id);
        if (pagamento != null) {
            entityManager.remove(pagamento);
        }
        entityManager.getTransaction().commit();
    }

    public void fechar() {
        Recursos.getRecursos().fechar();
    }

    // Método para buscar pagamentos por conta a receber
    public List<Pagamento> buscarPagamentosPorConta(Long contaId) {
        return entityManager.createQuery("FROM Pagamento p WHERE p.contaAReceber.id = :contaId", Pagamento.class)
                .setParameter("contaId", contaId)
                .getResultList();
    }

    public List<Pagamento> buscarTodosPagamentos() {
        return entityManager.createQuery("from Pagamento", Pagamento.class).getResultList();
    }
}