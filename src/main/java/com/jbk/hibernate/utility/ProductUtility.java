package com.jbk.hibernate.utility;

import java.util.Scanner;

import com.jbk.hibernate.entity.Product;

public class ProductUtility {
	public static Product prepareProductData(Scanner scanner) {
		System.out.println("Enter ProductId");
		long prodcuctId=scanner.nextLong();
		
		System.out.println("Enter Product Name");
		String productName=scanner.next();
		
		System.out.println("Enter Category Id");
		long categoryId=scanner.nextLong();
		
		System.out.println("Enter Supplier Id");
		long supplierId=scanner.nextLong();
		
		System.out.println("Enter Product QTY");
		int prodcuctQTY=scanner.nextInt();
		
		System.out.println("Enter Product Price");
		double productPrice=scanner.nextDouble();
		
		Product product=new Product(prodcuctId, productName, supplierId, categoryId, prodcuctQTY, productPrice);
		
		
		return product;
		
	}

}
