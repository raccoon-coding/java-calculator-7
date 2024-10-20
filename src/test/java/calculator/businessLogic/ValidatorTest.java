package calculator.businessLogic;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidatorTest {

    private Validator validator = Validator.of();

    @Test
    void 구분자_검증_확인() {
        String userInput = "//; \n1;2;3\n";

        assertThat(validator.validDelimiters(userInput)).isTrue();
    }

    @Test
    void 구분자_검증_구분자가_입력안되었을때() {
        List<String> splitEnterInput = List.of("", "", "", "1,2,3,4");

        assertThatThrownBy(() -> {
            validator.isNullDelimiters(splitEnterInput);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구분자 입력 방식이 잘못되었습니다. 다시 입력해주세요.");
    }

    @Test
    void 입력값_검증() {
        List<String> splitStringNumbers = List.of("1", "3", "3", "35");
        List<Integer> expect = List.of(1, 3, 3, 35);

        assertThat(validator.tryParseInteger(splitStringNumbers)).isEqualTo(expect);
    }

    @Test
    void 입력값_검증_문자_입력() {
        List<String> splitStringNumbers = List.of("1", "q", "3", "d");

        assertThatThrownBy(() -> {
            validator.tryParseInteger(splitStringNumbers);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자(양수인 정수)만 입력해주세요.");
    }
}