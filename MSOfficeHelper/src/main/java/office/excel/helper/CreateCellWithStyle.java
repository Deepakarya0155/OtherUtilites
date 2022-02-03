package office.excel.helper;

import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateCellWithStyle {
	
	Workbook workbook;
	Row row;
	
	

	
	XSSFFont font;
	CellStyle style;
	
	CreateCellWithStyle(Workbook workbook,Row row){
		this.workbook=workbook;
		this.row=row;
		this.font = ((XSSFWorkbook) workbook).createFont();
		style = workbook.createCellStyle();
		style.setFont(font);
	}
	CreateCellWithStyle(Workbook workbook){
		this.workbook=workbook;
		this.font = ((XSSFWorkbook) workbook).createFont();
		style = workbook.createCellStyle();
		style.setFont(font);
	}
	
	public void setfont(String fontName,Boolean bold,Boolean italic,IndexedColors color) {
		
		this.font.setFontName(fontName!=null?"Arial":fontName);
		this.font.setBold(bold?bold:false);
		this.font.setItalic(italic?italic:false);
		this.font.setColor(color!=null?color.getIndex():color.getIndex());
		
				
	}

	
	
	public  void createCell(Integer index,String text) {
		Cell cell = row.createCell(index);
		cell.setCellValue(text);
		cell.setCellStyle(style);
	}
	public  void createCell(Row row,Integer index,String text) {
		
		Cell cell = row.createCell(index);
		cell.setCellValue(text);
		cell.setCellStyle(style);
	}
	
	public void setFillBackground(IndexedColors colors,FillPatternType type) {
		this.style.setFillBackgroundColor(colors.getIndex());
		this.style.setFillPattern(type);
	}
	public void setBorder(BorderStyle style) {
		this.style.setBorderBottom(style);
		this.style.setBorderTop(style);
		this.style.setBorderLeft(style);
		this.style.setBorderRight(style);
	}
	public void setAlignment(HorizontalAlignment ha,VerticalAlignment va) {
		this.style.setAlignment(ha);
		this.style.setVerticalAlignment(va);
	}
	

	
}
