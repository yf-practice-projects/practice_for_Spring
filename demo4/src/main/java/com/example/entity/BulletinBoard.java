package com.example.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="bulletin")
public class BulletinBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    
    @Column
    private String title;
    
    @Column
    private String content;
    
    @Column
    private String fileName;
    
    @ManyToOne
    @JoinColumn(name = "user_id" ,nullable = true)
    private User user;
    
    @Column
    private LocalDateTime createDate;
    
    
    @OneToMany(mappedBy = "bulletinBoard")
    @JsonBackReference
    private List<BulletinComment> comments;
}
