
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Scanner;



public class Barang implements Penjualan{

      static Connection conn;
      static Statement stm;
      static ResultSet rs;
      

      static Scanner input = new Scanner(System.in);
      private int harga;
      private int jumlah;
      private int subtotal;
      private double diskon;
      Transaksi transaksi;
  
    public Connection getKoneksi() {
        
        String url = "jdbc:mysql://localhost:3306/pertemuan14";
        String user = "root";
        String pass = "";
   
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn  = DriverManager.getConnection(url, user, pass);
            System.out.println("koneksi berhasil");
        }catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }

  
  
    public static int menuPenjualan(){
        
            Calendar calendar = Calendar.getInstance();
            System.out.println("" + calendar.getTime());
            System.out.println("----------------------");
                System.out.println("Database Pesanan");
                System.out.println("----------------------");
                System.out.println("1. Tampil Pesanan");
                System.out.println("2. Tambah Pesanan");
                System.out.println("3. Ubah Pesanan");
                System.out.println("4. Hapus Pesanan");
                System.out.println("5. Cari Data Pesanan");
                System.out.println("\t");
            System.out.print("Masukkan pilihan : ");
            int pilihan =  input.nextInt();
            return pilihan;
       
    }   

    @Override
    public  void displayData(){
        
        String sql =  "SELECT * FROM penjualan";
        try {
            stm = conn.createStatement();
            rs =  stm.executeQuery(sql);
            
            String judul = "Daftar Pesanan";
            System.out.println("\t\t\t-----------------------");
            System.out.printf("\t\t\t    %s\n",judul.toUpperCase());
            System.out.println("\t\t\t-----------------------\n");
           
            
            System.out.println("| No Faktur | Nama Barang |  Harga Barang  | Jumlah |    Diskon    |     Total     |");
            System.out.println("------------------------------------------------------------------------------------");
            while(rs.next()){
                int no_faktur =  rs.getInt("no_faktur");
                String nama_barang =  rs.getString("nama_barang");
                int harga_barang =  rs.getInt("harga_barang");
                int jumlah=  rs.getInt("jumlah");
                int diskon=  rs.getInt("diskon");
                int total = rs.getInt("total");
                System.out.println(String.format("|   %d\t    | %-10s  | %-14d | %-6d |  %-10d  |  %-10d   |", no_faktur,nama_barang,harga_barang,jumlah,diskon,total));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insertData(){
        try {
            System.out.print("\n----- Tambah Pesanan -----\n");
            System.out.print("\nMasukkan no_faktur        : ");
            int  no_faktur = input.nextInt();
            input.nextLine();

            System.out.print("Masukkan Nama Barang      : ");
            String nama_barang = input.nextLine();

            System.out.print("Masukkan Harga Barang     : ");
            int harga_barang = input.nextInt();

            System.out.print("Masukkan Jumlah           : ");
            jumlah = input.nextInt();
           
            transaksi = new Transaksi(harga_barang, diskon);
            this.harga = harga_barang * jumlah;
            subtotal = transaksi.harga(this.harga);
            diskon =  transaksi.Discount(this.harga);
            
            String sql =  "INSERT INTO penjualan (no_faktur,nama_barang,harga_barang,jumlah,diskon,total) VALUE('"+no_faktur+"','"+nama_barang+"','"+harga_barang+"','"+jumlah+"','"+diskon+"','"+subtotal+"')";

            

            stm = conn.createStatement();
            stm.executeUpdate(sql);
            System.out.println("Barang berhasil dipesan");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }

     

    public void updateData(){
        try {
            displayData();
            System.out.println();
            System.out.print("Masukkan No Faktur        :");
            int no_faktur =  input.nextInt();
            input.nextLine();

            System.out.print("Masukkan Nama Barang      :");
            String nama_barang = input.nextLine();

            System.out.print("Masukkan Harga Barang     :");
            int harga_barang = input.nextInt();

            System.out.print("Masukkan Jumlah           :");
            int jumlah = input.nextInt();
            transaksi = new Transaksi(harga_barang, diskon);
            this.harga = harga_barang * jumlah;
            subtotal = transaksi.harga(this.harga);
            diskon =  transaksi.Discount(this.harga);
    
            String sql = "UPDATE penjualan SET no_faktur='"+no_faktur+"',nama_barang='"+nama_barang+"',harga_barang='"+harga_barang+"',jumlah='"+jumlah+"',diskon='"+diskon+"',total='"+subtotal+"'WHERE no_faktur= '"+no_faktur+"'";

            stm = conn.createStatement();
            stm.executeUpdate(sql);
            System.out.println("Pesanan berhasil diubah");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }

    public void deleteData(){
        try {
            displayData();
            System.out.println();
            System.out.print("Masukkan No Faktur : ");
            int no_faktur = input.nextInt();

            String sql = "DELETE FROM  penjualan WHERE no_faktur= " +no_faktur;

            stm = conn.createStatement();
            stm.executeUpdate(sql);
    
            System.out.println("Pesanan berhasil dibatalkan");
        } catch (SQLException e) {
            e.printStackTrace();
        }

       
    }


    public void searchData(){
        try {

            System.out.print("Masukkan Pesanan yang ingin dilihat : ");
            String keyword = input.next();

            String sql = "SELECT * FROM penjualan WHERE no_faktur LIKE '%" +keyword+ "%' OR  nama_barang LIKE '%" +keyword+ "%' ";

            stm = conn.createStatement();
            rs =  stm.executeQuery(sql);

            while(rs.next()){
                int no_faktur =  rs.getInt("no_faktur");
                String nama_barang = rs.getString("nama_barang");
                int harga_barang =  rs.getInt("harga_barang");
                int jumlah =  rs.getInt("jumlah");
                int diskon=  rs.getInt("diskon");
                int total = rs.getInt("total");
                System.out.println("\n");
                System.out.println("| No Faktur | Nama Barang |  Harga Barang  | Jumlah |    Diskon    |     Total     |");
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println(String.format("|   %d\t    | %-10s  | %-14d | %-6d |  %-10d  |  %-10d   |", no_faktur,nama_barang,harga_barang,jumlah,diskon,total));
                System.out.println("\n");

              }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
					
    }



    @Override
    public int Discount(Integer diskon) {
      // TODO Auto-generated method stub
      return 0;
    }



    @Override
    public int harga(Integer harga) {
      // TODO Auto-generated method stub
      return 0;
    }
}