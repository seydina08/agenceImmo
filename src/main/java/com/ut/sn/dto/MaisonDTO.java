package com.ut.sn.dto;

public class MaisonDTO {
  private long id;
  private String adresse;
  private int nbreChambres;
  private String statut;
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
 * @return the adresse
 */
public String getAdresse() {
	return adresse;
}
/**
 * @param adresse the adresse to set
 */
public void setAdresse(String adresse) {
	this.adresse = adresse;
}
/**
 * @return the nbreChambres
 */
public int getNbreChambres() {
	return nbreChambres;
}
/**
 * @param nbreChambres the nbreChambres to set
 */
public void setNbreChambres(int nbreChambres) {
	this.nbreChambres = nbreChambres;
}
/**
 * @return the statut
 */
public String getStatut() {
	return statut;
}
/**
 * @param statut the statut to set
 */
public void setStatut(String statut) {
	this.statut = statut;
}
  
  
}
