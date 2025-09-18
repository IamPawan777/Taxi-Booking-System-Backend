package com.project.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Driver implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int driver_id;
	
	private String name;
	private int age;
	
	@Column(unique = true, nullable = false)
	private String emailId;
	private String password;		//
	private String role;			//
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Drive_ID")
	private List<Booking> booking;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cabId")
	private Cab cab;

	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 return List.of(new SimpleGrantedAuthority(role));
	}
	@Override
	public String getUsername() {
		return this.emailId;	
	}
}
