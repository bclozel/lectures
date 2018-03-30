package fr.insalyon.tc.springboothol;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ShortURLRepository extends CrudRepository<ShortURL, String> {

	// add custom methods here
	Optional<ShortURL> findByShortCode(String shortCode);
}