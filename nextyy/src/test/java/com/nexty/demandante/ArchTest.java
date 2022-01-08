package com.nexty.demandante;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.nexty.demandante");

        noClasses()
            .that()
            .resideInAnyPackage("com.nexty.demandante.service..")
            .or()
            .resideInAnyPackage("com.nexty.demandante.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.nexty.demandante.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
