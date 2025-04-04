package br.com.yanramos.linkshortener.repository;

import br.com.yanramos.linkshortener.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LinkRepository extends JpaRepository<Link, UUID> {
    Optional<Link> findByShortenedLink(String shortenedLink);
}
