package agroludos.integration.dao.db;

import agroludos.to.UtenteTO;

public interface DBDAOFactory {

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Manager di
	 * ManagerDiCompetizione
	 */
	public ManagerDiCompetizioneDAO getManagerDiCompetizioneDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Manager di
	 * ManagerDiSistema
	 */
	public ManagerDiSistemaDAO getManagerDiSistemaDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Competizione
	 */
	public CompetizioneDAO getCompetizioneDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Tipo Competizione
	 */
	public TipoCompetizioneDAO getTipoCompetizioneDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Partecipante
	 */
	public PartecipanteDAO getPartecipanteDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Tipo Optional
	 */
	public TipoOptionalDAO getTipoOptionalDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Optional
	 */
	public OptionalDAO getOptionalDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Utente
	 */
	public UtenteDAO<UtenteTO> getUtenteDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Iscrizione
	 */
	public IscrizioneDAO getIscrizioneDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo StatoCompetizione
	 */
	public StatoCompetizioneDAO getStatoCompetizioneDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Stato Iscrizione
	 */
	public StatoIscrizioneDAO getStatoIscrizioneDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Stato Optional
	 */
	public StatoOptionalDAO getStatoOptionalDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Stato Utente
	 */
	public StatoUtenteDAO getStatoUtenteDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Tipo Utente
	 */
	public TipoUtenteDAO getTipoUtenteDAO();
}