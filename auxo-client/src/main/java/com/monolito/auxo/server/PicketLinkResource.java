package com.monolito.auxo.server;

import org.picketlink.annotations.PicketLink;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author alex
 */
public class PicketLinkResource {
	@Produces
	@PicketLink
	@PersistenceContext
	private EntityManager picketLinkEntityManager;
}
