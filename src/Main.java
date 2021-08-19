
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vanhv
 */
public class Main {

    public static void main(String[] args) throws IOException, Exception {
        BookManager m = new BookManager();
        Validation v = new Validation();
        Scanner sc = new Scanner(System.in);
        System.out.println("1.1.      Load data from file\n"
                + "1.2.      Input & insert data\n"
                + "1.3.      In-order traverse\n"
                + "1.4.      Breadth-first traverse\n"
                + "1.5.      In-order traverse to file\n"
                + "1.6.      Search by bcode\n"
                + "1.7.      Delete by bcode by copying\n"
                + "1.8.      Simply balancing\n"
                + "1.9.      Count number of books\n"
                + "1.10      \n"
                + "1.11      \n"
                + "1.12      \n"
                + "1.13      \n"
                + "1.14      \n"
                + "1.15      \n"
                + "================================\n");
        while (true) {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1: {
                    m.f1();
                    break;
                }
                case 2: {
                    m.f2();
                    break;
                }
                case 3: {
                    m.f3();
                    break;
                }
                case 4: {
                    m.f4();
                    break;
                }
                case 5: {
                    m.f5();
                    break;
                }
                case 6: {
                    m.f6();
                    break;
                }
                case 7: {
                    m.f7();
                    break;
                }
                case 8: {
                    m.f8();
                    break;
                }
                case 9: {
                    m.f9();
                    break;
                }
                case 10: {
                    m.f10();
                    break;
                }
                case 11: {
                    m.f11();
                    break;
                }
                case 12: {
                    m.f12();
                    break;
                }
                case 13: {
                    m.f13();
                    break;
                }
                case 14: {
                    m.f14();
                    break;
                }
                case 15: {
                    m.f15();
                    break;
                }
            }
        }
    }
}
