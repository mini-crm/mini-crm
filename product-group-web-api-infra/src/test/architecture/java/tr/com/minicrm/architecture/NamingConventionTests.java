package tr.com.minicrm.architecture;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import org.springframework.web.bind.annotation.RestController;


@AnalyzeClasses(packages = "tr.com.minicrm")
public class NamingConventionTests {

    @ArchTest
    ArchRule controllers_should_be_suffixed = classes()
            .that()
            .areAnnotatedWith(RestController.class)
            .should().haveSimpleNameEndingWith("Controller");
    
}
