package CommonUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ReadExcelFile {

	public Map<String , String> getCellData(String filePath, String sheetName, int headerRowIndex , int dataRowIndex ){
		Map<String , String> dataset = new HashMap<String , String>();
		Sheet sheet = getSheet(filePath,sheetName);
		Row headerRow = sheet.getRow(headerRowIndex); 
		Row dataRow = sheet.getRow(dataRowIndex);
		
		int lastColumnIndex = headerRow.getLastCellNum();//.getLastCellNum( );
		
		for(int i=0 ; i<lastColumnIndex ; i++) {
			Cell headercell = headerRow.getCell(i);
			Cell dataCell = dataRow.getCell(i);
			dataset.put(getCellValue(headercell), getCellValue(dataCell));
		}
		return dataset;		
	}

	public Workbook getWorkbook(String filePath) {
		try (FileInputStream file = new FileInputStream(filePath);
				XSSFWorkbook workbook = new XSSFWorkbook(file)) {
			return workbook;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Sheet getSheet(String filePath, String sheetName) {
		Workbook workbook = getWorkbook(filePath );
		return workbook.getSheet(sheetName);
	}

	public String getCellValue(Cell cell) {
		String cellValue ;
		DataFormatter dataFormatter = new DataFormatter();
		switch (cell.getCellType()) {
		case STRING:
			cellValue = cell.getStringCellValue();
			break;
		case NUMERIC:
			cellValue = dataFormatter.formatCellValue(cell);;
			break;
		case BOOLEAN:
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case BLANK:
			cellValue = "";
			break;
		default:
			throw new UnsupportedOperationException("Unsupported cell type: " + cell.getCellType()); 
		}
		return cellValue;
	}
}
