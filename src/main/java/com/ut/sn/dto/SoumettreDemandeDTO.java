package com.ut.sn.dto;

public class SoumettreDemandeDTO {
	private String prenom;
	private String nom;
	private String numTelephone;
	private long idMaison;
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
	 * @return the idMaison
	 */
	public long getIdMaison() {
		return idMaison;
	}
	/**
	 * @param idMaison the idMaison to set
	 */
	public void setIdMaison(long idMaison) {
		this.idMaison = idMaison;
	}
	/**
	 * @param prenom
	 * @param nom
	 * @param numTelephone
	 */
	public SoumettreDemandeDTO(String prenom, String nom, String numTelephone) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.numTelephone = numTelephone;
	}
	
	
}
