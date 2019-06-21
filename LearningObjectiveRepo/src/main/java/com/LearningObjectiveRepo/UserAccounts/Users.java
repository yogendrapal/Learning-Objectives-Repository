package com.LearningObjectiveRepo.UserAccounts;



import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
  
    @Column(name = "password")
    private String password;
    
    @Column(name = "user_name")
    private String username;
    
    @Column(name = "name")
    private String name;
     
    @Column(name = "email")
    private String email;
   
   
	public Users(String password, String username, List<Role> role, String name, String email) {
		super();
		this.password = password;
		this.username = username;
		this.name = name;
		this.email= email;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRole() {
		return role;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> role=new ArrayList<>();

    public Users() {
    }

    public Users(Users users) {
        //this.active = users.getActive();
     
        this.role = users.getRoles();
        this.username = users.getUserName();
       // this.lastName =users.getLastName();
        this.id = users.getId();
        this.password = users.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	


	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }


    public @JsonIgnore List<Role> getRoles() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }
}

