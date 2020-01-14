package com.monolito.auxo.shared;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alex
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "sharedWithMe", query = "SELECT s.user FROM ShareList s, in (s.sharedWith) w WHERE w.loginName = :loginName"),
		@NamedQuery(name = "mySharedLists", query = "SELECT s FROM ShareList s WHERE s.user.loginName = :loginName")})
public class ShareList {
	@Id
	//@GeneratedValue(generator = "uuid")
	//@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@OneToOne
	private TodoListUser user;

	@ManyToMany
	private List<TodoListUser> sharedWith;

	public String getId() {
		return id;
	}

	public TodoListUser getUser() {
		return user;
	}

	public void setUser(TodoListUser user) {
		this.user = user;
	}

	public List<TodoListUser> getSharedWith() {
		if (sharedWith == null) {
			sharedWith = new ArrayList<TodoListUser>();
		}
		return sharedWith;
	}

	public void setSharedWith(List<TodoListUser> sharedWith) {
		this.sharedWith = sharedWith;
	}

	@Override
	public String toString() {
		return "ShareList[" + "id=" + id + ", user=" + user + ", sharedWith="
				+ sharedWith + ']';
	}
}
