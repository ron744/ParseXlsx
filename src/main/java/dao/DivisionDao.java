package dao;

import utils.ConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class DivisionDao {
    public void writeDivision(String divisionName) {
        try (Connection connection = ConnUtil.getNewConnection()){
            int indexInc = DivisionDao.getIndex() + 1;
            PreparedStatement ps = connection.prepareStatement("insert into efko.division values (?, ?)");
            ps.setString(1, String.valueOf(indexInc));
            ps.setString(2, divisionName);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getIndex() {
        try (Connection connection = ConnUtil.getNewConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT divisionId FROM efko.division");
            int id = 0;
            while (resultSet.next()){
                id = resultSet.getInt("divisionid");
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
