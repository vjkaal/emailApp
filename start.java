import mailAddrs.addressManager;

import java.util.Scanner;

class start extends addressManager {

    private static final Scanner in=new Scanner(System.in);
    public static void main(String[] args){
        int selection=1;
        while(selection!=0){
            selection=menuBar();
            System.out.println();
            switch(selection){
                case 1: createAccount();break;
                case 2: login();break;
                case 0: System.out.println("Exiting");
            }
        }
        in.close();
    }

    private static int menuBar(){
		System.out.println("Create New Account: Press 1");
        System.out.println("Login to Existing Account: Press 2");
        System.out.println("Exit: Press 0");
        return in.nextInt();
    }
}
