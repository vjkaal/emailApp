package mailContent;

import java.io.*;

public class textFile extends fileOp{

    File file;
    public textFile(){
        super();
        file=new File("mail.txt");
        try{
            if(!file.exists()) file.createNewFile();
        }
        catch (Exception e){
            System.out.println("Error: "+e);
        }
    }

    public File fileWrite(String senderAddress,String receiverAddress){
        File file=new File("mail.txt");
        writeHeader(senderAddress,receiverAddress,file);
        writeContent(file);
        return file;
    }

    public void fileRead(File file){
//        System.out.println("name: "+file.getName());
//        System.out.println("file exists: "+file.exists());
        readfromFile(file);
    }

     public void deleteFile(File file){
        try{
            boolean del=file.delete();
            System.out.println("deleted: "+del);
        }
        catch (Exception e){
            System.out.println("Error: "+e);
        }
    }
}
