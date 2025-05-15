package com.thehecklers.sburrestdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    private List<Car> cars = new ArrayList<>();

    public CarController() {
        cars.addAll(List.of(
                new Car("Toyota Corolla"),
                new Car("Honda Civic"),
                new Car("Ford Mustang"),
                new Car("Chevrolet Camaro")
        ));
    }

    @GetMapping
    Iterable<Car> getCars() {
        return cars;
    }

    @GetMapping("/{id}")
    Optional<Car> getCarById(@PathVariable String id) {
        for (Car c : cars) {
            if (c.getId().equals(id)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    @PostMapping
    Car postCar(@RequestBody Car car) {
        if (car.getId() == null || car.getModel() == null) {
            throw new IllegalArgumentException("ID e model não podem ser nulos");
        }
        cars.add(car);
        return car;
    }

    @PutMapping("/{id}")
    ResponseEntity<Car> putCar(@PathVariable String id, @RequestBody Car car) {
        int carIndex = -1;
        
        for (Car c : cars) {
            if (c != null && c.getId() != null && c.getId().equals(id)) {
                carIndex = cars.indexOf(c);
                cars.set(carIndex, car);
            }
        }
        return (carIndex == -1) ?
                new ResponseEntity<>(postCar(car), HttpStatus.CREATED) :
                new ResponseEntity<>(car, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteCar(@PathVariable String id) {
        cars.removeIf(c -> c != null && id.equals(c.getId()));
    }

    @DeleteMapping("/limpar")
void limparCarrosInvalidosENulls() {
    System.out.println("Antes da limpeza: " + cars.size());

    cars.removeIf(c -> {
        System.out.println("Verificando: " + c);
        return c == null || c.getId() == null || c.getModel() == null;
    });

    System.out.println("Depois da limpeza: " + cars.size());
    }

    @DeleteMapping("/invalidos")
void deleteInvalidCars() {
    System.out.println("Antes da limpeza (de carros inválidos): " + cars.size());
    
    // Remove os carros com id ou modelo nulo
    cars.removeIf(c -> c == null || c.getId() == null || c.getModel() == null);

    System.out.println("Depois da limpeza (de carros inválidos): " + cars.size());
    }   
    @DeleteMapping("/remover-null")
    void removeNullCars() {
        System.out.println("Antes da limpeza (de objetos null): " + cars.size());

        // Remove qualquer objeto null da lista
        cars.removeIf(Objects::isNull);

        System.out.println("Depois da limpeza (de objetos null): " + cars.size());
    }


}

class Car {
    private final String id;
    private String model;

    public Car(String id, String model) {
        this.id = id;
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
}