import java.io.*;
import java.util.*;

public class QuanLyBenhAn {
    private List<BenhAn> danhSachBenhAn = new ArrayList<>();

    public void themBenhAn(BenhAn benhAn) throws DuplicateMedicalRecordException {
        for (BenhAn ba : danhSachBenhAn) {
            if (ba.getMaBenhAn().equals(benhAn.getMaBenhAn())) {
                throw new DuplicateMedicalRecordException("Benh an da ton tai!");
            }
        }
        danhSachBenhAn.add(benhAn);
        ghiFile();
    }

    public void xoaBenhAn(String maBenhAn) {
        danhSachBenhAn.removeIf(benhAn -> benhAn.getMaBenhAn().equals(maBenhAn));
        ghiFile();
    }

    public List<BenhAn> xemDanhSachBenhAn() {
        return danhSachBenhAn;
    }

    private void ghiFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/medical_records.csv"))) {
            for (BenhAn benhAn : danhSachBenhAn) {
                bw.write(benhAn.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void docFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("data/medical_records.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String maBenhAn = parts[1];
                String maBenhNhan = parts[2];
                String tenBenhNhan = parts[3];
                String ngayNhapVien = parts[4];
                String ngayRaVien = parts[5];
                String lyDoNhapVien = parts[6];

                if (parts.length == 8) {
                    double phiNamVien = Double.parseDouble(parts[7]);
                    BenhAn benhAn = new BenhAnThuong(maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien, phiNamVien);
                    danhSachBenhAn.add(benhAn);
                } else if (parts.length == 9) {
                    String loaiVIP = parts[7];
                    String thoiHanVIP = parts[8];
                    BenhAn benhAn = new BenhAnVIP(maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien, loaiVIP, thoiHanVIP);
                    danhSachBenhAn.add(benhAn);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}