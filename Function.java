import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Function {
    private static final String FILE_PATH = "data/employees.csv";
    private static List<NhanVien> nhanViens = new ArrayList<>();

    static {
        loadEmployeesFromFile();
    }

    private static void loadEmployeesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    String maNhanVien = parts[0];
                    String tenNhanVien = parts[1];
                    String ngaySinh = parts[2];
                    String ngayVaoCongTy = parts[3];
                    String boPhanLamViec = parts[4];
                    if (!parts[5].equals("-")) {
                        double luongCoBan = Double.parseDouble(parts[5]);
                        nhanViens.add(new NhanVienToanThoiGian(maNhanVien, tenNhanVien, ngaySinh, ngayVaoCongTy, boPhanLamViec, luongCoBan));
                    } else {
                        int soGioLamViec = Integer.parseInt(parts[6]);
                        double luongTheoGio = Double.parseDouble(parts[7]);
                        nhanViens.add(new NhanVienBanThoiGian(maNhanVien, tenNhanVien, ngaySinh, ngayVaoCongTy, boPhanLamViec, soGioLamViec, luongTheoGio));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Không thể đọc file dữ liệu: " + e.getMessage());
        }
    }

    private static void saveEmployeesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (NhanVien nv : nhanViens) {
                writer.write(nv.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Không thể ghi vào file dữ liệu: " + e.getMessage());
        }
    }

    public static void themMoiNhanVien(Scanner scanner) throws DuplicateEmployeeRecordException {
        String maNhanVien;
        while (true) {
            System.out.print("Nhập mã nhân viên (NV-XXX): ");
            maNhanVien = scanner.nextLine();
            if (Pattern.matches("NV-\\d{3}", maNhanVien)) {
                boolean duplicate = false;
                for (NhanVien nv : nhanViens) {
                    if (nv.getMaNhanVien().equals(maNhanVien)) {
                        duplicate = true;
                        break;
                    }
                }
                if (duplicate) {
                    System.out.println("Nhân viên đã tồn tại.");
                } else {
                    break;
                }
            } else {
                System.out.println("Mã nhân viên không đúng định dạng. Vui lòng nhập lại.");
            }
        }

        System.out.print("Nhập tên nhân viên: ");
        String tenNhanVien = scanner.nextLine();

        String ngaySinh;
        while (true) {
            System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
            ngaySinh = scanner.nextLine();
            if (Pattern.matches("\\d{2}/\\d{2}/\\d{4}", ngaySinh)) {
                break;
            } else {
                System.out.println("Ngày sinh không đúng định dạng. Vui lòng nhập lại.");
            }
        }

        String ngayVaoCongTy;
        while (true) {
            System.out.print("Nhập ngày vào công ty (dd/MM/yyyy): ");
            ngayVaoCongTy = scanner.nextLine();
            if (Pattern.matches("\\d{2}/\\d{2}/\\d{4}", ngayVaoCongTy)) {
                break;
            } else {
                System.out.println("Ngày vào công ty không đúng định dạng. Vui lòng nhập lại.");
            }
        }

        System.out.print("Nhập bộ phận làm việc: ");
        String boPhanLamViec = scanner.nextLine();

        System.out.print("Nhân viên toàn thời gian (1) hay bán thời gian (2): ");
        int loaiNhanVien = scanner.nextInt();
        scanner.nextLine();

        if (loaiNhanVien == 1) {
            System.out.print("Nhập lương cơ bản: ");
            double luongCoBan = scanner.nextDouble();
            scanner.nextLine();
            nhanViens.add(new NhanVienToanThoiGian(maNhanVien, tenNhanVien, ngaySinh, ngayVaoCongTy, boPhanLamViec, luongCoBan));
        } else if (loaiNhanVien == 2) {
            System.out.print("Nhập số giờ làm việc: ");
            int soGioLamViec = scanner.nextInt();
            System.out.print("Nhập lương theo giờ: ");
            double luongTheoGio = scanner.nextDouble();
            scanner.nextLine();
            nhanViens.add(new NhanVienBanThoiGian(maNhanVien, tenNhanVien, ngaySinh, ngayVaoCongTy, boPhanLamViec, soGioLamViec, luongTheoGio));
        } else {
            System.out.println("Lựa chọn không hợp lệ.");
            return;
        }

        saveEmployeesToFile();
        System.out.println("Thêm mới nhân viên thành công.");
    }

    public static void xoaNhanVien(Scanner scanner) {
        System.out.print("Nhập mã nhân viên cần xóa: ");
        String maNhanVien = scanner.nextLine();

        NhanVien nhanVienCanXoa = null;
        for (NhanVien nv : nhanViens) {
            if (nv.getMaNhanVien().equals(maNhanVien)) {
                nhanVienCanXoa = nv;
                break;
            }
        }

        if (nhanVienCanXoa == null) {
            System.out.println("Không tìm thấy nhân viên với mã nhân viên: " + maNhanVien);
            return;
        }

        System.out.print("Bạn có chắc chắn muốn xóa nhân viên này không? (Yes/No): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("Yes")) {
            nhanViens.remove(nhanVienCanXoa);
            saveEmployeesToFile();
            System.out.println("Xóa nhân viên thành công.");
        }
    }

    public static void xemDanhSachNhanVien() {
        if (nhanViens.isEmpty()) {
            System.out.println("Không có nhân viên nào.");
            return;
        }
        nhanViens.sort(Comparator.comparing(NhanVien::getMaNhanVien));
        System.out.printf("%-10s %-20s %-12s %-12s %-20s %-10s %-15s %-10s%n", "Mã NV", "Tên NV", "Ngày Sinh", "Ngày Vào CT", "Bộ Phận", "Lương CB", "Giờ LV", "Lương G");
        for (NhanVien nv : nhanViens) {
            if (nv instanceof NhanVienToanThoiGian) {
                NhanVienToanThoiGian nvtg = (NhanVienToanThoiGian) nv;
                System.out.printf("%-10s %-20s %-12s %-12s %-20s %-10.2f %-15s %-10s%n", nvtg.getMaNhanVien(), nvtg.getTenNhanVien(), nvtg.getNgaySinh(), nvtg.getNgayVaoCongTy(), nvtg.getBoPhanLamViec(), nvtg.getLuongCoBan(), "-", "-");
            } else if (nv instanceof NhanVienBanThoiGian) {
                NhanVienBanThoiGian nvbtg = (NhanVienBanThoiGian) nv;
                System.out.printf("%-10s %-20s %-12s %-12s %-20s %-10s %-15d %-10.2f%n", nvbtg.getMaNhanVien(), nvbtg.getTenNhanVien(), nvbtg.getNgaySinh(), nvbtg.getNgayVaoCongTy(), nvbtg.getBoPhanLamViec(), "-", nvbtg.getSoGioLamViec(), nvbtg.getLuongTheoGio());
            }
        }
    }

    public static void timKiemNhanVien(Scanner scanner) {
        System.out.print("Nhập mã nhân viên cần tìm: ");
        String maNhanVien = scanner.nextLine();

        boolean found = false;
        for (NhanVien nv : nhanViens) {
            if (nv.getMaNhanVien().equals(maNhanVien)) {
                found = true;
                System.out.printf("%-10s %-20s %-12s %-12s %-20s %-10s %-15s %-10s%n", "Mã NV", "Tên NV", "Ngày Sinh", "Ngày Vào CT", "Bộ Phận", "Lương CB", "Giờ LV", "Lương G");
                if (nv instanceof NhanVienToanThoiGian) {
                    NhanVienToanThoiGian nvtg = (NhanVienToanThoiGian) nv;
                    System.out.printf("%-10s %-20s %-12s %-12s %-20s %-10.2f %-15s %-10s%n", nvtg.getMaNhanVien(), nvtg.getTenNhanVien(), nvtg.getNgaySinh(), nvtg.getNgayVaoCongTy(), nvtg.getBoPhanLamViec(), nvtg.getLuongCoBan(), "-", "-");
                } else if (nv instanceof NhanVienBanThoiGian) {
                    NhanVienBanThoiGian nvbtg = (NhanVienBanThoiGian) nv;
                    System.out.printf("%-10s %-20s %-12s %-12s %-20s %-10s %-15d %-10.2f%n", nvbtg.getMaNhanVien(), nvbtg.getTenNhanVien(), nvbtg.getNgaySinh(), nvbtg.getNgayVaoCongTy(), nvbtg.getBoPhanLamViec(), "-", nvbtg.getSoGioLamViec(), nvbtg.getLuongTheoGio());
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy nhân viên với mã nhân viên: " + maNhanVien);
        }
    }
}
