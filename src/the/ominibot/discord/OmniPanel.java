package the.ominibot.discord;

import the.ominibot.discord.utils.omnibot.OmniState;

import javax.swing.*;
import java.awt.*;

public class OmniPanel extends JPanel {

    private static final OmniMain main = new OmniMain(false);
    private static ImageIcon backdraw = new ImageIcon(OmniPanel.class.getResource(main.getOmniKey().getOmniState().getBackgroundLocale()));
    private Timer timer;

    public OmniPanel(boolean load) {

        if (load) {
            timer = new Timer(1000 / 60, tick -> startDashTick());
            timer.start();
        }
    }

    public void stop() {
        timer.stop();
    }

    public void startDashTick() {
        backdraw = new ImageIcon(OmniPanel.class.getResource(main.getOmniKey().getOmniState().getBackgroundLocale()));

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(Color.DARK_GRAY);
        g2D.fillRect(0, 0, 950, 640);

        g2D.drawImage(backdraw.getImage(), 0, 0, null);

        if (main.getOmniKey().getOmniState() == OmniState.CONFIG_BOT) {
            g2D.setColor(Color.YELLOW);
            g2D.setFont(new Font("BOLD", Font.BOLD, 32));
            g2D.drawString(main.getSettings().getNAME(), 499, 256);

            g2D.setFont(new Font("BOLD", Font.BOLD, 32));
            g2D.drawString(main.getSettings().getTOKEN().substring(0, 8) + "...", 406, 290);
            g2D.drawString(main.getSettings().getSTATUS().toString(), 416, 325);

            g2D.drawString(main.getSettings().getMESSAGE(), 446, 354);
            g2D.drawString(main.getSettings().getVISIBILITY().toString(), 512, 384);
        }
    }
}
