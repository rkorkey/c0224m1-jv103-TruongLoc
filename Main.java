import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===============================");
            System.out.println("1. Thêm mới nhân viên");
            System.out.println("2. Xóa nhân viên");
            System.out.println("3. Xem danh sách nhân viên");
            System.out.println("4. Tìm kiếm nhân viên theo mã");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("===============================");

            switch (choice) {
                case 1:
                    try {
                        Function.themMoiNhanVien(scanner);
                    } catch (DuplicateEmployeeRecordException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    Function.xoaNhanVien(scanner);
                    break;
                case 3:
                    Function.xemDanhSachNhanVien();
                    break;
                case 4:
                    Function.timKiemNhanVien(scanner);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
