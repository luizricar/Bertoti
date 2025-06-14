package com.thehecklers.sburrestdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SburRestDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SburRestDemoApplication.class, args);
    }
}

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cars")
class CarController {
    private final List<Car> cars = new ArrayList<>();

    public CarController() {
        cars.addAll(List.of(
                new Car("Toyota Corolla"),
                new Car("Honda Civic"),
                new Car("Ford Mustang"),
                new Car("Chevrolet Camaro")
        ));
    }

    @GetMapping
    public List<Car> getCars() {
        return cars;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable String id) {
        return cars.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Car> postCar(@RequestBody Car car) {
        if (car.getModel() == null || car.getModel().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Car newCar = new Car(car.getModel());
        cars.add(newCar);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> putCar(@PathVariable String id, @RequestBody Car car) {
        if (car.getModel() == null || car.getModel().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        for (int i = 0; i < cars.size(); i++) {
            Car existingCar = cars.get(i);
            if (existingCar != null && existingCar.getId().equals(id)) {
                existingCar.setModel(car.getModel());
                return ResponseEntity.ok(existingCar);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable String id) {
        boolean removed = cars.removeIf(c -> c != null && id.equals(c.getId()));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

class Car {
    private String id;
    private String model;

    public Car() {}

    public Car(String id, String model) {
        this.id = (id == null || id.isBlank()) ? UUID.randomUUID().toString() : id;
        this.model = model;
    }

    public Car(String model) {
        this(UUID.randomUUID().toString(), model);
    }

    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
