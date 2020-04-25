package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

	private String login; 
	private String password ; 
	private String nom;
	private String prenom;
	private String role; 

	private String cinCli; 
	private String adresseCli;
	private String numTelCli;
}
