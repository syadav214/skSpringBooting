package pdf.audio;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.sound.sampled.AudioFileFormat;
import java.io.File;
import java.io.IOException;

public class convertor {
    public static void main(String[] args) {
        convertFile();
    }

    private static void convertFile(){
        File file = new File("C://Users//Downloads//The Phoenix Project.pdf");
        try {
            PDDocument document = PDDocument.load(file);
            //int totalPages = document.getNumberOfPages();

            for(int i = 16; i <= 16; i++){
                PDFTextStripper pdfStripper = new PDFTextStripper();
                pdfStripper.setStartPage(i);
                pdfStripper.setEndPage(i);
                String text = pdfStripper.getText(document);
                //System.out.println(text);
                getVoice(text);
            }

            //Closing the document
            document.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void getVoice(String text){
        System.setProperty("freetts.voices",
                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager vm = VoiceManager.getInstance();
        Voice voice = vm.getVoice("kevin16");

        voice.allocate();

        SingleFileAudioPlayer audioPlayer = new SingleFileAudioPlayer("output", AudioFileFormat.Type.WAVE);
        voice.setAudioPlayer(audioPlayer);

        voice.speak(text);

        voice.deallocate();
        audioPlayer.close();
    }
}