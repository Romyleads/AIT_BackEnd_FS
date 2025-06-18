package ait.car;

import ait.car.dto.CarDto;
import ait.car.dto.CarDtoLombok;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CarCreationAppl {


    private static ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) throws IOException {

        CarDtoLombok vw = new CarDtoLombok("VW",2020, Set.of("Golf", "Polo", "Passat"));
        mapper.writeValue(new File("vw.json"),vw);



    }

}
