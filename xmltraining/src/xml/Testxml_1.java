package xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Testxml_1 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		
		 // 获得当前日期
		Date today= new Date();
		 //日期格式化
		 DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		 //获得当前日期指定格式
		 String todayDate = df1.format(today);
		 
//		 System.out.println(todayDate);
		 //获得3年后的日期		 
		 long afterTime=(today.getTime()/1000)+60*60*24*365*3;
		 today.setTime(afterTime*1000); 
		 String afterYear = df1.format(today);
//		 System.out.println(afterYear);
		 
		//日期格式化2（带有时分秒）
		 DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd 'T'HH:mm:ss");
		 String todayTime = df2.format(new Date());
//		 System.out.println(todayTime);
		
		 //随机生成批次号
		 DateFormat df3 = new SimpleDateFormat("yyMMdd");
		 String dateData=df3.format(new Date());
		 int number = (int)(Math.random()*10)+1;
		 String batchNo = "pc"+dateData+"000"+String.valueOf(number);
//		 System.out.println(batchNo);
		 
		 //拆分包装比例并计算码数量
		 String packagerate ="10个:10盒:1箱";
		 String[] rateList=packagerate.split(":");
		 //获取各级包装比例
		 List levelrateList = new ArrayList(); // 创建集合
		 for(int i=0;i<rateList.length;i++)
		 {
		  	  levelrateList.add(rateList[i].substring(0, rateList[i].length()-1));
		  	System.out.println(i);
		 };
		 System.out.println(levelrateList.size());
		 //计算各级码数量
		 List codeCountList = new ArrayList(); // 创建集合
		 int count=1;
		 for(int i=rateList.length-1;i>=0;i--)
		 {
		  	 int countTemp = Integer.parseInt((String) levelrateList.get(i));
//		  	 System.out.println(countTemp);
			 count *=countTemp;
			 codeCountList.add(count);		
		 };
//		 System.out.println("三级码"+codeCountList.get(0)+"二级码"+codeCountList.get(1)+"一级码"+codeCountList.get(2));
		 
		
		// 1.加载xml文件到jvm中，形成数据流
		InputStream xml = Testxml_1.class.getClassLoader().getResourceAsStream("TTS_20211206-0227.XML");
		// 2、创建解析对象
		SAXReader sax = new SAXReader();
		// 3、获取文档对象（整过xml文件）[将数据流转换成一个文档对象]
		Document doc = sax.read(xml);
		// 4. 获得根元素
		System.out.println("获得根元素");
		Element relations = doc.getRootElement();
		System.out.println(relations.getName());
		
		System.out.println("");
		System.out.println("使用根目录全文搜索获取name");
		//使用根目录全文搜索获取nam
//		List<Element> nameList = relations.selectNodes("//Relation");
		Element relationNode = (Element) relations.selectSingleNode("//Relation");
		relationNode.addAttribute("ProductCode", "YY01");            //设置产品代码
		relationNode.addAttribute("ProduceCorpCode", "f100");        //设置生产企业代码
		relationNode.addAttribute("ProduceBatchNo", batchNo);         //设置生产批号		
		relationNode.addAttribute("ProduceWorkline", "line003");        //设置产线代码
		relationNode.addAttribute("OuterPackage", "10个:1盒");           //设置包装比例
		relationNode.addAttribute("ProduceDate", todayDate);            //设置生产线
		relationNode.addAttribute("ExpireDate", afterYear);             //设置失效日期
		relationNode.addAttribute("OperateDate", todayTime);            //设置操作日期

		Element codesNode = (Element) relations.selectSingleNode("//codes");
		int requestNumer = 1;
		int createNum = 1;
		for (int i=1; i<rateList.length; i++ ) {
			for(int j=1; i<10; j++) {}
			Element codeElement=codesNode.addElement("code");
			codeElement.addAttribute("Id", "YY01");            //设置追溯码
			codeElement.addAttribute("Level", "YY01");            //设置追溯码
			codeElement.addAttribute("ParentId", "");            //设置追溯码
			codeElement.addAttribute("ScanTime", todayTime);            //设置追溯码
			codeElement.addAttribute("ProduceTime", todayTime);            //设置追溯码
			codeElement.addAttribute("ProduceMemo", "I");            //设置追溯码
		};
		


//		System.out.println(codeNode.getName());
//		Element codesElement=relationNode.addElement("codes");
//		RelationNode.addElement("/codes/code");
		
		//写入XML文件中
		FileOutputStream out = new FileOutputStream(new File("F:/code.xml"));
		OutputFormat format = new OutputFormat("\t",true,"utf-8");
		XMLWriter XM = new XMLWriter(out,format);
		//将整个文档对象写到文件中
		XM.write(doc);
		System.out.println("写入成功");

	
	}
}