package TimeTable;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZip {
	String trainDate,fileName,filePath;
	
	public UnZip(String traindate) throws IOException{
		System.setProperty("file.encoding","UTF-8");
		trainDate = traindate;
		fileName = trainDate+".zip";
    	filePath = "Data/" + fileName;
		ZipInputStream zIn = new ZipInputStream (new BufferedInputStream(new FileInputStream(filePath)));
			 
		//取得壓縮檔內的下一個項目(一個壓縮的檔案或目錄，為壓縮時所建立的)
		ZipEntry zipEntry = zIn.getNextEntry();
			 
		//設定解壓縮的目的資料夾
		String unZipDir = "Data/";
		if(!zipEntry.isDirectory()) {
			 
			//先判斷上一層資料夾是否存在，若不存在則先建立資料夾，再解壓縮檔案
			File f1 = new File(unZipDir+zipEntry.getName());
			File parent = f1.getParentFile();
				if(!parent.exists()) {
					parent.mkdirs(); 
				}
			 
			    //開啟解壓縮欲寫入的檔案
				FileOutputStream fOut = new FileOutputStream(f1);
 
    			//以byte讀取解壓縮後的資料再寫入檔案
    		int byteNo1;
    		byte[] b1 = new byte[1024];
 
    		while ((byteNo1=zIn.read(b1))>0) {
   
        		fOut.write(b1, 0, byteNo1);
    		}
    		fOut.close();
    		zIn.close();
    		System.out.println("UnZip Finished");
		}
	}
}
