package test;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class crawl_url_uniqlo {
	private static String url;
	private static Document doc;
	public static void main(String[] args) {
		
		url ="https://store-kr.uniqlo.com/display/displayShop.lecs?storeNo=83&siteNo=50706&displayNo=NQ1A02A11A17";
		doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Elements sexs = doc.select("ul.gnb_2016_nav");
		Elements liclasses = sexs.select("li");
		for(Element sex: liclasses){
			Elements selectSex = sex.select("img");
			String sexText = selectSex.attr("alt");
			if(sexText.equals("WOMEN")){
				System.out.println(sexText);
			}
			else if(sexText.equals("MEN")){
				System.out.println(sexText);
			}
		}
	}
}