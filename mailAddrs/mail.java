package mailAddrs;

import mailContent.textFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class mail{
    String senderAddress;
    String receiverAddress;
    File file;

    public mail(){}

    public String sendMail(String Address){
        this.senderAddress=Address;
        this.receiverAddress=getAddress();
        getMailContent();
        return this.receiverAddress;
    }

    private String getAddress(){
        System.out.print("Enter Receiver's Address: ");
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String address=null;
        try {
            address=br.readLine();
        }
        catch (Exception e){
            System.out.println("error:"+e);
        }

//        try{
//            System.out.println(System.in.available());
//        }
//        catch (Exception e){
//            System.out.println("Error: "+e);
//        }
        return address;
    }

    protected void getMailContent(){
        mailContent.textFile tf=new textFile();
        file = tf.fileWrite(this.senderAddress,this.receiverAddress);
    }

    public File putMailContent(){
        return file;
    }

    void deleteFile(){
        textFile tf=new textFile();
        tf.deleteFile(file);
    }
}