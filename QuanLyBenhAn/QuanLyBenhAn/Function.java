import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Function {
    public static void themMoiBenhAn(Scanner scanner, QuanLyBenhAn quanLyBenhAn) {
        System.out.print("Nhap ma benh an: ");
        String maBenhAn = scanner.nextLine();
        while (!maBenhAn.matches("BA-\\d{3}")) {
            System.out.print("Ma benh an khong hop le. Nhap lai (theo dinh dang BA-XXX): ");
            maBenhAn = scanner.nextLine();
        }

        System.out.print("Nhap ma benh nhan: ");
        String maBenhNhan = scanner.nextLine();
        while (!maBenhNhan.matches("BN-\\d{3}")) {
            System.out.print("Ma benh nhan khong hop le. Nhap lai (theo dinh dang BN-XXX): ");
            maBenhNhan = scanner.nextLine();
        }

        System.out.print("Nhap ten benh nhan: ");
        String tenBenhNhan = scanner.nextLine();

        System.out.print("Nhap ngay nhap vien (dd/MM/yyyy): ");
        String ngayNhapVienStr = scanner.nextLine();
        while (!isValidDate(ngayNhapVienStr)) {
            System.out.print("Ngay nhap vien khong hop le. Nhap lai (dd/MM/yyyy): ");
            ngayNhapVienStr = scanner.nextLine();
        }

        System.out.print("Nhap ngay ra vien (dd/MM/yyyy): ");
        String ngayRaVienStr = scanner.nextLine();
        while (!isValidDate(ngayRaVienStr) || !isAfterOrEqual(ngayRaVienStr, ngayNhapVienStr)) {
            System.out.print("Ngay ra vien khong hop le hoac phai sau ngay nhap vien. Nhap lai (dd/MM/yyyy): ");
            ngayRaVienStr = scanner.nextLine();
        }

        System.out.print("Nhap ly do nhap vien: ");
        String lyDoNhapVien = scanner.nextLine();

        System.out.print("Loai benh an (1. Thuong, 2. VIP): ");
        int loaiBenhAn = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            if (loaiBenhAn == 1) {
                System.out.print("Nhap phi nam vien: ");
                double phiNamVien = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                BenhAn benhAn = new BenhAnThuong(maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVienStr, ngayRaVienStr, lyDoNhapVien, phiNamVien);
                quanLyBenhAn.themBenhAn(benhAn);
            } else if (loaiBenhAn == 2) {
                System.out.print("Nhap loai VIP (VIP I, VIP II, VIP III): ");
                String loaiVIP = scanner.nextLine();
                while (!isValidVIPPackage(loaiVIP)) {
                    System.out.print("Loai VIP khong hop le. Nhap lai (VIP I, VIP II, VIP III): ");
                    loaiVIP = scanner.nextLine();
                }
                System.out.print("Nhap thoi han VIP: ");
                String thoiHanVIP = scanner.nextLine();
                BenhAn benhAn = new BenhAnVIP(maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVienStr, ngayRaVienStr, lyDoNhapVien, loaiVIP, thoiHanVIP);
                quanLyBenhAn.themBenhAn(benhAn);
            } else {
                System.out.println("Loai benh an khong hop le!");
            }
        } catch (DuplicateMedicalRecordException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean isAfterOrEqual(String dateStr1, String dateStr2) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date1 = dateFormat.parse(dateStr1);
            Date date2 = dateFormat.parse(dateStr2);
            return !date2.after(date1);
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidDate(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            dateFormat.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidVIPPackage(String packageStr) {
        return packageStr.equals("VIP I") || packageStr.equals("VIP II") || packageStr.equals("VIP III");
    }

    public static void xoaBenhAn(Scanner scanner, QuanLyBenhAn quanLyBenhAn) {
        System.out.print("Nhap ma benh an can xoa: ");
        String maBenhAn = scanner.nextLine();
        quanLyBenhAn.xoaBenhAn(maBenhAn);
    }

    public static void xemDanhSachBenhAn(QuanLyBenhAn quanLyBenhAn) {
        List<BenhAn> danhSachBenhAn = quanLyBenhAn.xemDanhSachBenhAn();
        for (BenhAn benhAn : danhSachBenhAn) {
            System.out.println(benhAn);
        }
    }
}