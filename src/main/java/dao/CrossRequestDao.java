package dao;

import model.ResultObject;
import utils.ConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CrossRequestDao {
    public static List<ResultObject> toJson(List<ResultObject> list) {
        try (Connection connection = ConnUtil.getNewConnection()) {
            for (ResultObject j : list) {
                String consultantName = j.getName();
                PreparedStatement ps = connection.prepareStatement("SELECT serviceName, tasksNumber FROM (efko.consultant join efko.service_consultant using (consultantId)) " +
                        "join efko.service using (serviceId) where consultantName = ?");
                ps.setString(1, consultantName);
                List<String> serviceList = new ArrayList<>();
                double sum = 0;
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        serviceList.add(rs.getString("serviceName"));
                        sum += rs.getDouble("tasksNumber");
                    }
                    j.setSumTasksNumber(sum);
                    j.setServiceList(serviceList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
