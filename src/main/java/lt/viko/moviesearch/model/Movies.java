package lt.viko.moviesearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Represents Movies object that is used to store data from JSON file
 * with the help of Jackson library
 * @version 1.0
 */
public class Movies {
    @JsonProperty("_id")
    private String id;

    @JsonProperty("id")
    private String imdbId;

    @JsonProperty("titleText")

    private TitleText titleText;

    @JsonProperty("originalTitleText")
    private TitleText originalTitleText;

    @JsonProperty("releaseYear")
    private YearRange releaseYear;

    @JsonProperty("releaseDate")
    private ReleaseDate releaseDate;

    @JsonProperty("primaryImage")
    private PrimaryImage primaryImage;

    public Movies() {
    }

    public Movies(String id, String imdbId, TitleText titleText, TitleText originalTitleText, YearRange releaseYear, ReleaseDate releaseDate, PrimaryImage primaryImage) {
        this.id = id;
        this.imdbId = imdbId;
        this.titleText = titleText;
        this.originalTitleText = originalTitleText;
        this.releaseYear = releaseYear;
        this.releaseDate = releaseDate;
        this.primaryImage = primaryImage;
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("id")
    public String getImdbId() {
        return imdbId;
    }

    @JsonProperty("id")
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    @JsonProperty("titleText")
    public TitleText getTitleText() {
        return titleText;
    }

    @JsonProperty("titleText")
    public void setTitleText(TitleText titleText) {
        this.titleText = titleText;
    }

    @JsonProperty("originalTitleText")
    public TitleText getOriginalTitleText() {
        return originalTitleText;
    }

    @JsonProperty("originalTitleText")
    public void setOriginalTitleText(TitleText originalTitleText) {
        this.originalTitleText = originalTitleText;
    }

    @JsonProperty("releaseYear")
    public YearRange getReleaseYear() {
        return releaseYear;
    }

    @JsonProperty("releaseYear")
    public void setReleaseYear(YearRange releaseYear) {
        this.releaseYear = releaseYear;
    }

    @JsonProperty("releaseDate")
    public ReleaseDate getReleaseDate() {
        return releaseDate;
    }

    @JsonProperty("releaseDate")
    public void setReleaseDate(ReleaseDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @JsonProperty("primaryImage")
    public PrimaryImage getPrimaryImage() {
        return primaryImage;
    }

    @JsonProperty("primaryImage")
    public void setPrimaryImage(PrimaryImage primaryImage) {
        this.primaryImage = primaryImage;
    }

    public static class TitleText {
        private String text;

        public TitleText() {
        }

        public TitleText(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class YearRange {
        private int year;
        private Integer endYear;

        public YearRange() {
        }

        public YearRange(int year, Integer endYear) {
            this.year = year;
            this.endYear = endYear;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public Integer getEndYear() {
            return endYear;
        }

        public void setEndYear(Integer endYear) {
            this.endYear = endYear;
        }
    }

    public static class ReleaseDate {
        private int day;
        private int month;
        private int year;

        public ReleaseDate() {
        }

        public ReleaseDate(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }

    public static class PrimaryImage {
        private String id;
        private int width;
        private int height;
        private String url;
        private Caption caption;

        public PrimaryImage() {
        }

        public PrimaryImage(String id, int width, int height, String url, Caption caption) {
            this.id = id;
            this.width = width;
            this.height = height;
            this.url = url;
            this.caption = caption;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Caption getCaption() {
            return caption;
        }

        public void setCaption(Caption caption) {
            this.caption = caption;
        }

        public static class Caption {
            @JsonProperty("plainText")
            private String text;

            public Caption() {
            }

            public Caption(String text) {
                this.text = text;
            }

            @JsonProperty("plainText")
            public String getText() {
                return text;
            }

            @JsonProperty("plainText")
            public void setText(String text) {
                this.text = text;
            }
        }
    }
}
