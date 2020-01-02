package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.MessageFormat;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class FileController {
	
	@RequestMapping("file")
	public String index() {
        String message = "";
        try {
            String userDir = System.getProperty("user.dir");
            File directory = new File("");
            String canonicalPath = directory.getCanonicalPath();
            String absolutePath = directory.getAbsolutePath();

            message+=String.format("current directory: %s<br>", userDir);
            message += MessageFormat.format("current directory: {0}<br>", canonicalPath);
            message += MessageFormat.format("current directory: {0}<br>", absolutePath);
            String newPath = userDir + "\\temp";
            File newDirectory = new File(newPath);
            File newFile = new File(newPath + "\\test.txt");
            if(newDirectory.exists()){
                newDirectory.delete();
            }
            boolean boo = newDirectory.mkdir();
            if(!boo){
                throw new Exception("break");
            }
            message+="directory create: success<br>";
            boo = newDirectory.delete();
            if(!boo){
                throw new Exception("break");
            }
            message+="directory delete: success<br>";
            newDirectory.mkdir();
            message+="directory create: success<br>";
            boo = newFile.createNewFile();
            if(!boo){
                throw new Exception("break");
            }
            message+="file create: success<br>";
            if(newFile.exists()){
                boo = newFile.delete();
                if(!boo){
                    throw new Exception("break");
                }
            }
            message+="file delete: success<br>";
            newFile.createNewFile();
            FileWriter fileWriter = new FileWriter(newFile, true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write("file write test.");
            bufferWriter.close();

            FileOutputStream fop = new FileOutputStream(newFile, true);
            fop.write("\nfile write test2.".getBytes());
            fop.flush();
            fop.close();
            message+="file write: success<br>";
            
            FileReader reader = new FileReader(newFile);
            FileInputStream fis = new FileInputStream(newFile);
            int byteRead = 0;
            long length = newFile.length();
            byte[] rs = new byte[(int)length];
            byte[] tempBytes = new byte[100];
            int index = 0;
            while((byteRead = fis.read(tempBytes))!=-1){
                for(int i=0;i<byteRead;i++){
                    rs[index]=tempBytes[i];
                    index++;
                }
            }
            fis.close();
            String text = new String(rs);
            message+="file read: "+text+"<br>";
            BufferedReader br = new BufferedReader(reader);
            String tempString;
            text = "";
            while((tempString = br.readLine())!=null){
                text += tempString;
            }
            br.close();
            message+="file read: "+text+"<br>";
            message += "file测试成功";
        } catch (Exception e) {
            message += "file测试失败";
            e.printStackTrace();
        }
		return message;
	}
}
