package mailContent;

import java.io.*;

public class fileOp {

    public fileOp(){}

    void writeHeader(String senderAddress,String receiverAddress,File file){
        PrintWriter pw=new PrintWriter(System.out,true);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        try{
            String name=file.getName();
            FileWriter fw=new FileWriter(name,true);
            BufferedWriter bw=new BufferedWriter(fw);

            bw.write("Sender's Address: ");
            bw.write(senderAddress);
            bw.write("\n");
            bw.write("Receiver's Address: ");
            bw.write(receiverAddress);
            bw.write("\n");
            pw.printf("Enter Subject(editor.Stop to stop): ");
            String subject=br.readLine();
            bw.write("Subject: ");
            bw.write(subject);
            bw.write("\n\n");
            bw.close();
            fw.close();
//            readfromFile(file);
        }
        catch (Exception e){
            pw.println(e);
        }
    }

    void writeContent(File file){
        PrintWriter pw=new PrintWriter(System.out,true);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        try{
            String fileName=file.getName();
            FileWriter fw=new FileWriter(fileName,true);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write("\t");
            pw.printf("Enter content here:");
            boolean stop=false;
            while(!stop){
                String str=br.readLine();
                if(str.equals("editor.Stop")){
                    stop=true;
                }
                else if(str.contains("editor.Stop")){
                    StringBuilder s=new StringBuilder(str);
                    s.delete(str.length()-11,str.length());
                    str=s.toString();
                    bw.write(str);
                    bw.write("\n");
                    stop=true;
                }
                else{
                    bw.write(str);
                    bw.write("\n");
                }
            }
            bw.close();
            fw.close();
//            System.out.println(System.in.available());
//            pw.println("yes");
        }
        catch (Exception e){
            pw.println(e);
        }
    }


    void readfromFile(File file){
        PrintWriter pw=new PrintWriter(System.out,true);
//        pw.println("name: "+file.getName());
        FileReader fr;
        try{
            fr=new FileReader(file);
            char[] array=new char[(int)file.length()];
            fr.read(array);
            pw.println(array);
            fr.close();
        }
        catch (Exception e){
            pw.println(e);
        }
    }
}