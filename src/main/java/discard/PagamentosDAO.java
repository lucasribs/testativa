package discard;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class PagamentosDAO {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public PagamentosDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("pagamentosPU");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void salvarPagamento(Pagamentos pagamento) {
        entityManager.getTransaction().begin();
        entityManager.persist(pagamento);
        entityManager.getTransaction().commit();
    }

    public Pagamentos buscarPagamentoPorId(Long id) {
        return entityManager.find(Pagamentos.class, id);
    }

    public void atualizarPagamento(Pagamentos pagamento) {
        entityManager.getTransaction().begin();
        entityManager.merge(pagamento);
        entityManager.getTransaction().commit();
    }

    public void excluirPagamento(Long id) {
        entityManager.getTransaction().begin();
        Pagamentos pagamento = entityManager.find(Pagamentos.class, id);
        if (pagamento != null) {
            entityManager.remove(pagamento);
        }
        entityManager.getTransaction().commit();
    }

    public void fechar() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public List<Pagamentos> buscarTodosPagamentos() {
        return entityManager.createQuery("from Pagamentos", Pagamentos.class).getResultList();
    }
}


/*
import model.Pagamentos;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class PagamentosDAO {

    public void salvarPagamento(Pagamentos pagamento) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(pagamento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void atualizarPagamento(Pagamentos pagamento) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(pagamento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Pagamentos buscarPagamentoPorId(Long id) {
        Transaction transaction = null;
        Pagamentos pagamento = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            pagamento = session.get(Pagamentos.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return pagamento;
    }

    public List<Pagamentos> buscarTodosPagamentos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Pagamentos", Pagamentos.class).list();
        }
    }

    public void excluirPagamento(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Pagamentos pagamento = session.get(Pagamentos.class, id);
            if (pagamento != null) {
                session.remove(pagamento);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
*/