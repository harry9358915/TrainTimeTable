/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TimeTable;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/**
 *
 * @author MaLiang
 */
public class TRA extends Application {
    int a=1008;//台北 預設起點
    int b=1238;//高雄 預設終點
    int type=0;//0=所有 1=對號 2=非對號
    String c="00:00:00";//0 = 00:00
    String d="23:59:00";;//24 = 23:59
    int goarrive=1;//1=出發 2=到達
    LocalDate checkdate=LocalDate.now();
    DateTimeFormatter format= DateTimeFormatter.ofPattern("yyyyMMdd"); 
    String traindate=checkdate.format(format);
    
    @Override
    public void start(Stage primaryStage) {
       
       Pane root = new Pane();
       Label title = new Label("台鐵列車時刻查詢系統");
       title.setLayoutX(10);
       title.setLayoutY(10);
       title.setFont(Font.font("Serif", FontWeight.NORMAL, 50));
       root.getChildren().add(title);
       Label From = new Label("起程站");
       Label To = new Label("到達站");
       From.setLayoutX(10);
       From.setLayoutY(70);
       From.setFont(Font.font("Serif", FontWeight.NORMAL, 30));
       root.getChildren().add(From);
       To.setLayoutX(10);
       To.setLayoutY(150);
       To.setFont(Font.font("Serif", FontWeight.NORMAL, 30));
       root.getChildren().add(To);
       Label station1 = new Label("車站");
       Label station2 = new Label("車站");
       station1.setLayoutX(370);
       station1.setLayoutY(70);
       station1.setFont(Font.font("Serif", FontWeight.NORMAL, 30));
       root.getChildren().add(station1);
       station2.setLayoutX(370);
       station2.setLayoutY(150);
       station2.setFont(Font.font("Serif", FontWeight.NORMAL, 30));
       root.getChildren().add(station2);
       Label car = new Label("選擇車種");
       car.setLayoutX(150);
       car.setLayoutY(350);
       car.setFont(Font.font("Serif", FontWeight.NORMAL, 30));
       root.getChildren().add(car);
       Label date = new Label("選擇查詢日期");
       date.setLayoutX(580);
       date.setLayoutY(350);
       date.setFont(Font.font("Serif", FontWeight.NORMAL, 30));
       root.getChildren().add(date);
       Label title1 = new Label("您選擇的乘車時間為：");
       title1.setLayoutX(500);
       title1.setLayoutY(420);
       title1.setFont(Font.font("Serif", FontWeight.NORMAL, 20));
       root.getChildren().add(title1);
       Label date1 = new Label("日期");
       date1.setLayoutX(500);
       date1.setLayoutY(490);
       date1.setFont(Font.font("Serif", FontWeight.NORMAL, 20));
       root.getChildren().add(date1);
       Label time = new Label("時間");
       time.setLayoutX(500);
       time.setLayoutY(550);
       time.setFont(Font.font("Serif", FontWeight.NORMAL, 20));
       root.getChildren().add(time);
       Label toto = new Label("至");
       toto.setLayoutX(695);
       toto.setLayoutY(550);
       toto.setFont(Font.font("Serif", FontWeight.NORMAL, 20));
       root.getChildren().add(toto);
       
       
       String[] names = new String[] { "其他", "臺北", "板橋","桃園","中壢","新竹",
       "臺中","彰化","斗六","嘉義","臺南","高雄","屏東","宜蘭","花蓮","臺東" };  
       CheckBox[] cbs1 = new CheckBox[names.length];
       CheckBox[] cbs2 = new CheckBox[names.length];
       String[] cars = new String[] { "所有車種", "非對號列車", "對號列車" };  
       CheckBox[] cbs3 = new CheckBox[cars.length];
       String[] names1 = new String[] { "出發", "到達" };
       CheckBox[] cbs4 = new CheckBox[names1.length];
       
       for (int i = 0; i < names.length; i++){
           CheckBox cb1 = cbs1[i] = new CheckBox(names[i]); 
           CheckBox cb2 = cbs2[i] = new CheckBox(names[i]); 
           
           if(i>=0&&i<=7){
            cbs1[i].setLayoutX(450+i*70);
            cbs2[i].setLayoutX(450+i*70);
            cbs1[i].setLayoutY(70);
            cbs2[i].setLayoutY(150);
           }else if(i>=8&&i<=15){
            cbs1[i].setLayoutX(450+(i-8)*70);
            cbs2[i].setLayoutX(450+(i-8)*70);
            cbs1[i].setLayoutY(90);
            cbs2[i].setLayoutY(170);
           }
           root.getChildren().add(cbs1[i]);
           root.getChildren().add(cbs2[i]);
       }
       for (int i = 0; i < cars.length; i++){
           CheckBox cb3 = cbs3[i] = new CheckBox(cars[i]); 
           
            cbs3[i].setFont(Font.font("Serif", FontWeight.NORMAL, 30));
            cbs3[i].setLayoutX(100);
            cbs3[i].setLayoutY(450+i*70);
            root.getChildren().add(cbs3[i]);
           
       }
       for(int i=0;i<names1.length;i++){
           CheckBox cb4 = cbs4[i] = new CheckBox(names1[i]); 
           cbs4[i].setLayoutX(550+i*150);
           cbs4[i].setLayoutY(620);
           cbs4[i].setFont(Font.font("Serif", FontWeight.NORMAL, 30));
            
           
           root.getChildren().add(cbs4[i]);
       }
       cbs1[0].setSelected(true);
       cbs1[0].setDisable(true);
       cbs2[0].setSelected(true);
       cbs2[0].setDisable(true);
       cbs3[0].setSelected(true);
       cbs3[0].setDisable(true);
       cbs4[0].setSelected(true);
       cbs4[0].setDisable(true);
       
       Button start = new Button("開始查詢");
       start.setPrefSize(512, 60);
       start.setFont(Font.font("Serif", FontWeight.NORMAL, 30));
       
       start.setLayoutX(0);
       start.setLayoutY(708);
       root.getChildren().add(start);
       Button end = new Button("關閉視窗");
       end.setPrefSize(512, 60);
       end.setFont(Font.font("Serif", FontWeight.NORMAL, 30));
       
       end.setLayoutX(512);
       end.setLayoutY(708);
       root.getChildren().add(end);
       LocalDate today = LocalDate.now();
       LocalDate maxday = today.plusDays(59);
       
       DatePicker checkInDatePicker = new DatePicker();
       checkInDatePicker.setPrefSize(250, 50);
       checkInDatePicker.setLayoutX(580);
       checkInDatePicker.setLayoutY(470);
       checkInDatePicker.setValue(today);
       
       
       
       
       
       root.getChildren().add(checkInDatePicker);
       
       
       
       
       
       ObservableList<String> data1 = FXCollections.observableArrayList(
        "臺北/基隆地區", "桃園地區", "新竹地區","苗栗地區","臺中地區", 
        "彰化地區", "南投地區", "雲林地區", "嘉義地區","臺南地區", 
        "高雄地區", "屏東地區","臺東地區","花蓮地區","宜蘭地區",
        "平溪/深澳線","內灣/六家線","集集線","沙崙線");
       ObservableList<String> data2 = FXCollections.observableArrayList(
       "福隆","貢寮","雙溪","牡丹","三貂嶺","侯硐","瑞芳","四腳亭",
       "暖暖","基隆","三坑","八堵","七堵","百福","五堵","汐止","汐科",
       "南港","松山","臺北","萬華","板橋","浮洲","樹林","南樹林","山佳",
       "鶯歌"
       );
       ObservableList<String> data3 = FXCollections.observableArrayList(
       "大湖","路竹","岡山","橋頭","楠梓","新左營","左營","高雄",
       "鳳山","後庄","九曲堂"
       );
       ObservableList<String> data4 = FXCollections.observableArrayList(
       "桃園","內壢","中壢","埔心","楊梅","富岡"
       );
       ObservableList<String> data5 = FXCollections.observableArrayList(
       "湖口","新豐","竹北","北新竹","新竹","香山","北湖"
       );
       ObservableList<String> data6 = FXCollections.observableArrayList(
       "崎頂","竹南","談文","大山","後龍","龍港","白沙屯","新埔","通霄",
       "苑裡","造橋","豐富","苗栗","南勢","銅鑼","三義"
       );
       ObservableList<String> data7 = FXCollections.observableArrayList(
       "日南","大甲","臺中港","清水","沙鹿","龍井","大肚","追分","泰安",
       "后里","豐原","潭子","太原","臺中","大慶","烏日","新烏日","成功"
       );
       ObservableList<String> data8 = FXCollections.observableArrayList(
       "彰化","花壇","大村","員林","永靖","社頭","田中","二水"
       );
       ObservableList<String> data9 = FXCollections.observableArrayList(
       "源泉","濁水","龍泉","集集","水里","車埕"
       );
       ObservableList<String> data10 = FXCollections.observableArrayList(
       "林內","石榴","斗六","斗南","石龜"
       );
       ObservableList<String> data11 = FXCollections.observableArrayList(
       "大林","民雄","嘉北","嘉義","水上","南靖"
       );
       ObservableList<String> data12 = FXCollections.observableArrayList(
       "後壁","新營","柳營","林鳳營","隆田","拔林","善化","南科","新市",
       "永康","大橋","臺南","保安","仁德","中洲","長榮大學","沙崙"
       );
       ObservableList<String> data13 = FXCollections.observableArrayList(
       "六塊厝","屏東","歸來","麟洛","西勢","竹田","潮州","崁頂","南州",
       "鎮安","林邊","佳冬","東海","枋寮","加祿","內獅","枋山"
       );
       ObservableList<String> data14 = FXCollections.observableArrayList(
       "古莊","大武","瀧溪","金崙","太麻里","知本","康樂","臺東","山里",
       "鹿野","瑞源","瑞和","關山","海端","池上"
       );
       ObservableList<String> data15 = FXCollections.observableArrayList(
       "富里","東竹","東里","玉里","三民","瑞穗","富源","大富","光復",
       "萬榮","鳳林","南平","豐田","壽豐","平和","志學","吉安","花蓮",
       "北埔","景美","新城","崇德","和仁","和平"
       );
       ObservableList<String> data16 = FXCollections.observableArrayList(
       "漢本","武塔","南澳","東澳","永樂","蘇澳","蘇澳新","新馬","冬山",
       "羅東","中里","二結","宜蘭","四城","礁溪","頂埔","頭城","外澳",
       "龜山","大溪","大里","石城"
       );
       ObservableList<String> data17 = FXCollections.observableArrayList(
       "菁桐","平溪","嶺腳","望古","十分","大華","三貂嶺","海科館"
       );
       ObservableList<String> data18 = FXCollections.observableArrayList(
       "新竹","北新竹","千甲","新莊","竹中","六家","上員","榮華","竹東",
       "橫山","九讚頭","合興","富貴","內灣"
       );
       ObservableList<String> data19 = FXCollections.observableArrayList(
       "二水","源泉","濁水","龍泉","集集","水里","車埕"
       );
       ObservableList<String> data20 = FXCollections.observableArrayList(
       "長榮大學","沙崙"
       );
       ObservableList<String> time1 = FXCollections.observableArrayList(
       "00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00",
       "08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00",
       "16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"
       );
       ObservableList<String> time2 = FXCollections.observableArrayList(
       "01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00",
       "09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00",
       "17:00","18:00","19:00","20:00","21:00","22:00","23:00","23:59"
       );
       
       ComboBox combobox1 = new ComboBox();
       combobox1.setItems(FXCollections.observableArrayList(data1));
       combobox1.setPrefSize(150, 30);
       combobox1.setValue("臺北/基隆地區");
       combobox1.setEditable(false);
       combobox1.setLayoutX(110);
       combobox1.setLayoutY(70);
       
       root.getChildren().add(combobox1);
       ComboBox combobox2 = new ComboBox();
       combobox2.setItems(FXCollections.observableArrayList(data1));
       combobox2.setPrefSize(150, 30);
       combobox2.setValue("高雄地區");
       combobox2.setEditable(false);
       combobox2.setLayoutX(110);
       combobox2.setLayoutY(150);
       root.getChildren().add(combobox2);
       ComboBox combobox3 = new ComboBox();
       combobox3.setItems(FXCollections.observableArrayList(data2));
       combobox3.setPrefSize(85, 30);
       combobox3.setValue("臺北");
       combobox3.setEditable(false);
       combobox3.setLayoutX(280);
       combobox3.setLayoutY(70);
       root.getChildren().add(combobox3);
       ComboBox combobox4 = new ComboBox();
       combobox4.setItems(FXCollections.observableArrayList(data3));
       combobox4.setPrefSize(85, 30);
       combobox4.setValue("高雄");
       combobox4.setEditable(false);
       combobox4.setLayoutX(280);
       combobox4.setLayoutY(150);
       root.getChildren().add(combobox4);
       
       ComboBox combobox5 = new ComboBox();
       combobox5.setItems(FXCollections.observableArrayList(time1));
       combobox5.setPrefSize(100, 30);
       combobox5.setValue("00:00");
       combobox5.setEditable(false);
       combobox5.setLayoutX(580);
       combobox5.setLayoutY(550);
       root.getChildren().add(combobox5);
       
       ComboBox combobox6 = new ComboBox();
       combobox6.setItems(FXCollections.observableArrayList(time2));
       combobox6.setPrefSize(100, 30);
       combobox6.setValue("23:59");
       combobox6.setEditable(false);
       combobox6.setLayoutX(730);
       combobox6.setLayoutY(550);
       root.getChildren().add(combobox6);
       
       
       
       
       
       combobox1.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
            switch(t1){
                case "臺北/基隆地區":
                    combobox3.setItems(FXCollections.observableArrayList(data2));
                    combobox3.setValue("臺北");
                    a=1008;
                    break;
                case "桃園地區":
                    combobox3.setItems(FXCollections.observableArrayList(data4));
                    combobox3.setValue("桃園");
                    a=1015;
                    break;
                case "新竹地區":
                    combobox3.setItems(FXCollections.observableArrayList(data5));
                    combobox3.setValue("新竹");
                    a=1025;
                    break;
                case "苗栗地區":
                    combobox3.setItems(FXCollections.observableArrayList(data6));
                    combobox3.setValue("苗栗");
                    a=1305;
                    break;
                case "臺中地區":
                    combobox3.setItems(FXCollections.observableArrayList(data7));
                    combobox3.setValue("臺中");
                    a=1319;
                    break;
                case "彰化地區":
                    combobox3.setItems(FXCollections.observableArrayList(data8));
                    combobox3.setValue("彰化");
                    a=1120;
                    break;
                case "南投地區":
                    combobox3.setItems(FXCollections.observableArrayList(data9));
                    combobox3.setValue("集集");
                    a=2705;
                    break;
                case "雲林地區":
                    combobox3.setItems(FXCollections.observableArrayList(data10));
                    combobox3.setValue("斗六");
                    a=1210;
                    break;
                case "嘉義地區":
                    combobox3.setItems(FXCollections.observableArrayList(data11));
                    combobox3.setValue("嘉義");
                    a=1215;
                    break;
                case "臺南地區":
                    combobox3.setItems(FXCollections.observableArrayList(data12));
                    combobox3.setValue("臺南");
                    a=1228;
                    break;
                case "高雄地區":
                    combobox3.setItems(FXCollections.observableArrayList(data3));
                    combobox3.setValue("高雄");
                    a=1238;
                    break;
                case "屏東地區":
                    combobox3.setItems(FXCollections.observableArrayList(data13));
                    combobox3.setValue("屏東");
                    a=1406;
                    break;
                case "臺東地區":
                    combobox3.setItems(FXCollections.observableArrayList(data14));
                    combobox3.setValue("臺東");
                    a=1632;
                    break;
                case "花蓮地區":
                    combobox3.setItems(FXCollections.observableArrayList(data15));
                    combobox3.setValue("花蓮");
                    a=1715;
                    break;
                case "宜蘭地區":
                    combobox3.setItems(FXCollections.observableArrayList(data16));
                    combobox3.setValue("宜蘭");
                    a=1820;
                    break;
                case "平溪/深澳線":
                    combobox3.setItems(FXCollections.observableArrayList(data17));
                    combobox3.setValue("平溪");
                    a=1907;
                    break;
                case "內灣/六家線":
                    combobox3.setItems(FXCollections.observableArrayList(data18));
                    combobox3.setValue("內灣");
                    a=2210;
                    break;
                case "集集線":
                    combobox3.setItems(FXCollections.observableArrayList(data19));
                    combobox3.setValue("集集");
                    a=2705;
                    break;
                case "沙崙線":
                    combobox3.setItems(FXCollections.observableArrayList(data20));
                    combobox3.setValue("沙崙");
                    a=5102;
                    break;
            }
        }    
    });
       combobox2.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
            switch(t1){
                case "臺北/基隆地區":
                    combobox4.setItems(FXCollections.observableArrayList(data2));
                    combobox4.setValue("臺北");
                    b=1008;
                    break;
                case "桃園地區":
                    combobox4.setItems(FXCollections.observableArrayList(data4));
                    combobox4.setValue("桃園");
                    b=1015;
                    break;
                case "新竹地區":
                    combobox4.setItems(FXCollections.observableArrayList(data5));
                    combobox4.setValue("新竹");
                    b=1025;
                    break;
                case "苗栗地區":
                    combobox4.setItems(FXCollections.observableArrayList(data6));
                    combobox4.setValue("苗栗");
                    b=1305;
                    break;
                case "臺中地區":
                    combobox4.setItems(FXCollections.observableArrayList(data7));
                    combobox4.setValue("臺中");
                    b=1319;
                    break;
                case "彰化地區":
                    combobox4.setItems(FXCollections.observableArrayList(data8));
                    combobox4.setValue("彰化");
                    b=1120;
                    break;
                case "南投地區":
                    combobox4.setItems(FXCollections.observableArrayList(data9));
                    combobox4.setValue("集集");
                    b=2705;
                    break;
                case "雲林地區":
                    combobox4.setItems(FXCollections.observableArrayList(data10));
                    combobox4.setValue("斗六");
                    b=1210;
                    break;
                case "嘉義地區":
                    combobox4.setItems(FXCollections.observableArrayList(data11));
                    combobox4.setValue("嘉義");
                    b=1215;
                    break;
                case "臺南地區":
                    combobox4.setItems(FXCollections.observableArrayList(data12));
                    combobox4.setValue("臺南");
                    b=1228;
                    break;
                case "高雄地區":
                    combobox4.setItems(FXCollections.observableArrayList(data3));
                    combobox4.setValue("高雄");
                    b=1238;
                    break;
                case "屏東地區":
                    combobox4.setItems(FXCollections.observableArrayList(data13));
                    combobox4.setValue("屏東");
                    b=1406;
                    break;
                case "臺東地區":
                    combobox4.setItems(FXCollections.observableArrayList(data14));
                    combobox4.setValue("臺東");
                    b=1632;
                    break;
                case "花蓮地區":
                    combobox4.setItems(FXCollections.observableArrayList(data15));
                    combobox4.setValue("花蓮");
                    b=1715;
                    break;
                case "宜蘭地區":
                    combobox4.setItems(FXCollections.observableArrayList(data16));
                    combobox4.setValue("宜蘭");
                    b=1820;
                    break;
                case "平溪/深澳線":
                    combobox4.setItems(FXCollections.observableArrayList(data17));
                    combobox4.setValue("平溪");
                    b=1907;
                    break;
                case "內灣/六家線":
                    combobox4.setItems(FXCollections.observableArrayList(data18));
                    combobox4.setValue("內灣");
                    b=2210;
                    break;
                case "集集線":
                    combobox4.setItems(FXCollections.observableArrayList(data19));
                    combobox4.setValue("集集");
                    b=2705;
                    break;
                case "沙崙線":
                    combobox4.setItems(FXCollections.observableArrayList(data20));
                    combobox4.setValue("沙崙");
                    b=5102;
                    break;
            }
        }    
    });
       combobox3.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
            switch(t1){
                case "福隆":a=1810;break;
                case "貢寮":a=1809;break;
                case "雙溪":a=1808;break;
                case "牡丹":a=1807;break;
                case "三貂嶺":a=1806;break;
                case "侯硐":a=1805;break;
                case "瑞芳":a=1804;break;
                case "四腳亭":a=1803;break;
                case "暖暖":a=1802;break;
                case "基隆":a=1001;break;
                case "三坑":a=1029;break;
                case "八堵":a=1002;break;
                case "七堵":a=1003;break;
                case "百福":a=1030;break;
                case "五堵":a=1004;break;
                case "汐止":a=1005;break;
                case "汐科":a=1031;break;
                case "南港":a=1006;break;
                case "松山":a=1007;break;
                case "臺北":a=1008;break;
                case "萬華":a=1009;break;
                case "板橋":a=1011;break;
                case "浮洲":a=1032;break;
                case "樹林":a=1012;break;
                case "南樹林":a=1034;break;
                case "山佳":a=1013;break;
                case "鶯歌":a=1014;break;
                case "大湖":a=1231;break;
                case "路竹":a=1232;break;
                case "岡山":a=1233;break;
                case "橋頭":a=1234;break;
                case "楠梓":a=1235;break;
                case "新左營":a=1242;break;
                case "左營":a=1236;break;
                case "高雄":a=1238;break;
                case "鳳山":a=1402;break;
                case "後庄":a=1403;break;
                case "九曲堂":a=1404;break;
                case "桃園":a=1015;break;
                case "內壢":a=1016;break;
                case "中壢":a=1017;break;
                case "埔心":a=1018;break;
                case "楊梅":a=1019;break;
                case "富岡":a=1020;break;
                case "湖口":a=1021;break;
                case "新豐":a=1022;break;
                case "竹北":a=1023;break;
                case "北新竹":a=1024;break;
                case "新竹":a=1025;break;
                case "香山":a=1026;break;
                case "北湖":a=1033;break;
                case "崎頂":a=1027;break;
                case "竹南":a=1028;break;
                case "談文":a=1102;break;
                case "大山":a=1104;break;
                case "後龍":a=1105;break;
                case "龍港":a=1106;break;
                case "白沙屯":a=1107;break;
                case "新埔":a=1108;break;
                case "通霄":a=1109;break;
                case "苑裡":a=1110;break;
                case "造橋":a=1302;break;
                case "豐富":a=1304;break;
                case "苗栗":a=1305;break;
                case "南勢":a=1307;break;
                case "銅鑼":a=1308;break;
                case "三義":a=1310;break;
                case "日南":a=1111;break;
                case "大甲":a=1112;break;
                case "臺中港":a=1113;break;
                case "清水":a=1114;break;
                case "沙鹿":a=1115;break;
                case "龍井":a=1116;break;
                case "大肚":a=1117;break;
                case "追分":a=1118;break;
                case "泰安":a=1314;break;
                case "后里":a=1315;break;
                case "豐原":a=1317;break;
                case "潭子":a=1318;break;
                case "太原":a=1323;break;
                case "臺中":a=1319;break;
                case "大慶":a=1322;break;
                case "烏日":a=1320;break;
                case "新烏日":a=1324;break;
                case "成功":a=1321;break;
                case "彰化":a=1120;break;
                case "花壇":a=1202;break;
                case "大村":a=1240;break;
                case "員林":a=1203;break;
                case "永靖":a=1204;break;
                case "社頭":a=1205;break;
                case "田中":a=1206;break;
                case "二水":a=1207;break;
                case "源泉":a=2702;break;
                case "濁水":a=2703;break;
                case "龍泉":a=2704;break;
                case "集集":a=2705;break;
                case "水里":a=2706;break;
                case "車埕":a=2707;break;
                case "林內":a=1208;break;
                case "石榴":a=1209;break;
                case "斗六":a=1210;break;
                case "斗南":a=1211;break;
                case "石龜":a=1212;break;
                case "大林":a=1213;break;
                case "民雄":a=1214;break;
                case "嘉北":a=1241;break;
                case "嘉義":a=1215;break;
                case "水上":a=1217;break;
                case "南靖":a=1218;break;
                case "後壁":a=1219;break;
                case "新營":a=1220;break;
                case "柳營":a=1221;break;
                case "林鳳營":a=1222;break;
                case "隆田":a=1223;break;
                case "拔林":a=1224;break;
                case "善化":a=1225;break;
                case "南科":a=1244;break;
                case "新市":a=1226;break;
                case "永康":a=1227;break;
                case "大橋":a=1239;break;
                case "臺南":a=1228;break;
                case "保安":a=1229;break;
                case "仁德":a=1243;break;
                case "中洲":a=1230;break;
                case "長榮大學":a=5101;break;
                case "沙崙":a=5102;break;
                case "六塊厝":a=1405;break;
                case "屏東":a=1406;break;
                case "歸來":a=1407;break;
                case "麟洛":a=1408;break;
                case "西勢":a=1409;break;
                case "竹田":a=1410;break;
                case "潮州":a=1411;break;
                case "崁頂":a=1412;break;
                case "南州":a=1413;break;
                case "鎮安":a=1414;break;
                case "林邊":a=1415;break;
                case "佳冬":a=1416;break;
                case "東海":a=1417;break;
                case "枋寮":a=1418;break;
                case "加祿":a=1502;break;
                case "內獅":a=1503;break;
                case "枋山":a=1504;break;
                case "古莊":a=1507;break;
                case "大武":a=1508;break;
                case "瀧溪":a=1510;break;
                case "金崙":a=1512;break;
                case "太麻里":a=1514;break;
                case "知本":a=1516;break;
                case "康樂":a=1517;break;
                case "臺東":a=1632;break;
                case "山里":a=1631;break;
                case "鹿野":a=1630;break;
                case "瑞源":a=1629;break;
                case "瑞和":a=1628;break;
                case "關山":a=1626;break;
                case "海端":a=1625;break;
                case "池上":a=1624;break;
                case "富里":a=1623;break;
                case "東竹":a=1622;break;
                case "東里":a=1621;break;
                case "玉里":a=1619;break;
                case "三民":a=1617;break;
                case "瑞穗":a=1616;break;
                case "富源":a=1614;break;
                case "大富":a=1613;break;
                case "光復":a=1612;break;
                case "萬榮":a=1611;break;
                case "鳳林":a=1610;break;
                case "南平":a=1609;break;
                case "豐田":a=1607;break;
                case "壽豐":a=1606;break;
                case "平和":a=1605;break;
                case "志學":a=1604;break;
                case "吉安":a=1602;break;
                case "花蓮":a=1715;break;
                case "北埔":a=1714;break;
                case "景美":a=1713;break;
                case "新城":a=1712;break;
                case "崇德":a=1711;break;
                case "和仁":a=1710;break;
                case "和平":a=1709;break;
                case "漢本":a=1708;break;
                case "武塔":a=1706;break;
                case "南澳":a=1705;break;
                case "東澳":a=1704;break;
                case "永樂":a=1703;break;
                case "蘇澳":a=1826;break;
                case "蘇澳新":a=1827;break;
                case "新馬":a=1825;break;
                case "冬山":a=1824;break;
                case "羅東":a=1823;break;
                case "中里":a=1822;break;
                case "二結":a=1821;break;
                case "宜蘭":a=1820;break;
                case "四城":a=1819;break;
                case "礁溪":a=1818;break;
                case "頂埔":a=1817;break;
                case "頭城":a=1816;break;
                case "外澳":a=1815;break;
                case "龜山":a=1814;break;
                case "大溪":a=1813;break;
                case "大里":a=1812;break;
                case "石城":a=1811;break;
                case "菁桐":a=1908;break;
                case "平溪":a=1907;break;
                case "嶺腳":a=1906;break;
                case "望古":a=1905;break;
                case "十分":a=1904;break;
                case "大華":a=1903;break;
                case "海科館":a=6103;break;
                case "千甲":a=2212;break;
                case "新莊":a=2213;break;
                case "竹中":a=2203;break;
                case "六家":a=2214;break;
                case "上員":a=2204;break;
                case "榮華":a=2211;break;
                case "竹東":a=2205;break;
                case "橫山":a=2206;break;
                case "九讚頭":a=2207;break;
                case "合興":a=2208;break;
                case "富貴":a=2209;break;
                case "內灣":a=2210;break;
            }
        }    
    });
       combobox4.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
            switch(t1){
                case "福隆":b=1810;break;
                case "貢寮":b=1809;break;
                case "雙溪":b=1808;break;
                case "牡丹":b=1807;break;
                case "三貂嶺":b=1806;break;
                case "侯硐":b=1805;break;
                case "瑞芳":b=1804;break;
                case "四腳亭":b=1803;break;
                case "暖暖":b=1802;break;
                case "基隆":b=1001;break;
                case "三坑":b=1029;break;
                case "八堵":b=1002;break;
                case "七堵":b=1003;break;
                case "百福":b=1030;break;
                case "五堵":b=1004;break;
                case "汐止":b=1005;break;
                case "汐科":b=1031;break;
                case "南港":b=1006;break;
                case "松山":b=1007;break;
                case "臺北":b=1008;break;
                case "萬華":b=1009;break;
                case "板橋":b=1011;break;
                case "浮洲":b=1032;break;
                case "樹林":b=1012;break;
                case "南樹林":b=1034;break;
                case "山佳":b=1013;break;
                case "鶯歌":b=1014;break;
                case "大湖":b=1231;break;
                case "路竹":b=1232;break;
                case "岡山":b=1233;break;
                case "橋頭":b=1234;break;
                case "楠梓":b=1235;break;
                case "新左營":b=1242;break;
                case "左營":b=1236;break;
                case "高雄":b=1238;break;
                case "鳳山":b=1402;break;
                case "後庄":b=1403;break;
                case "九曲堂":b=1404;break;
                case "桃園":b=1015;break;
                case "內壢":b=1016;break;
                case "中壢":b=1017;break;
                case "埔心":b=1018;break;
                case "楊梅":b=1019;break;
                case "富岡":b=1020;break;
                case "湖口":b=1021;break;
                case "新豐":b=1022;break;
                case "竹北":b=1023;break;
                case "北新竹":b=1024;break;
                case "新竹":b=1025;break;
                case "香山":b=1026;break;
                case "北湖":b=1033;break;
                case "崎頂":b=1027;break;
                case "竹南":b=1028;break;
                case "談文":b=1102;break;
                case "大山":b=1104;break;
                case "後龍":b=1105;break;
                case "龍港":b=1106;break;
                case "白沙屯":b=1107;break;
                case "新埔":b=1108;break;
                case "通霄":b=1109;break;
                case "苑裡":b=1110;break;
                case "造橋":b=1302;break;
                case "豐富":b=1304;break;
                case "苗栗":b=1305;break;
                case "南勢":b=1307;break;
                case "銅鑼":b=1308;break;
                case "三義":b=1310;break;
                case "日南":b=1111;break;
                case "大甲":b=1112;break;
                case "臺中港":b=1113;break;
                case "清水":b=1114;break;
                case "沙鹿":b=1115;break;
                case "龍井":b=1116;break;
                case "大肚":b=1117;break;
                case "追分":b=1118;break;
                case "泰安":b=1314;break;
                case "后里":b=1315;break;
                case "豐原":b=1317;break;
                case "潭子":b=1318;break;
                case "太原":b=1323;break;
                case "臺中":b=1319;break;
                case "大慶":b=1322;break;
                case "烏日":b=1320;break;
                case "新烏日":b=1324;break;
                case "成功":b=1321;break;
                case "彰化":b=1120;break;
                case "花壇":b=1202;break;
                case "大村":b=1240;break;
                case "員林":b=1203;break;
                case "永靖":b=1204;break;
                case "社頭":b=1205;break;
                case "田中":b=1206;break;
                case "二水":b=1207;break;
                case "源泉":b=2702;break;
                case "濁水":b=2703;break;
                case "龍泉":b=2704;break;
                case "集集":b=2705;break;
                case "水里":b=2706;break;
                case "車埕":b=2707;break;
                case "林內":b=1208;break;
                case "石榴":b=1209;break;
                case "斗六":b=1210;break;
                case "斗南":b=1211;break;
                case "石龜":b=1212;break;
                case "大林":b=1213;break;
                case "民雄":b=1214;break;
                case "嘉北":b=1241;break;
                case "嘉義":b=1215;break;
                case "水上":b=1217;break;
                case "南靖":b=1218;break;
                case "後壁":b=1219;break;
                case "新營":b=1220;break;
                case "柳營":b=1221;break;
                case "林鳳營":b=1222;break;
                case "隆田":b=1223;break;
                case "拔林":b=1224;break;
                case "善化":b=1225;break;
                case "南科":b=1244;break;
                case "新市":b=1226;break;
                case "永康":b=1227;break;
                case "大橋":b=1239;break;
                case "臺南":b=1228;break;
                case "保安":b=1229;break;
                case "仁德":b=1243;break;
                case "中洲":b=1230;break;
                case "長榮大學":b=5101;break;
                case "沙崙":b=5102;break;
                case "六塊厝":b=1405;break;
                case "屏東":b=1406;break;
                case "歸來":b=1407;break;
                case "麟洛":b=1408;break;
                case "西勢":b=1409;break;
                case "竹田":b=1410;break;
                case "潮州":b=1411;break;
                case "崁頂":b=1412;break;
                case "南州":b=1413;break;
                case "鎮安":b=1414;break;
                case "林邊":b=1415;break;
                case "佳冬":b=1416;break;
                case "東海":b=1417;break;
                case "枋寮":b=1418;break;
                case "加祿":b=1502;break;
                case "內獅":b=1503;break;
                case "枋山":b=1504;break;
                case "古莊":b=1507;break;
                case "大武":b=1508;break;
                case "瀧溪":b=1510;break;
                case "金崙":b=1512;break;
                case "太麻里":b=1514;break;
                case "知本":b=1516;break;
                case "康樂":b=1517;break;
                case "臺東":b=1632;break;
                case "山里":b=1631;break;
                case "鹿野":b=1630;break;
                case "瑞源":b=1629;break;
                case "瑞和":b=1628;break;
                case "關山":b=1626;break;
                case "海端":b=1625;break;
                case "池上":b=1624;break;
                case "富里":b=1623;break;
                case "東竹":b=1622;break;
                case "東里":b=1621;break;
                case "玉里":b=1619;break;
                case "三民":b=1617;break;
                case "瑞穗":b=1616;break;
                case "富源":b=1614;break;
                case "大富":b=1613;break;
                case "光復":b=1612;break;
                case "萬榮":b=1611;break;
                case "鳳林":b=1610;break;
                case "南平":b=1609;break;
                case "豐田":b=1607;break;
                case "壽豐":b=1606;break;
                case "平和":b=1605;break;
                case "志學":b=1604;break;
                case "吉安":b=1602;break;
                case "花蓮":b=1715;break;
                case "北埔":b=1714;break;
                case "景美":b=1713;break;
                case "新城":b=1712;break;
                case "崇德":b=1711;break;
                case "和仁":b=1710;break;
                case "和平":b=1709;break;
                case "漢本":b=1708;break;
                case "武塔":b=1706;break;
                case "南澳":b=1705;break;
                case "東澳":b=1704;break;
                case "永樂":b=1703;break;
                case "蘇澳":b=1826;break;
                case "蘇澳新":b=1827;break;
                case "新馬":b=1825;break;
                case "冬山":b=1824;break;
                case "羅東":b=1823;break;
                case "中里":b=1822;break;
                case "二結":b=1821;break;
                case "宜蘭":b=1820;break;
                case "四城":b=1819;break;
                case "礁溪":b=1818;break;
                case "頂埔":b=1817;break;
                case "頭城":b=1816;break;
                case "外澳":b=1815;break;
                case "龜山":b=1814;break;
                case "大溪":b=1813;break;
                case "大里":b=1812;break;
                case "石城":b=1811;break;
                case "菁桐":b=1908;break;
                case "平溪":b=1907;break;
                case "嶺腳":b=1906;break;
                case "望古":b=1905;break;
                case "十分":b=1904;break;
                case "大華":b=1903;break;
                case "海科館":b=6103;break;
                case "千甲":b=2212;break;
                case "新莊":b=2213;break;
                case "竹中":b=2203;break;
                case "六家":b=2214;break;
                case "上員":b=2204;break;
                case "榮華":b=2211;break;
                case "竹東":b=2205;break;
                case "橫山":b=2206;break;
                case "九讚頭":b=2207;break;
                case "合興":b=2208;break;
                case "富貴":b=2209;break;
                case "內灣":b=2210;break;
            }
            
        }    
    });
       
       combobox5.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
            switch(t1){
                case "00:00":c="00:00:00";break;
                case "01:00":c="01:00:00";break;
                case "02:00":c="02:00:00";break;
                case "03:00":c="03:00:00";break;
                case "04:00":c="04:00:00";break;
                case "05:00":c="05:00:00";break;
                case "06:00":c="06:00:00";break;
                case "07:00":c="07:00:00";break;
                case "08:00":c="08:00:00";break;
                case "09:00":c="09:00:00";break;
                case "10:00":c="10:00:00";break;
                case "11:00":c="11:00:00";break;
                case "12:00":c="12:00:00";break;
                case "13:00":c="13:00:00";break;
                case "14:00":c="14:00:00";break;
                case "15:00":c="15:00:00";break;
                case "16:00":c="16:00:00";break;
                case "17:00":c="17:00:00";break;
                case "18:00":c="18:00:00";break;
                case "19:00":c="19:00:00";break;
                case "20:00":c="20:00:00";break;
                case "21:00":c="21:00:00";break;
                case "22:00":c="22:00:00";break;
                case "23:00":c="23:00:00";break;
            }
        }    
    });
       combobox6.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
            switch(t1){
                case "23:59":d="23:59:00";break;
                case "01:00":d="01:00:00";break;
                case "02:00":d="02:00:00";break;
                case "03:00":d="03:00:00";break;
                case "04:00":d="04:00:00";break;
                case "05:00":d="05:00:00";break;
                case "06:00":d="06:00:00";break;
                case "07:00":d="07:00:00";break;
                case "08:00":d="08:00:00";break;
                case "09:00":d="09:00:00";break;
                case "10:00":d="10:00:00";break;
                case "11:00":d="11:00:00";break;
                case "12:00":d="12:00:00";break;
                case "13:00":d="13:00:00";break;
                case "14:00":d="14:00:00";break;
                case "15:00":d="15:00:00";break;
                case "16:00":d="16:00:00";break;
                case "17:00":d="17:00:00";break;
                case "18:00":d="18:00:00";break;
                case "19:00":d="19:00:00";break;
                case "20:00":d="20:00:00";break;
                case "21:00":d="21:00:00";break;
                case "22:00":d="22:00:00";break;
                case "23:00":d="23:00:00";break;
            }
        }    
    });
       cbs1[0].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox1.setDisable(false);
                combobox3.setDisable(false);
                cbs1[0].setDisable(true);
                a=1008;
                combobox1.setItems(FXCollections.observableArrayList(data1));
                combobox1.setValue("臺北/基隆地區");
                combobox3.setItems(FXCollections.observableArrayList(data2));
                combobox3.setValue("臺北");
               for(int i=0;i<16;i++){
                   if(i!=0){
                        cbs1[i].setSelected(false);
                        cbs1[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs2[0].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox2.setDisable(false);
                combobox4.setDisable(false);
                cbs2[0].setDisable(true);
                b=1238;
                combobox2.setItems(FXCollections.observableArrayList(data1));
                combobox2.setValue("高雄地區");
                combobox4.setItems(FXCollections.observableArrayList(data3));
                combobox4.setValue("高雄");
               for(int i=0;i<16;i++){
                   if(i!=0){
                        cbs2[i].setSelected(false);
                        cbs2[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs1[1].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox1.setDisable(true);
                combobox3.setDisable(true);
                cbs1[1].setDisable(true);
                a=1008;
               for(int i=0;i<16;i++){
                   if(i!=1){
                        cbs1[i].setSelected(false);
                        cbs1[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs2[1].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox2.setDisable(true);
                combobox4.setDisable(true);
                cbs2[1].setDisable(true);
                b=1008;
               for(int i=0;i<16;i++){
                   if(i!=1){
                        cbs2[i].setSelected(false);
                        cbs2[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs1[2].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox1.setDisable(true);
                combobox3.setDisable(true);
                cbs1[2].setDisable(true);
                a=1011;
               for(int i=0;i<16;i++){
                   if(i!=2){
                        cbs1[i].setSelected(false);
                        cbs1[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs2[2].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox2.setDisable(true);
                combobox4.setDisable(true);
                cbs2[2].setDisable(true);
                b=1011;
               for(int i=0;i<16;i++){
                   if(i!=2){
                        cbs2[i].setSelected(false);
                        cbs2[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs1[3].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox1.setDisable(true);
                combobox3.setDisable(true);
                cbs1[3].setDisable(true);
                a=1015;
               for(int i=0;i<16;i++){
                   if(i!=3){
                        cbs1[i].setSelected(false);
                        cbs1[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs2[3].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox2.setDisable(true);
                combobox4.setDisable(true);
                cbs2[3].setDisable(true);
                b=1015;
               for(int i=0;i<16;i++){
                   if(i!=3){
                        cbs2[i].setSelected(false);
                        cbs2[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs1[4].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox1.setDisable(true);
                combobox3.setDisable(true);
                cbs1[4].setDisable(true);
                a=1017;
               for(int i=0;i<16;i++){
                   if(i!=4){
                        cbs1[i].setSelected(false);
                        cbs1[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs2[4].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox2.setDisable(true);
                combobox4.setDisable(true);
                cbs2[4].setDisable(true);
                b=1017;
               for(int i=0;i<16;i++){
                   if(i!=4){
                        cbs2[i].setSelected(false);
                        cbs2[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs1[5].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox1.setDisable(true);
                combobox3.setDisable(true);
                cbs1[5].setDisable(true);
                a=1025;
               for(int i=0;i<16;i++){
                   if(i!=5){
                        cbs1[i].setSelected(false);
                        cbs1[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs2[5].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox2.setDisable(true);
                combobox4.setDisable(true);
                cbs2[5].setDisable(true);
                b=1025;
               for(int i=0;i<16;i++){
                   if(i!=5){
                        cbs2[i].setSelected(false);
                        cbs2[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs1[6].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox1.setDisable(true);
                combobox3.setDisable(true);
                cbs1[6].setDisable(true);
                a=1319;
               for(int i=0;i<16;i++){
                   if(i!=6){
                        cbs1[i].setSelected(false);
                        cbs1[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs2[6].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox2.setDisable(true);
                combobox4.setDisable(true);
                cbs2[6].setDisable(true);
                b=1319;
               for(int i=0;i<16;i++){
                   if(i!=6){
                        cbs2[i].setSelected(false);
                        cbs2[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs1[7].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox1.setDisable(true);
                combobox3.setDisable(true);
                cbs1[7].setDisable(true);
                a=1120;
               for(int i=0;i<16;i++){
                   if(i!=7){
                        cbs1[i].setSelected(false);
                        cbs1[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs2[7].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox2.setDisable(true);
                combobox4.setDisable(true);
                cbs2[7].setDisable(true);
                b=1120;
               for(int i=0;i<16;i++){
                   if(i!=7){
                        cbs2[i].setSelected(false);
                        cbs2[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs1[8].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox1.setDisable(true);
                combobox3.setDisable(true);
                cbs1[8].setDisable(true);
                a=1210;
               for(int i=0;i<16;i++){
                   if(i!=8){
                        cbs1[i].setSelected(false);
                        cbs1[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs2[8].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox2.setDisable(true);
                combobox4.setDisable(true);
                cbs2[8].setDisable(true);
                b=1210;
               for(int i=0;i<16;i++){
                   if(i!=8){
                        cbs2[i].setSelected(false);
                        cbs2[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs1[9].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox1.setDisable(true);
                combobox3.setDisable(true);
                cbs1[9].setDisable(true);
                a=1215;
               for(int i=0;i<16;i++){
                   if(i!=9){
                        cbs1[i].setSelected(false);
                        cbs1[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs2[9].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox2.setDisable(true);
                combobox4.setDisable(true);
                cbs2[9].setDisable(true);
                b=1215;
               for(int i=0;i<16;i++){
                   if(i!=9){
                        cbs2[i].setSelected(false);
                        cbs2[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs1[10].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox1.setDisable(true);
                combobox3.setDisable(true);
                cbs1[10].setDisable(true);
                a=1228;
               for(int i=0;i<16;i++){
                   if(i!=10){
                        cbs1[i].setSelected(false);
                        cbs1[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs2[10].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox2.setDisable(true);
                combobox4.setDisable(true);
                cbs2[10].setDisable(true);
                b=1228;
               for(int i=0;i<16;i++){
                   if(i!=10){
                        cbs2[i].setSelected(false);
                        cbs2[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs1[11].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox1.setDisable(true);
                combobox3.setDisable(true);
                cbs1[11].setDisable(true);
                a=1238;
               for(int i=0;i<16;i++){
                   if(i!=11){
                        cbs1[i].setSelected(false);
                        cbs1[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs2[11].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox2.setDisable(true);
                combobox4.setDisable(true);
                cbs2[11].setDisable(true);
                b=1238;
               for(int i=0;i<16;i++){
                   if(i!=11){
                        cbs2[i].setSelected(false);
                        cbs2[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs1[12].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox1.setDisable(true);
                combobox3.setDisable(true);
                cbs1[12].setDisable(true);
                a=1406;
               for(int i=0;i<16;i++){
                   if(i!=12){
                        cbs1[i].setSelected(false);
                        cbs1[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs2[12].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox2.setDisable(true);
                combobox4.setDisable(true);
                cbs2[12].setDisable(true);
                b=1406;
               for(int i=0;i<16;i++){
                   if(i!=12){
                        cbs2[i].setSelected(false);
                        cbs2[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs1[13].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox1.setDisable(true);
                combobox3.setDisable(true);
                cbs1[13].setDisable(true);
                a=1820;
               for(int i=0;i<16;i++){
                   if(i!=13){
                        cbs1[i].setSelected(false);
                        cbs1[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs2[13].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox2.setDisable(true);
                combobox4.setDisable(true);
                cbs2[13].setDisable(true);
                b=1820;
               for(int i=0;i<16;i++){
                   if(i!=13){
                        cbs2[i].setSelected(false);
                        cbs2[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs1[14].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox1.setDisable(true);
                combobox3.setDisable(true);
                cbs1[14].setDisable(true);
                a=1715;
               for(int i=0;i<16;i++){
                   if(i!=14){
                        cbs1[i].setSelected(false);
                        cbs1[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs2[14].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox2.setDisable(true);
                combobox4.setDisable(true);
                cbs2[14].setDisable(true);
                b=1715;
               for(int i=0;i<16;i++){
                   if(i!=14){
                        cbs2[i].setSelected(false);
                        cbs2[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs1[15].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox1.setDisable(true);
                combobox3.setDisable(true);
                cbs1[15].setDisable(true);
                a=1632;
               for(int i=0;i<16;i++){
                   if(i!=15){
                        cbs1[i].setSelected(false);
                        cbs1[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs2[15].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                combobox2.setDisable(true);
                combobox4.setDisable(true);
                cbs2[15].setDisable(true);
                b=1632;
               for(int i=0;i<16;i++){
                   if(i!=15){
                        cbs2[i].setSelected(false);
                        cbs2[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs3[0].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                
                cbs3[0].setDisable(true);
                type=0;
               for(int i=0;i<3;i++){
                   if(i!=0){
                        cbs3[i].setSelected(false);
                        cbs3[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs3[1].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                
                cbs3[1].setDisable(true);
                type=1;
               for(int i=0;i<3;i++){
                   if(i!=1){
                        cbs3[i].setSelected(false);
                        cbs3[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs3[2].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                
                cbs3[2].setDisable(true);
                type=2;
               for(int i=0;i<3;i++){
                   if(i!=2){
                        cbs3[i].setSelected(false);
                        cbs3[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs4[0].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                goarrive=1;
                cbs4[0].setDisable(true);
                
               for(int i=0;i<2;i++){
                   if(i!=0){
                        cbs4[i].setSelected(false);
                        cbs4[i].setDisable(false);
                   }
               }
            }
                
        });
       cbs4[1].setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                goarrive=2;
                cbs4[1].setDisable(true);
                
               for(int i=0;i<2;i++){
                   if(i!=1){
                        cbs4[i].setSelected(false);
                        cbs4[i].setDisable(false);
                   }
               }
            }
                
        });
       end.setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
               primaryStage.close();
            }
        });
       
       checkInDatePicker.setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
               checkdate=checkInDatePicker.getValue();
               
               traindate=checkdate.format(format);
               
               
               
            }
        });
       start.setOnAction(new EventHandler<ActionEvent>() {
             @Override 
            public void handle(ActionEvent e) {
                 TrainResult trainResult = new TrainResult();
                 System.out.println(traindate);
                 System.out.println(goarrive);
                 trainResult.s(a,b,c,d,goarrive,traindate,String.valueOf(type));
                 try {
					DownloadFile downloadFile = new DownloadFile(traindate+".zip","http://163.29.3.98/json/",traindate+".zip");
					UnZip unZip = new UnZip(traindate);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                 Stage secondStage = new Stage();
                 trainResult.start(secondStage);
                 primaryStage.close();
            }
        });
        
        
       
        
       Scene scene = new Scene(root, 1024, 768);
        
       primaryStage.setTitle("台鐵列車時刻查詢系統");
       primaryStage.setScene(scene);
       primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        launch(args);
    }
    
}
