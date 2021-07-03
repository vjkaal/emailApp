package mailAddrs;

import mailContent.textFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class addressManager {
    private static final LinkedList<mailAddress> mail=new LinkedList<>();
    private static int i=0;

    private static final PrintWriter pw = new PrintWriter(System.out,true);

    //resolve srno
    protected static void createAccount(){
        makeInfoFile();
        Scanner in=new Scanner(System.in);
        pw.print("Enter first name: ");
        String fName=in.nextLine();
        pw.print("Enter last name: ");
        String lName=in.nextLine();
        pw.print("Enter dept: ");
        String dept=in.nextLine();
        mail.add(new mailAddress());
        mail.get(i).setName(fName,lName, getSrNo());
        mail.get(i).setDept(dept);
        i++;
    }

    private static void makeInfoFile(){
        try{
            File file=new File("info.csv");
            if(!file.exists()){
                file.createNewFile();
                String[] Header={"Sr.no","Mail Address","First Name","Last Name","Dept.","Password"};
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
            pw.println("\nyes");
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
        Scanner in=new Scanner(System.in);
        pw.print("Enter mail Address: ");
        String mailAddress=in.nextLine();
        pw.print("Enter password: ");
        String pass=in.nextLine();
        boolean found, matched;
        File file;
        pw.println("yes");
        try{
            file=new File("info.csv");
            List<String> lines= Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            int j=0;
            for(String line:lines){
                String[] array=line.split(",");
                found = mailAddress.equals(array[1]);
                if(found){
                    matched=pass.equals(array[array.length-1]);
                    if(matched){
                        loginMenu(j);
                    }
                    else{pw.println("mail address or pass is wrong!");}
                    break;
                }
                j++;
            }
        }
        catch (Exception e){
            pw.println("Error: "+e);
        }
    }

    private static void loginMenu(int x){
        Scanner in=new Scanner(System.in);
        mail.get(x).putInfo();
//        try{
//            pw.println(System.in.available());
//        }
//        catch (Exception e){
//            pw.println("error: "+e);
//        }
        pw.println("\nActions:");
        pw.println("Change password: Press 1");
        pw.println("Send Mail: Press 2");
        pw.println("Read Mails: Press 3");
        pw.println("Exit: Press 0");
        int res;
        try{
            res=in.nextInt();
        }
        catch (InputMismatchException e) {
            pw.print("Enter only above values: ");
            res=in.nextInt();
        }
        in.nextLine();
        switch(res){
            case 1: changePass(x);break;
            case 2: sendMail(x);break;
            case 3: readMails(x);break;
            case 0:pw.println("Exiting");break;
        }
    }

    private static void changePass(int x){
        Scanner in=new Scanner(System.in);
        pw.println("Enter current pass: ");
        String pass=in.nextLine();
        boolean matched=mail.get(x).chkPass(pass);
        if(matched){
            boolean equal = false;
            while(!equal){
                pw.print("Enter new pass: ");
                pass=in.nextLine();
                pw.print("Re-Enter new pass: ");
                String pass2=in.nextLine();
                equal=pass.equals(pass2);
                if(equal){
                    mail.get(x).setPass(pass);
                    pw.println("Pass changed");
                }
            }
        }
        else{
            pw.println("wrong pass, enter again..");
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
        pw.println("currently in development process");
        loginMenu(x);
    }

    private static void readMails(int x){
//        mailAddress mails=new mailAddress();
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String dirPath=mail.get(x).getDirPath();
        pw.println(dirPath);
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
