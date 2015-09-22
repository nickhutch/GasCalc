import java.text.DecimalFormat;


public class Time {
	int hours;
	double minutes;
	
	
	public Time(int hourIn, int minIn){
		hours = hourIn;
		minutes = minIn;
	}
	
	public Time(double d){
		
		hours = (int)	(Math.floor(d));
		
		minutes =  (d - Math.floor(d)) * 60;
	}
	
	public String toString(){
		DecimalFormat dblVar = new DecimalFormat("#.##");
		return "" + hours + " hr " + dblVar.format(minutes) + " min";
	}
	
	public double toDouble(){
		double d = hours;
		d += minutes/60;
		return d;
	}
}
