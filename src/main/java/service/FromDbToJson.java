package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.ConsultantDao;
import dao.CrossRequestDao;
import model.ResultObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FromDbToJson {

    private static final String fileName = "result.txt";
    private static final String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + fileName;

    private static String parse() {
        List<ResultObject> list = CrossRequestDao.toJson(ConsultantDao.addConsultantName());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(list);
    }

    public static void toFile() throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, Collections.singleton(FromDbToJson.parse()), StandardCharsets.UTF_8);
    }
}
