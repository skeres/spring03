package com.example.demo;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Cet import static est à ajouter pour les tests
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArchunitApplicationTests {

    private JavaClasses importedClasses;


    @BeforeEach
    public void setup() {
        importedClasses = new ClassFileImporter()
                       .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                       .importPackages("com.example.demo");

    }

    /*    Package Dependency Checks*/

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        noClasses()
                .that().resideInAnyPackage("com.example.demo.service..")
                .or().resideInAnyPackage("com.example.demo.repository..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage("com.example.demo.controller..")
                .because("Services and repositories should not depend on web layer")
                .check(importedClasses);
    }

    @Test
    void serviceClassesShouldOnlyBeAccessedByController() {
        classes()
                .that().resideInAPackage("..service..")
                .should().onlyBeAccessed().byAnyPackage("..service..", "..controller..")
                .because("Service classes should only be accesse by controllers")
                .check(importedClasses);
    }


    /* naming convention */

    @Test
    void serviceClassesShouldBeNamedXServiceOrXComponentOrXServiceImpl() {
        classes()
                .that().resideInAPackage("..service..")
                .should().haveSimpleNameEndingWith("Service")
                .orShould().haveSimpleNameEndingWith("ServiceImpl")
                .orShould().haveSimpleNameEndingWith("Component")
                .because("Service classes name must follow rules")
                .check(importedClasses);
    }

    @Test
    void repositoryClassesShouldBeNamedXRepository() {
        classes()
                .that().resideInAPackage("..repository..")
                .should().haveSimpleNameEndingWith("Repository")
                .check(importedClasses);
    }

    @Test
    void controllerClassesShouldBeNamedXController() {
        classes()
                .that().resideInAPackage("..controller..")
                .should().haveSimpleNameEndingWith("Controller")
                .check(importedClasses);
    }


    @Test
    void interfacesShouldNotHaveNamesEndingWithTheWordInterface() {
        noClasses().that().areInterfaces().should().haveNameMatching("I*").check(importedClasses);
    }

    //enum classes
    @Test
    void enumClassesShouldResideInEnumPackage() {
        classes().that().areEnums().should().resideInAPackage("..enum..");
    }

    //Custom rules

    //TODO

}
