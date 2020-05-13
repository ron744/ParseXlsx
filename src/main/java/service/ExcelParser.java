package service;

import dao.*;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExcelParser {

    private static final String fileName = "data.xlsx";
    private static final String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + fileName;

    public static void parse() {
        InputStream inputStream = null;
        XSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream(filePath);
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = null;
        int lastRowNum = 0;
        if (workBook != null) {
            sheet = workBook.getSheetAt(0);
            lastRowNum = sheet.getLastRowNum();
        }

        for (int i = 2; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            String subDivisionValue = null;
            int indexConsultant = -1;

            for (Cell cell : row) {
                CellType cellType = cell.getCellType();
                int columnIndex = cell.getColumnIndex();

                if (cellType == CellType.STRING) {
                    String cellValue = cell.getStringCellValue();

                    switch (columnIndex) {
                        case 0:
                            DivisionDao divisionDao = new DivisionDao();
                            divisionDao.writeDivision(cellValue);
                            break;
                        case 1:
                            DirectionDao directionDao = new DirectionDao();
                            directionDao.writeDirection(cellValue);
                            break;
                        case 2:
                            ServiceDao serviceDao = new ServiceDao();
                            serviceDao.writeService(cellValue);
                            break;
                        case 3:
                            subDivisionValue = cellValue;
                            break;
                        case 4:
                            ConsultantDao consultantDao = new ConsultantDao();
                            indexConsultant = consultantDao.writeConsultant(cellValue);
                            break;
                    }
                } else if (cellType == CellType.NUMERIC) {
                    ServiceConsultantDao servConDao = new ServiceConsultantDao();
                    servConDao.writeSerCons(subDivisionValue, indexConsultant, cell.getNumericCellValue());
                }
            }
        }
    }
}
