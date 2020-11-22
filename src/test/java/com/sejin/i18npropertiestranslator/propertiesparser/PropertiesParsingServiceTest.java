package com.sejin.i18npropertiestranslator.propertiesparser;

import com.sejin.i18npropertiestranslator.common.exception.PropertiesDataFormatException;
import com.sejin.i18npropertiestranslator.propertiesparser.dto.PropertyDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {PropertiesParsingService.class})
@ActiveProfiles("TEST")
class PropertiesParsingServiceTest {
    @Autowired
    private PropertiesParsingService propertiesParsingService;

    @Test
    void parsePropertiesRawContent() {
        final StringBuilder propertiesRawContentBuilder = new StringBuilder();
        final String[] keys = {"title", "content", "recommendation"};
        final String[] values = {"여름 남자 옷 추천", "여름엔 시원한 게 장땡이다. 그렇다고 해서 벗고 다니지는 말자. =_=;;", "린넨 셔츠, 반팔 오버티, 라프 시몬스 운동화 (베이지)"};
        final String[] newLines = {"", ""};
        final String[] fullComments = {" S/S 남자 의류 정보", "그냥 뜬금없는 중간 주석", "###끝"};
        final String[] subComments = {" 추천 상품 리스트"};
        final String propertiesRawContent = propertiesRawContentBuilder
                .append(newLines[0] + "\n") // 첫 줄 공백
                .append("#" + fullComments[0] + "\n")
                .append(keys[0] + "=" + values[0] + "\n")
                .append(keys[1] + "=" + values[1] + "\n") // value에 '=' 포함
                .append(newLines[1] + "\n") // 중간 공백
                .append("#" + fullComments[1] + "\n") // #과 내용 사이에 공백 없음
                .append(keys[2] + " =  " + values[2] + " #" + subComments[0] + "\n") // '=' 전후에 공백, 라인 끝에 주석
                .append("#" + fullComments[2] + "\n") // #이 여러개
                .toString();

        final List<PropertyDto> result = propertiesParsingService.parsePropertiesRawContent(propertiesRawContent);
        assertThat(result).hasSize(keys.length + newLines.length + fullComments.length);
        assertThat(result.stream().map(PropertyDto::getKey)).contains(keys);
        assertThat(result.stream().map(PropertyDto::getValue)).contains(values);
        assertThat(result.stream().map(PropertyDto::getComment))
                .contains(Stream.concat(Arrays.stream(fullComments), Arrays.stream(subComments)).toArray(String[]::new));
        assertThat(result.stream()
                .filter(property -> property.getKey() == null && property.getValue() == null && property.getComment() == null).count())
                .isEqualTo(newLines.length);
        log.debug("parsed result = {}", result);
    }

    @Test
    void parsePropertiesRawContent_withIllegalFormatParameter() {
        final StringBuilder propertiesRawContentBuilder = new StringBuilder();
        final String illegalPropertyRow = "나는 나는 저팔계 왜 날 싫어하나";
        final String propertiesRawContent = propertiesRawContentBuilder
                .append(illegalPropertyRow)
                .toString();

        final PropertiesDataFormatException exception = assertThrows(
                PropertiesDataFormatException.class,
                () -> propertiesParsingService.parsePropertiesRawContent(propertiesRawContent)
        );
        assertThat(exception.getParams()).isEqualTo(new String[]{illegalPropertyRow});
    }
}
