package br.com.fiap.dailymovies.movies;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository repository;

    public List<Movie> findAll(){
        return repository.findAll();
    }

    public boolean delete(Long id) {
        var task = repository.findById(id);
        if(task.isEmpty()) return false;

        repository.deleteById(id);
        return true;
    }
    
}
