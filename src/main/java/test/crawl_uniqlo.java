package test;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class crawl_uniqlo {

	public static void main(String[] args) {
		String url = "https://store-kr.uniqlo.com/display/displayShop.lecs?storeNo=83&siteNo=50706&displayNo=NQ1A01A10A03";
				//"https://store-kr.uniqlo.com/display/displayShop.lecs?storeNo=83&siteNo=50706&displayNo=NQ1A02A12A02"; //크롤링할 url지정
		Document doc = null;

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements items = doc.select("li.item"); 
		for(Element item: items){
			Elements price = item.select("strong.price");
			Elements name = item.select("span.name");
	        Elements imgurl = item.select("div.thumb");
	        
			Elements nametag = name.select("a");
			String nameText = nametag.first().ownText();
			
			Elements imgurlsrc = imgurl.select("img");
			String imgUrlText =  imgurlsrc.first()
					.attr("src");
			
			String priceText = price.text();
	
			System.out.println(nameText+",\t"+priceText+",\t"+imgUrlText);
	
		}
	}
}
