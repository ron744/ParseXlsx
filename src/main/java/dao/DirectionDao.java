package dao;

import utils.ConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DirectionDao {
    public void writeDirection(String directionName) {
        try (Connection connection = ConnUtil.getNewConnection()){
            int indexInc = DirectionDao.getIndex() + 1;
            PreparedStatement ps = connection.prepareStatement("insert into efko.direction values (?, ?, ?)");
            ps.setString(1, String.valueOf(indexInc));
            ps.setString(2, directionName);
            ps.setString(3, String.valueOf(DivisionDao.getIndex()));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getIndex() {
        try (Connection connection = ConnUtil.getNewConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT directionid FROM efko.direction");
            int id = 0;
            while (resultSet.next()){
                id = resultSet.getInt("directionid");
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
