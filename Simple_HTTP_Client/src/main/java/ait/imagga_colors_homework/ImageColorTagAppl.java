
package ait.imagga_colors_homework;

import ait.imagga_colors_homework.dto.ColorsResponseDto;
import ait.imagga_colors_homework.dto.ImageColorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

public class ImageColorTagAppl {
    public static void main(String[] args) {
        // image url
        String imgUrl = "https://www.chudesa.kiev.ua/wp-content/uploads/2020/01/87ceead492298d404b417a0ada815820-1.jpg";

        // HTTP client
        RestTemplate restTemplate = new RestTemplate();

        // auth headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic YWNjXzFjMmIzNzlhMzhiYTJmMzozMGNlMGExMjJkNzRjNDU4YmUwZTAxYjE4MDM0OGM3Zg==");

        // build url
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("https://api.imagga.com/v2/colors")
                .queryParam("image_url", imgUrl);

        // request URL
        URI url = builder.build().toUri();
        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, url);

        // send request
        ResponseEntity<ColorsResponseDto> response = restTemplate.exchange(request, ColorsResponseDto.class);

        // get color list
        List<ImageColorDto> colors = response.getBody()
                                              .getResult()
                                              .getColors()
                                              .getImage_colors();

        // print table
        System.out.printf("%-20s %-20s %-10s%n", "color name", "parent color", "coverage %");
        System.out.println("-----------------------------------------------------------");

        // print each color
        colors.forEach(color -> System.out.printf("%-20s %-20s %-10.2f%n",
                color.getClosest_palette_color(),
                color.getParent_color(),
                color.getPercent()));
    }
}
