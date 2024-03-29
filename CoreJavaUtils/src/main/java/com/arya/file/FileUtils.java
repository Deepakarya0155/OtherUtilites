package com.arya.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class FileUtils {
	
	
//	public static void main(String...args) throws IOException {
//		String[] data=new String[] {"Deepak","Arya"};
//		writeCSVFile("data.csv", Arrays.asList(data,data));
//		readCSVFile("data.csv").forEach(S->{System.out.println(S[0]+" : "+S[1]);});
//	}
		
	
	public static Optional<List<String>> readFile(String pathWithFile) throws IOException{
		File file=new File(pathWithFile);
		List<String> ls=null;
		
		if(file.exists()) {

			try(BufferedReader reader=new BufferedReader(new FileReader(file))){
				
				ls=reader.lines().toList();
		
			}catch(Exception e) {
				System.out.println("Exception while file reading...");
			}

		}else {
			System.out.println("File Not Found at "+pathWithFile);
		}
		
		return Optional.of(ls);
	}
	public static void writeFile(String pathWithFile,List<String> ls) throws IOException{
		File file=new File(pathWithFile);
		
		
			try(BufferedWriter writer=new BufferedWriter(new FileWriter(file))){
				ls.forEach(S->{
					try {
						writer.write(S);
						writer.newLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				});
			}catch(Exception e) {
				System.out.println("Exception while file reading...");
			}
			
		
	}
	
	
//	<artifactId>opencsv</artifactId> depancacy
	public static void writeCSVFile(String pathWithFile,List<String[]> ls) throws IOException{
			File file=new File(pathWithFile);
			try(CSVWriter writer=new CSVWriter(new FileWriter(file))){
				
						writer.writeAll(ls);
				
			}catch(Exception e) {
				System.out.println("Exception while file reading...");
			}
	}
	
	
//	<artifactId>opencsv</artifactId> depancacy
	public static List<String[]> readCSVFile(String pathWithFile) {
			File file=new File(pathWithFile);
			List<String[]> ls=null;
			try(CSVReader reader=new CSVReader(new FileReader(file))){
				ls=reader.readAll();
			}catch(Exception e) {
				System.out.println("Exception while file reading...");
			}
			return ls;
	}
	
	public static List<String[]> readCSVFile(File file) {
		
		List<String[]> ls=null;
		try(CSVReader reader=new CSVReader(new FileReader(file))){
			ls=reader.readAll();
		}catch(Exception e) {
			System.out.println("Exception while file reading...");
		}
		return ls;
	}
	
	public static List<String[]> readCSVFile(InputStream inpuStream) {
		List<String[]> ls=null;
		try(CSVReader reader=new CSVReader(new InputStreamReader(inpuStream))){
			ls=reader.readAll();
		}catch(Exception e) {
			System.out.println("Exception while file reading...");
		}
		return ls;
	}
	
	
	public static <T> Optional<List<T>> readCSVtoBean(InputStream inputSteam,Class<T> cls) {
		
		List<T> ls=null;
		
		CsvToBean<T> ctb=null;
		try (InputStreamReader reader=new InputStreamReader(inputSteam)){
			ctb=new CsvToBeanBuilder<T>(reader)
					.withType(cls).build();
			
			ls=new ArrayList<>();
			ctb.forEach(ls::add);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.ofNullable(ls);
	}
	
}
