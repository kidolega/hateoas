package pl.dolega.hateoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dolega.hateoas.model.Car;
import pl.dolega.hateoas.service.CarService;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/cars", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<Car>> getCars() {
        List<Car> allCars = carService.getAllCars();
        allCars.forEach(car -> car.add(linkTo(CarController.class).slash(car.getCarId()).withSelfRel()));
        Link link = linkTo(CarController.class).withSelfRel();
        CollectionModel<Car> carCollectionModel = CollectionModel.of(allCars, link);
        return new ResponseEntity<>(carCollectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Car>> getCarById(@PathVariable long id) {
        Link link = linkTo(CarController.class).slash(id).withSelfRel();
        Optional<Car> carById = carService.getCarById(id);
        EntityModel<Car> carEntityModel = EntityModel.of(carById.get(), link);
        return new ResponseEntity<>(carEntityModel, HttpStatus.OK);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<CollectionModel<Car>> getCarByColor(@PathVariable String color) {
        List<Car> carList = carService.getCarsByColor(color);
        carList.forEach(car -> car.add(linkTo(CarController.class).slash(car.getCarId()).withSelfRel()));
        carList.forEach(car -> car.add(linkTo(CarController.class).withRel("allColors")));
        Link link = linkTo(CarController.class).withSelfRel();
        CollectionModel<Car> carCollectionModel = CollectionModel.of(carList, link);
        return new ResponseEntity<>(carCollectionModel, HttpStatus.OK);
    }
}
