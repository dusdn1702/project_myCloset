package crawler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class whoru {
	private static String url;
	private static Document doc;
	public static void main(String[] args) {
		
		url ="http://whoau.elandmall.com/main/initMain.action";
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
		for(int i=3;i<=6;i++){	//기둥 3부터 6까지만
			String colText = "div.gnb_2016_col.col"+i;
			//System.out.println(colText+i+"\n");
			Elements colitems = liclass.select(colText);
			Elements idItems = colitems.select("div.col_block"); 
			for(Element idItem: idItems){	//기둥속에 카테고리들따오기
				Elements categoryName = idItem.select("a");
				String nameText = categoryName.first().ownText();
				System.out.println(nameText);	//tops outer bottom
				Elements itemsurl = idItem.select("li");	
				List<String> idList = new ArrayList<String>();
				for(Element category: itemsurl){
					String idItemText = category.attr("id");
					idList.add(idItemText);
					Elements Name = category.select("a");
					String name = Name.first().ownText();
					System.out.println(name);	//티셔츠 ut 셔츠블라우스 오버사이즈 ...
				}
				for(String it: idList){	//리스트에 id 전부 다 받고 한번에 하기
					String newurl ="https://store-kr.uniqlo.com/display/displayShop.lecs?storeNo=83&siteNo=50706&displayNo="
							+ it;
					page(newurl);	
				}
			}
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
			
			String itemUrl = "https://store-kr.uniqlo.com/"+imgurl.select("a").attr("href");
			Elements imgurlsrc = imgurl.select("img");
			String imgUrlText =  imgurlsrc.first()
					.attr("src");
			
			String priceText = price.text();
	
			//String result = nameText+",\t"+priceText+",\t"+imgUrlText;
			System.out.println("uniqlo, \t"+nameText+",\t"+priceText+",\t"+imgUrlText+itemUrl+url);
	
		}
	}
}

