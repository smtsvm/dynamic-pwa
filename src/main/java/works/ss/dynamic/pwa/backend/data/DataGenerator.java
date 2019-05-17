package works.ss.dynamic.pwa.backend.data;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import works.ss.dynamic.pwa.backend.entity.Availability;
import works.ss.dynamic.pwa.backend.entity.Category;
import works.ss.dynamic.pwa.backend.entity.Product;
import works.ss.dynamic.pwa.backend.service.BaseService;
import works.ss.dynamic.pwa.backend.service.CategoryService;
import works.ss.dynamic.pwa.backend.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class DataGenerator implements InitializingBean {

    @Autowired
    private BaseService baseService;


    @Override
    public void afterPropertiesSet() throws Exception {
//
//        Random random = new Random();
//        List<Category> categoryList = new ArrayList<>();
//        for(int i = 0 ; i < 10; i++) {
//            Category category = new Category();
//            category.setName(categoryNames[random.nextInt(categoryNames.length)]);
//            category.setCategoryDetail(categoryNames[random.nextInt(categoryNames.length)]);
//            baseService.saveEntity(category);
//            categoryList.add(category);
//        }
//
//        for( int i = 0; i < 100; i++) {
//            Product product = new Product();
//            product.setName(word1[random.nextInt(word1.length)] + " " + word2[random.nextInt(word2.length)]);
//            product.setAvailability(i %3 == 0 ? Availability.AVAILABLE : (i % 3 == 1 ? Availability.COMING : Availability.DISCONTINUED));
//            product.setPrice(BigDecimal.valueOf(random.nextInt()));
//            product.setCategory(Arrays.asList(categoryList.get(random.nextInt(categoryList.size()))));
//            baseService.saveEntity(product);
//        }
    }



    private static final String categoryNames[] = new String[] {
            "Children's books", "Best sellers", "Romance", "Mystery",
            "Thriller", "Sci-fi", "Non-fiction", "Cookbooks" };

    private static String[] word1 = new String[] { "The art of", "Mastering",
            "The secrets of", "Avoiding", "For fun and profit: ",
            "How to fail at", "10 important facts about",
            "The ultimate guide to", "Book of", "Surviving", "Encyclopedia of",
            "Very much", "Learning the basics of", "The cheap way to",
            "Being awesome at", "The life changer:", "The Vaadin way:",
            "Becoming one with", "Beginners guide to",
            "The complete visual guide to", "The mother of all references:" };

    private static String[] word2 = new String[] { "gardening",
            "living a healthy life", "designing tree houses", "home security",
            "intergalaxy travel", "meditation", "ice hockey",
            "children's education", "computer programming", "Vaadin TreeTable",
            "winter bathing", "playing the cello", "dummies", "rubber bands",
            "feeling down", "debugging", "running barefoot",
            "speaking to a big audience", "creating software", "giant needles",
            "elephants", "keeping your wife happy" };
}
