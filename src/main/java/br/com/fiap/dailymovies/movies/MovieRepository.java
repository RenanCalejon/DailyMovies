package br.com.fiap.dailymovies.movies;


import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>{
    
}
