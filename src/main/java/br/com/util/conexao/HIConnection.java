package br.com.util.conexao;

import java.util.HashMap;
import java.util.Map;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class HIConnection {

    private Map<Object, Object> listFactory;

	public HIConnection() {
		listFactory = new HashMap<>();
	}

    @Produces
    @RequestScoped
    @Default
    public EntityManager createEntityManager() {
        try {
            String puName = "LS";
            EntityManagerFactory factory = (EntityManagerFactory) listFactory.get(puName);
            if (factory == null || !factory.isOpen()) {
                factory = Persistence.createEntityManagerFactory(puName);
                listFactory.put(puName, factory);
            }
            return factory.createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao criar EntityManager: " + e.getMessage());
            return null;
        }
    }

	public void closeEntityManager(@Disposes @Default EntityManager session) {
		try {
			if (session.isOpen()) {
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
