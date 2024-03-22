import java.io.FileOutputStream;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import java.math.BigInteger;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DocxCreator {

    private ArrayList<Word> words;
    private ArrayList<String> formattedParts;
    private int partCounter;
    private int tableNumber;
    private XWPFDocument document;
    private Map<Integer, Integer> secondTableMap;

    public DocxCreator(ArrayList<Word> words){

        this.tableNumber = getTableNumber(words);
        this.secondTableMap = new HashMap<>();
        configureHashMap(this.tableNumber);
        this.partCounter = 0;
        this.words = words;
        this.formattedParts = formatParts(words);
        this.document = new XWPFDocument();
        setupDocument(this.document);
    }

    private void configureHashMap(int tableNumber){

        boolean adding = true;
        for (int i = 0; i < tableNumber * 2; i++){
            if (adding) {
                this.secondTableMap.put(i, i + 1);
                adding = false;
            } else {
                this.secondTableMap.put(i, i-1);
                adding = true;
            }
        }

    }

    private ArrayList<String> formatParts(ArrayList<Word> words){

        ArrayList<String> formattedParts = new ArrayList<>();
        for (int i = 0; i < this.tableNumber / 2; i++){

            for (int j = i * 4; j < (i * 4 + 4); j++){

                try {
                    formattedParts.add(words.get(j).latin);
                } catch (IndexOutOfBoundsException e){
                    formattedParts.add("");
                }
                formattedParts.add("");

            }

            for (int k = i * 4; k < (i * 4 + 4); k++){

                try{
                    formattedParts.add(words.get(this.secondTableMap.get(k)).translation);
                } catch (IndexOutOfBoundsException e){
                    formattedParts.add("");
                }
                try{
                    formattedParts.add(words.get(this.secondTableMap.get(k)).partOfSpeech);
                } catch (IndexOutOfBoundsException e){
                    formattedParts.add("");
                }
            }
        }

        return formattedParts;
    }

    private int getTableNumber(ArrayList<Word> words){

        if (words.size() % 4 == 0){

            return (words.size() / 4) * 2;
        }

        return ((words.size() / 4) + 1) * 2;
    }

    private void createTable(XWPFDocument document){

        XWPFTable table = document.createTable();
        table.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(15840));

        createFirstRow(table);
        createSecondRow(table);
    }

    private void createFirstRow(XWPFTable table){

        XWPFTableRow row = table.getRow(0);
        row.setHeight(6119);
        XWPFTableCell row1column1 = row.getCell(0);
        XWPFTableCell row1column2 = row.addNewTableCell();
        formatCell(row1column1);
        formatCell(row1column2);

    }

    private void createSecondRow(XWPFTable table){

        XWPFTableRow row = table.createRow();
        row.setHeight(6119);
        XWPFTableCell row2column1 = row.getCell(0);
        XWPFTableCell row2column2 = row.getCell(1);
        formatCell(row2column1);
        formatCell(row2column2);
    }

    private void formatCell(XWPFTableCell cell){

        System.out.println("partCounter = " + partCounter);
        cell.setWidth("7920");
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        CTTc cttc1 = cell.getCTTc();
        CTTcPr ctPr1 = cttc1.addNewTcPr();
        ctPr1.addNewNoWrap();

        XWPFParagraph row1column1Paragraph = cell.addParagraph();
        row1column1Paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun row1column1ParagraphRun = row1column1Paragraph.createRun();
        row1column1ParagraphRun.setBold(true);
        if (this.formattedParts.get(this.partCounter).length() +
                this.formattedParts.get(this.partCounter + 1).length() > 58){
            row1column1ParagraphRun.setFontSize(35);
        } else {
            row1column1ParagraphRun.setFontSize(50);
        }
        row1column1ParagraphRun.setText(this.formattedParts.get(this.partCounter));
        this.partCounter++;
        row1column1ParagraphRun.addBreak();
        row1column1ParagraphRun.setText(this.formattedParts.get(this.partCounter));
        this.partCounter++;

    }

    private void setupDocument(XWPFDocument document){

        CTBody body = document.getDocument().getBody();

        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        CTPageMar pageMar = sectPr.addNewPgMar();
        pageMar.setLeft(BigInteger.valueOf(0L));
        pageMar.setTop(BigInteger.valueOf(0L));
        pageMar.setRight(BigInteger.valueOf(0L));
        pageMar.setBottom(BigInteger.valueOf(0L ));

        if (!body.isSetSectPr()) {
            body.addNewSectPr();
        }
        CTSectPr section = body.getSectPr();

        if(!section.isSetPgSz()) {
            section.addNewPgSz();
        }
        CTPageSz pageSize = section.getPgSz();

        pageSize.setW(BigInteger.valueOf(15840));
        pageSize.setH(BigInteger.valueOf(12240));
        pageSize.setOrient(STPageOrientation.LANDSCAPE);
    }

    public void createDocument(){

        for (int i = 0; i < this.tableNumber; i++){

            createTable(this.document);
        }

        try (FileOutputStream out = new FileOutputStream("table_output.docx")) {
            this.document.write(out);
            System.out.println("Table created successfully!");
        } catch (IOException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }

    }

}
