package libgdx.controls.label;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import libgdx.controls.TextTable;
import libgdx.game.Game;
import libgdx.resources.FontManager;
import libgdx.utils.model.FontColor;

public class MyWrappedLabel extends TextTable {

    private MyWrappedLabelConfig myWrappedLabelConfig;
    private List<MyLabel> rowLabels = new ArrayList<>();

    public MyWrappedLabel() {
        this("");
    }

    public MyWrappedLabel(String text) {
        this(new MyWrappedLabelConfigBuilder().setSingleLineLabel().setText(text).build());
    }

    public MyWrappedLabel(MyWrappedLabelConfig myWrappedLabelConfig) {
        this.myWrappedLabelConfig = myWrappedLabelConfig;
        create(myWrappedLabelConfig.getText());
        if (myWrappedLabelConfig.isSingleLineLabel()) {
            fitToContainer();
        }
    }

    public void setEllipsis(float labelWidth) {
        for (MyLabel myLabel : getLabels()) {
            myLabel.setEllipsis(true);
            myLabel.setWidth(labelWidth);
            Table table = (Table) myLabel.getParent();
            table.clearChildren();
            table.add(myLabel).width(labelWidth);
        }
    }

    public void setAlignment(int align) {
        for (MyLabel myLabel : getLabels()) {
            myLabel.setAlignment(align);
        }
    }

    public void setFontScale(float fontScale) {
        for (MyLabel myLabel : getLabels()) {
            myLabel.setFontScale(fontScale);
        }
    }

    public void setTextColor(FontColor color) {
        for (MyLabel myLabel : getLabels()) {
            Label.LabelStyle style = new Label.LabelStyle();
            style.font = getFont(color);
            style.fontColor = color.getColor();
            myLabel.setStyle(style);
        }
    }

    public void setText(String text) {
        getLabels().get(0).setText(text);
    }

    @Override
    public String getText() {
        return myWrappedLabelConfig.getText();
    }

    @Override
    public List<MyLabel> getLabels() {
        return rowLabels;
    }

    private void create(String text) {
        if (myWrappedLabelConfig.isSingleLineLabel()) {
            addSingleLineLabel(text);
        } else {
            List<String> wrappedLines = new ArrayList<>();
            BitmapFont font = getFont(myWrappedLabelConfig.getTextColor());
            font.getData().setScale(myWrappedLabelConfig.getFontScale());
            try {
                GlyphLayout glyphLayout = new GlyphLayout(font, text, Color.RED, myWrappedLabelConfig.getWidth(), Align.center, true);
                for (GlyphLayout.GlyphRun glyphRun : glyphLayout.runs) {
                    wrappedLines.add(getGlyphsString(glyphRun.glyphs));
                }
                clearChildren();
                for (String line : wrappedLines) {
                    MyLabel myLabel = configLabel(line);
                    myLabel.setWidth(myWrappedLabelConfig.getWidth());
                    add(myLabel).width(myWrappedLabelConfig.getWidth()).row();
                }
            } catch (IndexOutOfBoundsException e) {
                create(text);
            }
        }
    }

    private void addSingleLineLabel(String text) {
        add(configLabel(text));
    }

    private MyLabel configLabel(String text) {
        MyLabel label = new MyLabel(text);
        label.setFontScale(myWrappedLabelConfig.getFontScale());
        label.setAlignment(Align.center);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = getFont(myWrappedLabelConfig.getTextColor());
        label.setStyle(labelStyle);
        rowLabels.add(label);
        return label;
    }

    private BitmapFont getFont(FontColor textColor) {
        return Game.getInstance().getFontManager().getFont(textColor);
    }

    private String getGlyphsString(Array<BitmapFont.Glyph> glyphs) {
        StringBuilder buffer = new StringBuilder(glyphs.size);
        for (int i = 0, n = glyphs.size; i < n; i++) {
            BitmapFont.Glyph g = glyphs.get(i);
            buffer.append((char) g.id);
        }
        return buffer.toString();
    }

    public void setStyleDependingOnContrast() {
        setTextColor(MyWrappedLabelConfigBuilder.getScreenContrastStyle());
    }

    public void setStyleDependingOnContrast(FontColor darkContrastStyle, FontColor lightContrastStyle) {
        setTextColor(MyWrappedLabelConfigBuilder.getScreenContrastStyle(darkContrastStyle, lightContrastStyle));
    }

    public MyWrappedLabel fitToContainer() {
        MyWrappedLabel label = this;
        while (label.getLabels().size() > 1) {
            myWrappedLabelConfig.setFontScale(myWrappedLabelConfig.getFontScale() / 1.01f);
            label = new MyWrappedLabel(myWrappedLabelConfig);
        }
        return label;
    }
}
