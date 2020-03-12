package com.daurada.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Where;

import com.daurada.base.BasicEntity;

@Entity
@Where(clause = BasicEntity.DELETED)
public class Role extends BasicEntity {

	private static final long serialVersionUID = 1L;
	
	public Role() {} 
	
	public Role(String name) {
		setName(name);
	}
	
	@ManyToMany(mappedBy = "roles")
    private Set<User> users;
	
	Set<User> getUsers() {
		return users;
	}
	
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
