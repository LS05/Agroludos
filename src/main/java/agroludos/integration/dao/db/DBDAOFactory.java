package agroludos.integration.dao.db;

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
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Manager di
	 * Competizione
	 */
	public CompetizioneDAO getCompetizioneDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Manager di
	 * Partecipante
	 */
	public TipoCompetizioneDAO getTipoCompetizioneDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Manager di
	 * Partecipante
	 */
	public PartecipanteDAO getPartecipanteDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Manager di
	 * Partecipante
	 */
	public TipoOptionalDAO getTipoOptionalDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Manager di
	 * Partecipante
	 */
	public OptionalDAO getOptionalDAO();

	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Manager di
	 * Partecipante
	 */
	public UtenteDAO getUtenteDAO();
	
	/**
	 * Metodo per ottenere il DATA ACCESS OBJECT per il tipo Iscrizione
	 * 
	 */
	public IscrizioneDAO getIscrizioneDAO();
}
