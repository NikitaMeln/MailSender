package mail.app.service;

public interface XlsxReader {
    public String getPrimaryMail(int lineInTable);

    public String getSecondaryMail(int lineInTable);

    public String getName(int lineInTable);

    public String getDistrict(int lineInTable);

    public int getNumberLineByMail(String mail);

    public int getSizeofTable();
}
