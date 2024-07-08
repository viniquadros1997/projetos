package com.example.playlists.repositorys;

import com.example.playlists.entitys.Playlist;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PlaylistRepository implements PanacheRepository<Playlist> {

    public Playlist findByNome(String nome) {
        return find("nome", nome).firstResult();
    }

    public void deleteByNome(String nome) {
        delete("nome", nome);
    }
}
