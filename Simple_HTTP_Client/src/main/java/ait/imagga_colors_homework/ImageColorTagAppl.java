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

        // create request
        URI url = builder.build().toUri();
        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, url);

        // send request
        ResponseEntity<ColorsResponseDto> response = restTemplate.exchange(request, ColorsResponseDto.class);

        // get all colors
        var colorsDto = response.getBody().getResult().getColors();

        // print table
        System.out.printf("%-20s %-20s %-20s %-10s%n", "color name", "parent color", "type of color", "coverage %");
        System.out.println("--------------------------------------------------------------------------------");

        // print each group
        printColorList(colorsDto.getImage_colors(), "image");
        printColorList(colorsDto.getBackground_colors(), "background");
        printColorList(colorsDto.getForeground_colors(), "foreground");
    }

    // print color list
    private static void printColorList(List<ImageColorDto> colors, String groupName) {
        if (colors == null) return;

        // print row
        colors.forEach(color -> System.out.printf("%-20s %-20s %-20s %-10.2f%n",
                color.getClosest_palette_color(),
                color.getParent_color() != null ? color.getParent_color() : "-",
                groupName,
                color.getPercent()));
    }
}
