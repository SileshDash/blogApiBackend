package com.org.blog_api.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    @Column(nullable=false,length = 100)
	private String title;
    @Column(nullable = false,length = 1000)
	private String content;
	private String imageName;
	private Date addDate;
	
	@ManyToOne
	private User user;
	@ManyToOne 
	@JoinColumn(name = "catagory_Id")
	private Catagory catagory;
}
