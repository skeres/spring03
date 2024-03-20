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
        String because="Service classes should only be accessed by controllers";
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
        String because="Service classes should only be accessed by controllers";
        classes()
                .that().resideInAPackage("..service..")
                .should().onlyBeAccessed().byAnyPackage("..service..", "..controller..")
                .because(because)
                .check(importedClasses);
    }


    /* naming convention */

    @Test
    void serviceClassesShouldBeNamedXServiceOrXComponentOrXServiceImpl() {
        String because="Classes name in service package name must end with Service or ServiceImpl or Component";
        classes()
                .that().resideInAPackage("..service..")
                .should().haveSimpleNameEndingWith("Service")
                .orShould().haveSimpleNameEndingWith("ServiceImpl")
                .orShould().haveSimpleNameEndingWith("Component")
                .because(because)
                .check(importedClasses);
    }

    @Test
    void repositoryClassesShouldBeNamedXRepository() {
        String because="Classes name in repository package name must end with Repository";
        classes()
                .that().resideInAPackage("..repository..")
                .should().haveSimpleNameEndingWith("Repository")
                .because(because)
                .check(importedClasses);
    }

    @Test
    void controllerClassesShouldBeNamedXController() {
        String because="Classes name in controller package name must end with Controller";
        classes()
                .that().resideInAPackage("..controller..")
                .should().haveSimpleNameEndingWith("Controller")
                .because(because)
                .check(importedClasses);
    }


    @Test
    void interfacesShouldNotHaveNamesEndingWithTheWordInterface() {
        String because="interfaces should not have names ending with the word Interface";
        noClasses()
                .that().areInterfaces()
                .should().haveSimpleNameEndingWith("Interface").check(importedClasses);
    }

    //enum classes
    @Test
    void enumClassesShouldResideInEnumPackage() {
        String because="Classes ENUM should reside in enums package";
        classes()
                .that().areEnums()
                .should().resideInAPackage("..enums..")
                .because(because)
                .check(importedClasses);
    }

    //Custom rules

    //TODO

}
