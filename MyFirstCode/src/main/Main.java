package main;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.*;



public class Main {
	static Scanner sc = new Scanner(System.in);
	static int maxExpense = 0;
	static int totalSpending = 0;
	static String csvFile = "data.csv";
	static int id = 1;
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static void updateExpense() {
		String tempFile = "temp.csv";
		boolean found = false;
		
		System.out.println("You're going to update your expense!");
		System.out.print("Enter expense id: ");
		int EID = sc.nextInt();
		
		
		try(BufferedReader br = new BufferedReader(new FileReader(csvFile));
				FileWriter writer = new FileWriter(tempFile)){
			String line;
			while((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				int CID = Integer.parseInt(parts[0]);
				
				if(EID == CID) {
					found = true;
					System.out.print("New item : ");
					String NItem = sc.nextLine();
					System.out.print("New price ");
					int NPrice = sc.nextInt();
					sc.nextLine();
					System.out.print("New Date (yyyy-mm--dd) : ");
					String NDate = sc.nextLine();
					
					try{
						LocalDate UNDate = LocalDate.parse(NDate , formatter);
						totalSpending = totalSpending - Integer.parseInt(parts[2]) + NPrice;
						
						writer.write(CID + "," + NItem + "," + NPrice + "," + NDate + "\n");
						System.out.println("Expense updated successfully!");
						sc.nextLine();
					}catch(DateTimeParseException e){
						System.out.println("Invalid date format , Update cancelled!");
						writer.write(line + "\n");
					}
					continue;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(found) {
			new File(csvFile).delete();
			new File(tempFile).renameTo(new File(csvFile));
		}else {
			System.out.println("Expense ID not found!");
			new File(tempFile).delete();
		}
	}
	
	public static void viewExpense() {
		try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
			String line;
			boolean found = false;
			System.out.print("Insert starting date range (yyyy-mm-dd): ");
			String startDateStr = sc.nextLine();
			System.out.print("Insert Ending date range (yyyy-mm-dd) : ");
			String endDateStr = sc.nextLine();
			
			LocalDate startDate = LocalDate.parse(startDateStr , formatter);
			LocalDate endDate = LocalDate.parse(endDateStr , formatter);
			
			while((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				String currId = parts[0];
				String item = parts[1];
				String price = parts[2];
				LocalDate date = LocalDate.parse(parts[3] , formatter);
				
				if((date.isEqual(startDate) || date.isAfter(startDate)) 
						&& (date.isEqual(endDate) || date.isBefore(endDate))) {
					System.out.println("ID : "+ currId + "\nItem : " + item + "\n Price : " + price + "\n Date : " + date + "\n");
					found = true;
				}
				
			}
			
			if(!found) {
				System.out.println("Cant find the transaction you're looking for");
				sc.nextLine();
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("No trasactions verified!");
			sc.nextLine();
		}
		
	}
	
	public static void deleteExpense() {
		System.out.println("You're going to delete an expense!");
		System.out.println("1. Continue");
		System.out.println("2. Cancel");
		
		int confirmation = sc.nextInt();
		sc.nextLine();
		
		while(confirmation < 1 || confirmation > 2) {
			System.out.println("Please enter a valid input!");
			sc.nextLine();
			return;
		}
		
		if(confirmation == 1) {
			System.out.println("Expense ID : ");
			int EID = sc.nextInt();
			sc.nextLine();
			
			String tempFile = "temp.csv";
			boolean found = false;
					
			try(BufferedReader br = new BufferedReader(new FileReader(csvFile));
					FileWriter writer = new FileWriter(tempFile)){
				
				int currentSpend = 0;
				String line;
				while((line = br.readLine()) != null) {
					String[] parts = line.split(",");
					int CID = Integer.parseInt(parts[0]);
					if(EID == CID) {
						found = true;
						currentSpend = Integer.parseInt(parts[2]);
						System.out.println(CID + "/ " + parts[1] + " / " + currentSpend + " <-- is deleted" );
						totalSpending = totalSpending - currentSpend;
						continue;
					}
					writer.write(line + "\n");
				}
				
			}catch(IOException E){
				E.printStackTrace();
			}	
			
			if(found) {
				new File(csvFile).delete();
				new File(tempFile).renameTo(new File(csvFile));
				
			}else {
				System.out.println("Failed to delete! / DNE");
			}
			
		}else {
			return;
		}
	}
	
	public static void budgetHandling() {
		System.out.println("Your left budget : Rp." + maxExpense);
		System.out.print("Set your max budget for the month : ");
		maxExpense = sc.nextInt();
		sc.nextLine();
	}
	
	public static void addExpense(){
		try(FileWriter writer = new FileWriter(csvFile , true)){
			LocalDate date = null;
			System.out.print("Add item : ");
			String item = sc.nextLine();
			System.out.print("Add price : ");
			int price = sc.nextInt();
			sc.nextLine();
			System.out.print("Add date : ");
			String Tdate = sc.nextLine();
			
			date = LocalDate.parse(Tdate , formatter);
			totalSpending = totalSpending + price;
		
			
			if(price > maxExpense) {
				System.out.println("You're spending more than your budget!");
				sc.nextLine();
				return;
			}else {
				writer.append(id + "," + item + "," + price + ","+ date + "\n");
				id++;
				System.out.println("Expenses added");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}catch(DateTimeParseException e) {
			System.out.println("Please enter the right date format! (yyyy-mm-dd)");
		}
		
		
	}
	
	public static void Menu() {
		
		System.out.println("Expense Tracker V 1.0");
		System.out.println("0. Add Budget");
		System.out.println("1. Add expense");
		System.out.println("2. Update expense");
		System.out.println("3. View all expense");
		System.out.println("4. Delete expense");
		System.out.println("5. Exit");
		System.out.print("Enter your option : ");
		
	}
	
	public static void main(String[] args) {
		
		int option = 0;
		while(true) {
			Menu();
			option = sc.nextInt();
			sc.nextLine();
			while(option < 0 || option > 5 ) {
				System.out.println("Please pick between the options!");
				sc.nextLine();
			}
			
			if(option == 5) {
				System.out.println("System exit successful!");
				System.exit(0);
			}
			
			switch(option) {
				case 0 : 
					budgetHandling();
					break;
				case 1:
					addExpense();
					break;
				case 2: 
					updateExpense();
					break;
				case 3 : 
					viewExpense();
					break;
				case 4:
					deleteExpense();
					break;
			}
			
		}
		
		
		
		
	}
}
