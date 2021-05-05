package mailAddrs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

class mailBox {

    private int x=0;

    //find a way to enter files after capacity exceeds
    protected void enterFile(File mail,String location){
        try{
            int capacity = 10;
            if(x<= capacity){
                x++;
//                System.out.println(location);
//                File dir=new File(System.getProperty(location));
                Path path= Paths.get(Path.of(location) + File.separator + setName(mail.getName()));
                File file=new File(String.valueOf(path));
//                System.out.println(mail.getAbsolutePath());
                if(!file.exists()) file.createNewFile();
//                System.out.println("name: "+file.getName());
//                System.out.println("path: "+file.getPath());
                getContent(mail, file);
//                System.out.println("file exists in mailbox: "+file.exists());
//                textFile tf=new textFile();
//                tf.fileRead(file);
            }
//            else{
//                File folder=new File("location");
//                System.out.println(location);
//            }
        }
        catch (Exception e){
            System.out.println("Error: "+e);
        }
    }

    private void getContent(File mail, File file) {
        PrintWriter pw=new PrintWriter(System.out,true);
        try{
            int i;
            FileInputStream fin=new FileInputStream(mail);
            FileOutputStream fout=new FileOutputStream(file);
            do{
                i=fin.read();
                if(i!=-1) fout.write(i);
            }
            while(i!=-1);
            fin.close();
        }
        catch (Exception e){
            pw.println(e);
        }
    }

    private String setName(String name){
        StringBuilder str=new StringBuilder(name);
        str.insert(str.length()-4,x);
        return str.toString();
    }
}
