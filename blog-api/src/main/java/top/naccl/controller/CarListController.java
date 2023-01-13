package top.naccl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.naccl.entity.CarList;
import top.naccl.service.CarListService;

import java.util.List;

@RestController
public class CarListController {
    @Autowired
    private CarListService carListService;
    @GetMapping("/carlist/getall")
    public List<CarList> getAll(){
        return carListService.getAll();
    }
}
