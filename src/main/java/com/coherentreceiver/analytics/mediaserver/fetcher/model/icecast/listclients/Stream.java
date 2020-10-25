/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients;


        import javax.xml.bind.annotation.XmlAccessType;
        import javax.xml.bind.annotation.XmlAccessorType;
        import javax.xml.bind.annotation.XmlRootElement;
        import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement (name="")

public class Stream
{


    private String mountPoint;
    private String uri;

    private String title;

    private String description;


    private Integer currentListenerCount = -1;


    private Integer maxListenerCount = -1;


    private Integer peakListenerCount = -1;


    private String bitRate;


    private String currentSong;


    private String contentType;


    private String genre;

    private Date timeStamp;

    public Stream()
    {
    }

    public void clear()
    {
        title = null;
        description = null;
        uri = null;
        currentListenerCount = -1;
        maxListenerCount = -1;
        peakListenerCount = -1;
        bitRate = null;
        currentSong = null;
        contentType = null;
        genre = null;
    }

    public void merge(Stream another)
    {
        if (title == null) {
            title = another.getTitle();
        }
        if (description == null) {
            description = another.getDescription();
        }
        if (uri == null) {
            uri = another.getUri();
        }
        if (currentListenerCount < 0) {
            currentListenerCount = another.getCurrentListenerCount();
        }
        if (maxListenerCount < 0) {
            maxListenerCount = another.getMaxListenerCount();
        }
        if (peakListenerCount < 0) {
            peakListenerCount = another.getPeakListenerCount();
        }
        if (bitRate == null) {
            bitRate = another.getBitRate();
        }
        if (currentSong == null) {
            currentSong = another.getCurrentSong();
        }
        if (contentType == null) {
            contentType = another.getContentType();
        }
        if (genre == null) {
            genre = another.getGenre();
        }
    }

    public String getTimeStamp()
    {
        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(timeStamp);
    }

    public void setTimeStamp(Date timeStamp){
        this.timeStamp=timeStamp;
    }

    public String getMountPoint() {
        return mountPoint;
    }

    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }

    public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public String getDescription()
        {
            return description;
        }

        public void setDescription(String description)
        {
            this.description = description;
        }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }

    public Integer getCurrentListenerCount()
    {
        return currentListenerCount;
    }

    public void setCurrentListenerCount(int currentListenerCount)
    {
        this.currentListenerCount = currentListenerCount;
    }

    public Integer getMaxListenerCount()
    {
        return maxListenerCount;
    }

    public void setMaxListenerCount(int maxListenerCount)
    {
        this.maxListenerCount = maxListenerCount;
    }

    public Integer getPeakListenerCount()
    {
        return peakListenerCount;
    }

    public void setPeakListenerCount(int peakListenerCount)
    {
        this.peakListenerCount = peakListenerCount;
    }

    public String getBitRate()
    {
        return bitRate;
    }

    public void setBitRate(String bitRate)
    {
        this.bitRate = bitRate;
    }

    public String getCurrentSong()
    {
        return currentSong;
    }

    public void setCurrentSong(String currentSong)
    {
        this.currentSong = currentSong;
    }

    public String getContentType()
    {
        return contentType;
    }

    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Stream{" +
                "mountPoint='" + mountPoint + '\'' +
                ", uri='" + uri + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", currentListenerCount=" + currentListenerCount +
                ", maxListenerCount=" + maxListenerCount +
                ", peakListenerCount=" + peakListenerCount +
                ", bitRate='" + bitRate + '\'' +
                ", currentSong='" + currentSong + '\'' +
                ", contentType='" + contentType + '\'' +
                ", genre='" + genre + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}

