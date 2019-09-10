package libgdx.controls.popup.notificationpopup;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.Objects;

import libgdx.resources.Res;
import libgdx.resources.dimen.MainDimen;
import libgdx.utils.ScreenDimensionsManager;


public class MyNotificationPopupConfig {

    private String text;
    private Res resource;
    private float imageDimen = MainDimen.side_notification_popup_icon.getDimen();
    private float popupWidth = ScreenDimensionsManager.getScreenWidthValue(70);
    private Table contentTable;

    MyNotificationPopupConfig() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Res getResource() {
        return resource;
    }

    public void setResource(Res resource) {
        this.resource = resource;
    }

    public Float getImageDimen() {
        return imageDimen;
    }

    public void setImageDimen(Float imageDimen) {
        this.imageDimen = imageDimen;
    }

    public Float getPopupWidth() {
        return popupWidth;
    }

    public void setPopupWidth(Float popupWidth) {
        this.popupWidth = popupWidth;
    }

    public Table getContentTable() {
        return contentTable;
    }

    public void setContentTable(Table contentTable) {
        this.contentTable = contentTable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyNotificationPopupConfig config = (MyNotificationPopupConfig) o;
        return Float.compare(config.imageDimen, imageDimen) == 0 &&
                Float.compare(config.popupWidth, popupWidth) == 0 &&
                Objects.equals(text, config.text) &&
                resource == config.resource;
    }

    @Override
    public int hashCode() {

        return Objects.hash(text, resource, imageDimen, popupWidth);
    }
}
