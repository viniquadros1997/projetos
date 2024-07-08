package com.example.playlists.entitys;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Musica extends PanacheEntity {

    public String titulo;
    public String artista;
    public String album;
    public String ano;
    public String genero;
}
