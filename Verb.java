import org.json.JSONObject;
import java.lang.StringBuilder;
import java.util.Arrays;

public class Verb extends Word{

    String translation;
    String latin;
    String partOfSpeech;
    String type;

    public Verb(String translation, String latin){
        this.partOfSpeech = "Verb";
        this.translation = translation;
        this.type = identifyType(latin);
        this.latin = cleanLatin(latin, this.type);


    }

    public Verb(){

    }

    public void setTranslation(String translation){
        this.translation = translation;
    }

    public void setLatin(String latin){
        this.latin = latin;
    }
    
    public String toString(){

        return this.partOfSpeech + " - " + this.type + ": " +
                this.latin + "; " + this.translation + "\n";
        
    }

    // Amo, Amas, Amare A, amabi, amatum -- REGULAR 0
    // fero, fers, ferre IR, tuli, latum -- IRREGULAR 1
    // conor, conaris, conari A, conatus sum (Dep.) -- DEPONENT 2
    // diffido, diffidis, diffidere C, diffisus sum, diffisum -- SEMI-DEPONENT 3

    public String cleanLatin(String latin, String type){

        String[] parts = latin.split(",");
        String plainInfinitive = parts[2].split(" ")[1];

        if (type.equals("deponent")) {

            StringBuilder builder = new StringBuilder();
            builder.append(parts[3]);
            builder.delete(parts[3].length() - 7, parts[3].length());

            return parts[0] + ", " + plainInfinitive + "," + builder;
        }
        if (type.equals("semi-deponent")){

            return parts[0] + ", " + plainInfinitive + "," + parts[3];
        }


        return parts[0] + ", " + plainInfinitive + "," + parts[3] + "," + parts[4];

    }

    // Irregular:
//    [Verb: fio, fis, fieri IR, fieri, factum; to become
//, Verb: volo, vis, velle IR, volui, -; want, wish
//, Verb: nolo, non vis, nolle IR, nolui, -; be unwilling, wish not, want not, refuse
//, Verb: fero, fers, ferre IR, tuli, latum; (1.) bear, carry, bring (2.) suffer, endure, tolerate (3.) say, report

    // Semi deoponent:
//    [Verb: audeo, audes, audere E, ausus sum, ausum; dare, risk
//, Verb: gaudeo, gaudes, gaudere E, gavisus sum, gavisum; rejoice, take pleasure in
//, Verb: soleo, soles, solere E, solitus sum, solitum; I am used to
//, Verb: fido, fidis, fidere C, fisus sum, fisum; trust, rely on


    // Deponent:
//[Verb: conor, conaris, conari A, conatus sum (Dep.); try, attempt
//, Verb: mereor, mereris, mereri E, meritus sum (Dep.); earn, deserve/merit/have right, win/gain/incur, earn soldier/whore pay, serve
//, Verb: patior, pateris, pati M, passus sum (Dep.); suffer, endure, permit
//, Verb: orior, orieris, orii C, oritus sum (Dep.); rise (sun/river), arise/emerge, crop up, get up (wake), begin, originate from

    // Regular:
//    [Verb: amo, amas, amare A, amavi, amatum; love
//, Verb: teneo, tenes, tenere E, tenui, tentum; (1.) to hold, to come (2.) hold (3.) to hold, to come
//, Verb: duco, ducis, ducere C, duxi, ductum; lead, guide, develop, prolong
//, Verb: capio, capis, capere M, cepi, captum; catch
//            , Verb: audio, audis, audire I, audivi, auditum; hear, listen to, to Hear, to Listen

    public String identifyType(String latin){

        String[] parts = latin.split(",");

        if (parts[3].contains("(Dep.)"))
        {
            return "deponent";
        }
        else if (parts[3].split(" ").length > 2)
        {
            return "semi-deponent";
        }
        else if (parts[2].contains("IR"))
        {
            return "irregular";
        }
        else{
            return "regular";
        }



    }



}
