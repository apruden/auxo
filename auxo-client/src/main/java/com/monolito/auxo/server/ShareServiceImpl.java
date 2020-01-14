package com.monolito.auxo.server;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.jboss.errai.bus.server.annotations.Service;
import com.monolito.auxo.shared.ShareList;
import com.monolito.auxo.shared.ShareService;
import com.monolito.auxo.shared.UnknownUserException;
import com.monolito.auxo.shared.TodoListUser;
import org.jboss.errai.security.shared.service.AuthenticationService;

/**
 * @author alex
 */
@Stateless
@Service
public class ShareServiceImpl implements ShareService {

	@Inject
	AuthenticationService service;
	@Inject
	EntityManager entityManager;

	@Override
	public void share(String email) throws UnknownUserException {
		org.jboss.errai.security.shared.api.identity.User currentUser = service
				.getUser();

		// if this was the real world we would sent a mail to the user that this
		// todo list was just shared with him.
		// but this is _only_ a demo.

		final TypedQuery<TodoListUser> query = entityManager.createNamedQuery(
				"userByEmail", TodoListUser.class);
		query.setParameter("email", email);
		TodoListUser user;
		try {
			user = query.getSingleResult();
		} catch (NoResultException exception) {
			throw new UnknownUserException("user with email '" + email
					+ "' is not a registered user");
		}

		ShareList shareList;
		try {
			shareList = entityManager
					.createNamedQuery("mySharedLists", ShareList.class)
					.setParameter("loginName", currentUser.getIdentifier())
					.getSingleResult();
		} catch (NoResultException e) {
			shareList = new ShareList();
			shareList.setUser(entityManager.find(TodoListUser.class,
					currentUser.getIdentifier()));
		}

		shareList.getSharedWith().add(user);

		entityManager.persist(shareList);
		entityManager.flush();
	}
}
