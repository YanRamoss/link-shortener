package br.com.yanramos.linkshortener.service;

import br.com.yanramos.linkshortener.controller.CreateLinkDTO;
import br.com.yanramos.linkshortener.entity.Link;
import br.com.yanramos.linkshortener.repository.LinkRepository;
import br.com.yanramos.linkshortener.util.RandomLinkGenerator;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Optional<String> createLink(CreateLinkDTO createLinkDTO) {
        boolean isPresent = false;
        boolean generatedByDefault = false;
        String url = "";
        String generateRandomLink = !Objects.equals(createLinkDTO.shortenedLink(), "") ? createLinkDTO.shortenedLink() : null;
        do{
            if(generateRandomLink == null){
                generateRandomLink = RandomLinkGenerator.generateRandomLink();
                generatedByDefault = true;
            }

            Optional<Link> tryLink = linkRepository.findByShortenedLink(generateRandomLink);

            isPresent = tryLink.isPresent();
            if(isPresent & !generatedByDefault) return Optional.empty();
        }while(isPresent);

        if(!createLinkDTO.linkTo().startsWith("http://") &
                !createLinkDTO.linkTo().startsWith("https://")) {
            url = "http://"+createLinkDTO.linkTo();
        }

        Link link = new Link(null, generateRandomLink, url);

        linkRepository.save(link);
        return Optional.of(generateRandomLink);
    }

    public Optional<Link> getLinkByShortened(String shortenedLink) {
        return linkRepository.findByShortenedLink(shortenedLink);
    }
}
