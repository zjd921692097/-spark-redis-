package com.example.demo.dao;

import com.example.demo.model.Music;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MusicDAOImplTest {

    @Test
    public void getAllMusic() {
        MusicDAOImpl musicDAO=new MusicDAOImpl();
        List<Music> musicList=musicDAO.getAllMusic();
        for(Music music:musicList){
            System.out.print("******************"+music.getName()+"******************");
        }
    }
}