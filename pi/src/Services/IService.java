/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author DeathKnight
 */
public interface IService<T> {
    public void ajouter(T o) throws SQLException;
    public void update(T o) throws SQLException;
    public void delete(int id) throws SQLException;
    public List<T> afficher() throws SQLException;
    
}
