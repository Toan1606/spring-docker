package com.codedecode.demo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	static final int START = 24;
	
	public static void main(String[] args) {
		try {
			File file = new File("S:\\Algorithm\\districtByProvince.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				if(st.contains("district_name")) {
					int length = st.length();
					System.out.println(st.substring(START, length - 2));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
