	package com.woofers.grocery.config;
	
	import com.woofers.grocery.entity.Fruit;
	import com.woofers.grocery.repository.FruitRepository;
	import org.springframework.boot.CommandLineRunner;
	import org.springframework.context.annotation.Configuration;
	
	import java.util.List;
	
	@Configuration
	public class FruitInitializer implements CommandLineRunner {
	
	    private final FruitRepository fruitRepository;
	
	    public FruitInitializer(FruitRepository fruitRepository) {
	        this.fruitRepository = fruitRepository;
	    }
	
	    @Override
	    public void run(String... args) {
	
	        if (fruitRepository.count() > 0) {
	            return;
	        }
	
	        Fruit apple = new Fruit();
	        apple.setName("Apple");
	        apple.setPrice(40.0);
	        apple.setAvailableQuantity(100);
	        apple.setImageUrl("https://th.bing.com/th/id/OIP.8-TDS_SZ5qa8jHWZKGw3iAHaHa?w=163&h=180&c=7&r=0&o=7&pid=1.7&rm=3");
	
	        Fruit banana = new Fruit();
	        banana.setName("Banana");
	        banana.setPrice(10.0);
	        banana.setAvailableQuantity(150);
	        banana.setImageUrl("https://www.bing.com/th/id/OIP.O8lKDwWSZP_Cfm8eeyw3wAHaFu?w=259&h=211&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2");
	
	        Fruit orange = new Fruit();
	        orange.setName("Orange");
	        orange.setPrice(20.0);
	        orange.setAvailableQuantity(50);
	        orange.setImageUrl("https://www.bing.com/th/id/OIP.ipAy9uhIxyyH81t7W2ZBxwHaFj?w=271&h=211&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2");
	
	        Fruit grapes = new Fruit();
	        grapes.setName("Grapes");
	        grapes.setPrice(40.0);
	        grapes.setAvailableQuantity(30);
	        grapes.setImageUrl("https://th.bing.com/th/id/OIP.oulK1r7_iIyAJZ1AWsH78QHaFj?w=247&h=185&c=7&r=0&o=7&pid=1.7&rm=3");
	        
	        Fruit mango = new Fruit();
	        mango.setName("Mango");
	        mango.setPrice(40.0);
	        mango.setAvailableQuantity(200);
	        mango.setImageUrl("https://th.bing.com/th/id/OIP.b3Ht1YXzHYShnCCAPJkB_wHaHa?w=156&h=180&c=7&r=0&o=7&pid=1.7&rm=3");
	
	        Fruit strawberry = new Fruit();
	        strawberry.setName("Strawberry");
	        strawberry.setPrice(40.0);
	        strawberry.setAvailableQuantity(30);
	        strawberry.setImageUrl("https://th.bing.com/th/id/OIP.CmbO_IgvScBpt9QSGcMG8QHaE7?w=263&h=180&c=7&r=0&o=7&pid=1.7&rm=3");
	
	        Fruit kiwi = new Fruit();
	        kiwi.setName("Kiwi");
	        kiwi.setPrice(40.0);
	        kiwi.setAvailableQuantity(30);
	        kiwi.setImageUrl("https://th.bing.com/th/id/OIP.073G08FFe9wf6W4OMhhcWwHaHa?w=167&h=180&c=7&r=0&o=7&pid=1.7&rm=3");
	
	        fruitRepository.saveAll(List.of(apple, banana, orange,mango,strawberry,kiwi, grapes));
	    }
	}
