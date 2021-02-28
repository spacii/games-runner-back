package com.api.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long videoId;
    private String videoName;
    private String videoDescription;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonIgnore
    //@JsonBackReference
    private Game game;

    public Video() {
    }

    public Video(Long videoId, String videoName, String videoDescription) {
        this.videoId = videoId;
        this.videoName = videoName;
        this.videoDescription = videoDescription;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
