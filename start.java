import mailAddrs.addressManager;

import java.io.*;

public final class start extends addressManager {

  private static final BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
  private static final PrintWriter pw = new PrintWriter(System.out,true);
  public static void main(String[] args){
    int selection=1;
    while(selection!=0){
      try {
        selection=menuBar();
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println();
      switch(selection){
        case 1: createAccount();break;
        case 2: login();break;
        case 0: pw.println("Exiting");
      }
    }
    try {
      br.close();
    }
    catch(Exception e){
      pw.println(e);
    }
    pw.close();
  }

  private static int menuBar()throws IOException{
    System.out.println("Create New Account: Press 1");
    System.out.println("Login to Existing Account: Press 2");
    System.out.println("Exit: Press 0");
    return Integer.parseInt(br.readLine());
  }
}
