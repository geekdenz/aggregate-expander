/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.heuer.cordspulse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 *
 * @author informatics-palmerson
 */
public class Main {

	public static void main(String args[]) throws FileNotFoundException, IOException {
		System.out.println("Hello Tim");
		args = new String[2];
		args[0] = "sample.csv";
		args[1] = "sample.out.csv";
		if (args.length != 2) {
			System.out.println("Usage: java -jar program input output");
			System.exit(1);
		}
		File file = new File(args[0]);
		PrintWriter out = new PrintWriter(args[1]);
		InputStream is = new FileInputStream(file);
		StringBuilder builder = new StringBuilder();
		int ch;
		while ((ch = is.read()) != -1) {
			builder.append((char) ch);
		}

		//System.out.println(builder.toString());
		int i = 0;
		for (String line : builder.toString().split(""+'\n')) {
			if (i > 0) {
				String row[] = line.split(",");
				int pregnant = Integer.parseInt(row[6]);
				int total = Integer.parseInt(row[7]);
				int zero = total - pregnant;
				for (int j = 0; j < zero; ++j) {
					row[6] = "0";
					out.println(String.join(",", row));
				}
				for (int j = 0; j < pregnant; ++j) {
					row[6] = "1";
					out.println(String.join(",", row));
				}
			}
			i++;
		}
		out.close();
	}
}
