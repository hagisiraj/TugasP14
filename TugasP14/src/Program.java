
import java.util.InputMismatchException;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) throws Exception {
     

        try{
            Scanner input = new Scanner(System.in);
            Barang connection = new Barang();
            connection.getKoneksi();
            boolean lanjut = true;

            System.out.println();
            while(lanjut){
                   
                    switch(Barang.menuPenjualan()){
        
                        case 1:
                          connection.displayData();
                        // App.main(null);
                          
                        break;
        
                        case 2:
                           connection.insertData();
                            break;
                        case 3: 
                            connection.updateData();
                            break;
                        case 4:
                            connection.deleteData();
                            break;
                        case 5:
                            connection.searchData();
                            break;
                        case 6:
                         

                        default:
                            Program.main(null);
                            System.out.println("pilihan tidak ada");

                        break;

                        
                        
        
                    }
                System.out.print("Apakah anda ingin melanjutkan y/n ?  ");
                String pilihan =  input.next();
                lanjut = pilihan.equalsIgnoreCase("y");
             }
                  
        }catch (InputMismatchException e){
            e.printStackTrace();
        }
    
        
    }
       

}
       
        


