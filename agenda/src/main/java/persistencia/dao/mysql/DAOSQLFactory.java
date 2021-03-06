/**
 * 
 */
package persistencia.dao.mysql;

import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.DomicilioDAO;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.MedioTransporteDAO;
import persistencia.dao.interfaz.PaisDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.ProvinciaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;

public class DAOSQLFactory implements DAOAbstractFactory 
{
	/* (non-Javadoc)
	 * @see persistencia.dao.interfaz.DAOAbstractFactory#createPersonaDAO()
	 */
	public PersonaDAO createPersonaDAO() 
	{
			return new PersonaDAOSQL();
	}

	public DomicilioDAO createDomicilioDAO() {
		return new DomicilioDAOSQL();
	}

	public LocalidadDAO createLocalidadDAO() {
		return new LocalidadDAOSQL();
	}

	public ProvinciaDAO createProvinciaDAO() {
		return new ProvinciaDAOSQL();
	}

	public PaisDAO createPaisDAO() {
		return new PaisDAOSQL();
	}
	
	public TipoContactoDAO createTipoContactoDAO() {
		return new TipoContactoDAOSQL();
	}
	
	public MedioTransporteDAO createMedioTransporteDAO() {
	    return new MedioTransporteDAOSQL();
	}

}
