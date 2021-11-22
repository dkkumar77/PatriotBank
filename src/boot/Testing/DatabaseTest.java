package boot.Testing;

import boot.Controllers.Database;

public class DatabaseTest {

    public static void success(){
        System.out.println("Success");
    }



    public static void main(String[] args) {
        boot.Controllers.Database e = new boot.Controllers.Database();

        if(e.table != null){
            success();

        }
        e.addUser("username","password",10101,"John Doe","johndoe@gmu.edu","01-01-1990",.00,0,0);

        if(e.getName("username").equals("John Doe")){
            success();
        }

        if(e.getPassword("username").equals("password")){
            success();
        }
        if(e.getAccountID("username") == 10101){
            success();
        }
        if(e.getEmail("username").equals("johndoe@gmu.edu")){
            success();
        }
        if(e.getDOB("username").equals("01-01-1990")){
            success();
        }
        if(e.getBalance("username") == 0.0){
            success();
        }

        e.updateBalance("username",100.00);

        if(e.getBalance("username") != 0.0){
            success();
        }
        if(e.getBalance("username") == 100.00){
            success();
        }

        e.updatePassword("username", "new_password");

        if(!e.getPassword("username").equals("password")){
            success();
        }

        if(e.getPassword("username").equals("new_password")){
            success();
        }

        e.updateEmail("username","doejohn@gmu.edu");

        if(!e.getEmail("username").equals("johndoe@gmu.edu")){
            success();
        }

        if(e.getEmail("username").equals("doejohn@gmu.edu")){
            success();
        }








    }



}
