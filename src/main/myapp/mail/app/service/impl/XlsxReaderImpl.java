package mail.app.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import mail.app.service.XlsxReader;
import mail.app.storage.DataStorage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class XlsxReaderImpl implements XlsxReader {
    private static final int PRIMARY_MAIL_COLUMN = 9;
    private static final int SECONDARY_MAIL_COLUMN = 10;
    private static final int NAME_COLUMN = 4;
    private static final int DISTRICT_COLUMN = 2;
    private File file;

    @Override
    public String getPrimaryMail(int lineInTable) {
        file = DataStorage.getFile();
        String primaryMail = "";
        try (InputStream inputStream = new FileInputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row = sheet.getRow(lineInTable + 1);
            if (row.getCell(PRIMARY_MAIL_COLUMN).getCellType() == null) {
                throw new RuntimeException("Column mail is empty " + file);
            }

            if (row.getCell(PRIMARY_MAIL_COLUMN).getCellType() == CellType.STRING) {
                primaryMail = row.getCell(PRIMARY_MAIL_COLUMN).getStringCellValue();
                return primaryMail;
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't find file " + file, e);
        }
        return primaryMail;
    }

    @Override
    public String getSecondaryMail(int lineInTable) {
        file = DataStorage.getFile();
        String secondaryMail = "";
        try (InputStream inputStream = new FileInputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row = sheet.getRow(lineInTable + 1);

            if (row.getCell(SECONDARY_MAIL_COLUMN).getCellType() == CellType.STRING) {
                secondaryMail = row.getCell(SECONDARY_MAIL_COLUMN).getStringCellValue();
                return secondaryMail;
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't find file " + file, e);
        }
        return secondaryMail;
    }

    @Override
    public String getName(int lineInTable) {
        file = DataStorage.getFile();
        String name = "";
        try (InputStream inputStream = new FileInputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row = sheet.getRow(lineInTable + 1);

            if (row.getCell(NAME_COLUMN).getCellType() == CellType.STRING) {
                name = row.getCell(NAME_COLUMN).getStringCellValue();
                String[] splitName = name.split(" ");
                name = splitName[0];
                return name;
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't find file " + file, e);
        }
        return name;
    }

    @Override
    public String getDistrict(int lineInTable) {
        file = DataStorage.getFile();
        String district = "";
        try (InputStream inputStream = new FileInputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row = sheet.getRow(lineInTable + 1);

            if (row.getCell(DISTRICT_COLUMN).getCellType() == CellType.STRING) {
                district = row.getCell(DISTRICT_COLUMN).getStringCellValue();
                return district;
            }
            if (row.getCell(DISTRICT_COLUMN).getCellType() == CellType.NUMERIC) {
                district = String.valueOf(Math.round(row.getCell(DISTRICT_COLUMN).getNumericCellValue()));
                return district;
            }

        } catch (Exception e) {
            throw new RuntimeException("Can't find file " + file, e);
        }
        return district;
    }

    @Override
    public int getNumberLineByMail(String mail) {
        file = DataStorage.getFile();
        mail = mail.replaceAll(" ", "");
        int lineNumber = 0;
        try (InputStream inputStream = new FileInputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(PRIMARY_MAIL_COLUMN);
                if (cell != null && cell.getStringCellValue().contains(mail)) {
                    int rowIndex = row.getRowNum();
                    int columnIndex = cell.getColumnIndex();
                    System.out.println("Cell found at row " + rowIndex + ", column " + columnIndex);
                    break;
                }
                lineNumber++;
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't find file " + file, e);
        }
        return lineNumber;
    }

    @Override
    public int getSizeofTable() {
        file = DataStorage.getFile();
        int size = 0;
        try (InputStream inputStream = new FileInputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            size = sheet.getLastRowNum() + 1;
        } catch (Exception e) {
            throw new RuntimeException("Can't find file " , e);
        }

        return size;
    }
}
