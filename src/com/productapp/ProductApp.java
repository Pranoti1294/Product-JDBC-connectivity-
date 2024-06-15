package com.productapp;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.productapp.daoimpl.ProductDaoImpl;
import com.productapp.model.Product;

public class ProductApp {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int ch = 0;
        Product product;
        ProductDaoImpl productDaoImpl = new ProductDaoImpl(); 
        int id, price;
        String name;
        int result;

        do {
            System.out.println("1.Insert  2.Update  3.Delete  4.Fetch All  5.Fetch by id   6.Exit");
            System.out.println("Enter choice: ");
            ch = scanner.nextInt();

            switch (ch) {
                case 1:
                    id = getInputInt("Enter id: ");
                    name = getInputString("Enter name: ");
                    price = getInputInt("Enter price: ");

                    product = new Product(id, name, price);

                    result = productDaoImpl.save(product);
                    if (result > 0) {
                        System.out.println(result + " record inserted successfully :)\n");
                    } else {
                        System.out.println("Something went wrong\n");
                    }

                    break;
                case 2:
                    id = getInputInt("Enter id: ");
                    name = getInputString("Enter name: ");
                    price = getInputInt("Enter price: ");

                    product = new Product(id, name, price);

                    result = productDaoImpl.update(id, product);
                    if (result > 0) {
                        System.out.println(result + " record updated successfully :)\n");
                    } else {
                        System.out.println("Something went wrong\n");
                    }
                    break;

                case 3:
                    id = getInputInt("Enter id: ");

                    result = productDaoImpl.remove(id);

                    if (result > 0) {
                        System.out.println(result + " record deleted successfully :)\n");
                    } else {
                        System.out.println("Something went wrong\n");
                    }
                    break;

                case 4:
                    List<Product> productsList = productDaoImpl.getAll();
                    Iterator<Product> productIterator = productsList.iterator();
                    System.out.println("id		 name 		         price ");
                    System.out.println("------------------------------------------------------");
                    while (productIterator.hasNext()) {
                        Product products = productIterator.next();
                        System.out.println(products);

                    }
                    System.out.println();
                    break;

                case 5:
                    id = getInputInt("Enter id: ");
                    System.out.println("id		 name 		         price ");
                    System.out.println("-------------------------------------------------------");
                    Product products = productDaoImpl.getById(id);
                    System.out.println(products);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (ch != 6);

        scanner.close(); 
    }

    private static int getInputInt(String prompt) {
        System.out.println(prompt);
        return scanner.nextInt();
    }

    private static String getInputString(String prompt) {
        System.out.println(prompt);
        scanner.nextLine(); 
        return scanner.nextLine();
    }
}
