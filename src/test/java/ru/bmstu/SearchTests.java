package ru.bmstu;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.conditions.Text;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

public class SearchTests extends BaseTest {

    @ValueSource(strings = {"Э7", "Э8", "Э9"})
    @ParameterizedTest(name = "Страница должна содержать информацию о кафедре {0}")
    void siteShouldContainDepartment(String department) {

        open("faculties-and-branches/");
        $("[class = '_3f4Mx _2HM9h _1taao']").find(new ByText("Э Энергомашиностроение")).click();
        $("*").shouldHave(new Text(department));
        sleep(4000); //Для избежания ошибки 429 при запуске всех тестов
    }

    @CsvSource(value = {
            "Э Энергомашиностроение,'Факультет с 1868 года готовит ученых, проектировщиков, конструкторов и испытателей энергетических устройств.'",
            "СМ Специальное машиностроение,Факультет основан в 1938 году. Это один из самых больших и динамично развивающихся факультетов Университета."
    })
    @ParameterizedTest(name = "Страница о факультете \"{0}\", должна содержать описание \"{1}\"")
    void siteShouldContainFacultyDescription(String department, String expected) {

        open("faculties-and-branches/");
        $("[class = '_3f4Mx _2HM9h _1taao']").find(new ByText(department)).click();
        $("*").shouldHave(new Text(expected));
        sleep(4000); //Для избежания ошибки 429 при запуске всех тестов
    }

    static Stream<Arguments> dataProviderForButtonsTest() {
        return Stream.of(
                Arguments.of("Рус", List.of("1\nИУ Информатика, искусственный интеллект и системы управления", "2\nИБМ Инженерный бизнес и менеджмент",
                        "3\nМТ Машиностроительные технологии", "4\nСМ Специальное машиностроение", "5\nБМТ Биомедицинская техника",
                        "6\nРЛ Радиоэлектроника и лазерная техника", "7\nЭ Энергомашиностроение", "8\nРК Робототехника и комплексная автоматизация",
                        "9\nФН Фундаментальные науки", "10\nЛ Лингвистика", "11\nЮР Кафедра «Безопасность в цифровом мире»",
                        "12\nСГН Социальные и гуманитарные науки", "13\nВУЦ Военный учебный центр", "14\nГУИМЦ Головной учебно-исследовательский и методический центр",
                        "15\nФМОП Факультет международных образовательных программ", "16\nФОФ Физкультурно-оздоровительный")),
                Arguments.of("Eng", List.of("1\nИУ Информатика, искусственный интеллект и системы управления", "2\nИБМ Инженерный бизнес и менеджмент",
                        "3\nМТ Машиностроительные технологии", "4\nСМ Специальное машиностроение", "5\nБМТ Биомедицинская техника",
                        "6\nРЛ Радиоэлектроника и лазерная техника", "7\nЭ Энергомашиностроение", "8\nРК Робототехника и комплексная автоматизация",
                        "9\nФН Фундаментальные науки", "10\nЛ Лингвистика", "11\nЮР Кафедра «Безопасность в цифровом мире»",
                        "12\nСГН Социальные и гуманитарные науки", "13\nВУЦ Военный учебный центр", "14\nГУИМЦ Головной учебно-исследовательский и методический центр",
                        "15\nФМОП Факультет международных образовательных программ", "16\nФОФ Физкультурно-оздоровительный"))
        );
    }

    @MethodSource("dataProviderForButtonsTest")
    @ParameterizedTest(name = "Проверим, что \"Лучший технический вуз страны\" не показал английские названия " +
            "кафедр на сайте. Язык: {0}")
    void siteShouldContainFacultyDescription1(String lang, List<String> expected) {

        open("faculties-and-branches/");
        $("._3PgI2").find(new ByText(lang)).click();
        $$("[class = '_2Zppq LinkItem_theme_default']")
                .shouldHave(CollectionCondition.texts(expected));
        sleep(4000); //Для избежания ошибки 429 при запуске всех тестов
    }

}
