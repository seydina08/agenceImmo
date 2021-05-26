package com.ut.sn.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ut.sn.models.Maison.Statut;

@Entity
@Table(name = "DEMANDE")
public class Demande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private long id;
	@Column(name = "PRENOM", nullable = false)
	private String prenom;
	@Column(name = "NOM", nullable = false)
	private String nom;
	@Column(name = "NUM_TELEPHONE", nullable = false)
	private String numTelephone;
	
	@ManyToOne
	@JoinColumn(name = "MAISON_ID")
	private Maison maison;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUT", nullable = false)
	private Statut statut = Statut.EN_ATTENTE;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the numTelephone
	 */
	public String getNumTelephone() {
		return numTelephone;
	}
	/**
	 * @param numTelephone the numTelephone to set
	 */
	public void setNumTelephone(String numTelephone) {
		this.numTelephone = numTelephone;
	}
	/**
	 * @return the maison
	 */
	public Maison getMaison() {
		return maison;
	}
	/**
	 * @param maison the maison to set
	 */
	public void setMaison(Maison maison) {
		this.maison = maison;
	}
	
	
	/**
	 * @return the statut
	 */
	public Statut getStatut() {
		return statut;
	}
	/**
	 * @param statut the statut to set
	 */
	public void setStatut(Statut statut) {
		this.statut = statut;
	}


	public enum Statut {
		EN_ATTENTE,
		REJETTE,
		VALIDE
	}
	
	
	
	
	
	
	
}
