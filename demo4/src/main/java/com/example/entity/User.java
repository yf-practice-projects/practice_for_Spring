package com.example.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="users")
public class User implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @Column
    private String userId;
    @Column
    private String name;
    @Column
    @JsonIgnore
    private String encodedPassword;
    
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<BulletinBoard> bulletins;
    
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<BulletinComment> comment;
    
    public String getEncodedPassword() {
        return encodedPassword;
    }
    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }
}