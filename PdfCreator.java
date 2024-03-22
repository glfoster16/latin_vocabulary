//import java.io.*;
//import java.math.BigInteger;
//
////needed jars: xdocreport-2.0.1.jar,
////             odfdom-java-0.8.7.jar,
////             itext-2.1.7.jar
//import fr.opensagres.xdocreport.converter.Options;
//import fr.opensagres.xdocreport.converter.IConverter;
//import fr.opensagres.xdocreport.converter.ConverterRegistry;
//import fr.opensagres.xdocreport.converter.ConverterTypeTo;
//import fr.opensagres.xdocreport.core.document.DocumentKind;
//
////needed jars: apache poi and it's dependencies
////             and additionally: ooxml-schemas-1.3.jar
//import org.apache.poi.xwpf.usermodel.*;
//import org.apache.poi.util.Units;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
//
//public class PdfCreator {
//
//    XWPFDocument document;
//
//    public PdfCreator(XWPFDocument document){
//
//        this.document = document;
//
//    }
//
//    public void XWPFDocumentToPdf() throws IOException {
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        document.write(out);
//        document.close();
//
//        // 1) Create options DOCX 2 PDF to select well converter form the registry
//        Options options = Options.getFrom(DocumentKind.DOCX).to(ConverterTypeTo.PDF);
//
//        // 2) Get the converter from the registry
//        IConverter converter = ConverterRegistry.getRegistry().getConverter(options);
//
//        // 3) Convert DOCX 2 PDF
//        InputStream docxin= new ByteArrayInputStream(out.toByteArray());
//        OutputStream pdfout = new FileOutputStream(new File("XWPFToPDFXDocReport.pdf"));
//        converter.convert(docxin, pdfout, options);
//
//        docxin.close();
//        pdfout.close();
//    }
//
//}
