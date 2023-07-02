package lcd;

import java.util.ArrayList;

public class BagliListe extends Node{
	public Node nptr = null, iter = null; 
	public int len;
	public BagliListe() {
		head = null; 
		len = 0;
	}
	
	public boolean isEmpty() { 
		if(head == null) { 
			return true;
		}
		return false; 
	}
	public int  returnKayitSayisi() { 
		return len;
	}
	public void append( String b,String t, int s ) { 
		len++;
		nptr = new Node(b,t,s, null);
		if(isEmpty()) { 
			head = nptr;
			iter = head;
		}
		else{ 
			while(iter.next != null) { 	
				iter = iter.next; 			
			}
			iter.next = nptr;
							
		}
	}
	public void ekranaYazdir() { 
		if (isEmpty()) { 
			System.out.println("Liste bostur.");
		}
		else { 
			iter = head; 
			System.out.println("---------- Tek Yonlu Sirali Baglantili Liste Tamami ----------");
			for(int i = 1; i <= len && iter != null; i++) { 
				System.out.println(""+iter.block +" "+ iter.state); 
				iter = iter.next;
			}
			System.out.println("--------------------  --------------------");
		}
	}
	
	public Node get(int pos) { 
		if(isEmpty()) { 
			System.out.println("Liste bostur.");
			return null;
		}
		else {
			if(pos > len || pos <= 0) {  
				System.out.println("Hatali index degeri girdiniz."); 
				return null;
			}
			iter = head; 
			for(int i = 1; i <= len; i++) { 
				if(pos == i) {
					System.out.println("Girilen Pozisyondaki Veriler= "+iter.block+" "+iter.state);
					return iter;
				}
				iter = iter.next; 
			}
		}
		return null;
	}
	
	public Node search(String bl1) { 
		if(isEmpty()) { 
			System.out.println("Liste bostur.");
			return null;
		}
		else {
			iter = head; 
			boolean flag = false; 
			for(int i = 1; i <= len; i++) { 
				if(iter.block.equals(bl1)) { 
					System.out.println("Aranilan degerdeki Veriler="+ iter.block +" "+ iter.state); 
					flag = true; 
					return iter;
				}
				iter = iter.next; 	
			}
			if(! (flag)) { 
				System.out.println("Listede eleman bulunamadi hatali veya eksik girdiniz."); 
				return null;
			}
		}
		return null; 
	}
	
	public int durum(int pos) { 
		if(isEmpty()) { 
			System.out.println("Liste bostur.");
		}
		else {
			if(pos > len || pos <= 0) {  
				System.out.println("Hatali index degeri girdiniz."); 
			}
			iter = head; 
			for(int i = 1; i <= len; i++) { 
				if(pos == i) {
					return iter.state;
				}
				iter = iter.next; 
			}
		}
		return -1;
	}
	
	public ArrayList<String> durumlar(int dur) {
		if(isEmpty()) { 
			System.out.println("Liste bostur.");
			return null;
		}
		else {
			iter = head;
			ArrayList<String> durl = new ArrayList<String>();
			for(int i = 0; i < len; i++) { 
				if(dur == iter.state) {
					durl.add(iter.block);
				}
				iter = iter.next; 
			}
			return durl;
		}
	}
	
	public String blok(int pos) { 
		if(isEmpty()) { 
			System.out.println("Liste bostur.");
		}
		else {
			if(pos > len || pos <= 0) {  
				System.out.println("Hatali index degeri girdiniz."); 
			}
			iter = head; 
			for(int i = 1; i <= len; i++) { 
				if(pos == i) {
					return iter.block;
				}
				iter = iter.next; 
			}
		}
		return null;
	}
	
	public ArrayList<String> minterm(){
		if(isEmpty()) { 
			System.out.println("Liste bostur.");
			return null;
		}
		else {
			iter = head;
			ArrayList<String> terms = new ArrayList<String>();
			for(int i = 1; i <= len; i++) { 
				if(iter.state == 1) {
					terms.add(iter.term);
				}
				iter = iter.next; 
			}
			return terms;
		}
	}
	
	public ArrayList<String> maxterm(){
		if(isEmpty()) { 
			System.out.println("Liste bostur.");
			return null;
		}
		else {
			iter = head;
			ArrayList<String> terms = new ArrayList<String>();
			for(int i = 1; i <= len; i++) { 
				if(iter.state == 0) {
					terms.add(iter.term.toUpperCase());
				}
				iter = iter.next; 
			}
			return terms;
		}
	}

}
