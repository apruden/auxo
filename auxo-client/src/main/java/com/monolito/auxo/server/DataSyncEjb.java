package com.monolito.auxo.server;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.errai.jpa.sync.client.shared.DataSyncService;
import org.jboss.errai.jpa.sync.client.shared.JpaAttributeAccessor;
import org.jboss.errai.jpa.sync.client.shared.SyncRequestOperation;
import org.jboss.errai.jpa.sync.client.shared.SyncResponse;
import org.jboss.errai.jpa.sync.client.shared.SyncableDataSet;
import org.jboss.errai.jpa.sync.server.JavaReflectionAttributeAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class DataSyncEjb {

	private static final Logger log = LoggerFactory.getLogger(DataSyncEjb.class);

	@PersistenceContext
	private EntityManager em;

	private final JpaAttributeAccessor attributeAccessor = new JavaReflectionAttributeAccessor();

	public <E> List<SyncResponse<E>> coldSync(SyncableDataSet<E> dataSet,
			List<SyncRequestOperation<E>> remoteResults) {
		log.debug("Doing a cold sync!");
		DataSyncService dss = new org.jboss.errai.jpa.sync.server.DataSyncServiceImpl(
				em, attributeAccessor);
		return dss.coldSync(dataSet, remoteResults);
	}
}
