package com.org.blog_api.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Catagories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Catagory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
 private int id;
	@Column(nullable = false)
 private String title;
	@Column(nullable = false)
 private String description;
	@OneToMany(mappedBy = "catagory",cascade = CascadeType.ALL)
	private List<Post> posts=new ArrayList<>();
}
