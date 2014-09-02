package agroludos.integration.dao.db;

public interface DBDAOFactory {
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
	public abstract DBConfigurazioneDAO getConfigurazioneDAO();	
	
	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Iscrizione
	 * 
	 */
	public abstract IscrizioneDAO getIscrizioneDAO();
}
