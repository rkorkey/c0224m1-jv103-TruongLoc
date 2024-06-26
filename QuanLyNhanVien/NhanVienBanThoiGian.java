public class NhanVienBanThoiGian extends NhanVien {
    private int soGioLamViec;
    private double luongTheoGio;

    public NhanVienBanThoiGian(String maNhanVien, String tenNhanVien, String ngaySinh, String ngayVaoCongTy, String boPhanLamViec, int soGioLamViec, double luongTheoGio) {
        super(maNhanVien, tenNhanVien, ngaySinh, ngayVaoCongTy, boPhanLamViec);
        this.soGioLamViec = soGioLamViec;
        this.luongTheoGio = luongTheoGio;
    }

    public int getSoGioLamViec() {
        return soGioLamViec;
    }

    public double getLuongTheoGio() {
        return luongTheoGio;
    }

    @Override
    public String getLoaiNhanVien() {
        return "Bán Thời Gian";
    }

    @Override
    public String toCSV() {
        return maNhanVien + "," + tenNhanVien + "," + ngaySinh + "," + ngayVaoCongTy + "," + boPhanLamViec + ",-," + soGioLamViec + "," + luongTheoGio;
    }
}
