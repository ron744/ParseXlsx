package dao;

import utils.ConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServiceConsultantDao {
    public void writeSerCons(String subDivision, int indexConsultant, Double taskNumber) {
        try (Connection connection = ConnUtil.getNewConnection()){
            int indexInc = ServiceConsultantDao.getIndex() + 1;
            PreparedStatement ps = connection.prepareStatement("insert into efko.service_consultant values (?, ?, ?, ?, ?)");
            ps.setString(1, String.valueOf(indexInc));
            ps.setString(2, String.valueOf(ServiceDao.getIndex()));
            ps.setString(3, String.valueOf(indexConsultant));
            ps.setString(4, String.valueOf(subDivision));
            ps.setString(5, String.valueOf(taskNumber));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getIndex() {
        try (Connection connection = ConnUtil.getNewConnection()){
            PreparedStatement ps = connection.prepareStatement("SELECT max(hasid) FROM efko.service_consultant");
            ResultSet resultSet = ps.executeQuery();
            int id = 0;
            while (resultSet.next()){
                id = resultSet.getInt(1);
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
