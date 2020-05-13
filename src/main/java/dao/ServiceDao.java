package dao;

import utils.ConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ServiceDao {
    public void writeService(String serviceName) {
        try (Connection connection = ConnUtil.getNewConnection()){
            int indexInc = ServiceDao.getIndex() + 1;
            PreparedStatement ps = connection.prepareStatement("insert into efko.service values (?, ?, ?)");
            ps.setString(1, String.valueOf(indexInc));
            ps.setString(2, serviceName);
            ps.setString(3, String.valueOf(DirectionDao.getIndex()));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getIndex() {
        try (Connection connection = ConnUtil.getNewConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT serviceid FROM efko.service");
            int id = 0;
            while (resultSet.next()){
                id = resultSet.getInt("serviceid");
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
