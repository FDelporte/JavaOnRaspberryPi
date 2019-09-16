package be.webtechie.lednumberdisplaycontroller;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LedNumberDisplay extends Pane {

    private final LedSegment segmentA;
    private final LedSegment segmentB;
    private final LedSegment segmentC;
    private final LedSegment segmentD;
    private final LedSegment segmentE;
    private final LedSegment segmentF;
    private final LedSegment segmentG;
    private final DotSegment segmentDot;

    private final Color idleColor;
    private final Color selectedColor;

    public LedNumberDisplay(DisplaySkin displaySkin, Color backgroundColor, Color idleColor, Color selectedColor) {
        this(displaySkin, backgroundColor, idleColor, selectedColor, true);
    }

    public LedNumberDisplay(DisplaySkin displaySkin, Color backgroundColor, Color idleColor, Color selectedColor, boolean showDot) {
        this.idleColor = idleColor;
        this.selectedColor = selectedColor;

        // Set the background color
        this.setStyle("-fx-background-color: #" + ColorUtil.colorToHex(backgroundColor, false));

        // Horizontal segments
        this.segmentA = new LedSegment(displaySkin.getSegmentDefinition("A"), idleColor, displaySkin.getWidth(showDot), displaySkin.getHeight());
        this.segmentB = new LedSegment(displaySkin.getSegmentDefinition("B"), idleColor, displaySkin.getWidth(showDot), displaySkin.getHeight());
        this.segmentC = new LedSegment(displaySkin.getSegmentDefinition("C"), idleColor, displaySkin.getWidth(showDot), displaySkin.getHeight());
        this.segmentD = new LedSegment(displaySkin.getSegmentDefinition("D"), idleColor, displaySkin.getWidth(showDot), displaySkin.getHeight());
        this.segmentE = new LedSegment(displaySkin.getSegmentDefinition("E"), idleColor, displaySkin.getWidth(showDot), displaySkin.getHeight());
        this.segmentF = new LedSegment(displaySkin.getSegmentDefinition("F"), idleColor, displaySkin.getWidth(showDot), displaySkin.getHeight());
        this.segmentG = new LedSegment(displaySkin.getSegmentDefinition("G"), idleColor, displaySkin.getWidth(showDot), displaySkin.getHeight());
        this.getChildren().addAll(this.segmentA, this.segmentB, this.segmentC, this.segmentD, this.segmentE, this.segmentF, this.segmentG);

        this.segmentDot = new DotSegment(
                displaySkin.getDotDiameter(),
                displaySkin.getWidth(showDot) - displaySkin.getDotSpacing() - displaySkin.getDotDiameter(),
                displaySkin.getHeight() - displaySkin.getDotDiameter(),
                idleColor,
                displaySkin.getWidth(showDot),
                displaySkin.getHeight());

        if (showDot) {
            this.getChildren().add(this.segmentDot);
        }
    }

    public void highlight(HighlightType type) {
        this.highlight(type, false);
    }

    public void highlight(HighlightType type, boolean dot) {
        if (type == null) {
            type = HighlightType.CLEAR;
        }

        this.highlight(type.isA(), type.isB(), type.isC(), type.isD(), type.isE(), type.isF(), type.isG(), dot);
    }

    public void highlight(boolean a, boolean b, boolean c, boolean d, boolean e, boolean f, boolean g, boolean dot) {
        this.segmentA.setColor(a ? this.selectedColor : this.idleColor);
        this.segmentB.setColor(b ? this.selectedColor : this.idleColor);
        this.segmentC.setColor(c ? this.selectedColor : this.idleColor);
        this.segmentD.setColor(d ? this.selectedColor : this.idleColor);
        this.segmentE.setColor(e ? this.selectedColor : this.idleColor);
        this.segmentF.setColor(f ? this.selectedColor : this.idleColor);
        this.segmentG.setColor(g ? this.selectedColor : this.idleColor);

        this.segmentDot.setColor(dot ? this.selectedColor : this.idleColor);
    }
}
