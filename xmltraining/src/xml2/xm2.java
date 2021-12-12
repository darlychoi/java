package xml2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import xml.Testxml_1;

public class xm2 {

	public static void main(String[] args) throws Exception {
		// 1.加载xml文件到jvm中，形成数据流
				InputStream xml = Testxml_1.class.getClassLoader().getResourceAsStream("TTs_20211206-0227.xml");		
				//2、创建解析对象
				SAXReader sax= new SAXReader();		
				//3、获取文档对象（整过xml文件）[将数据流转换成一个文档对象]
				Document doc = sax.read(xml);		
				//4. 获得根元素		
				Element tts = doc.getRootElement();
				System.out.println(tts.getName());
				
				//获取关联关系元素（Relations）
				Element relations=tts.element("//Relation");
				System.out.println(relations.getName());
				
				 //写入XML文件中 
				FileOutputStream out = new FileOutputStream(new File("F:/code.xml")); 
				OutputFormat format = new OutputFormat("\t",true,"utf-8"); 
				XMLWriter XM = new XMLWriter(out,format);
				 //将整个文档对象写到文件中 
				XM.write(doc); System.out.println("写入成功"); XM.close();


	}

}
