package be.webtechie.lednumberdisplaycontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public enum DisplaySkin {
    CLASSIC(75, 100, 5, 10,
        new LedSegmentDefinition("A",
            new double[] {0, 10, 50, 60, 50, 10, 0},
            new double[] {5, 0, 0, 5, 10, 10, 5}
        ),
            new LedSegmentDefinition("B",
            new double[] {60, 60, 50, 50, 60},
            new double[] {7, 48, 43, 12, 7}
        ),
            new LedSegmentDefinition("C",
            new double[] {60, 60, 50, 50, 60},
            new double[] {52, 93, 88, 57, 52}
        ),
            new LedSegmentDefinition("D",
            new double[] {0, 10, 50, 60, 50, 10, 0},
            new double[] {95, 90, 90, 95, 100, 100, 95}
        ),
            new LedSegmentDefinition("E",
            new double[] {0, 10, 10, 0, 0},
            new double[] {52, 57, 88, 93, 52}
        ),
            new LedSegmentDefinition("F",
            new double[] {0, 10, 10, 0, 0},
            new double[] {7, 12, 43, 48, 7}
        ),
            new LedSegmentDefinition("G",
            new double[] {0, 10, 50, 60, 50, 10, 0},
            new double[] {50, 45, 45, 50, 55, 55, 50}
        ));

    private final int width;
    private final int height;
    private final int dotSpacing;
    private final int dotDiameter;

    private final List<LedSegmentDefinition> segmentDefinitions;

    DisplaySkin(int width, int height, int dotSpacing, int dotDiameter,
            LedSegmentDefinition segmentDefinitionA,
            LedSegmentDefinition segmentDefinitionB,
            LedSegmentDefinition segmentDefinitionC,
            LedSegmentDefinition segmentDefinitionD,
            LedSegmentDefinition segmentDefinitionE,
            LedSegmentDefinition segmentDefinitionF,
            LedSegmentDefinition segmentDefinitionG) {
        this.width = width;
        this.height = height;
        this.dotSpacing = dotSpacing;
        this.dotDiameter = dotDiameter;

        this.segmentDefinitions = new ArrayList<>();
        this.segmentDefinitions.add(segmentDefinitionA);
        this.segmentDefinitions.add(segmentDefinitionB);
        this.segmentDefinitions.add(segmentDefinitionC);
        this.segmentDefinitions.add(segmentDefinitionD);
        this.segmentDefinitions.add(segmentDefinitionE);
        this.segmentDefinitions.add(segmentDefinitionF);
        this.segmentDefinitions.add(segmentDefinitionG);
    }

    public int getWidth(boolean includeDot) {
        return includeDot ? this.width : this.width - this.dotSpacing - this.dotDiameter;
    }

    public int getHeight() {
        return this.height;
    }

    public int getDotSpacing() {
        return dotSpacing;
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
