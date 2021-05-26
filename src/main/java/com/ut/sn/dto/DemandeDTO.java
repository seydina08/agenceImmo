package com.ut.sn.dto;

public class DemandeDTO {
	private long id;
	private String prenom;
	private String nom;
	private String numTelephone;
	private MaisonDTO maison;
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
	public MaisonDTO getMaison() {
		return maison;
	}
	/**
	 * @param maison the maison to set
	 */
	public void setMaison(MaisonDTO maison) {
		this.maison = maison;
	}
	
	
}
