package br.com.yanramos.linkshortener.controller;

import br.com.yanramos.linkshortener.entity.Link;
import br.com.yanramos.linkshortener.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/in/")
public class LinkController {
    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping
    public ResponseEntity<Void> goBackHome() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .build();
    }

    @GetMapping("/{shortenedLink}")
    public ResponseEntity<Void> getLink(@PathVariable("shortenedLink") String shortenedLink) {
        Optional<Link> link = linkService.getLinkByShortened(shortenedLink);

        if(link.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(link.get().getLinkTo()))
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CreateLinkDTO> createLink(@RequestBody CreateLinkDTO createLinkDTO) {

        Optional<String> response = linkService.createLink(createLinkDTO);

        if(response.isEmpty()) {
            return ResponseEntity.status(409).build();
        }
        String uri = response.get();

        CreateLinkDTO link = new CreateLinkDTO(createLinkDTO.linkTo(), uri);

        return ResponseEntity.status(201).body(link);
    }
}
