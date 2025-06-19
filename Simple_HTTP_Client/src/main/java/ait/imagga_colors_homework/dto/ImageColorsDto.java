
package ait.imagga_colors_homework.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ImageColorsDto {
    private List<ImageColorDto> image_colors;
    private List<ImageColorDto> background_colors;
    private List<ImageColorDto> foreground_colors;
}
