package the.ominibot.discord.events;

import the.ominibot.discord.OmniMain;
import the.ominibot.discord.utils.omnibot.OmniState;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OmniKey implements MouseListener {

    private static final OmniMain main = new OmniMain(false);
    private static OmniState omniState = OmniState.STOPPED_BOT;
    private static OmniState recOmniState = OmniState.STOPPED_BOT;

    @Override
    public void mouseClicked(MouseEvent e) {
        int xM = e.getX();
        int yM = e.getY();

        Rectangle mouseTangle = new Rectangle(xM, yM, 40, 40);
        Rectangle configBOT = new Rectangle(474, 476, 334, 108);
        Rectangle powerToggleBOT = new Rectangle(476, 417, 333, 47);

        switch (getOmniState()) {
            case STOPPED_BOT:
                if (mouseTangle.intersects(powerToggleBOT)) {
                    setOmniStateAction(2, OmniState.STARTED_BOT);
                } else if (mouseTangle.intersects(configBOT)) {
                    setOmniStateAction(3, OmniState.STARTED_BOT);
                }
                break;
            case STARTED_BOT:
                if (mouseTangle.intersects(powerToggleBOT)) {
                    setOmniStateAction(1, OmniState.STOPPED_BOT);
                } else if (mouseTangle.intersects(configBOT)) {
                    setOmniStateAction(1, OmniState.STOPPED_BOT);
                }
                break;
            case CONFIG_BOT:
                Rectangle toDashboard = new Rectangle(491, 525, 334, 108);

                if (mouseTangle.intersects(toDashboard)) {
                    setOmniState(getRecOmniState());
                }
                break;
        }
    }

    private void setOmniStateAction(int i, OmniState state) {
        switch (i) {
            case 1:
                setRecOmniState(state);
                setOmniState(OmniState.STOPPED_BOT);
                main.unloadBot();
                break;
            case 2:
                setRecOmniState(state);
                setOmniState(OmniState.STARTED_BOT);
                main.loadBot();
                break;
            case 3:
                setRecOmniState(state);
                setOmniState(OmniState.CONFIG_BOT);
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public OmniState getRecOmniState() {
        return recOmniState;
    }

    public OmniState getOmniState() {
        return omniState;
    }

    public void setRecOmniState(OmniState recOmniState) {
        this.recOmniState = recOmniState;
    }

    public void setOmniState(OmniState state) {
        omniState = state;
    }
}
