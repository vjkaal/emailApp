package mailAddrs;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class addressManager {

	private static final PrintWriter pw = new PrintWriter(System.out,true);
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	//resolve srno
	protected static void createAccount(){
		makeInfoFile();
		Scanner in=new Scanner(System.in);
		pw.printf("Enter first name: ");
		String fName=in.nextLine();
		pw.printf("Enter last name: ");
		String lName=in.nextLine();
		pw.printf("Enter dept: ");
		String dept=in.nextLine();
		mailAddress a = new mailAddress();
		a.setName(fName,lName, getSrNo());
		a.setDept(dept);
	}

	private static void makeInfoFile(){
		try{
			File file=new File("info.csv");
			if(!file.exists()){
					file.createNewFile();
					String[] Header={"Sr.no","Mail Address","Password","First Name","Last Name","Dept."};
					FileWriter cw=new FileWriter(file,true);
					cw.write(Arrays.toString(Header));
					cw.close();
					readFile(file);
			}
		}
		catch (Exception e){
			pw.println("Error: "+e);
		}
	}

	private static void readFile(File file){
		try{
			Scanner in=new Scanner(new File(String.valueOf(file)));
			in.useDelimiter(",");
			while(in.hasNext()){
					pw.print(in.next());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static int getSrNo(){
		int srNo=0;
		File file=new File("info.csv");
		try{
			BufferedReader br=new BufferedReader(new FileReader(file));
			while(br.readLine()!=null) srNo++;
		}
		catch (Exception e){
			pw.println("Error: "+e);
		}
		return srNo;
	}

	//Find a way to fast out the login process for large list size
	protected static void login(){
		pw.printf("Enter mail Address: ");
		String mailAddress = "";
		try{
			mailAddress = br.readLine();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		pw.printf("Enter password: ");
		String pass = "";
		try{
			pass = br.readLine();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		boolean found, matched;
		File file;
		try{
			file=new File("info.csv");
			List<String> lines= Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			int j=0;
			for(String line:lines) {
					String[] array = line.split(",");
					found = mailAddress.equals(array[1].trim());
//                pw.println(mailAddress+"_"+array[1].trim()+"_"+found);
					if (found) {
						matched = pass.equals(array[2].trim());
//                    pw.println(pass+"_"+array[2].trim()+"_"+matched);
						if (matched) {
							loginMenu(j);
							break;
						} else {
							pw.println("mail address or pass is wrong!");
						}
					}
					j++;
			}
		}
		catch (Exception e){
			pw.println("Error: "+e);
		}
	}

	private static void loginMenu(int x){
		try{
			File file = new File("info.csv");
			List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			String[] info = lines.get(x).split(",");
			pw.println("Name: "+info[3].trim()+" "+info[4].trim()+".");
			pw.println("Dept: "+info[5].trim().replace(']','.'));
			pw.println("\nActions:");
			pw.println("Change password: Press 1");
			pw.println("Send Mail: Press 2");
			pw.println("Read Mails: Press 3");
			pw.println("Exit: Press 0");
			int res = 0;
			try{
					res = Integer.parseInt(br.readLine());
			}
			catch (IOException e) {
					pw.printf("Enter only Integer value: ");
					try{
						res = Integer.parseInt(br.readLine());
					}
					catch(IOException f){
						f.printStackTrace();
					}
			}
			switch(res){
					case 1: changePass(x);break;
					case 2: sendMail();break;
					case 3: readMails();break;
					case 0:pw.println("Exiting");break;
			}
		}
		catch (Exception e){
			pw.println(e);
		}
	}

	private static void changePass(int x){
		try{
			File file = new File("info.csv");
			List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			String[] info = lines.get(x).split(",");
			pw.printf("Enter current pass: ");
			String pass = "";
			try{
					pass = br.readLine();
			}
			catch(IOException e){
					e.printStackTrace();
			}
			boolean matched = pass.equals(info[2].trim());
			if(matched){
					boolean equal = false;
					while(!equal){
						pw.printf("Enter new pass: ");
						pass = "";
						try{
							pass = br.readLine();
						}
						catch(IOException e){
							e.printStackTrace();
						}
						pw.printf("Re-Enter new pass: ");
						String pass2 = "";
						try{
							pass2 = br.readLine();
						}
						catch(IOException e){
							e.printStackTrace();
						}
						equal=pass.equals(pass2);
						if(equal){
//                        FileWriter fw = new FileWriter(file,true);
//                        fw.
//                        pw.println("Pass changed");
						}
					}
			}
			else{
					pw.println("wrong pass, enter again..");
					changePass(x);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		loginMenu(x);
	}

	private static void sendMail(){
		pw.println("currently in development process");
		loginMenu(2);
	}

	private static void readMails(){
//        mailAddress mails=new mailAddress();
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//        String dirPath=mail.get(x).getDirPath();
//        pw.println(dirPath);
//        File folder=new File(dirPath);
//        File[] fileList=folder.listFiles();
//        for(File file: fileList){
//            if(file.isFile()) pw.println(file.getName());
//        }
//        String name = "";
//        pw.printf("Enter file name: ");
//        try{
//            name=br.readLine();
//        }
//        catch (Exception e){
//            pw.println(e);
//        }
//        for(File file:fileList){
//            if(file.isFile()){
//                if(name.equals(file.getName())){
//                    textFile tf=new textFile();
//                    tf.fileRead(file);
//                }
//            }
//            else pw.println("File not found");
//        }
		pw.println("currently in development process");
		loginMenu(2);
	}
}
