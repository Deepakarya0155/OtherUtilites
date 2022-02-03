package office.excel.helper;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriterHelper {
	public static void main(String[] args) throws IOException {
		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Test");
		
		sheet.setColumnWidth(0, 6000);
		sheet.setColumnWidth(1, 6000);
		sheet.setColumnWidth(2, 6000);
		sheet.setColumnWidth(3, 6000);

		Row row=sheet.createRow(0);
		Row row2=sheet.createRow(1);
		
		CreateCellWithStyle c1=new CreateCellWithStyle(workbook,row);
		c1.setBorder(BorderStyle.THIN);
		c1.setAlignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
		
		
		c1.createCell(0, "Deepak");
		c1.createCell(row2,1, "Arya");
		
		
		
		
	
		FileOutputStream Fout=new FileOutputStream("temp.xlsx");
		workbook.write(Fout);
		workbook.close();
		Fout.close();
		
		
	}




	
	
}
