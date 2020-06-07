package crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class topten {
	private static String url;
	private static Document doc;
	public static void main(String[] args) {
		
		url ="https://topten.topten10mall.com/kr/front/viewMain.do";
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
				womenPage(url);
			}
			else if(sexText.equals("MEN")){
				System.out.println(sexText);
				menPage(url);
			}
		}
	}

	public static void womenPage(String womanUrl){
		doc = null;
		try {
			doc = Jsoup.connect(womanUrl).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		takeCategory(womanUrl);
	}
	public static void menPage(String manUrl){
		doc = null;
		try {
			doc = Jsoup.connect(manUrl).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    takeCategory(manUrl);
	}
	public static void takeCategory(String Url){
		Elements categories = doc.select("div.catg_menu_swp");
		
		Elements categoryNames = categories.select("a");
		List<String> ctgNumList = new ArrayList<String>();
		for(Element categoryName:categoryNames){
			String categoryNum = categoryName.attr("data-val");	//카테고리 번호
			if(!categoryNum.equals("1711332978")){
				String categoryNameText = categoryName.text();	//아우터 상의 ..
				ctgNumList.add(categoryNum);
				System.out.println(categoryNameText);
			}
		}
		for(String it: ctgNumList){	//리스트에 id 전부 다 받고 한번에 하기
			String newurl = "http://m-spao.elandmall.com/dispctg/initDispCtg.action?disp_ctg_no="+it;
			page(newurl);	
			//System.out.println(newurl);
		}
	}

	//이거 아이템 이름 가격 사진url 상세보기url 받아오는거 짜기
	//상세보기url이 문제....
	//onclick 따오는거 하기..
	
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