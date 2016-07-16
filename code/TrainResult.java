package TimeTable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class TrainResult extends Application {
	
	int startstation = 1823;//出發之車站
	int endstation = 1827;//抵達之車站
	String c = "21:00:00";//時間限制
	String d = "23:58:00";//時間限制
	String TrainDate;
	String type;
	int goarrive= 0;//0 is departure,1 is arrive
	int counter = 0;
    private TableView<TrainTable> table = new TableView<TrainTable>();
    private final ObservableList<TrainTable> data = FXCollections.observableArrayList();
    FourToStation fourToStation = new FourToStation();
   
    public static void main(String[] args) {
    	
        launch(args);
    }
    public void s(int startstation,int endstation,String c,String d,int goarrive,String TrainDate,String type) {
    	this.startstation = startstation;
    	this.endstation = endstation;
    	this.c = c;
    	this.d = d;
    	this.goarrive =goarrive;
    	this.TrainDate = TrainDate;
    	this.type = type;

		// TODO Auto-generated constructor stub
	}
    public String CarToClass(int CarClass){
    	if(CarClass == 1100 || CarClass == 1101 || CarClass == 1103 || CarClass ==1108	)
    		return "自強";
    	else if(CarClass == 1102)
    		return "太魯閣";
    	else if(CarClass == 1107)
    		return "普悠瑪";
    	else if(CarClass == 1110 || CarClass == 1111 || CarClass == 1114 || CarClass ==1115)
    		return "莒光";
    	else if(CarClass == 1120)
    		return "復興";
    	else if(CarClass == 1131)
    		return "區間車";
    	else if(CarClass == 1132)
    		return "區間快";
    	else if(CarClass == 1140)
    		return "普快車";
    	else
    		return "無法辨識";
    				
    			
    }
    public String ToLine(String line){
    	int lineInt = Integer.parseInt(line);
    	if(lineInt == 1)
    		return "山";
    	else if(lineInt == 2)
    		return "海";
    	else
    		return "-";
    }
    @Override
    public void start(Stage stage) {
    	ticket ticket1;
    	TrainTimeTable trainTimeTable = new TrainTimeTable(startstation,endstation,c,d,goarrive,TrainDate,type);
    	String ChineseStartstation = fourToStation.setFourToStation(startstation);
    	String ChineseEndstation = fourToStation.setFourToStation(endstation);
        Scene scene = new Scene(new Group());
        stage.setTitle("TrainTimeTable");
        stage.setWidth(1100);
        stage.setHeight(1000);
 
        final Label label = new Label("Train Table");
        label.setFont(new Font("Arial", 20));
  
        table.setEditable(true);
        table.setMinWidth(1000);
        table.setMinHeight(900);
        TableColumn CarClassT = new TableColumn("車種");
        CarClassT.setCellValueFactory(new PropertyValueFactory<TrainTable,String>("CarClass"));
        TableColumn TrainT = new TableColumn("車次");
        TrainT.setCellValueFactory(new PropertyValueFactory<TrainTable,String>("Train"));
        TableColumn LineT = new TableColumn("經由");
        LineT.setCellValueFactory(new PropertyValueFactory<TrainTable,String>("Line"));
        TableColumn StartEndstationT = new TableColumn("發車站->終點站");
        StartEndstationT.setCellValueFactory(new PropertyValueFactory<TrainTable,String>("StartEndstation"));
        StartEndstationT.setMinWidth(130);
        TableColumn DepTimeT = new TableColumn(ChineseStartstation+"開車時間");
        DepTimeT.setCellValueFactory(new PropertyValueFactory<TrainTable,String>("DepTime"));
        TableColumn ArrTimeT = new TableColumn(ChineseEndstation+"抵達時間");
        ArrTimeT.setCellValueFactory(new PropertyValueFactory<TrainTable,String>("ArrTime"));
        TableColumn RunTimeT = new TableColumn("行駛時間");
        RunTimeT.setCellValueFactory(new PropertyValueFactory<TrainTable,String>("RunTime"));
        TableColumn NoteT = new TableColumn("Note");
        NoteT.setCellValueFactory(new PropertyValueFactory<TrainTable,String>("Note"));
        TableColumn ticketPriceT = new TableColumn("票價");
        ticketPriceT.setCellValueFactory(new PropertyValueFactory<TrainTable,String>("Ticket"));
        TableColumn BuyticketT = new TableColumn("訂票");
        ticket1 = new ticket(String.valueOf(startstation),String.valueOf(endstation));
        table.getColumns().addAll(CarClassT, TrainT, LineT,StartEndstationT,DepTimeT,ArrTimeT,RunTimeT,NoteT,ticketPriceT,BuyticketT);
        for(int i=0;i<trainTimeTable.allrecordarray.size();i++){
        	Map t,t1,t2,t3,t4;
        	String t5;
        	t = (Map)trainTimeTable.TrainInfos.get((int) ((allrecord)trainTimeTable.allrecordarray.get(i)).getrecordi());
        	trainTimeTable.TimeInfos = (List)t.get("TimeInfos");
        	t1 = (Map)trainTimeTable.TimeInfos.get(0);
        	t2 = (Map)trainTimeTable.TimeInfos.get(trainTimeTable.TimeInfos.size()-1);
        	t3 = (Map)trainTimeTable.TimeInfos.get((int) ((allrecord)trainTimeTable.allrecordarray.get(i)).getstartrecordk());
        	t4 = (Map)trainTimeTable.TimeInfos.get((int) ((allrecord)trainTimeTable.allrecordarray.get(i)).getendrecordk());
        	t5 = (String) ((allrecord)trainTimeTable.allrecordarray.get(i)).getrecordRuntime();
        	ticket1.setLineDir(String.valueOf((Integer.parseInt((String)t.get("LineDir"))+1)),Integer.parseInt((String) t.get("CarClass")));
        	try {
				data.add(new TrainTable(CarToClass(Integer.parseInt((String) t.get("CarClass"))),(String)t.get("Train"),ToLine((String)t.get("Line")),
						fourToStation.setFourToStation(Integer.parseInt((String)t1.get("Station")))+"->"+fourToStation.setFourToStation(Integer.parseInt(String.valueOf(t2.get("Station")))),(String)t3.get("DepTime")
						,(String)t4.get("ArrTime"),t5,(String)t.get("Note"),"$"+ticket1.setPrice()));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
     
        table.setItems(data);
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
        
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
 
    public static class TrainTable {
 
        private final SimpleStringProperty CarClass;
        private final SimpleStringProperty Train;
        private final SimpleStringProperty Line;
        private final SimpleStringProperty StartEndstation;
        private final SimpleStringProperty DepTime;
        private final SimpleStringProperty ArrTime;
        private final SimpleStringProperty RunTime;
        private final SimpleStringProperty Note;
        private final SimpleStringProperty Ticket;
        
 
        private TrainTable(String CarClass, String Train, String Line,String StartEndstation,String DepTime,String ArrTime,String RunTime,String Note,String ticket) throws UnsupportedEncodingException {
        	
        	this.CarClass = new SimpleStringProperty(CarClass);
            this.Train = new SimpleStringProperty(Train);
            this.Line = new SimpleStringProperty(Line);
            this.StartEndstation = new SimpleStringProperty(StartEndstation);
            this.DepTime = new SimpleStringProperty(DepTime);
            this.ArrTime = new SimpleStringProperty(ArrTime);
            this.RunTime = new SimpleStringProperty(RunTime);
            this.Note = new SimpleStringProperty(Note);
            this.Ticket = new SimpleStringProperty(ticket);
           
        }


        public String getCarClass() {
            return CarClass.get();
        }
        public String getTrain(){
			return Train.get();
		}
		public String getLine(){
			return Line.get();
		}
		public String getStartEndstation(){
			return StartEndstation.get();
		}
		public String getDepTime(){
			return DepTime.get();
		}
		public String getArrTime(){
			return ArrTime.get();
		}
		public String getRunTime(){
			return RunTime.get();
		}
		public String getNote(){
			return Note.get();
		}
		public String getTicket(){
			return Ticket.get();
		}
    }
} 