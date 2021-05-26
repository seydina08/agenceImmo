package com.ut.sn.models;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Maison {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private long id;
	@Column(name = "ADRESSE", nullable = false)
	private String adresse;
	@Column(name = "NBRE_CHAMBRE", nullable = false)
	private int nbreChambre;
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUT", nullable = false)
	private Statut statut = Statut.DISPONIBLE;
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
	 * @return the nbreChambre
	 */
	public int getNbreChambre() {
		return nbreChambre;
	}
	/**
	 * @param nbreChambre the nbreChambre to set
	 */
	public void setNbreChambre(int nbreChambre) {
		this.nbreChambre = nbreChambre;
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
		DISPONIBLE,
		PRISE,
		SUPPRIME
	}
}
