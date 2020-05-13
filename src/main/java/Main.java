import service.FromDbToJson;
import service.ExcelParser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ExcelParser.parse();

        FromDbToJson.toFile();
    }
}
