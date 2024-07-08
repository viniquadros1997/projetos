package com.example.playlists.services;

import com.example.playlists.entitys.Playlist;
import com.example.playlists.repositorys.PlaylistRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PlaylistService {

    @Inject
    PlaylistRepository playlistRepository;

    @Transactional
    public Playlist savePlaylist(Playlist playlist) {
        playlist.persist();
        return playlist;
    }

    @Transactional
    public List<Playlist> findAllPlaylists() {
        return playlistRepository.listAll();
    }

    @Transactional
    public Playlist findPlaylistByNome(String nome) {
        return playlistRepository.findByNome(nome);
    }

    @Transactional
    public void deletePlaylistByNome(String nome) {
        playlistRepository.deleteByNome(nome);
    }
}
