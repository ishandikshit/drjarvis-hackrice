package com.luminosity.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GG {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Hello World!");
		
		int x = 0;
		int y = 0;
		Scanner scanner = new Scanner(new File("/Users/ishandikshit/Downloads/movements.csv"));
		Scanner scanner2 = new Scanner(new File("/Users/ishandikshit/Downloads/map.csv"));
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
			String a = scanner.next();

			// if(scanner.next().equals("N"))
			// y=y-1;
			// if(scanner.next().equals("S"))
			// y=y+1;
			// if(scanner.next().equals("E"))
			// x=x+1;
			// if(scanner.next().equals("W"))
			// x=x-1;
			// System.out.print("("+x+", "+y+") ");
			// System.out.print(scanner.next());
			if (a.equals("N")) {
				x = x - 1;
			} else if (a.equals("S")) {
				x = x + 1;
			} else if (a.equals("E")) {
				y = y + 1;
			} else if (a.equals("W")) {
				y = y - 1;
			}
			// }
			System.out.println(" (" + y + "," + x + ") ");
		}
		scanner.close();
	}

}
