package com.views;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.logic.*;
import com.views.interfaces.IReportView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.logic.ProjectConstants.*;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class PDFView implements IReportView {

    private static BaseFont bf = null;
    static {
        try {
            bf = BaseFont.createFont("C:\\Windows\\Fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (Exception e) {

        }
    }

    public static final Font standardFont = new Font(bf, 14, 0, BaseColor.BLACK);
    public static final Font standardBoldFont = new Font(bf, 14, Font.BOLD, new BaseColor(115,205,105));
    public static final Font header1Font = new Font(bf, 20, Font.BOLDITALIC, new BaseColor(98,98,255));
    public static final Font header2Font = new Font(bf, 18, Font.BOLD, new BaseColor(98,98,255));
    public static final Font titleFont = new Font(bf, 26, Font.BOLDITALIC, BaseColor.ORANGE);
    public static final Font titleSmallFont = new Font(bf, 18, Font.ITALIC, new BaseColor(155,115,255));
    public static final Font titleHeaderFont = new Font(bf, 16, Font.BOLD, BaseColor.PINK);
    public static final Font titleFooterFont = new Font(bf, 12, 0, BaseColor.LIGHT_GRAY);
    public static final Font pageNumberFont = new Font(bf, 14, 0, BaseColor.PINK);

    private Paragraph getStandardParagraph(float leftIndent) {
        Paragraph p = new Paragraph(15);
        p.setAlignment(Element.ALIGN_JUSTIFIED);
        p.setIndentationLeft(leftIndent);
        p.setFont(standardFont);
        p.setSpacingAfter(3);
        return p;
    }

    private Paragraph getStandardParagraphBold(float leftIndent) {
        Paragraph p = getStandardParagraph(leftIndent);
        p.setFont(standardBoldFont);
        return p;
    }

    private Paragraph createStandardParagraph(String boldText, String text, float leftIndent) {
        Paragraph p = getStandardParagraphBold(leftIndent);
        p.setFont(standardBoldFont);
        p.add(boldText + ": ");
        p.setFont(standardFont);
        p.add(text);
        return p;
    }

    private Paragraph createBoldParagraph(String text, float leftIndent) {
        Paragraph p = getStandardParagraphBold(leftIndent);
        p.add(text);
        return p;
    }

    private Paragraph createBoldColoredParagraph(String text, float leftIndent, BaseColor color) {
        Paragraph p = getStandardParagraphBold(leftIndent);
        p.getFont().setColor(color);
        p.add(text);
        return p;
    }

    private Paragraph createHeader1Paragraph(String text) {
        Paragraph p = new Paragraph();
        p.setFont(header1Font);
        p.setIndentationLeft(30);
        p.setSpacingAfter(14);
        p.add(text);
        return p;
    }

    private Paragraph createHeader2Paragraph(String text) {
        Paragraph p = new Paragraph();
        p.setFont(header2Font);
        p.setIndentationLeft(20);
        p.setSpacingAfter(10);
        p.add(text);
        return p;
    }

    private Document getTypicalDocument(File file, String title) throws Exception {
        Rectangle pageSize = new Rectangle(PageSize.A4);
        pageSize.setBackgroundColor(new BaseColor(255, 238, 238));
        Document document = new Document(pageSize);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        TableHeader event = new TableHeader();
        writer.setPageEvent(event);
        document.open();
        addMetaData(document, title);
        addTitlePage(document, title);
        return document;
    }

    private Paragraph getParagraphOfAlignmentAndFont(String text, Font font, int alignment) {
        Paragraph newP = new Paragraph(text, font);
        newP.setAlignment(alignment);
        return newP;
    }

    private  void addEmptyLine(Document document, int number) throws DocumentException {
        for (int i = 0; i < number; i++) {
            document.add(new Paragraph(" "));
        }
    }

    private void addMetaData(Document document, String title) {
        document.addTitle("Отчёт: " + title);
        document.addSubject("Отчёт, сгенерированный проектом DeadPool");
        document.addKeywords("Отчёт, DeadPool");
        document.addAuthor("Проект Deadpool");
        document.addCreator("Автогенератор отчётов DeadPool");
    }

    private boolean addImageFromInternet(Document document, String url) {
        boolean result = true;
        try {
            Image logo = Image.getInstance(url);
            logo.setAlignment(Image.ALIGN_LEFT);
            logo.setSpacingAfter(15);
            logo.scaleToFit(530, 530);
            document.add(logo);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    private void addLink(Paragraph paragraph, String link, String text) {
        Phrase phrase = new Phrase();
        Chunk chunk = new Chunk(text);
        chunk.setAnchor(link);
        phrase.add(chunk);
        paragraph.add(phrase);
    }

    private void addTitlePage(Document document, String title) throws DocumentException {
        document.add(getParagraphOfAlignmentAndFont("(c) Проект Deadpool", titleHeaderFont, Element.ALIGN_CENTER));
        addEmptyLine(document, 2);
        document.add(getParagraphOfAlignmentAndFont("ОТЧЁТ", titleFont, Element.ALIGN_CENTER));
        document.add(getParagraphOfAlignmentAndFont(title, titleFont, Element.ALIGN_CENTER));
        addEmptyLine(document, 1);

        try {
            Image logo = Image.getInstance(getClass().getClassLoader().getResource("logo.png"));
            logo.setAlignment(Image.ALIGN_CENTER);
            logo.setSpacingAfter(10);
            logo.setSpacingBefore(10);
            logo.scaleToFit(200, 150);
            document.add(logo);
        } catch (Exception e) {}

        addEmptyLine(document, 3);
        document.add(getParagraphOfAlignmentAndFont("Отчёт был автоматически сгенерирован системой DeadPool после пользовательского запроса",
                titleSmallFont, Element.ALIGN_CENTER));
        addEmptyLine(document, 2);

        try {
            Paragraph paragraph = new Paragraph();
            Phrase phrase = new Phrase();
            Image logo = Image.getInstance(getClass().getClassLoader().getResource("followus.png"));
            logo.setAlignment(Image.ALIGN_CENTER);
            logo.setSpacingAfter(10);
            logo.setSpacingBefore(10);
            logo.scaleToFit(500, 200);
            Chunk chunk = new Chunk(logo,0,0, true);
            chunk.setAnchor("http://localhost:4200");
            phrase.add(chunk);
            paragraph.add(phrase);
            document.add(paragraph);
        } catch (Exception e) {
            Phrase phrase = new Phrase();
            phrase.add("Welcome to ");
            Chunk chunk = new Chunk("our app");
            chunk.setAnchor("http://localhost:4200");
            phrase.add(chunk);
            phrase.add(" in WEB!");
        }

        addEmptyLine(document, 5);
        document.add(getParagraphOfAlignmentAndFont("Отчёт сгенерирован " + LocalDateTime.now().format(reportTitleDateTimeFormatter),
                titleFooterFont, Element.ALIGN_CENTER));

        document.newPage();
    }

    private void addCriminalCaseData(Document document, CriminalCase criminalCase, boolean withDetective, float leftIndent) throws DocumentException {
        document.add(createStandardParagraph("Номер", criminalCase.getCriminalCaseNumber(), leftIndent));
        if (withDetective) {
            document.add(createStandardParagraph("Детектив", ReportFunctions.getDetectiveData(criminalCase.getParentDetective()), leftIndent));
        }
        document.add(createStandardParagraph("Дата создания",
                criminalCase.getCreateDate().format(JSON_FORMATTER_DATE), leftIndent));
        if (!criminalCase.isClosed()) {
            document.add(createStandardParagraph("Статус", "открыто", leftIndent));
        } else {
            if (criminalCase.getCloseDate() != null) {
                document.add(createStandardParagraph("Статус", "раскрыто", leftIndent));
                document.add(createStandardParagraph("Дата закрытия", criminalCase.getCloseDate() != null
                        ? criminalCase.getCloseDate().format(JSON_FORMATTER_DATE) : "почему-то не указана", leftIndent));
            } else {
                document.add(createStandardParagraph("Статус", "не раскрыто (передано в архив)", leftIndent));
            }
        }
    }

    private void addCrimeData(Document document, Crime crime, boolean withCriminalCase, float leftIndent) throws DocumentException {
        if (withCriminalCase) {
            document.add(createBoldColoredParagraph("Уголовное дело", leftIndent, BaseColor.ORANGE));
            addCriminalCaseData(document, crime.getParentCriminalCase(), true, leftIndent+15);
        }
        document.add(createStandardParagraph("Тип", crime.getCrimeType().getName(), leftIndent));
        document.add(createStandardParagraph("Дата совершения", crime.getCrimeDate().format(JSON_FORMATTER_DATE), leftIndent));
        document.add(createStandardParagraph("Время совершения", crime.getCrimeTime() != null ?
                crime.getCrimeTime().format(JSON_FORMATTER_TIME) : "не установлено", leftIndent));
        document.add(createStandardParagraph("Место совершения", !isEmpty(crime.getCrimePlace())
                ? crime.getCrimePlace() : "не указано", leftIndent));
        document.add(createStandardParagraph("Описание", !isEmpty(crime.getDescription())
                ? crime.getDescription() : "не указано", leftIndent));
    }

    private void addEvidenceData(Document document, Evidence evidence, float leftIndent) throws DocumentException {
        document.add(createStandardParagraph("Название", evidence.getName(), leftIndent));
        document.add(createStandardParagraph("Описание", evidence.getDescription(), leftIndent));
    }

    private void addEvidenceOfCrimeData(Document document, EvidenceOfCrime evidenceOfCrime, boolean withParentEvidence,
                                        boolean withParentCrime, float leftIndent) throws DocumentException {
        boolean addedImage = evidenceOfCrime.getPhotoPath() != null && addImageFromInternet(document, evidenceOfCrime.getPhotoPath());
        if (withParentEvidence) {
            addEvidenceData(document, evidenceOfCrime.getParentEvidence(), leftIndent);
        }
        if (withParentCrime) {
            document.add(createBoldColoredParagraph("Преступление", leftIndent, BaseColor.ORANGE));
            addCrimeData(document, evidenceOfCrime.getParentCrime(), true,leftIndent+15);
        }
        document.add(createStandardParagraph("Тип улики", evidenceOfCrime.getEvidenceType().getName(), leftIndent));
        document.add(createStandardParagraph("Детальная информация", !isEmpty(evidenceOfCrime.getDetails()) ?
                evidenceOfCrime.getDetails() : "отсутствует", leftIndent));
        document.add(createStandardParagraph("Дата добавления",
                evidenceOfCrime.getDateAdded().format(JSON_FORMATTER_DATETIME), leftIndent));
        if (!addedImage) {
            if (evidenceOfCrime.getPhotoPath() != null) {
                Paragraph paragraph = createStandardParagraph("Фотография", "", leftIndent);
                addLink(paragraph, evidenceOfCrime.getPhotoPath(), "link");
                document.add(paragraph);
            } else {
                document.add(createStandardParagraph("Фотография", "отсутствует", leftIndent));
            }
        }
    }

    private void addManData(Document document, Man man, float leftIndent) throws DocumentException {
        boolean addedImage = man.getPhotoPath() != null && addImageFromInternet(document, man.getPhotoPath());
        document.add(createStandardParagraph("Имя", man.getName(), leftIndent));
        document.add(createStandardParagraph("Фамилия", man.getSurname(), leftIndent));
        document.add(createStandardParagraph("Дата рождения", man.getBirthDay() != null ?
                man.getBirthDay().format(JSON_FORMATTER_DATE) : "неизвестно", leftIndent));
        document.add(createStandardParagraph("Домашний адрес", !isEmpty(man.getHomeAddress()) ?
                man.getHomeAddress() : "неизвестен", leftIndent));
        if (!addedImage) {
            if (man.getPhotoPath() != null) {
                Paragraph paragraph = createStandardParagraph("Фотография", "", leftIndent);
                addLink(paragraph, man.getPhotoPath(), "link");
                document.add(paragraph);
            } else {
                document.add(createStandardParagraph("Фотография", "отсутствует", leftIndent));
            }
        }
    }

    private void addDetectiveData(Document document, Detective detective, float leftIndent) throws DocumentException {
        addManData(document, detective, leftIndent);
        document.add(createStandardParagraph("Логин", detective.getLogin(), leftIndent));
        document.add(createStandardParagraph("Адрес электронной почты", detective.getEmail(), leftIndent));
    }

    private void addParticipantData(Document document, Participant participant, boolean withParentMan,
                                    boolean withParentCrime, float leftIndent) throws DocumentException {
        if (withParentMan) {
            addManData(document, participant, leftIndent);
        }
        if (withParentCrime) {
            document.add(createBoldColoredParagraph("Преступление", leftIndent, BaseColor.ORANGE));
            addCrimeData(document, participant.getParentCrime(), true,leftIndent+15);
        }
        document.add(createStandardParagraph("Статус", participant.getParticipantStatus().getName(), leftIndent));
        document.add(createStandardParagraph("Алиби", !isEmpty(participant.getAlibi())
                ? participant.getAlibi() : "неизвестно", leftIndent));
        document.add(createStandardParagraph("Отчёт (показания человека)", !isEmpty(participant.getWitnessReport())
                ? participant.getWitnessReport() : "неизвестно", leftIndent));
        document.add(createStandardParagraph("Дата добавления", participant.getDateAdded().format(JSON_FORMATTER_DATETIME),
                leftIndent));
    }

    @Override
    public String generateReportByCrimes(List<Crime> crimes, LocalDate startDate, LocalDate endDate) throws Exception {
        File tempFile = File.createTempFile("report", ".pdf");
        Document document = getTypicalDocument(tempFile, "Преступления, совершённые с " + startDate.format(JSON_FORMATTER_DATE)
                + " по " + endDate.format(JSON_FORMATTER_DATE));

        document.add(createHeader1Paragraph("Преступления"));
        for (Crime crime : crimes) {
            addCrimeData(document, crime,true, 0);
            addEmptyLine(document, 1);
        }
        if (crimes.isEmpty()) {
            document.add(createBoldParagraph("Не найдены преступления, совершённые в указанный промежуток!", 0));
        }

        document.close();
        return tempFile.getAbsolutePath();
    }

    private String getTitleForCriminalCases(String status) {
        switch(status) {
            case "open":
                return "Расследуемые уголовные дела";
            case "solved":
                return "Раскрытые уголовные дела";
            case "unsolved":
                return "Нераскрытые уголовные дела";
            default:
                return "Все уголовные дела";
        }
    }

    @Override
    public String generateReportByCriminalCases(List<CriminalCase> criminalCases, String status) throws Exception {
        File tempFile = File.createTempFile("report", ".pdf");
        Document document = getTypicalDocument(tempFile, getTitleForCriminalCases(status));

        document.add(createHeader1Paragraph("Уголовные дела"));
        for (CriminalCase criminalCase : criminalCases) {
            addCriminalCaseData(document, criminalCase, true, 0);
            addEmptyLine(document, 1);
        }
        if (criminalCases.isEmpty()) {
            document.add(createBoldParagraph("Не найдены уголовные дела, удовлетворяющие критериям!", 0));
        }

        document.close();
        return tempFile.getAbsolutePath();
    }

    @Override
    public String generateReportByCrime(Crime crime, List<EvidenceOfCrime> evidencesOfCrime, List<Participant> participants) throws Exception {
        File tempFile = File.createTempFile("report", ".pdf");
        Document document = getTypicalDocument(tempFile, "Преступление №" + crime.getCrimeId());

        document.add(createHeader1Paragraph("Преступление"));
        addCrimeData(document, crime, true, 0);
        addEmptyLine(document, 1);

        document.add(createHeader2Paragraph("Прикреплённые улики"));
        addEmptyLine(document, 1);
        for (EvidenceOfCrime evidenceOfCrime : evidencesOfCrime) {
            addEvidenceOfCrimeData(document, evidenceOfCrime, true, false, 0);
            addEmptyLine(document, 1);
        }
        if (evidencesOfCrime.isEmpty()) {
            document.add(createBoldParagraph("Нет улик, связанных с этим преступлением!", 0));
        }

        document.add(createHeader2Paragraph("Люди, связанные с преступлением"));
        addEmptyLine(document, 1);
        for (Participant participant : participants) {
            addParticipantData(document, participant, true, false, 0);
            addEmptyLine(document, 1);
        }
        if (participants.isEmpty()) {
            document.add(createBoldParagraph("Нет людей, связанных с этим преступлением!", 0));
        }

        document.close();
        return tempFile.getAbsolutePath();
    }

    @Override
    public String generateReportByMan(Man man, List<Participant> participants) throws Exception {
        File tempFile = File.createTempFile("report", ".pdf");
        Document document = getTypicalDocument(tempFile, "Человек " + man.getSurname() + ", " + man.getName());

        document.add(createHeader1Paragraph("Человек"));
        addManData(document, man, 0);
        addEmptyLine(document, 1);

        document.add(createHeader2Paragraph("Участие человека в преступлениях"));
        addEmptyLine(document, 1);
        for (Participant participant : participants) {
            addParticipantData(document, participant, false, true, 0);
            addEmptyLine(document, 1);
        }
        if (participants.isEmpty()) {
            document.add(createBoldParagraph("Нет людей, связанных с этим преступлением!", 0));
        }

        document.close();
        return tempFile.getAbsolutePath();
    }

    @Override
    public String generateReportByCriminalCase(CriminalCase criminalCase, List<Crime> crimes) throws Exception {
        File tempFile = File.createTempFile("report", ".pdf");
        Document document = getTypicalDocument(tempFile, "Уголовное дело" + criminalCase.getCriminalCaseNumber());

        document.add(createHeader1Paragraph("Уголовное дело"));
        addCriminalCaseData(document, criminalCase, true, 0);
        addEmptyLine(document, 1);

        document.add(createHeader2Paragraph("Преступления, включённые в состав дела"));
        addEmptyLine(document, 1);
        for (Crime crime : crimes) {
            addCrimeData(document, crime, false, 0);
            addEmptyLine(document, 1);
        }
        if (crimes.isEmpty()) {
            document.add(createBoldParagraph("Нет преступлений в составе этого дела!", 0));
        }

        document.close();
        return tempFile.getAbsolutePath();
    }

    @Override
    public String generateReportByEvidence(Evidence evidence, List<EvidenceOfCrime> evidenceOfCrimes) throws Exception {
        File tempFile = File.createTempFile("report", ".pdf");
        Document document = getTypicalDocument(tempFile, "Улика №" + evidence.getEvidenceId());

        document.add(createHeader1Paragraph("Улика"));
        addEvidenceData(document, evidence, 0);
        addEmptyLine(document, 1);

        document.add(createHeader2Paragraph("Участие улики в преступлениях"));
        addEmptyLine(document, 1);
        for (EvidenceOfCrime evidenceOfCrime : evidenceOfCrimes) {
            addEvidenceOfCrimeData(document, evidenceOfCrime, false, true, 0);
            addEmptyLine(document, 1);
        }
        if (evidenceOfCrimes.isEmpty()) {
            document.add(createBoldParagraph("Эта улика не связана ни с каким преступлением!", 0));
        }

        document.close();
        return tempFile.getAbsolutePath();
    }

    @Override
    public String generateReportByDetective(Detective detective, List<CriminalCase> criminalCases) throws Exception {
        File tempFile = File.createTempFile("report", ".pdf");
        Document document = getTypicalDocument(tempFile, "Детектив " + detective.getSurname() + ", " + detective.getName()
                + " (" + detective.getLogin() + ")");

        document.add(createHeader1Paragraph("Детектив"));
        addDetectiveData(document, detective, 0);
        addEmptyLine(document, 1);

        document.add(createHeader2Paragraph("Уголовные дела, расследуемые детективом"));
        addEmptyLine(document, 1);
        for (CriminalCase criminalCase : criminalCases) {
            addCriminalCaseData(document, criminalCase, false, 0);
            addEmptyLine(document, 1);
        }
        if (criminalCases.isEmpty()) {
            document.add(createBoldParagraph("Этот детектив не расследовал ещё ни одного дела!", 0));
        }

        document.close();
        return tempFile.getAbsolutePath();
    }

    public String encryptPDF(String oldFile) throws Exception {
        try {
            File tempFile = File.createTempFile("report", ".pdf");
            PdfReader reader = new PdfReader(oldFile);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(tempFile));
            stamper.setEncryption(null, "deadpoll_created_deadppol_deasallowed".getBytes(),
                    ~(PdfWriter.ALLOW_COPY) | PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_SCREENREADERS,
                    PdfWriter.ENCRYPTION_AES_256 | PdfWriter.DO_NOT_ENCRYPT_METADATA);
            stamper.close();
            return tempFile.getAbsolutePath();
        } finally {
            File prevFile = new File(oldFile);
            if (prevFile.exists()) {
                prevFile.delete();
            }
        }
    }
}