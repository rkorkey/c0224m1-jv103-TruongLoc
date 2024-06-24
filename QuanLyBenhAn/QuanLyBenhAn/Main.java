import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        QuanLyBenhAn quanLyBenhAn = new QuanLyBenhAn();
        quanLyBenhAn.docFile(); // Đọc dữ liệu từ file CSV khi khởi động chương trình
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("--CHUONG TRINH QUAN LY BENH AN--");
            System.out.println("1. Them moi");
            System.out.println("2. Xoa");
            System.out.println("3. Xem danh sach");
            System.out.println("4. Thoat");
            System.out.print("Chon chuc nang: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    Function.themMoiBenhAn(scanner, quanLyBenhAn);
                    break;
                case 2:
                    Function.xoaBenhAn(scanner, quanLyBenhAn);
                    break;
                case 3:
                    Function.xemDanhSachBenhAn(quanLyBenhAn);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
}