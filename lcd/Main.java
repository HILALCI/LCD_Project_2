package lcd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		try {
			File f = new File("karnaugh.txt");
			//File f = new File("karnaugh2.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			String degiskenler = br.readLine();
			
			ArrayList<Integer> kdg = new ArrayList<Integer>();
			String satir = "";
			br.readLine();
			System.out.println();
			
			while ((satir = br.readLine()) != null) {
				List<String> dg = Arrays.asList(satir.split("\\s* \\s*"));
				for (int j = 0; j < dg.size(); j++) {
					kdg.add(Integer.parseInt(dg.get(j)));
				}
			}
			
			br.close();
			
			degiskenler = degiskenler.substring(degiskenler.indexOf(":")+1, degiskenler.length());
			List<String> dlist = Arrays.asList(degiskenler.split("\\s*,\\s*"));
			//System.out.println(""+dlist.get(2));
			
			System.out.println(""+f.getName()+" dosyasi okundu.\n\nKarnaugh Diyagrami:");
			
			for (int i = 0; i < dlist.size(); i++) {
				System.out.print(""+dlist.get(i)+" ");
			}
			
			System.out.println();
			
			for (int m = 0; m < kdg.size(); m++) {
				if (m != 0 && m % 4 == 0) {
					System.out.println();
				}
				System.out.print(""+kdg.get(m)+" ");
			}
			
			
			
			BagliListe kt = new BagliListe();
			int[][] dk3 = {{0,0,0},{0,0,1},{0,1,1},{0,1,0},{1,0,0},{1,0,1},{1,1,1},{1,1,0}};
			int[][] dk4 = {{0,0,0,0},{0,0,0,1},{0,0,1,1},{0,0,1,0},{0,1,0,0},{0,1,0,1},{0,1,1,1},{0,1,1,0},{1,1,0,0},{1,1,0,1},{1,1,1,1},{1,1,1,0},{1,0,0,0},{1,0,0,1},{1,0,1,1},{1,0,1,0}};
			
			String [] mint3 = {"m0","m1","m3","m2","m4","m5","m7","m6"};
			String [] mint4 = {"m0","m1","m3","m2","m4","m5","m7","m6","m12","m13","m15","m14","m8","m9","m11","m10"}; 
			
			String b = "";
			
			if (dlist.size() == 3) {
				for (int k = 0; k < Math.pow(2, dlist.size()); k++) {
					for (int l = 0; l < 3; l++) {
						if (dk3[k][l] == 0) {
							b += dlist.get(l)+"'"; 
						}
						else {
							b += dlist.get(l);
						}
					}
					kt.append(b, mint3[k], kdg.get(k));
					b = "";
				}
			}
			
			else if (dlist.size() == 4) {
				for (int k = 0; k < Math.pow(2, dlist.size()); k++) {
					for (int l = 0; l < 4; l++) {
						if (dk4[k][l] == 0) {
							b += dlist.get(l)+"'"; 
						}
						else {
							b += dlist.get(l);
						}
					}
					kt.append(b,mint4[k], kdg.get(k));
					b = "";
				}
			}
			
			System.out.println("\n");
			System.out.println("Dogruluk Tablosu:");
			for (int x = 0; x < dlist.size(); x++) {
				System.out.print(dlist.get(x));
				if (x != dlist.size()-1) {
					System.out.print(" ");
				}
			}System.out.print("| F\n");
			
			for (int v = 1; v <= kt.returnKayitSayisi(); v++) {
				for (int c = 1; c < kt.blok(v).length(); c++) {
					if ( (c+1) < kt.blok(v).length() &&  kt.blok(v).charAt(c+1) == '\'') {
						System.out.print("0 ");
						c += 1;
					}
					else if (kt.blok(v).charAt(c) != ' ') {
						System.out.print("1 ");
					}
				}
				System.out.print("| "+ kt.durum(v));
				System.out.println();
			}
			System.out.println("\nFonksiyon Ifadeleri:");
			
			ArrayList<String> fl = new ArrayList<String>();
			ArrayList<String> fld = new ArrayList<String>();
			fl = kt.durumlar(1);
			fld = kt.durumlar(0);
			
			ArrayList<String> ktm = kt.minterm();
			System.out.print("F"+ dlist+" = E(");
			for (int i=0; i< ktm.size(); i++) {
				System.out.print(""+ ktm.get(i).substring(1));
				if (i != (ktm.size() - 1)){
					System.out.print(" + ");
				}
				else {
					System.out.print(")");
				}
			}
			System.out.println();
			
			ArrayList<String> ktmx = kt.maxterm();
			System.out.print("F"+ dlist+" = |-|(");
			for (int i=0; i< ktmx.size(); i++) {
				System.out.print(""+ ktmx.get(i).substring(1));
				if (i != (ktmx.size() - 1)){
					System.out.print(" + ");
				}
				else {
					System.out.print(")");
				}
			}
			System.out.println("\n");
			
			System.out.print("F = ");
			for (int m = 0; m < fl.size(); m++) {
				System.out.print(fl.get(m));
				if (m != (fl.size() - 1)) {
					System.out.print(" +" );
				}
			}
			System.out.println();
			
			System.out.print("F = ");
			
			for (int i = 0; i < ktm.size() ; i++) {
				System.out.print(ktm.get(i));
				if (i != (ktm.size() - 1)){
					System.out.print(" + ");
				}
			}
			System.out.println();
			
			System.out.print("F' = ");
			for (int m = 0; m < fl.size(); m++) {
				System.out.print("( ");
				for (int n =1; n < fl.get(m).length(); n++) {
					if ( (n+1) < fl.get(m).length() && fl.get(m).charAt(n+1) == '\'') {
						System.out.print(fl.get(m).charAt(n));
						n+=1;
					}
					else {
						System.out.print(fl.get(m).charAt(n)+"'");
					}
					if (n+1 != fl.get(m).length()) {
						System.out.print("+");
					}
					
				}
				System.out.print(" )");
				
				if (m != (fl.size() - 1)) {
					System.out.print(" . " );
				}
			}
			System.out.println();
			
			System.out.print("F' = ");
			for (int i = 0; i < ktm.size() ; i++) {
				System.out.print(ktm.get(i).toUpperCase());
				if (i != (ktm.size() - 1)){
					System.out.print(" . ");
				}
			}
			System.out.println();
			
			System.out.println();
			
			
			System.out.print("F = ");
			for (int m = 0; m < fld.size(); m++) {
				System.out.print("( ");
				for (int n = 1; n < fld.get(m).length(); n++) {
					if ( (n+1) < fld.get(m).length() && fld.get(m).charAt(n+1) == '\'') {
						System.out.print(fld.get(m).charAt(n)+"'");
						if (n+2 != fld.get(m).length()) {
							System.out.print("+");
						}
						n+=1;
					}
					else {
						System.out.print(fld.get(m).charAt(n));
						if (n+2 != fld.get(m).length()) {
							System.out.print("+");
						}
					}
					
				}
				System.out.print(" )");
				if (m != (fld.size() - 1)) {
					System.out.print(" . " );
				}
			}
			System.out.println();
			
			System.out.print("F = ");
			for (int i = 0; i < kt.maxterm().size(); i++) {
				System.out.print(kt.maxterm().get(i));
				if (i != kt.maxterm().size() - 1) {
					System.out.print(" . ");
				}
			}
			System.out.println();
			
			
			System.out.print("F' = ");
			for (int m = 0; m < fld.size(); m++) {
				for (int n = 1; n < fld.get(m).length(); n++) {
					if ( (n+1) < fld.get(m).length() && fld.get(m).charAt(n+1) == '\'') {
						System.out.print(fld.get(m).charAt(n));
						n+=1;
					}
					else {
						System.out.print(fld.get(m).charAt(n)+"'");
					}
					
				}
				if (m != (fld.size() - 1)) {
					System.out.print(" + ");
				}
			}
			System.out.println();
			
			System.out.print("F' = ");
			for (int i = 0; i < kt.maxterm().size(); i++) {
				System.out.print(kt.maxterm().get(i).toLowerCase());
				if (i != kt.maxterm().size() - 1) {
					System.out.print(" + ");
				}
			}
			System.out.println();
			
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
