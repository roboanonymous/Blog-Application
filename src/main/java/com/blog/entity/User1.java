package com.blog.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user1")
@NoArgsConstructor
@Getter
@Setter
public class User1 implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "User_name" , nullable = false, length = 100)
	private String name;
	private String email;
	private String password;
	private String about;
	
	@OneToMany(mappedBy = "user1" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();
	
	@OneToMany(mappedBy = "user1" , cascade = CascadeType.ALL)
	 private Set<Comment> comments = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinTable(name = "user_role" , joinColumns = @JoinColumn(name = "users" , referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "role" , referencedColumnName = "id")
			)
	private Set<Role> role = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> autorities = this.role.stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return autorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
