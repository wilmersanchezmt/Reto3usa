/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3v3.demo;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Wilmer_Sánchez
 */
@Service
public class ServiciosMotorbike {
     @Autowired
    private RepositorioMotorBike metodosCrud;

    public List<Motorbike> getAll(){
        return metodosCrud.getAll();
    }

    public Optional<Motorbike> getMotorbikes(int bikeId) {
        return metodosCrud.getBike(bikeId);
    }

    public Motorbike save(Motorbike motorbike){
        if(motorbike.getId()== null){
            return metodosCrud.save(motorbike);
        }else{
            Optional<Motorbike> e=metodosCrud.getBike(motorbike.getId());
            if(e.isEmpty()){
                return metodosCrud.save(motorbike);
            }else{
                return motorbike;
            }
        }
    }

    public Motorbike update(Motorbike motorbike){
        if(motorbike.getId()!=null){
            Optional<Motorbike> e=metodosCrud.getBike(motorbike.getId());
            if(!e.isEmpty()){
                if(motorbike.getName()!=null){
                    e.get().setName(motorbike.getName());
                }
                if(motorbike.getBrand()!=null){
                    e.get().setBrand(motorbike.getBrand());
                }
                if(motorbike.getYear()!=null){
                    e.get().setYear(motorbike.getYear());
                }
                if(motorbike.getDescription()!=null){
                    e.get().setDescription(motorbike.getDescription());
                }
                if(motorbike.getCategory()!=null){
                    e.get().setCategory(motorbike.getCategory());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return motorbike;
            }
        }else{
            return motorbike;
        }
    }


    public boolean deleteMotorBike(int motorbikeId) {
        Boolean aBoolean = getMotorbikes(motorbikeId).map(motorbike -> {
            metodosCrud.delete(motorbike);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}

