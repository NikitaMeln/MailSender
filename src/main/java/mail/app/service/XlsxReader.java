package mail.app.service;

public interface XlsxReader {
    String getPrimaryMail(int lineInTable);

    String getSecondaryMail(int lineInTable);

    String getName(int lineInTable);

    String getDistrict(int lineInTable);

    int getNumberLineByMail(String mail);

    int getSizeofTable();
}
