import java.io.FileOutputStream;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import java.math.BigInteger;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;


public class DocxCreator {

    private ArrayList<String> formattedParts;
    private int partCounter;
    private int tableNumber;
    private XWPFDocument document;
    private HashMap<Integer, Integer> secondTableMap;
    private HashMap<Integer, Integer> fontMap;

    public DocxCreator(ArrayList<Word> words){

        this.tableNumber = getTableNumber(words);
        this.secondTableMap = new HashMap<>();
        this.fontMap = new HashMap<>();
        configureHashMaps(this.tableNumber);
        this.partCounter = 0;
        this.formattedParts = formatParts(words);
        this.document = new XWPFDocument();
        setupDocument(this.document);
    }

    private void configureHashMaps(int tableNumber){

        boolean adding = true;
        /*
         When putting the translations and parts of speech on the second table of a table set,
         it will be printed on the back, which means that for the two squares (latin and translation)
         to line up, I had to swap the position of the cells in the rows. Numerically it looks like this:

         {0,1,2,3} ----> {1,0,3,2}

         And, visually:
         ________________________
         |          |           |
         |     0    |     1     |
         |          |           |
         |__________|___________|      FRONT
         |          |           |
         |     2    |    3      |
         |          |           |
         |__________|___________|

         ________________________
         |          |           |
         |     1    |     0     |
         |          |           |
         |__________|___________|      BACK
         |          |           |
         |     3    |    2      |
         |          |           |
         |__________|___________|

         Since I have an arraylist of all the parts lined out in the correct order so adding a cell
         is simply just taking the next index from the arraylist, I had to alter the order of putting
         strings into the arraylist

         And, since each "swap" is only either adding 1 or subtracting 1, this solution below works
         i < tableNumber * 2 because I only need maps for 1/2 of the total amount of tables but for 4 cells
         per table - (1/2) * 4 = 2

         This is just a repeating pattern, so for the first page it's:

         {0,1,2,3} ----> {1,0,3,2}

         And, the second:

         {4,5,6,7} ----> {5,4,7,6}

         And so on...
        */
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
        // i < tableNumber / 2 because this loop creates the 3 parts of speech for the 4 words per
        // table PAIR - not per table
        for (int i = 0; i < this.tableNumber / 2; i++){

            // Adding the latin - straightforward
            // j = i * 4 because we don't want to repeat words - keep going forward!
            for (int j = i * 4; j < (i * 4 + 4); j++){

                try {
                    formattedParts.add(words.get(j).latin);
                } catch (IndexOutOfBoundsException e){
                    formattedParts.add("");
                }
                formattedParts.add("");

            }

            // Adding the translation and part of speech for each word using the hashmap seen above
            // For each set of tables we have 12 pieces of information, but after the first 4 the next
            // 8 need to be grouped in groups of 2, that's why this for loop has two additions to the
            // arraylist and the first for loop only had 1
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

        // This only works since I am using integer division
        if (words.size() % 4 == 0){

            return (words.size() / 4) * 2;
        }

        // The words.size() / 4 returns the remainder, then we add 1 since because there is a
        // remainder, that remainder will need its own table pair to itself (or it could be 2 or 3 also)
        return ((words.size() / 4) + 1) * 2;
    }

    private double getFontSize(String string){
        // 500 per substring add a space
        // 1000 downsize to 35 whole string font 50
        // 1500 downsize to 28 font 35
        // 2000 downsize to 22 font 28
        int fontSize = 50;
        AffineTransform affineTransform =  new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affineTransform, true, true);
        Font font = new Font("Calibri", Font.BOLD, fontSize);

        // 500 per substring add a space
        // 1000 downsize to 35 whole string font 50
        // 1500 downsize to 28 font 35
        // 2000 downsize to 22 font 28
        // Since the length starts at 1000 and goes up by increments of 500 this for loop is the most efficient way to structure this
        for (int i = 2; i < 6; i++){

            if (font.getStringBounds(string, frc).getWidth() > i * 500){

                fontSize = this.fontMap.get(i);
                font = new Font("Calibri", Font.BOLD, fontSize);

            }
        }

        return fontSize;

    }

    private void preparePart(String part, double fontSize){

        AffineTransform affineTransform =  new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affineTransform, true, true);
        Font font = new Font("Calibri", Font.BOLD, (int) fontSize);

        String[] parts = part.split(" ");
        for (String splitPart : parts){

            if (font.getStringBounds(splitPart, frc).getWidth() > 500){
                StringBuilder builder = new StringBuilder();
                builder.append(this.formattedParts.get(this.partCounter));
                builder.insert(builder.length() / 2, " ");
                this.formattedParts.set(this.partCounter, builder.toString());
            }
        }

    }

    // These next four methods go together, it's one big chain
    private void createTable(XWPFDocument document){

        XWPFTable table = document.createTable();
        table.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(15840));

        createFirstRow(table);
        createSecondRow(table);
    }

    // First and second row is different because of how tables are created with Apache POI
    // The first row first cell is always created, then we need to add cells to the first row
    // All rows created after automatically have the number of cells the row before it has (the first row)
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

        cell.setWidth("7920");
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        CTTc cttc = cell.getCTTc();
        CTTcPr ctPr = cttc.addNewTcPr();
        ctPr.addNewNoWrap();

        XWPFParagraph paragraph = cell.addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setBold(true);
        double fontSize = getFontSize(this.formattedParts.get(this.partCounter));
        run.setFontSize(fontSize);

        preparePart(this.formattedParts.get(this.partCounter), fontSize);
        run.setText(this.formattedParts.get(this.partCounter));
        this.partCounter++;

        run.addBreak();

        preparePart(this.formattedParts.get(this.partCounter), fontSize);
        run.setText(this.formattedParts.get(this.partCounter));
        this.partCounter++;

    }

    // Setting settings for the document
    // no margins, landscape, size, etc.
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

        // Since I created a formatted arraylist to take care of knowing what text to put in what box,
        // I can just create as many tables as there needs to be without worrying
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
