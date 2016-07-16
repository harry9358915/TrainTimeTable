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
			 
		//���o���Y�ɤ����U�@�Ӷ���(�@�����Y���ɮשΥؿ��A�����Y�ɩҫإߪ�)
		ZipEntry zipEntry = zIn.getNextEntry();
			 
		//�]�w�����Y���ت���Ƨ�
		String unZipDir = "Data/";
		if(!zipEntry.isDirectory()) {
			 
			//���P�_�W�@�h��Ƨ��O�_�s�b�A�Y���s�b�h���إ߸�Ƨ��A�A�����Y�ɮ�
			File f1 = new File(unZipDir+zipEntry.getName());
			File parent = f1.getParentFile();
				if(!parent.exists()) {
					parent.mkdirs(); 
				}
			 
			    //�}�Ҹ����Y���g�J���ɮ�
				FileOutputStream fOut = new FileOutputStream(f1);
 
    			//�HbyteŪ�������Y�᪺��ƦA�g�J�ɮ�
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
