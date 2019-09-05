package be.webtechie.lednumberdisplaycontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LedType {

    private final int width;
    private final int height;
    private final int dotDiameter;

    private List<LedSegmentDefinition> segmentDefinitions = new ArrayList<>();

    public LedType(int width, int height, int dotDiameter) {
        this.width = width;
        this.height = height;
        this.dotDiameter = dotDiameter;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDotDiameter() {
        return dotDiameter;
    }

    public void addSegmentDefinition(LedSegmentDefinition segmentDefinition) {
        this.segmentDefinitions.add(segmentDefinition);
    }

    public LedSegmentDefinition getSegmentDefinition(String label) {
        Optional<LedSegmentDefinition> ledSegmentDefinition = this.segmentDefinitions.stream().filter(l -> l.getLabel().equals(label)).findFirst();

        if (ledSegmentDefinition.isPresent()) {
            return ledSegmentDefinition.get();
        } else {
            throw new IllegalArgumentException("The segment with label " + label + " is not defined!");
        }
    }
}
