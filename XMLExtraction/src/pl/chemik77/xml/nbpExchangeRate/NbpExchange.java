package pl.chemik77.xml.nbpExchangeRate;


public class NbpExchange {
	
	//fields for exchange rate
	private String nazwa;
	private int przelicznik;
	private String kod;
	private double kurs;
	
	//constructor takes initial values
	public NbpExchange(String nazwa, int przelicznik, String kod, double kurs) {
		this.nazwa = nazwa;
		this.przelicznik = przelicznik;
		this.kod = kod;
		this.kurs = kurs;
	}
	
	//getters 
	public String getNazwa() {
		return this.nazwa;
	}
	
	public int getPrzelicznik() {
		return this.przelicznik;
	}
	
	public String getKod() {
		return this.kod;
	}
	
	public double getKurs() {
		return this.kurs;
	}
}
