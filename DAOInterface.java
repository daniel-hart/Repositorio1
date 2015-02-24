package son.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAOInterface {
	public ArrayList<String> getCellList() throws SQLException;
	public boolean existCell(String cellName) throws SQLException;

}
