package tr.com.minicrm;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;


@AnalyzeClasses(packages = "tr.com.minicrm")
public class NamingConventionTests {

  @ArchTest
  ArchRule exceptions_should_be_in_exception_package =
      classes().that().areAssignableTo(RuntimeException.class).should().resideInAPackage("..exceptions..");

  @ArchTest
  ArchRule classes_in_service_package_should_end_with_data_service =
      classes().that().resideInAPackage("..service..").should().haveSimpleNameEndingWith("DataService");

}
