package com.example.demo.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Article;
import com.example.demo.entity.Categorie;
import com.example.demo.entity.Client;
import com.example.demo.entity.Fournisseur;
import com.example.demo.entity.Marque;
import com.example.demo.entity.SousCategorie;
import com.example.demo.entity.VenteFlash;
import com.example.demo.service.CrudRestAdmin;


@RestController
public class AdminController {
	
	@Autowired
	private CrudRestAdmin service;
	
	
	// Gestion de Fournisseur
	
	@GetMapping("/fournisseur")
	public Resources<Fournisseur> getAllFour(){
		return service.getAllFour();
	}
	@PostMapping("/fournisseur")
	public Fournisseur addFour(@RequestBody Fournisseur fournisseur) {

		Fournisseur four = new Fournisseur();
		four.setAddress(fournisseur.getAddress());
		four.setNom(fournisseur.getNom());
		four.setPhoneNumber(fournisseur.getPhoneNumber());
		four.setPassword(fournisseur.getPassword());
		four.setPrenom(fournisseur.getPrenom());
		four.setUsername(fournisseur.getUsername());
		
		service.addFour(fournisseur);
		return fournisseur;	
	}
	
	@DeleteMapping("/deleteFou/{login}")
	public void deleteFou(@PathVariable("login") String login) {
		
		service.deleteByLogin(login);
	}
	
	@PutMapping("/modifyFour/{login}")
	public Fournisseur modifyFour(@RequestBody Fournisseur fournisseur , @PathVariable("login") String login) {
		
		Optional<Fournisseur> f = service.findByLogin(login);
		Fournisseur four = new Fournisseur();
		four.setAddress(fournisseur.getAddress());
		four.setUsername(fournisseur.getUsername());
		four.setNom(fournisseur.getNom());
		four.setPhoneNumber(fournisseur.getPhoneNumber());
		four.setPassword(fournisseur.getPassword());
		four.setPrenom(fournisseur.getPrenom());
		four.setRole(fournisseur.getRole());
		service.modifyFour(four , login);
		return four;	
	}
	
	// Consulter toutes les articles
	
	@GetMapping("/article")
	public Resources<Article> getAllArticle(){
		return service.getAllArticle();
	}
	
	// Gestion des Clients
	
	@GetMapping("/client")
	public Resources<Client> getAllClient(){
		return service.getAllClient();
	}
	@PostMapping("/addClient")
	public Client addClient(@RequestBody Client client) {

		Client cl = new Client();
		cl.setAdresseCli(client.getAdresseCli());
		cl.setCinCli(client.getCinCli());
		cl.setLogin(client.getLogin());
		cl.setNom(client.getNom());
		cl.setNumTelCli(client.getNumTelCli());
		cl.setPassword(client.getPassword());
		cl.setPrenom(client.getPrenom());
		cl.setRole(client.getRole());
		service.addClient(cl);
		return cl;
	}

	@DeleteMapping("/deleteCl/{login}")
	public void deleteCl(@PathVariable("login") String login) {
		
		service.deleteByLoginCl(login);
	}
	
	@PutMapping("/modifyCl/{login}")
	public Client modifyCl(@RequestBody Client client , @PathVariable("login") String login) {
		
		Optional<Client> c = service.findByLoginCl(login);
		Client cl = new Client();
		cl.setAdresseCli(client.getAdresseCli());
		cl.setCinCli(client.getCinCli());
		cl.setLogin(client.getLogin());
		cl.setNom(client.getNom());
		cl.setNumTelCli(client.getNumTelCli());
		cl.setPassword(client.getPassword());
		cl.setPrenom(client.getPrenom());
		cl.setRole(client.getRole());
		service.modifyCl(cl, login);
		return cl;
	}
	
	// Gestion Categories
	
	@GetMapping("/categorie")
	public Resources<Categorie> getAllCategorie(){
		return service.findAllCategoreis();
	}
	
	@PostMapping("/categorie")
	public Categorie addCategorie(@RequestBody Categorie categorie) {
		Categorie c = new Categorie();
		c.setIdCat(categorie.getIdCat());
		c.setLibelleCat(categorie.getLibelleCat());
		service.addCategorie(c);
		return c ;
	}
	
	@DeleteMapping("/categorie/{id}")
	public void deleteCategorieById(@PathVariable("id") String id){
		service.deleteCategorieById(id);
	}
	
	@PutMapping("/categorie/{id}")
	public Categorie modifyCategorie(@RequestBody Categorie categorie ,@PathVariable("id") String id) {
			service.modifyCategorie(categorie, id);
		return categorie ;
	}
	
	// Gestion SousCategorie
	
	@GetMapping("/souscategorie")
	public Resources<SousCategorie> getAllSousCategoreis(){
		return service.findAllSousCategoreis();
	}
	@GetMapping("/categorie/{idCat}")
	public Categorie findCategoreisById(@PathVariable("idCat") String id) {
		return service.findCategoreisById(id);
	}
	
	@PostMapping("/souscategorie") 
	public SousCategorie addSousCategorie(@RequestBody SousCategorie sousCategorie ) {
		
		 SousCategorie sousCat = new SousCategorie();
         sousCat.setIdSousCat(sousCategorie.getIdSousCat());
         sousCat.setLibelleSousCat(sousCategorie.getLibelleSousCat());
         sousCat.setValeur(sousCategorie.getValeur());
         Categorie cat = service.findCategoreisById(sousCategorie.getCategorie().getIdCat());
         if (cat != null )
         {
             System.err.println(cat);
             sousCat.setCategorie(cat);
         }
        
         service.addSousCategorie(sousCat);
         return sousCat ;
	}
	
	@DeleteMapping("/souscategorie/{id}")
	public void deleteSousCategorieById(@PathVariable("id") String id) {
		service.deleteSousCategorieById(id);
	}
	
	@PutMapping("/souscategorie/{id}")
	public SousCategorie modifySousCategorie(@RequestBody SousCategorie sousCategorie ,@PathVariable("id") String id) {
		SousCategorie s = new SousCategorie();
		s.setCategorie(sousCategorie.getCategorie());
		s.setLibelleSousCat(sousCategorie.getLibelleSousCat());
		s.setValeur(sousCategorie.getValeur());
		service.modifySousCategorie(s, id);
		return s ;
	}
	
	// Gestion Marque
	
	@GetMapping("/marque")
	public Resources<Marque> getAllMarque(){
		return service.findAllMarque();
	}
	
	@PostMapping("/marque")
	public Marque addMarque( @RequestBody Marque marque) {
		Marque m = new Marque();
		m.setLibelleMarq(marque.getLibelleMarq());
		service.addMarque(marque);
		return m ;
	}
	
	@DeleteMapping("/marque/{id}")
	public void deleteMarqueById(@PathVariable("id") String id) {
		service.deleteMarqueById(id);
	}
	
	@PutMapping("/marque/{id}")
	public Marque modifyMarque(@RequestBody Marque marque ,@PathVariable("id") String id) {
		Marque m = new Marque();
		m.setLibelleMarq(marque.getLibelleMarq());
		service.modifyMarque(m, id);
		return m ;
	}
	
	// Gestion Vente Flash
	
	@GetMapping("/venteflash")
	public Resources<VenteFlash> getAllVenteFlash(){
		return service.findAllventeflash();
	}
	
	@PostMapping("/postventeflash")
	public VenteFlash addVenteFlash( @RequestBody VenteFlash venteFlash) {
		service.addVenteFlash(venteFlash);
		
		return venteFlash;
	}
	
	@DeleteMapping("/venteflash/{id}")
	public void deleteVenteFlashById(@PathVariable("id") String id) {
		service.deleteVenteFlashById(id);
	}
	
	@PutMapping("/venteflash/{id}")
	public VenteFlash modifyVenteFlash(@RequestBody VenteFlash venteFlash ,@PathVariable("id") String id) {
		service.modifyVenteFlash(venteFlash, id);
		return venteFlash;
	}
	@GetMapping("/venteflash/{id}")
	public VenteFlash findVentFlashById(@PathVariable("id") String id){
		return service.findVentFlashById(id).orElse(null);
	}
	


}
