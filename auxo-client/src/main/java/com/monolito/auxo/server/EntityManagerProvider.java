package com.monolito.auxo.server;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * A simple EJB that makes the server-side EntityManager available as a CDI
 * bean.
 * 
 * @author alex
 */
@Stateless
public class EntityManagerProvider {

	@Produces
	@PersistenceContext
	private static EntityManager em;

}
