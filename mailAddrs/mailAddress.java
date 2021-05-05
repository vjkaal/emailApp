package mailAddrs;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

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
        char[] pass=new char[10];
        Random rand=new Random();
        String passVars ="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for(int i=0;i<pass.length;i++){
            int num=rand.nextInt(passVars.length());
            pass[i]=passVars.charAt(num);
        }
        this.password=String.valueOf(pass);
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
            CSVWriter cw=new CSVWriter(new FileWriter("info.csv"));
            String[] entry={String.valueOf(this.srno),this.mailAddress,this.firstName,this.lastName,this.dept,this.password};
            cw.writeNext(entry);
        }
        catch (Exception e){
            pw.println(e);
        }
    }

    public void putInfo(){
        System.out.println("Name: "+this.firstName+" "+this.lastName);
        System.out.println("Department: "+this.dept);
    }

    public String getAddress(){
        return this.mailAddress;
    }

//    public static boolean chkAddress(String mailAddress){
//        boolean success=false;
//        if(this.mailAddress.equals(mailAddress)){success=true;}
//        return success;
//    }

    public boolean chkPass(String pass){
        boolean success=false;
        if(this.password.equals(pass)){success=true;}
        return success;
    }

    public void setPass(String pass){
        this.password=pass;
    }

    public void receive(File file){
        enterFile(file,this.dirPath);
    }
}
