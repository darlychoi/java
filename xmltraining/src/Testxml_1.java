
import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Testxml_1 {	
  
	public static void main(String[] args) throws Exception {
		
		// 1.加载xml文件到jvm中，形成数据流
		InputStream xml = Testxml_1.class.getClassLoader().getResourceAsStream("demon5xml.xml");
		
		//2、创建解析对象
		SAXReader sax= new SAXReader();
		
		//3、获取文档对象（整过xml文件）[将数据流转换成一个文档对象]
		Document doc = sax.read(xml);
		
//		//4. 获得根元素
//		
//		Element root = doc.getRootElement();
//		//5.获得根元素下的所有子元素
//		List<Element> list = root.selectNodes("stduent");
		
		}
		
}
