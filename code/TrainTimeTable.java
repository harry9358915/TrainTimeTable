package TimeTable;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException; 
public class TrainTimeTable {

	String TrainDate;
	String Filename = TrainDate +".json";;
	private String filePath = "Data/" + Filename;
	int startstation = 1823;//�X�o������
	int endstation = 1827;//��F������
	String c = "21:00:00";//�ɶ�����
	String d = "23:58:00";//�ɶ�����
	int goarrive= 0;//0 is departure,1 is arrive
	int counter = 0;
	String type;
	ArrayList startrecordk = new ArrayList<>();//�����������X�o`��m��json��m
	ArrayList endrecordk = new ArrayList<>();//������������F��m��json��m
	ArrayList recordi = new ArrayList<>();//�����C������T��json��m
	ArrayList recordRuntime = new ArrayList<>();
	ArrayList allrecordarray = new ArrayList<>();
	List TrainInfos;
	List TimeInfos;
	public void Main() {
		boolean stationexist = false;
		Map t=null,t1=null;
		try {
			// read the json file
			InputStreamReader read = new InputStreamReader (new FileInputStream(filePath),"UTF-8"); 
			//FileReader reader = new FileReader(filePath);

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(read);

			TrainInfos = (List)jsonObject.get("TrainInfos");
			// take the elements of the json array
			for(int i=0; i<TrainInfos.size(); i++){
				t = (Map)TrainInfos.get(i);
				System.out.println("Train:"+t.get("Train"));
			}
			for(int i=0; i<TrainInfos.size(); i++){
				t = (Map)TrainInfos.get(i);
				System.out.println(String.valueOf(t.get("Train")));
				System.out.println("finished"+ ++counter);
				TimeInfos = (List)t.get("TimeInfos");
				stationexist = false;
				for(int k=0; k<TimeInfos.size(); k++){
					t1 = (Map)TimeInfos.get(k);
					
					if(Integer.parseInt(t1.get("Station").toString())==startstation){
						System.out.println("Station:"+t1.get("Station"));
						startrecordk.add(k);
						stationexist = true;
					}
					if(Integer.parseInt(t1.get("Station").toString())==endstation && stationexist){
						System.out.println("Station:"+t1.get("Station"));
						endrecordk.add(k);
						recordi.add(i);
						//counter++;
						//System.out.println("finished"+counter);
					}
				}
				if(startrecordk.size() != endrecordk.size())
					startrecordk.remove(startrecordk.size()-1);
			}
			for(int z=0;z<startrecordk.size();z++){
				t = (Map)TrainInfos.get((int) recordi.get(z));
				counter--;
			}
			checktime(c,d,goarrive,t1, recordi, startrecordk,endrecordk, TrainInfos, TimeInfos, t);
			

			// take each value from the json array separately
			// handle a s	tructure into the json object

			
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}

	}
    public TrainTimeTable(int startstation,int endstation,String c,String d,int goarrive,String TrainDate,String type) {
    	this.startstation = startstation;
    	this.endstation = endstation;
    	this.c = c;
    	this.d = d;
    	this.type = type;
    	this.goarrive =goarrive;
    	this.TrainDate = TrainDate;
    	Filename = this.TrainDate +".json";
    	filePath = "Data/" + Filename;
    	Main();
		// TODO Auto-generated constructor stub
	}
    public TrainTimeTable(){
    	
    }
	public void checktime(String c,String d,int goarrive,Map t1,ArrayList recordi,ArrayList startrecordk,ArrayList endrecordk,List TrainInfos,List TimeInfos,Map t) {
		
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		DateFormat df1 = new SimpleDateFormat("yyyyMMddHH:mm:ss");
		DateFormat df2 = new SimpleDateFormat("HH時mm分");
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		Calendar c3=Calendar.getInstance();
		long minutes;
		try {
			c2.setTime(df.parse(c));
			c3.setTime(df.parse(d));
			Date d1,d2;
			Date d3 = null;
			if(goarrive == 1){
				for(int i=0;i<recordi.size();i++){
					t = (Map)TrainInfos.get((int) recordi.get(i));
					boolean tmp1 = false;
					int CarClass = Integer.parseInt(String.valueOf(t.get("CarClass")));
					if(type.equals("2")){
						if(CarClass == 1120 || CarClass == 1131 ||CarClass == 1132 ||CarClass == 1140){
							recordi.remove(i);
							startrecordk.remove(i);
							endrecordk.remove(i);
							i--;
							tmp1 = true;
						}
					}else if(type.equals("1")){
						if(CarClass == 1100 || CarClass == 1101 || CarClass == 1103 || CarClass ==1107 || CarClass ==1108|| CarClass ==1110 || CarClass ==1111 || CarClass ==1114 || CarClass ==1115	){
							recordi.remove(i);
							startrecordk.remove(i);
							endrecordk.remove(i);
							i--;
							tmp1 = true;
						}
					}else{
						tmp1 = false;
					}
					if(tmp1 == false){
						TimeInfos = (List)t.get("TimeInfos");
						t1 = (Map)TimeInfos.get((int) startrecordk.get(i));
						c1.setTime(df.parse(String.valueOf(t1.get("DepTime"))));
						if(c1.compareTo(c2)<0){
							recordi.remove(i);
							startrecordk.remove(i);
							endrecordk.remove(i);
							i--;
						
				
						}else{
							if(c1.compareTo(c3)>0){
								recordi.remove(i);
								startrecordk.remove(i);
								endrecordk.remove(i);
								i--;
							}else{
								String tmp = String.valueOf(t.get("OverNightStn"));
								if(tmp.equals("0")){
									d2 = df.parse((String) t1.get("DepTime"));
									t1 = (Map)TimeInfos.get((int) endrecordk.get(i));
									d1 = df.parse((String) t1.get("ArrTime"));
									minutes = (d1.getTime()-d2.getTime());
									d3 = new Date(minutes-28800000);
									recordRuntime.add(df2.format(d3));
								}else{
									String OverDay = TrainDate;
									d2 = df1.parse(OverDay+(String) t1.get("DepTime"));
									t1 = (Map)TimeInfos.get((int) endrecordk.get(i));
									d1 = df1.parse(OverDay+(String) t1.get("ArrTime"));
									if(d1.compareTo(d2)>0){
										minutes = (d1.getTime()-d2.getTime());
									}else{
										d1 = df1.parse(String.valueOf((Integer.parseInt(OverDay)+1))+(String) t1.get("ArrTime"));
										minutes = (d1.getTime()-d2.getTime());
									}
									d3 = new Date(minutes-28800000);
									recordRuntime.add(df2.format(d3));
								}
							}
						}
					}
				}
			}else if(goarrive == 2){
				for(int i=0;i<recordi.size();i++){
					
					t = (Map)TrainInfos.get((int) recordi.get(i));
					boolean tmp1 = false;
					int CarClass = Integer.parseInt(String.valueOf(t.get("CarClass")));
					
					if(type.equals("2")){
						if(CarClass == 1120 || CarClass == 1131 ||CarClass == 1132 ||CarClass == 1140){
							recordi.remove(i);
							startrecordk.remove(i);
							endrecordk.remove(i);
							i--;		
							tmp1 = true;
						}
					}else if(type.equals("1")){
						if(CarClass == 1100 || CarClass == 1101 || CarClass == 1103 || CarClass ==1108|| CarClass ==1110 || CarClass ==1111 || CarClass ==1114 || CarClass ==1115	){
							recordi.remove(i);
							startrecordk.remove(i);
							endrecordk.remove(i);
							i--;
							tmp1 = true;
						}
					}else{
						tmp1 = false;
					}
					if(tmp1 == false){
						TimeInfos = (List)t.get("TimeInfos");
						t1 = (Map)TimeInfos.get((int) endrecordk.get(i));
						c1.setTime(df.parse(String.valueOf(t1.get("ArrTime"))));
						
						if(c1.compareTo(c2)<0){
							recordi.remove(i);
							startrecordk.remove(i);
							endrecordk.remove(i);
							i--;
						}else{						
							if(c1.compareTo(c3)>0){
								recordi.remove(i);
								startrecordk.remove(i);
								endrecordk.remove(i);
								i--;
							}else{
								String tmp = String.valueOf(t.get("OverNightStn"));
								if(tmp.equals("0")){
									d1 = df.parse((String) t1.get("ArrTime"));
									t1 = (Map)TimeInfos.get((int) startrecordk.get(i));
									d2 = df.parse((String) t1.get("DepTime"));
									minutes = (d1.getTime()-d2.getTime());
									d3 = new Date(minutes-28800000);
									recordRuntime.add(df2.format(d3));
									
								}else{
									String OverDay = TrainDate;
									d1 = df1.parse(OverDay+(String) t1.get("ArrTime"));
									t1 = (Map)TimeInfos.get((int) startrecordk.get(i));
									d2 = df1.parse(OverDay+(String) t1.get("DepTime"));
									if(d1.compareTo(d2)>0){
										minutes = (d1.getTime()-d2.getTime());
									}else{
									d2 = df1.parse(String.valueOf((Integer.parseInt(OverDay)-1))+(String) t1.get("DepTime"));
									minutes = (d1.getTime()-d2.getTime());
									}
									d3 = new Date(minutes-28800000);
									recordRuntime.add(df2.format(d3));
						
								}
							}
						}
					}
				}
			}
			for(int s=0;s<recordi.size();s++){
				allrecordarray.add(new allrecord(recordi.get(s),startrecordk.get(s),endrecordk.get(s),recordRuntime.get(s)));
			}
			Collections.sort(allrecordarray, new MapComparator(startrecordk, endrecordk, recordi, TimeInfos,TrainInfos ));

		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class MapComparator implements Comparator {
	ArrayList startrecordk;
	ArrayList endrecordk;
	ArrayList recordi;
	List TimeInfos;
	List TrainInfos;
	Map t;
	Map t1;
	Map t2;
	DateFormat df = new SimpleDateFormat("HH:mm:ss");
	DateFormat df1 = new SimpleDateFormat("yyyyMMddHH:mm:ss");
	Calendar c1=Calendar.getInstance();
	Calendar c2=Calendar.getInstance();
	 
	public MapComparator(ArrayList startrecordk,ArrayList endrecordk,ArrayList recordi,List TimeInfos,List TrainInfos)
	  {
	    this.startrecordk = startrecordk;
	    this.endrecordk = endrecordk;
	    this.recordi = recordi;
	    this.TimeInfos = TimeInfos;
	    this.TrainInfos = TrainInfos;		
	  }
	  
	public int compare(Object arg0, Object arg1)
	  {		System.out.println();
			t = (Map)TrainInfos.get((int) ((allrecord) arg0).getrecordi());
			TimeInfos = (List)t.get("TimeInfos");
			t1 = (Map)TimeInfos.get((int) ((allrecord) arg0).getstartrecordk());
			t = (Map)TrainInfos.get((int) ((allrecord) arg1).getrecordi());
			TimeInfos = (List)t.get("TimeInfos");
			t2 = (Map)TimeInfos.get((int) ((allrecord) arg1).getstartrecordk());
			try {
				c1.setTime(df.parse(String.valueOf(t1.get("ArrTime"))));
				c2.setTime(df.parse(String.valueOf(t2.get("ArrTime"))));
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			if(c1.compareTo(c2)>0){
				return 1;
			}
			return -1;
	  }
}
class allrecord {
	Object recordi,startrecordk,endrecordk,recordRuntime;
	public allrecord(Object a,Object b,Object c,Object d) {
		recordi = a;
		startrecordk = b;
		endrecordk = c;
		recordRuntime = d;
	}
	public Object getrecordi() {
		return recordi;
	}
	public Object getstartrecordk() {
		return startrecordk;
	}
	public Object getendrecordk() {
		return endrecordk;
	}
	public Object getrecordRuntime() {
		return recordRuntime;
	}
}