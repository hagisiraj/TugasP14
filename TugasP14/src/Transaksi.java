
public class Transaksi extends Barang {
	
	  
    public Transaksi(Integer harga,double diskon2)
    {
        this.harga = harga;
        this.diskon = diskon2;
    }

 
    @Override
    public  int harga(Integer harga){
        this.harga = harga;
        if(harga > 500000 ){
            this.harga = (int)((double)(this.harga*0.70));
        }else if(harga > 50000){
            this.harga = (int)((double)(this.harga*0.80));
        }else if(harga > 25000){
            this.harga = (int)((double)(this.harga*0.90));
        }
        return this.harga;
    }

    @Override
    public  int Discount(Integer diskon){
        this.harga = diskon;
        if(harga >= 500000 ){
            this.harga = (int)((double)(this.harga*0.3));
        }else if(harga >= 300000){
            this.harga = (int)((double)(this.harga*0.2));
        }else if(harga >= 150000){
            this.harga = (int)((double)(this.harga*0.1));
        }
        return this.harga;
	}

  public int harga;
    public double diskon;
    


    public void batalPesan() {
      // TODO Auto-generated method stub
      
    }


    public void cariData() {
      // TODO Auto-generated method stub
      
    }


    public double diskon(int harga2) {
        return 0;
    }
	

    
}
