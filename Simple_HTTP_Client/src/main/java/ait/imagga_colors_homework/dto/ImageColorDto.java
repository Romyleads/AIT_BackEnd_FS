
package ait.imagga_colors_homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ImageColorDto {
    private String closest_palette_color;

    @JsonProperty("closest_palette_color_parent")
    private String parent_color;

    private double percent;
}

