package com.example.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name="bulletin_comment")
public class BulletinComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    
    @Column
    private String comment;
    
    @ManyToOne
    @JoinColumn(name = "bulletin_id", nullable = true)
    @JsonManagedReference
    private BulletinBoard bulletinBoard;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = true)
    @JsonManagedReference
    private User user;
    
    @Column
    private LocalDateTime createDate;
    
    @Column
    private boolean deleteFlag = false;

}
