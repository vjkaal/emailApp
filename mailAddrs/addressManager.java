package mailAddrs;

import com.opencsv.CSVWriter;
import mailContent.textFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class addressManager {
    private static final LinkedList<mailAddress> mail=new LinkedList<>();
    private static int i=0;

    //resolve srno
    protected static void createAccount(){
        makeInfoFile();
        Scanner in=new Scanner(System.in);
        System.out.print("Enter first name: ");
        String fName=in.nextLine();
        System.out.print("Enter last name: ");
        String lName=in.nextLine();
        System.out.print("Enter dept: ");
        String dept=in.nextLine();
        mail.add(new mailAddress());
        mail.get(i).setName(fName,lName, getSrNo());
        mail.get(i).setDept(dept);
        i++;
    }

    private static void makeInfoFile(){
        try{
            File file=new File("info.csv");
            System.out.println("File Exists: "+file.exists());
            FileWriter fw=new FileWriter(file);
            CSVWriter cw=new CSVWriter(fw);
            String[] Header={"Sr.no","Mail Address","First Name","Last Name","Dept.","Password"};
            cw.writeNext(Header);
        }
        catch (Exception e){
            System.out.println("Error: "+e);
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
            System.out.println("Error: "+e);
        }
        return srNo;
    }

    //Find a way to fast out the login process for large list size
    protected static void login(){
        Scanner in=new Scanner(System.in);
        System.out.print("Enter mail Address: ");
        String mailAddress=in.nextLine();
        System.out.print("Enter password: ");
        String pass=in.nextLine();
        boolean found, matched;
        File file;
        try{
            file=new File("info.csv");
            List<String> lines= Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            int j=0;
            for(String line:lines){
                String[] array=line.split(";");
                found = mailAddress.equals(array[1]);
                if(found){
                    matched=pass.equals(array[array.length-1]);
                    if(matched){
                        loginMenu(j);
                    }
                    else{System.out.println("mail address or pass is wrong!");}
                    break;
                }
                j++;
            }
        }
        catch (Exception e){
            System.out.println("Error: "+e);
        }
    }

    private static void loginMenu(int x){
        Scanner in=new Scanner(System.in);
        mail.get(x).putInfo();
//        try{
//            System.out.println(System.in.available());
//        }
//        catch (Exception e){
//            System.out.println("error: "+e);
//        }
        System.out.println("\nActions:");
        System.out.println("Change password: Press 1");
        System.out.println("Send Mail: Press 2");
        System.out.println("Read Mails: Press 3");
        System.out.println("Exit: Press 0");
        int res;
        try{
            res=in.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.print("Enter only above values: ");
            res=in.nextInt();
        }
        in.nextLine();
        switch(res){
            case 1: changePass(x);break;
            case 2: sendMail(x);break;
            case 3: readMails(x);break;
            case 0:System.out.println("Exiting");break;
        }
    }

    private static void changePass(int x){
        Scanner in=new Scanner(System.in);
        System.out.println("Enter current pass: ");
        String pass=in.nextLine();
        boolean matched=mail.get(x).chkPass(pass);
        if(matched){
            boolean equal = false;
            while(!equal){
                System.out.print("Enter new pass: ");
                pass=in.nextLine();
                System.out.print("Re-Enter new pass: ");
                String pass2=in.nextLine();
                equal=pass.equals(pass2);
                if(equal){
                    mail.get(x).setPass(pass);
                    System.out.println("Pass changed");
                }
            }
        }
        else{
            System.out.println("wrong pass, enter again..");
            changePass(x);
        }
        loginMenu(x);
    }

    private static void sendMail(int x){
//        mailAddrs.mail email=new mail();
//        String Address=mail.get(x).getAddress();
//        String address2=email.sendMail(Address);
//        for (mailAddrs.mailAddress mailAddress : mail) {
//            boolean found = mailAddrs.mailAddress.chkAddress(address2);
//            if (found) {
//                mailAddress.receive(email.putMailContent());
//                break;
//            }
//        }
//        email.deleteFile();
        System.out.println("currently in development process");
        loginMenu(x);
    }

    private static void readMails(int x){
//        mailAddress mails=new mailAddress();
        PrintWriter pw=new PrintWriter(System.out,true);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String dirPath=mail.get(x).getDirPath();
        System.out.println(dirPath);
        File folder=new File(dirPath);
        File[] fileList=folder.listFiles();
        for(File file: fileList){
            if(file.isFile()) pw.println(file.getName());
        }
        String name = "";
        pw.printf("Enter file name: ");
        try{
            name=br.readLine();
        }
        catch (Exception e){
            pw.println(e);
        }
        for(File file:fileList){
            if(file.isFile()){
                if(name.equals(file.getName())){
                    textFile tf=new textFile();
                    tf.fileRead(file);
                }
            }
            else pw.println("File not found");
        }
        loginMenu(x);
    }
}
