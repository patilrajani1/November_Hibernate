package com.jbk.hibernate;

import java.security.Provider.Service;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.jbk.hibernate.dao.ProductDao;
import com.jbk.hibernate.entity.Product;
import com.jbk.hibernate.service.ProductService;
import com.jbk.hibernate.utility.ProductUtility;

public class ProductController {

	public static void main(String[] args) {
        char ch;
        Scanner scanner= new Scanner(System.in);
         ProductService service = new ProductService();
         
		do {
			System.out.println("Press 1 for save product");
			System.out.println("Press 2 for get product by id");
			System.out.println("Press 3 for delete product by id");
			System.out.println("Press 4 for update product");
			System.out.println("Press 5 for get all product");
			System.out.println("Press 6 for get product by id");
		
			
			 int choice = scanner.nextInt();
			 
			 switch (choice) {
			
			 case 1:{
				 Product product = ProductUtility.prepareProductData(scanner);
				 boolean isAdded = service.saveProduct(product);
				if(isAdded) {
					System.out.println("Saved !!");
				}else {
					System.out.println("Product Already Exists With Id "+product.getProductId());
				}
				 break;
			 }
			 
			 case 2:{
			 System.out.println("Enter Product Id");
			 long productId=scanner.nextLong();
			 Product product = service.getProductById(productId);
			 
			 if(product!=null) { 
				 System.out.println(product);
			 }else {
				 System.out.println("Product Not Exists !! ID = "+productId);
			 }
			 break;
			 }
			 
			 case 3:{
				 System.out.println("Enter Product Id");
				 long productId=scanner.nextLong();
				 boolean isDeleted = service.deleteProductById(productId);
				 if (isDeleted) {
					 System.out.println("Deleted");					
				} else {
					System.out.println("Product Not Exists !! ID = "+productId);
				}
				 break;
			 }
			 
			 case 4:{
				 Product product = ProductUtility.prepareProductData(scanner);

				boolean isUpdated = service.updateProduct(product);
				
				if (isUpdated) {
				    System.out.println("Updated");
				} else {
					System.out.println("Product Not Exists !! ID= "+ product.getProductId());
				}
				break;
			 }
			 
			 case 5: {
				 List<Product> allProduct = service.getAllProduct();
				 
				 if (allProduct.isEmpty() || allProduct==null) {
					System.out.println("Product Not Exists");
				} else {
					for (Product product : allProduct) {
						System.out.println(product);
					}

				}
				 
				 break;
			 }
			 default:
				 break;
			 
			 }
			 
			System.out.println("Do you want to continue y/n");
		 ch = scanner.next().charAt(0);
			
		}while (ch == 'y' || ch== 'Y');
		
		
		System.out.println("Terminated");
		 
	}

}
