# TrainTimeTable
利用台鐵提供每天列車之行駛之開放資料，做出台鐵火車時刻表。

台鐵提供每天列車之行駛之開放資料欄位說明，請參考[台鐵火車時刻網際資訊交換說明.rtf]，資料參考位置為http://163.29.3.98/json/

台鐵提供之票價表資料欄位說明，請參考[票價檔中文欄位說明.docx]，資料參考位置為http://www.railway.gov.tw/tw/CP.aspx?sn=16938&n=19573

TrainTimeTable.java 為台鐵提供之json解析，並計算出使用者所選擇之條件內列車資訊。

TRA.java 為初始之javafx介面之設計。

DownloadFile.java 為下載台鐵所提供之每天列車資訊zip檔。

UnZip.java 為將下載下來之zip檔，解壓縮。

FourToStation.java 為台鐵json檔車站代碼轉換為車站名。

FourTOThree.java 為台鐵json檔車站代碼轉換為票價檔車站代碼。

ticket.java 為台鐵票價之計算。

TrainResult.java 為結果之javafx介面之設計。
