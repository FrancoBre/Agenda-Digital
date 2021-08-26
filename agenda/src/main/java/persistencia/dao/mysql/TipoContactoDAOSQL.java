package persistencia.dao.mysql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumMap;

import dto.TipoContacto;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoContactoDAO;

public class TipoContactoDAOSQL implements TipoContactoDAO {

    private static final String readAll = "SELECT * FROM tipo_contacto;";
    /*
     * private static final String readIdByNombre =
     * "SELECT idTipo_contacto FROM tipo_contacto WHERE Tipo = ?;"; private static
     * final String readNombreById =
     * "SELECT nombre FROM tipo_contacto WHERE idTipo_contacto = ?;";
     */

    public EnumMap<TipoContacto, Integer> readAll() {
	PreparedStatement statement;
	ResultSet resultSet;
	EnumMap<TipoContacto, Integer> ret = new EnumMap<TipoContacto, Integer>(TipoContacto.class);
	Conexion conexion = Conexion.getConexion();
	try {
	    statement = conexion.getSQLConexion().prepareStatement(readAll);
	    resultSet = statement.executeQuery();
	    while (resultSet.next()) {

		ret.put(getTipoContacto(resultSet), getIdTipoContacto(resultSet));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return ret;
    }

    private TipoContacto getTipoContacto(ResultSet resultSet) throws SQLException {

	TipoContacto ret = TipoContacto.valueOf(resultSet.getString("tipo"));
	return ret;
    }

    private Integer getIdTipoContacto(ResultSet resultSet) throws SQLException {
	int ret = resultSet.getInt("idTipo_contacto");
	return ret;
    }

    /*
     * private TipoContactoDTO getTipoContactoByName(String name) {
     * ArrayList<TipoContactoDTO> tipos = new ArrayList<TipoContactoDTO>();
     * 
     * TipoContactoDTO dos = TipoContactoDTO.Amigo; TipoContactoDTO tres =
     * TipoContactoDTO.Trabajo; TipoContactoDTO cuatro = TipoContactoDTO.Futbol;
     * 
     * tipos.add(dos); tipos.add(tres); tipos.add(cuatro);
     * 
     * for (TipoContactoDTO tipo : tipos) { if (tipo.name().equals(name)) return
     * tipo; }
     * 
     * return null; }
     * 
     * public int readIdByNombre(String nombre) { PreparedStatement statement;
     * ResultSet resultSet; int idTipoContacto = 0; Conexion conexion =
     * Conexion.getConexion(); try { statement =
     * conexion.getSQLConexion().prepareStatement(readIdByNombre);
     * statement.setString(1, nombre); resultSet = statement.executeQuery(); if
     * (resultSet.next()) idTipoContacto = resultSet.getInt("idTipo_contacto"); }
     * catch (SQLException e) { e.printStackTrace(); } return idTipoContacto; }
     * 
     * @Override public String readNombreById(int id) { PreparedStatement statement;
     * Connection conexion = Conexion.getConexion().getSQLConexion(); ResultSet
     * resultSet; String ret = ""; try { statement =
     * conexion.prepareStatement(readNombreById); statement.setInt(1, id); resultSet
     * = statement.executeQuery(); if (resultSet.next()) ret =
     * resultSet.getString("Nombre");
     * 
     * } catch (SQLException e) { e.printStackTrace(); } return ret; }
     */

}
