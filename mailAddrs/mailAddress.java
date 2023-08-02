package mailAddrs;

import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class mailAddress extends mailBox{

//    private static final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));
	private String firstName;
	private String lastName;
	private String dept;
	private String mailAddress;
	private String password;
	private String dirPath;
	private int srno;

	//String alternateEmail;

	public mailAddress(){}

	public void setName(String firstName, String lastName,int srno){
		this.firstName=firstName;
		this.lastName=lastName;
		this.srno=srno;
		//System.out.println("Email created: "+this.firstName+" "+this.lastName);
	}

	public void setDept(String dept){
		this.dept=dept;
		theMailAddress();
		randomPass();
	}

	private void theMailAddress(){
		this.mailAddress=this.firstName.toLowerCase()+"."+this.lastName.toLowerCase()+"@"+this.dept.toUpperCase()+".company.com";
	}

	private void randomPass(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] pass=new char[10];
		String pass = new String();
		pw.printf("%s", "Enter your password: ");
		pass = br.readLine();
		// Random rand=new Random();
		// String passVars ="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		// for(int i=0;i<pass.length;i++){
		//     int num=rand.nextInt(passVars.length());
		//     pass[i]=passVars.charAt(num);
		// }
		// this.password=String.valueOf(pass);
		createMailBox();
		finished();
	}


	//methods about mailbox
	private void createMailBox(){
		String dirName=this.dept+File.separator+this.firstName+this.lastName;
		dirPath=osPath(dirName);
//        System.out.println(pathName);
		try{
			Files.createDirectories(Path.of(dirPath));
			System.out.println("Mailbox generated");
			
		}
		catch (Exception e){
			System.out.println("mailbox not generated:\t"+e);
		}
	}

	protected String getDirPath(){
		return dirPath;
	}

	private String osPath(String dirName){
		String path=null;
		String os=System.getProperty("os.name");
//        System.out.println(os);
		if(os.contains("Win")){
			path="B:\\projects\\emailApp\\mailBox\\";
		}
		else if(os.contains("nux")){
			path="/media/vanshaj/Code/projects/emailApp/mailBox/";
		}
		return String.format("%s%s", path, dirName);
	}


	private void finished(){
		putEntry();
		System.out.println("Your Email Address: "+this.mailAddress);
		System.out.println("Current Password: "+this.password);
		System.out.println("plz login to change pass.");
		System.out.println("\n");
	}

	private void putEntry(){
		//read csv file here
		PrintWriter pw=new PrintWriter(System.out,true);
		try {
			File file=new File("info.csv");
			FileWriter fw=new FileWriter(file,true);
			String[] entry={String.valueOf(this.srno),this.mailAddress,this.password,this.firstName,this.lastName,this.dept};
			fw.append("\n");
			fw.append(Arrays.toString(entry));
			try{
					fw.close();
			}
			catch(IOException e){
					e.printStackTrace();
			}
			readFile(file);
		}
		catch (Exception e){
			pw.println(e);
		}
	}

	private void readFile(File file){
		try {
			Scanner in=new Scanner(new File(String.valueOf(file)));
			while(in.hasNext()){System.out.print(in.next());}
			System.out.println("yes");
		}
		catch (Exception e){e.printStackTrace();}
	}

	public String getAddress(){
		return this.mailAddress;
	}

	public void receive(File file){
		enterFile(file,this.dirPath);
	}
}
