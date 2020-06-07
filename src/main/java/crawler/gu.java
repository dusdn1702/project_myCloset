package crawler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class gu {
	private static String url;
	private static Document doc;
	public static void main(String[] args) {
		
		url ="https://www.gu-global.com/kr/display/displayShop.lecs?storeNo=84&siteNo=57706&displayNo=GU1A01&stonType=P";
		doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Elements navbar = doc.select("ul.gu_store_menu");
		Elements liclasses = navbar.select("li");
		//li가 너무 많다... 가장 밖에만 데려오는법
		for(Element sex: liclasses){
			Elements selectSex = sex.select("a");
			String sexText = selectSex.first().ownText();
			System.out.println(sexText);
			if(sexText.equals("WOMEN")){
				System.out.println(sexText);
				womenPage(sex);
			}
			else if(sexText.equals("MEN")){
				System.out.println(sexText);
				menPage(sex);
			}
		}
	}

	public static void womenPage(Element liclass){
		takeCategory(liclass);
	}
	public static void menPage(Element liclass){
	    takeCategory(liclass);
	}
	public static void takeCategory(Element liclass){
		Elements categories = liclass.select("li");	//top outers
		Elements categoryName = categories.select("a");
		String categoryNameText = categoryName.first().ownText();
		System.out.println(categoryNameText);
		List<String> urlList = new ArrayList<String>();
		for(Element category: categories){
			Elements detailCategories = category.select("li");	//casual jacket coat
			Elements categoryUrl = detailCategories.select("a");
			String UrlText =  categoryUrl.attr("href");
			urlList.add(UrlText);
		}
		for(String it: urlList){	//리스트에 id 전부 다 받고 한번에 하기
			String newurl = it;
			//page(newurl);	
			System.out.println(newurl);
		}
	}

	public static void page(String CategoryUrl){
		Document takeresultdoc = null;
		try {
			takeresultdoc = Jsoup.connect(CategoryUrl).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements items = takeresultdoc.select("li.item"); 
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
	
			//String result = nameText+",\t"+priceText+",\t"+imgUrlText;
			System.out.println("uniqlo, \t"+nameText+",\t"+priceText+",\t"+imgUrlText+url);
	
		}
	}
}

