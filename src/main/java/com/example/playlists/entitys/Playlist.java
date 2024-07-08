package com.example.playlists.entitys;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Playlist extends PanacheEntity {

    public String nome;
    public String descricao;

    @OneToMany(fetch = FetchType.EAGER, cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    public List<Musica> musicas;
}
