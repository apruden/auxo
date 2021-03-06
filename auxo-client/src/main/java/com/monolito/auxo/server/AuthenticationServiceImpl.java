package com.monolito.auxo.server;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.errai.bus.server.annotations.Service;
import com.monolito.auxo.shared.TodoListUser;
import org.jboss.errai.security.shared.exception.AuthenticationException;
import org.jboss.errai.security.shared.exception.FailedAuthenticationException;
import org.jboss.errai.security.shared.service.AuthenticationService;
/*import org.picketlink.Identity;
import org.picketlink.Identity.AuthenticationResult;
import org.picketlink.authentication.UserAlreadyLoggedInException;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.model.basic.User;*/

@Stateless
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	/*@Inject
	private DefaultLoginCredentials credentials;
	@Inject
	private Identity identity;*/
	@Inject
	private EntityManager entityManager;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public org.jboss.errai.security.shared.api.identity.User login(
			String username, String password) {
		return null;
		/*credentials.setUserId(username);
		credentials.setPassword(password);

		final AuthenticationResult result;

		try {
			result = identity.login();
		} catch (UserAlreadyLoggedInException ex) {
			throw new UserAlreadyLoggedInException("Already logged in as "
					+ ((User) identity.getAccount()).getLoginName());
		} catch (RuntimeException ex) {
			throw new AuthenticationException(
					"An error occurred during authentication.", ex);
		}

		if (result == Identity.AuthenticationResult.SUCCESS) {
			final User picketLinkUser = (User) identity.getAccount();
			final TodoListUser todoListUser = lookupTodoListUser(picketLinkUser
					.getEmail());

			return todoListUser;
		} else {
			throw new FailedAuthenticationException();
		}*/
	}

	@Override
	public boolean isLoggedIn() {
		//return identity.isLoggedIn();
		return true;
	}

	@Override
	public void logout() {
		//identity.logout();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public org.jboss.errai.security.shared.api.identity.User getUser() {
		/*if (identity.isLoggedIn()) {
			final User picketLinkUser = (User) identity.getAccount();
			return lookupTodoListUser(picketLinkUser.getEmail());
		} else {
			return org.jboss.errai.security.shared.api.identity.User.ANONYMOUS;
		}*/
		return null;
	}

	private TodoListUser lookupTodoListUser(String email) {
		final TodoListUser todoListUser = entityManager
				.createNamedQuery("userByEmail", TodoListUser.class)
				.setParameter("email", email).getSingleResult();

		return todoListUser;
	}
}
