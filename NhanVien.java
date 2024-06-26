public abstract class NhanVien {
    protected String maNhanVien;
    protected String tenNhanVien;
    protected String ngaySinh;
    protected String ngayVaoCongTy;
    protected String boPhanLamViec;

    public NhanVien(String maNhanVien, String tenNhanVien, String ngaySinh, String ngayVaoCongTy, String boPhanLamViec) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.ngaySinh = ngaySinh;
        this.ngayVaoCongTy = ngayVaoCongTy;
        this.boPhanLamViec = boPhanLamViec;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public String getNgayVaoCongTy() {
        return ngayVaoCongTy;
    }

    public String getBoPhanLamViec() {
        return boPhanLamViec;
    }

    public abstract String getLoaiNhanVien();

    public abstract String toCSV();
}
