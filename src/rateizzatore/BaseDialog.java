package rateizzatore;

import javax.swing.JDialog;
import javax.swing.JFrame;

abstract public class BaseDialog extends JDialog {

    public BaseDialog(JFrame owner, String title) {
        super(owner, title);
    }

    abstract void reset();
}
