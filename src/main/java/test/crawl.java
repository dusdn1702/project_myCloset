package test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
public class crawl {

	public static void main(String[] args) {
		String url = "https://store-kr.uniqlo.com/display/displayShop.lecs?storeNo=83&siteNo=50706&displayNo=NQ1A02A12A02"; //크롤링할 url지정
		Document doc = null;

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//select를 이용하여 원하는 태그를 선택 
		Elements element = doc.select("div.uniqlo_unit"); 
		Elements names = doc.select("span.name");
        Elements img = element.select("img");
        
        //img 불러오기
        Elements imgurls = doc.select("div.thumb");
        
 //       Elements title = element.select("img");
		System.out.println("============================================================");

		//Iterator을 사용하여 하나씩 값 가져오기\
		Iterator<Element> ie1 = element.select("strong.price").iterator();
		
		//span 안에 a 태그에 있음
		for(Element name:names){
			Elements nametag = name.select("a");
			String nameText = nametag.first().ownText();
			System.out.println(nameText);
		}
		
		//Iterator<Element> ie2 = name.select("span.name").iterator();
		
		List<String> title = new ArrayList<String>();
		for(Element imgtitle : img) {
		  title.add(imgtitle.attr("alt"));
		}
		
		for(Element imgurl:imgurls){
			//a태그 주소 속에
		String href = imgurl.attr("abs:href");
			//img태그 존재
		Elements imgurlsrc = imgurl.select("img");
		String realimgurl =  imgurlsrc.first()
				.attr("src");
		System.out.println(realimgurl);
		}
		
//		int page = 0;
//		for(Element im : img){
//            String imurl = im.getElementsByAttribute("src").attr("src");
//            URL imgUrl = new URL(imurl);
//            BufferedImage jpg = ImageIO.read(imgUrl);
//            File file = new File("C:\\"+folder+"\\"+page+".jpg");
//            ImageIO.write(jpg, "jpg", file);
//            page+=1;			
//		} 	//ㅠㅠㅠㅠㅠㅠ
//사진주소받기 & 필요한것만 받기
		while (ie1.hasNext()){//&&ie2.hasNext()) {
			System.out.println(ie1.next().text()+"\t");
		//+ie2.next().text());
		}
		System.out.println(title);
		
		/*
        Document doc = Jsoup.connect("url주소").get();
        String folder = doc.title();
        Element element = doc.select("이미지들이 포함된 선택자").get(0);
        Elements img = element.select("img");
        int page = 0;
        for (Element e : img) {
            String url = e.getElementsByAttribute("src").attr("src");
            
            URL imgUrl = new URL(url);
            BufferedImage jpg = ImageIO.read(imgUrl);
            File file = new File("경로"+folder+"\\"+page+".jpg");
            ImageIO.write(jpg, "jpg", file);
            page+=1;
        }
*/
        
        System.out.println("============================================================");
	}

}
