package com.netcracker.hackathon.smartwfm.linemanager.service;

import com.netcracker.hackathon.smartwfm.linemanager.dao.Demand;
import com.netcracker.hackathon.smartwfm.linemanager.exception.DemandNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandDaoService {
    @Autowired
    private DemandRepository demandRepository;

    public List<Demand> getAllDemands() {
        return demandRepository.findAll();
    }

    public void save(Demand demand) {
        demandRepository.save(demand);
    }

    public Demand getDemandById(String Id) {
        Demand demand = demandRepository.findByDemandId(Id);
        if (demand != null) {
            return demand;
        }
        throw new DemandNotFoundException("Demand with Id " + Id + " does not exist");
    }

    public void deleteDemandById(String Id) {
        Demand demand = demandRepository.findByDemandId(Id);
        if (demand != null) {
            demandRepository.delete(demand);
        }
        throw new DemandNotFoundException("Demand for the Id " + Id + " does not exist");
    }
}
