package office.excel.helper;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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

		
		CellStyle cellstyle1=createCellStyle(workbook, IndexedColors.LIGHT_BLUE, FillPatternType.SOLID_FOREGROUND,HorizontalAlignment.CENTER);
		
		XSSFFont font=createFont(workbook, "Arial", 16, true, false,IndexedColors.GOLD);
		
		cellstyle1.setFont(font);
		
		
		
		Row currentrow=sheet.createRow(0);
		
		createCell(currentrow, 1, "test", cellstyle1);
		
		
		
		sheet.addMergedRegion(CellRangeAddress.valueOf("B1:D1"));
	
		FileOutputStream Fout=new FileOutputStream("temp.xlsx");
		workbook.write(Fout);
		workbook.close();
		Fout.close();
		
		
	}

//	CellStyle cellstyle1=createCellStyle(workbook, IndexedColors.LIGHT_BLUE, FillPatternType.SOLID_FOREGROUND,HorizontalAlignment.CENTER);
	public static  CellStyle createCellStyle(Workbook workbook,IndexedColors code, FillPatternType pattern, HorizontalAlignment alignment) {
		CellStyle headerStyle = workbook.createCellStyle();
		
		if(code!=null) {
			headerStyle.setFillForegroundColor(code.getIndex());
		}
		
		if(pattern!=null) {
			headerStyle.setFillPattern(pattern);
		}
		if(alignment!=null) {
			headerStyle.setAlignment(alignment);
		}
		return headerStyle;
	}
//	XSSFFont font=createFont(workbook, "Arial", 16, true, false,IndexedColors.GOLD);
	public static XSSFFont createFont(Workbook workbook,String fontname,int size,Boolean bold,Boolean itatlic,IndexedColors color) {
		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName(fontname);
		font.setFontHeightInPoints((short)size);
		font.setBold(bold);
		font.setItalic(itatlic);
		if(color !=null) {
			font.setColor(color.getIndex());
		}
		
		return font;
	}
//	createCell(currentrow, 1, "test", cellstyle1);
	public static Cell createCell(Row row,Integer index,String value,CellStyle style) {
		Cell cell = row.createCell(index);
		cell.setCellValue(value);
		if(style!=null) {
			cell.setCellStyle(style);
		}
		
		return cell;
	}

	
	
}
