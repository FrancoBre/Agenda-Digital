package persistencia.dao.interfaz;


public interface DAOAbstractFactory 
{
	public PersonaDAO createPersonaDAO();
	public TipoContactoDAO createTipoContactoDAO();
	public DomicilioDAO createDomicilioDAO();
	public LocalidadDAO createLocalidadDAO();
	public ProvinciaDAO createProvinciaDAO();
	public PaisDAO createPaisDAO();
	public MedioTransporteDAO createMedioTransporteDAO();
}
