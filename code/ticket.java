package TimeTable;
import java.io.*;
import java.net.URL;
import java.util.*; 
public class ticket { 
	String Startstation,Endstation,LineDir;
	ArrayList<List<String>> list = new ArrayList<>();
	int tmp = 0;
	int price = 0 ;
	FourTOThree fourTOThree = new FourTOThree();
	public ticket(String Startstation,String Endstation) {
		this.Startstation = fourTOThree.setFourTOThree(Integer.parseInt(Startstation));
		this.Endstation = fourTOThree.setFourTOThree(Integer.parseInt(Endstation));
		setList();
	}
	public void setList(){
		BufferedReader br =null; 
		String tempString=""; 
			try{ 	
				InputStream in = getClass().getResourceAsStream("ticket.txt"); 
				br = new BufferedReader(new InputStreamReader(in));
				String data; 
				int counter= 0;
				while (br.ready()){ 
					data = br.readLine();
					tempString = data; 
					String[] tempArray = tempString.split(",");
					list.add(Arrays.asList(tempArray));
					counter++;
				}
			}catch(IOException e){} finally{ 
				try{ 
					br.close();
					//Ãö³¬¦ê¬y 
				} catch(IOException e){} 
			} 
	}
	public void setLineDir(String LineDir,int CarClass){
		this.LineDir = LineDir;
    	if(CarClass == 1100 || CarClass == 1101 || CarClass == 1103 || CarClass ==1108 || CarClass == 1102 || CarClass == 1107)
    		tmp = 4;
    	if(CarClass == 1110 || CarClass == 1111 || CarClass == 1114 || CarClass ==1115)
    		tmp = 9;
    	if(CarClass == 1120 || CarClass == 1131 || CarClass == 1132 || CarClass ==1140)
    		tmp = 14;
    	if(CarClass == 1140)
    		tmp = 19;
	}
	public String setPrice(){
				for(int i=0;i<list.size();i++){
					if(list.get(i).get(0).equals(Startstation) && list.get(i).get(2).equals(LineDir) ){
						if(list.get(i).get(1).equals(Endstation)){
							price = Integer.parseInt(list.get(i).get(tmp));
							}
					}
				}
	return String.valueOf(price);
	}

} 