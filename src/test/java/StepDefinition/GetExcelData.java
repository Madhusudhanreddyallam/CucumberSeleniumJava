package StepDefinition;

import java.util.Map;
import java.util.Map.Entry;

import CommonUtility.ReadExcelFile;

public class GetExcelData {

	public static void main(String[] args) {
		String filePath = "src/test/resources/TestData/prodigylabs_TestData.xlsx"; 
		String sheetName = "Contact";
		ReadExcelFile file = new ReadExcelFile();
		Map<String , String> exceldata = file.getCellData(filePath, sheetName , 3 , 4);
		for(Entry<String , String> data : exceldata.entrySet()) {
			System.out.println(data.getKey() +" - "+ data.getValue());
		}
	}
}
