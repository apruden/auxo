package com.monolito.auxo.shared;

import org.jboss.errai.bus.server.annotations.Remote;

/**
 * @author alex
 */
@Remote
public interface ShareService {

	/**
	 * Share my list with another user via email.
	 * 
	 * @param email
	 *            the email of the user to share my list with
	 */
	void share(String email) throws UnknownUserException;
}
