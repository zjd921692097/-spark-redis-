package com.example.demo.service;

import com.example.demo.dao.MusicDAO;
import com.example.demo.dao.MusicDAOImpl;
import com.example.demo.model.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MusicManagerImpl implements MusicManager{


    @Autowired
    MusicDAO musicDAO=new MusicDAOImpl();

    @Override
    public List<Music> getAllMusic() {
        return musicDAO.getAllMusic();
    }
}
