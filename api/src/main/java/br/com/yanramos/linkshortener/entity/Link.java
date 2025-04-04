package br.com.yanramos.linkshortener.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "link")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID linkId;

    private String shortenedLink;

    private String linkTo;

    public Link() {}

    public Link(UUID linkId, String shortenedLink, String linkTo) {
        this.linkId = linkId;
        this.shortenedLink = shortenedLink;
        this.linkTo = linkTo;
    }

    public UUID getLinkId() {
        return linkId;
    }

    public void setLinkId(UUID linkId) {
        this.linkId = linkId;
    }

    public String getShortenedLink() {
        return shortenedLink;
    }

    public void setShortenedLink(String shortenedLink) {
        this.shortenedLink = shortenedLink;
    }

    public String getLinkTo() {
        return linkTo;
    }

    public void setLinkTo(String linkTo) {
        this.linkTo = linkTo;
    }
}