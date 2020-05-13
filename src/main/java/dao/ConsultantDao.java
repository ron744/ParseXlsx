package dao;

import model.ResultObject;
import utils.ConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConsultantDao {
    public int writeConsultant(String consultantName) {
        int indexConsultant = ConsultantDao.checkUniq(consultantName);
        if (indexConsultant < 0) {
            try (Connection connection = ConnUtil.getNewConnection()){
                int indexInc = ConsultantDao.getIndex() + 1;
                PreparedStatement ps = connection.prepareStatement("insert into efko.consultant values (?, ?)");
                ps.setString(1, String.valueOf(indexInc));
                ps.setString(2, consultantName);
                ps.executeUpdate();
                return indexInc;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return indexConsultant;
    }

    public static int getIndex() {
        try (Connection connection = ConnUtil.getNewConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT consultantid FROM efko.consultant");
            int id = 0;
            while (resultSet.next()){
                id = resultSet.getInt("consultantid");
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int checkUniq(String consultantName) {
        try (Connection connection = ConnUtil.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("select consultantid from efko.consultant where consultantName = ?");
            ps.setString(1, consultantName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static List<ResultObject> addConsultantName() {
        List<ResultObject> list = new ArrayList<>();
        try (Connection connection = ConnUtil.getNewConnection()) {
            Statement statement = connection.createStatement();
            try (ResultSet rs1 = statement.executeQuery("select * from efko.consultant")) {
                while (rs1.next()) {
                    list.add(new ResultObject(rs1.getString("consultantName")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
