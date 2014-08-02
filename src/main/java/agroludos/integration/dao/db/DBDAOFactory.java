package agroludos.integration.dao.db;

import agroludos.integration.dao.config.ConfigurazioneDAODB;

public abstract class DBDAOFactory{
	private static DBDAOFactory dao;

	// --- Metodo di factory ---

	/**
	 * In base al parametro di input il metoro ritorna una delle possibili
	 * implementazioni di questo factory, basate sulla specifica
	 * dell'interfaccia DAOFactory
	 */
	public static DBDAOFactory getDAOFactory() {
		return dao;
	}

	// --- Factory specification: concrete factories implementing this spec must
	// provide this methods! ---

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Manager di
	 * ManagerDiCompetizione
	 */
	public abstract ManagerDiCompetizioneDAO getManagerDiCompetizioneDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Manager di
	 * ManagerDiSistema
	 */
	public abstract ManagerDiSistemaDAO getManagerDiSistemaDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Manager di
	 * Competizione
	 */
	public abstract CompetizioneDAO getCompetizioneDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Manager di
	 * Partecipante
	 */
	public abstract TipoCompetizioneDAO getTipoCompetizioneDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Manager di
	 * Partecipante
	 */
	public abstract PartecipanteDAO getPartecipanteDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Manager di
	 * Partecipante
	 */
	public abstract TipoOptionalDAO getTipoOptionalDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Manager di
	 * Partecipante
	 */
	public abstract OptionalDAO getOptionalDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Manager di
	 * Partecipante
	 */
	public abstract UtenteDAO getUtenteDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Manager di
	 * Partecipante
	 */
	public abstract ConfigurazioneDAODB getConfigurazioneDAO();	
}