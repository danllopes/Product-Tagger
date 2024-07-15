import entities.ImportedProduct;
import entities.Product;
import entities.UsedProduct;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        var sc = new Scanner(System.in);

        System.out.println("""

                Confira o enunciado completo no arquivo PDF
                localizado no pacote 'documentação'.
                """);

        final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ArrayList<Product> products = new ArrayList<>();

        System.out.print("Enter the number of products: ");
        int number = sc.nextInt();

        for (int i = 1; i <= number; i++) {
            System.out.println("\nProduct #" + i + " data:");

            char productType;

            do {
                System.out.print("Common, used or imported (c/u/i)? ");
                productType = sc.next().charAt(0);
            } while(productType != 'c' && productType != 'u' && productType != 'i');

            System.out.print("Name: ");
            sc.nextLine();
            String productName = sc.nextLine().trim();

            System.out.print("Price: ");
            double productPrice = sc.nextDouble();

            switch (productType) {
                case 'u':
                    System.out.print("Manufacture date (DD/MM/YYYY): ");
                    sc.nextLine();
                    LocalDate manufactureDate = LocalDate.parse(sc.nextLine().trim(), DATE_FORMATTER);
                    products.add(new UsedProduct(productName, productPrice, manufactureDate));
                    break;

                case 'i':
                    System.out.print("Customs fee: ");
                    double customsFee = sc.nextDouble();
                    products.add(new ImportedProduct(productName, productPrice, customsFee));
                    break;

                default:
                    products.add(new Product(productName, productPrice));
                    break;
            }
        }

        System.out.println("\n\nPRICE TAGS:");

        for (Product product: products) {
            System.out.println(product.priceTag());
        }

        sc.close();
    }
}