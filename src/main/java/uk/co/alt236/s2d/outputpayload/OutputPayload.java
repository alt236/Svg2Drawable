package uk.co.alt236.s2d.outputpayload;

public class OutputPayload {
    private final String svgPath;
    private final int width;
    private final int height;
    private final String targetFile;
    private final String logComment;

    public OutputPayload(final String svgPath,
                         final int width,
                         final int height,
                         final String targetFile,
                         final String logComment) {
        this.svgPath = svgPath;
        this.width = width;
        this.height = height;
        this.targetFile = targetFile;
        this.logComment = logComment;
    }

    @Override
    public String toString() {
        return "OutputPayload{" +
                "svgPath='" + svgPath + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", targetFile='" + targetFile + '\'' +
                ", logComment='" + logComment + '\'' +
                '}';
    }

    public String getSvgPath() {
        return svgPath;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTargetFile() {
        return targetFile;
    }

    public String getLogComment() {
        return logComment;
    }
}
