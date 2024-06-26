public class NhanVienToanThoiGian extends NhanVien {
    private double luongCoBan;

    public NhanVienToanThoiGian(String maNhanVien, String tenNhanVien, String ngaySinh, String ngayVaoCongTy, String boPhanLamViec, double luongCoBan) {
        super(maNhanVien, tenNhanVien, ngaySinh, ngayVaoCongTy, boPhanLamViec);
        this.luongCoBan = luongCoBan;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    @Override
    public String getLoaiNhanVien() {
        return "Toàn Thời Gian";
    }

    @Override
    public String toCSV() {
        return maNhanVien + "," + tenNhanVien + "," + ngaySinh + "," + ngayVaoCongTy + "," + boPhanLamViec + "," + luongCoBan + ",-,-";
    }
}
