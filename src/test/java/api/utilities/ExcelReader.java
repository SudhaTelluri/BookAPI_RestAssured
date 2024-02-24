package api.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

	public static int totalRow;
	String v_name;
	String v_email;
	String excelPath = "src\\test\\resources\\Authentication.xlsx";
	String sheetName="ClientRegister";

	public List<Map<String, String>> getData(String excelFilePath, String sheetName)
			throws EncryptedDocumentException, IOException {

		Workbook workbook = WorkbookFactory.create(new File(excelPath));
		Sheet sheet = workbook.getSheet(sheetName);
		workbook.close();
		return readSheet(sheet);

	}

	private List<Map<String, String>> readSheet(Sheet sheet) {
		Row row;
		Cell cell;
		totalRow = sheet.getLastRowNum();
		List<Map<String, String>> excelRows = new ArrayList<Map<String, String>>();
		for (int currentRow = 1; currentRow <= totalRow; currentRow++) {
			row = sheet.getRow(currentRow);
			int totalColumn = row.getLastCellNum();
			LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
			for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
				cell = row.getCell(currentColumn);
				String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn)
						.getStringCellValue();
				columnMapdata.put(columnHeaderName, cell.getStringCellValue());
			}
			excelRows.add(columnMapdata);
		}
		return excelRows;
	}

	public int countRow() {

		return totalRow;
	}

	public String getNameFromExcel(String sheetname, int rownumber) throws EncryptedDocumentException, IOException {
		ExcelReader reader = new ExcelReader();
		List<Map<String, String>> testdata = reader.getData(excelPath, sheetname);
		v_name = testdata.get(rownumber).get("name");
		return v_name;
	}
	
	public String getEmailFromExcel(String sheetname, int rownumber) throws EncryptedDocumentException, IOException  {
		ExcelReader reader = new ExcelReader();
		List<Map<String, String>> testdata = reader.getData(excelPath, sheetname);
		v_email = testdata.get(rownumber).get("email");
		return v_email;
	}
}
