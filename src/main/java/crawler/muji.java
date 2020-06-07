package crawler;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class muji {
	private static String url;
	private static Document doc;
	public static void main(String[] args) {
		
		url ="http://www.mujikorea.net";
		doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Elements nav = doc.select("div.content");
		Elements ctg = nav.select("ul.snCate02");
		//Elements tli = nav.select("li");
		//for(Element ttli: tli){
		//	String takeli = ttli.attr("id");
		//	System.out.println(takeli);
		//if(takeli.equals("MJ1A01")){
			Elements liss = ctg.select("li");
			System.out.println("여기");
/////////여기서 안넘어감
			for(Element lis: liss){
				String sexId = lis.attr("id");
				Elements atag = lis.select("a");
				String newUrl = atag.attr("href");
				System.out.println(sexId);

				if(sexId.equals("MJ1A01A01")){
					menPage(newUrl, sexId);
					System.out.println("men");
				}
				else if(sexId.equals("MJ1A01A02")){
					womenPage(newUrl, sexId);
				}
			}			
		}
	

	public static void womenPage(String Sexurl, String id){
		takeCategory(Sexurl, id);
	}
	public static void menPage(String Sexurl, String id){
	    takeCategory(Sexurl, id);
	}
	public static void takeCategory(String SexUrl, String sexId){
		Document sexDoc = null;
		String hereUrl = url+SexUrl;
		System.out.println(hereUrl);
		try {
			sexDoc = Jsoup.connect(hereUrl).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements findctg = sexDoc.select("div.main");
		Elements inSex = findctg.select("div.categoryType02Div");
		Elements ctgs = inSex.select("dl.c_type02_con");
		for(Element ctg: ctgs){	//카테고리들 
			Elements categoryName = ctg.select("a");
			String nameText = categoryName.first().ownText();
			System.out.println(nameText);	//상세 이름 뽑기
			String ctgUrl = categoryName.attr("href");
			String pageUrl = url+ctgUrl;
			page(pageUrl);
		}
	}
	public static void page(String CategoryUrl){
		Document takeresultdoc = null;
		try {
			takeresultdoc = Jsoup.connect(CategoryUrl).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		///안에서 따오는거 하면 됨 페이지도 있음
		Elements group = takeresultdoc.select("div.tmpl30");
		Elements items = group.select("li");
		for(Element item: items){
			Elements price = item.select("div.price");
			Elements name = item.select("div.contents");
	        Elements imgurl = item.select("div.photo_zone");
	        
			Elements nametag = name.select("a");
			String nameText = nametag.first().ownText();
			String itemUrl = name.select("a").attr("href");
			
			Elements imgurlsrc = imgurl.select("img");
			String imgUrlText =  imgurlsrc.first()
					.attr("src");
			
			String priceText = price.text();
	
			//String result = nameText+",\t"+priceText+",\t"+imgUrlText;
			System.out.println("muji, \t"+nameText+",\t"+priceText+",\t"+imgUrlText+itemUrl+url);
	
		}
	}
}

