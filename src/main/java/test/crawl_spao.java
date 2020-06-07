package test;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class crawl_spao {

	public static void main(String[] args) {
		String url = "http://spao.elandmall.com/dispctg/initDispCtg.action?disp_ctg_no=1607300075";
				//"https://store-kr.uniqlo.com/display/displayShop.lecs?storeNo=83&siteNo=50706&displayNo=NQ1A01A10A03";
				//"https://store-kr.uniqlo.com/display/displayShop.lecs?storeNo=83&siteNo=50706&displayNo=NQ1A02A12A02"; //크롤링할 url지정
		Document doc = null;

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int cntname=0;
		int cntprice=0;
		int cntimgurl=0;
		Elements items = doc.select("div.box_md_recomm"); 
		for(Element item: items){
			Elements price = item.select("span.c_price");
			Elements name = item.select("span.prod_nm");
	        Elements imgurl = item.select("div.thumb");
	        
			//Elements nametag = name.select("span.prod_nm");
			String nameText = name.text();
			
			Elements imgurlsrc = imgurl.select("img");
			String imgUrlText =  imgurlsrc.attr("src");
			
			String priceText = price.text();
	
			System.out.println(nameText+",\t"+priceText+",\t"+imgUrlText+"\n");
	
		}
	}
}
