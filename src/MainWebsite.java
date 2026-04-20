
import java.awt.*;
import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;

public class MainWebsite {

   public static void main(String[] args) {

    try {
        UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
    } catch (Exception e) {
        e.printStackTrace();
    }

    new Authenticate(); 
}
}
