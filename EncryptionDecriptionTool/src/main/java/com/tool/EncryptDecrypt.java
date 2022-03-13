package com.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptDecrypt {

	private static final String SALT = "my_super_secret_key";

	public static void main(String[] args)
			throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, IOException {
		
		Scanner scan = new Scanner(System.in);
		String SECRET_KEY = input("Enter Your password", scan);
		String choise=null;
		if(args.length==0) {
			choise = input("E / D for Encryption / Decryption", scan);
		}else {
			choise=args[0].trim();
		}
		
		List<String> filedata=readFile("InputFile").get();
		List<String> output=new ArrayList<String>();
		
		for(String S:filedata) {
			try {
				String response=choise.trim().equalsIgnoreCase("e")?encode(SECRET_KEY, SALT, S):decode(SECRET_KEY, SALT, S);
				output.add(response);
			} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException
					| InvalidAlgorithmParameterException | NoSuchAlgorithmException | InvalidKeySpecException
					| NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		writeFile("outfile", output);
		
	}
	
	public static Optional<List<String>> readFile(String pathWithFile) throws IOException{
		File file=new File(pathWithFile);
		List<String> ls=null;
		if(file.exists()) {
			try(BufferedReader reader=new BufferedReader(new FileReader(file))){
				ls=reader.lines().collect(Collectors.toList());
			}catch(Exception e) {
				System.out.println("Exception while file reading...");
			}
		}else {
			System.out.println("File Not Found at "+pathWithFile);
		}
		return Optional.of(ls);
	}

	public static String input(String message, Scanner scan) {
		System.out.println(message);
		return scan.nextLine();
	}

	public static SecretKeySpec generateKey(String password, String salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		SecretKeyFactory secretKeyFactor = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(password.toCharArray(), SALT.getBytes(), 65536, 256);
		SecretKeySpec secret = new SecretKeySpec(secretKeyFactor.generateSecret(spec).getEncoded(), "AES");
		return secret;
	}

	public static IvParameterSpec generateIv() {
		byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		return new IvParameterSpec(iv);
	}

	public static String encode(String password, String salt, String message) throws IllegalBlockSizeException,
			BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException {
		SecretKeySpec secret = generateKey(password, salt);
		IvParameterSpec ivParameterSpec = generateIv();

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secret, ivParameterSpec);
		byte[] bt = cipher.doFinal(message.getBytes());

		String ss = new String(Base64.getEncoder().encode(bt));
		return ss;
	}

	public static String decode(String password, String salt, String message) throws IllegalBlockSizeException,
			BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException {

		Base64.getDecoder().decode(message);

		SecretKeySpec secret = generateKey(password, salt);
		IvParameterSpec ivParameterSpec = generateIv();

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secret, ivParameterSpec);
		byte[] bt = cipher.doFinal(Base64.getDecoder().decode(message));

		String ss = new String(bt);
		return ss;
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
}
