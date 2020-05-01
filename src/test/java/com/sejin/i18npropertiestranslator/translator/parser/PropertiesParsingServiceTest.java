package com.sejin.i18npropertiestranslator.translator.parser;

import com.sejin.i18npropertiestranslator.common.exception.PropertiesDataFormatException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

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
        final String propertiesRawContent = propertiesRawContentBuilder
                .append("\n") // 첫 줄 공백
                .append(keys[0] + "=" + values[0] + "\n")
                .append(keys[1] + "=" + values[1] + "\n") // value에 '=' 포함
                .append("\n") // 중간 공백
                .append(keys[2] + " =  " + values[2] + "\n") // '=' 전후에 공백
                .toString();

        final Map<String, String> result = propertiesParsingService.parsePropertiesRawContent(propertiesRawContent);
        assertThat(result).hasSize(keys.length);
        assertThat(result).containsKeys(keys);
        assertThat(result).containsValues(values);
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
