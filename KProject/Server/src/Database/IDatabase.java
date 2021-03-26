package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
public interface IDatabase
{
    public void insert(String sql);
    public void delete(String sql);
    public void update(String sql);
    public ResultSet select(String sql);
    public void close ();
}
