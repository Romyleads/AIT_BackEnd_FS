package ait.car;

import ait.car.dto.CarDtoLombok;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class CarCreationAppl2 {


    private static ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) throws IOException {

        CarDtoLombok vw = new CarDtoLombok("VW",2020, Set.of("Golf", "Polo", "Passat"));
        mapper.writeValue(new File("vwlombook.json"),vw);



    }

}
