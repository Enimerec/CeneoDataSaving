package pl.sda;

import pl.sda.entity.Product;
import pl.sda.entity.Products;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * author:
 * Mateusz
 * Marczak
 **/
public class App {
    private boolean nextPage = false;
    private ImageService imageService = new ImageService();
    private SavingService savingService = new SavingService();
    public void app(String urlSet) {
        List<Product> productList = new ArrayList<>();
        String contex = ";0020-30-0-0-0.htm";
        urlSet = urlSet + contex ;
        int maxPages = 2;

        for (int i = 1; i < maxPages; i++) {
            try {

                BufferedReader bufferedReader = getBufferedReader(urlSet);
                String line = bufferedReader.readLine();

                String imgHtml = "data-src=\"//image.ceneostatic.pl/data/products";
                String titleHtml = "<div class=\"list-prod-name\">";
                String priceHtml = "<span class=\"price-from-label\">od";

                productList.addAll(saveDataToList(bufferedReader, line, imgHtml, titleHtml, priceHtml));

                if(nextPage=false){
                 i= maxPages;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            String newUrl = ";0020-30-0-0-"+i+".htm";
            urlSet = urlSet.replaceAll(contex,newUrl);
            contex = newUrl;

        }

        Products products = new Products();
        products.setProductList(productList);
        try {
            savingService.saveToXml(products);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    private List<Product> saveDataToList(BufferedReader bufferedReader, String line, String imgHtml, String titleHtml, String priceHtml) throws IOException {
        Product product = new Product();
        List<Product> productList = new ArrayList<>();
        int index = 0;


        while (line != null) {

            if(line.contains("<link rel=\"next\"")){
                nextPage = true;
            }

            if (line.contains(imgHtml)) {
                index++;
                String img = getImg(line);
                product.setImg(img);
            } else if (line.contains(titleHtml)) {
                index++;
                line = getTitle(line);
                product.setTitle(line);
            } else if (line.contains(priceHtml)) {
                index++;
                String price = getPrice(line);
                product.setPrice(price);
            } else if (index == 3) {
                productList.add(product);
                product = new Product();
                index = 0;
            }

            line = bufferedReader.readLine();
        }
        return productList;
    }

    private String getImg(String line) {
        line = line.substring(line.indexOf("image.ceneostatic.pl"), line.indexOf("\" data-preloader"));
        return imageService.getImgInBase64("http://"+line);
    }

    private String getTitle(String line) {
        line = line.substring(line.indexOf(">"), line.indexOf("</div>"));
        line = line.replace(">", "");
        return line;
    }

    private String getPrice(String line) {
        line = line.substring(line.indexOf("price-int"), line.indexOf("zł"));
        String price = line.substring(line.indexOf(">"), line.indexOf("<"));
        price = price.concat(line.substring(line.indexOf(","), line.indexOf("</span></span>")));
        price = price.replace(">", "");
        return price;
    }


    private BufferedReader getBufferedReader(String setUrl) throws IOException {
        URL url = new URL(setUrl);
        URLConnection connection = url.openConnection();
        InputStreamReader inStream = new InputStreamReader(connection.getInputStream());
        return new BufferedReader(inStream);
    }


}
