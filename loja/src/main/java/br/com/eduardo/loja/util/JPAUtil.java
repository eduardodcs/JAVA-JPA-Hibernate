package br.com.eduardo.loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	//indicar no create o nome que foi colocado no persistence-unit
	private static final EntityManagerFactory FACTORY = Persistence
			.createEntityManagerFactory("loja");
	
	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
}
