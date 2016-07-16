package TimeTable;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class DownloadFile {
	
	String FName;
	String fileName;
	String savePath;
	URL link;
	File file;
	public DownloadFile(String FName,String link,String SaveName) throws IOException {
		
        File f = new File("Data");
        if (f.mkdir()) {
            System.out.println("folder Finished");
        } else {
            System.out.println("folder error");
        }
		this.FName = FName;
		fileName = SaveName; //The file that will be saved on your computer
		savePath = "Data/";
		this.link = new URL(link+this.FName); //The file that you want to download
		
		file = new File(savePath,fileName);
		//Code to download
		InputStream in = new BufferedInputStream(this.link.openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		while (-1!=(n=in.read(buf)))
		 {
		    out.write(buf, 0, n);
		 }
		out.close();
		in.close();
		byte[] response = out.toByteArray();
 
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(response);
		fos.close();
		 //End download code
 
		System.out.println("File Save Finished");

	}
  public void DownloadFile(String Date,String savePath,String link){
	  FName = Date;
	  this.fileName = FName+".zip";
	  this.savePath = savePath;
	  try {
		this.link = new URL(link+fileName);
	  } catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  file = new File(savePath,fileName);
  }
  public void Download(){
	  InputStream in;
	  try {
		  in = new BufferedInputStream(link.openStream());
		  ByteArrayOutputStream out = new ByteArrayOutputStream();
		  byte[] buf = new byte[1024];
		  int n = 0;
		  while (-1!=(n=in.read(buf)))
		  {
			  out.write(buf, 0, n);
		  }
		  out.close();
		  in.close();
		  byte[] response = out.toByteArray();
		  FileOutputStream fos = new FileOutputStream(file);
		  fos.write(response);
		  fos.close();
		
		  //End download code		
		  System.out.println("File Save Finished");
		
	  	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
  }
}

