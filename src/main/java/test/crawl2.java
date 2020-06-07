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
public class crawl2 {

	public static void main(String[] args) {
		String url = "https://store-kr.uniqlo.com/display/displayShop.lecs?storeNo=83&siteNo=50706&displayNo=NQ1A01A10A03";
				//"https://store-kr.uniqlo.com/display/displayShop.lecs?storeNo=83&siteNo=50706&displayNo=NQ1A02A12A02"; //크롤링할 url지정
		Document doc = null;

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//select를 이용하여 원하는 태그를 선택 
		Elements element = doc.select("li.item"); 
		Elements items = doc.select("li.item"); 
		Elements names = doc.select("span.name");
        Elements imgurls = doc.select("div.thumb");

		System.out.println("============================================================");

		//가격 받아오기
		//Iterator을 사용하여 하나씩 값 가져오기\
		Iterator<Element> ie = element.select("strong.price").iterator();
		int cnt = 0;
		while (ie.hasNext()){
			cnt++;
			System.out.println(ie.next().text()+"\t");
		}
		System.out.println("가격 개수"+cnt);

		//이름 받아오기
		//span 안에 a 태그에 있음
		int namecnt = 0;
		for(Element name:names){
			namecnt++;
			Elements nametag = name.select("a");
			String nameText = nametag.first().ownText();
			System.out.println(nameText);
		}
		System.out.println("이름 개수"+namecnt);
		//사진url받아오기
		//a 태그 속 img 태그에서 src 받기
		int imgcnt = 0;
		for(Element imgurl:imgurls){
			imgcnt++;
			Elements imgurlsrc = imgurl.select("img");
			String realimgurl =  imgurlsrc.first()
					.attr("src");
			System.out.println(realimgurl);
		}
		System.out.println("사진 개수"+imgcnt);
        System.out.println("============================================================");
	}

}
