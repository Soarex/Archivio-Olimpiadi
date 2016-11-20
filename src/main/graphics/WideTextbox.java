package main.graphics;

import javax.swing.*;
import java.awt.*;

public class WideTextbox extends JTextField{
    public static final Color DEFAULT_TEXT_COLOR = new Color(0x999999);
    public static final Color HOVER_TEXT_COLOR = new Color(0xeeeeee);

    public WideTextbox() {
        super();
        setForeground(DEFAULT_TEXT_COLOR);
        setFont(new Font("Dialog", Font.PLAIN, 24));
        setOpaque(false);
        setCaretColor(DEFAULT_TEXT_COLOR);
    }

    public void setSelected(boolean b) {
        if(b) setForeground(HOVER_TEXT_COLOR);
        else setForeground(DEFAULT_TEXT_COLOR);
    }

    public Dimension getPreferredSize() {
        Dimension parentDimension = getParent().getSize();
        return new Dimension((int)(parentDimension.getWidth() - parentDimension.getWidth() * 0.2), 40);
    }

}
