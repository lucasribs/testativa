package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;



public class Recursos {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static Recursos recursos = null;
    private Recursos(){
        entityManagerFactory = Persistence.createEntityManagerFactory("pagamentosPU");
        entityManager = entityManagerFactory.createEntityManager();
    }
    public static Recursos getRecursos(){
        if(recursos == null){
            recursos = new Recursos();
        }
        return recursos;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    public void fechar() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
